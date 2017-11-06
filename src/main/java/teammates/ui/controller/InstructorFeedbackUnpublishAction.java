package teammates.ui.controller;

import teammates.common.datatransfer.attributes.FeedbackSessionAttributes;
import teammates.common.datatransfer.attributes.InstructorAttributes;
import teammates.common.exception.EntityDoesNotExistException;
import teammates.common.exception.InvalidParametersException;
import teammates.common.util.*;

public class InstructorFeedbackUnpublishAction extends Action {
    @Override
    protected ActionResult execute() throws EntityDoesNotExistException {
        String courseId = getRequestParamValue(ParamNameConst.ParamsNames.COURSE_ID);
        String feedbackSessionName = getRequestParamValue(ParamNameConst.ParamsNames.FEEDBACK_SESSION_NAME);
        String nextUrl = getRequestParamValue(ParamNameConst.ParamsNames.NEXT_URL);

        Assumption.assertPostParamNotNull(ParamNameConst.ParamsNames.COURSE_ID, courseId);
        Assumption.assertPostParamNotNull(ParamNameConst.ParamsNames.FEEDBACK_SESSION_NAME, feedbackSessionName);

        InstructorAttributes instructor = logic.getInstructorForGoogleId(courseId, account.googleId);
        FeedbackSessionAttributes session = logic.getFeedbackSession(feedbackSessionName, courseId);
        boolean isCreatorOnly = false;

        gateKeeper.verifyAccessible(
                instructor, session, isCreatorOnly, ParamNameConst.ParamsNames.INSTRUCTOR_PERMISSION_MODIFY_SESSION);

        try {
            logic.unpublishFeedbackSession(session);
            if (session.isPublishedEmailEnabled()) {
                taskQueuer.scheduleFeedbackSessionUnpublishedEmail(session.getCourseId(), session.getFeedbackSessionName());
            }

            statusToUser.add(new StatusMessage(StatusMessageConst.StatusMessages.FEEDBACK_SESSION_UNPUBLISHED,
                                               StatusMessageColor.SUCCESS));
            statusToAdmin = "Feedback Session <span class=\"bold\">(" + feedbackSessionName + ")</span> "
                            + "for Course <span class=\"bold\">[" + courseId + "]</span> unpublished.";
        } catch (InvalidParametersException e) {
            setStatusForException(e);
        }

        if (nextUrl == null) {
            nextUrl = Const.ActionURIs.INSTRUCTOR_FEEDBACK_SESSIONS_PAGE;
        }

        return createRedirectResult(nextUrl);
    }
}
