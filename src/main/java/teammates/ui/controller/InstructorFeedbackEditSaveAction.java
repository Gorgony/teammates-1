package teammates.ui.controller;

import teammates.common.datatransfer.attributes.FeedbackSessionAttributes;
import teammates.common.exception.EntityDoesNotExistException;
import teammates.common.exception.InvalidParametersException;
import teammates.common.util.*;
import teammates.ui.pagedata.InstructorFeedbackEditPageData;

public class InstructorFeedbackEditSaveAction extends InstructorFeedbackAbstractAction {

    @Override
    protected ActionResult execute() throws EntityDoesNotExistException {

        String courseId = getRequestParamValue(ParamNameConst.ParamsNames.COURSE_ID);
        String feedbackSessionName = getRequestParamValue(ParamNameConst.ParamsNames.FEEDBACK_SESSION_NAME);

        Assumption.assertPostParamNotNull(ParamNameConst.ParamsNames.COURSE_ID, courseId);
        Assumption.assertPostParamNotNull(ParamNameConst.ParamsNames.FEEDBACK_SESSION_NAME, feedbackSessionName);

        gateKeeper.verifyAccessible(
                logic.getInstructorForGoogleId(courseId, account.googleId),
                logic.getFeedbackSession(feedbackSessionName, courseId),
                false,
                ParamNameConst.ParamsNames.INSTRUCTOR_PERMISSION_MODIFY_SESSION);

        InstructorFeedbackEditPageData data = new InstructorFeedbackEditPageData(account, sessionToken);
        FeedbackSessionAttributes feedbackSession = extractFeedbackSessionData(false);

        // A session opening reminder email is always sent as students
        // without accounts need to receive the email to be able to respond
        feedbackSession.setOpeningEmailEnabled(true);

        try {
            logic.updateFeedbackSession(feedbackSession);
            statusToUser.add(new StatusMessage(StatusMessageConst.StatusMessages.FEEDBACK_SESSION_EDITED, StatusMessageColor.SUCCESS));
            statusToAdmin =
                    "Updated Feedback Session "
                    + "<span class=\"bold\">(" + feedbackSession.getFeedbackSessionName() + ")</span> for Course "
                    + "<span class=\"bold\">[" + feedbackSession.getCourseId() + "]</span> created.<br>"
                    + "<span class=\"bold\">From:</span> " + feedbackSession.getStartTime()
                    + "<span class=\"bold\"> to</span> " + feedbackSession.getEndTime()
                    + "<br><span class=\"bold\">Session visible from:</span> " + feedbackSession.getSessionVisibleFromTime()
                    + "<br><span class=\"bold\">Results visible from:</span> " + feedbackSession.getResultsVisibleFromTime()
                    + "<br><br><span class=\"bold\">Instructions:</span> " + feedbackSession.getInstructions();
            data.setStatusForAjax(StatusMessageConst.StatusMessages.FEEDBACK_SESSION_EDITED);
            data.setHasError(false);
        } catch (InvalidParametersException e) {
            setStatusForException(e);
            data.setStatusForAjax(e.getMessage());
            data.setHasError(true);
        }
        return createAjaxResult(data);
    }
}
