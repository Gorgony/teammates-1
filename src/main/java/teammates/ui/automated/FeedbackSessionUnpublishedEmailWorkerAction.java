package teammates.ui.automated;

import java.util.List;

import teammates.common.datatransfer.attributes.FeedbackSessionAttributes;
import teammates.common.exception.TeammatesException;
import teammates.common.util.Assumption;
import teammates.common.util.EmailWrapper;
import teammates.common.util.Logger;
import teammates.common.util.ParamNameConst;
import teammates.logic.api.EmailGenerator;

/**
 * Task queue worker action: prepares session unpublished reminder for a particular session to be sent.
 */
public class FeedbackSessionUnpublishedEmailWorkerAction extends AutomatedAction {

    private static final Logger log = Logger.getLogger();

    @Override
    protected String getActionDescription() {
        return null;
    }

    @Override
    protected String getActionMessage() {
        return null;
    }

    @Override
    public void execute() {
        String feedbackSessionName = getRequestParamValue(ParamNameConst.ParamsNames.EMAIL_FEEDBACK);
        Assumption.assertPostParamNotNull(ParamNameConst.ParamsNames.EMAIL_FEEDBACK, feedbackSessionName);

        String courseId = getRequestParamValue(ParamNameConst.ParamsNames.EMAIL_COURSE);
        Assumption.assertPostParamNotNull(ParamNameConst.ParamsNames.EMAIL_COURSE, courseId);

        FeedbackSessionAttributes session = logic.getFeedbackSession(feedbackSessionName, courseId);
        if (session == null) {
            log.severe("Feedback session object for feedback session name: " + feedbackSessionName
                       + " for course: " + courseId + " could not be fetched.");
            return;
        }
        List<EmailWrapper> emailsToBeSent =
                new EmailGenerator().generateFeedbackSessionUnpublishedEmails(session);
        try {
            taskQueuer.scheduleEmailsForSending(emailsToBeSent);
            session.setSentPublishedEmail(false);
            logic.updateFeedbackSession(session);
        } catch (Exception e) {
            log.severe("Unexpected error: " + TeammatesException.toStringWithStackTrace(e));
        }
    }

}
