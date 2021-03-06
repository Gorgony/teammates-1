package teammates.ui.controller;

import teammates.common.datatransfer.attributes.FeedbackSessionAttributes;
import teammates.common.datatransfer.attributes.InstructorAttributes;
import teammates.common.exception.EntityAlreadyExistsException;
import teammates.common.exception.EntityDoesNotExistException;
import teammates.common.exception.InvalidParametersException;
import teammates.common.util.*;
import teammates.ui.pagedata.PageData;

public class InstructorFeedbackCopyAction extends Action {

    @Override
    protected ActionResult execute() throws EntityDoesNotExistException {

        String copiedFeedbackSessionName = getRequestParamValue(ParamNameConst.ParamsNames.COPIED_FEEDBACK_SESSION_NAME);
        String copiedCourseId = getRequestParamValue(ParamNameConst.ParamsNames.COPIED_COURSE_ID);
        String feedbackSessionName = getRequestParamValue(ParamNameConst.ParamsNames.FEEDBACK_SESSION_NAME);
        String courseId = getRequestParamValue(ParamNameConst.ParamsNames.COURSE_ID);

        Assumption.assertPostParamNotNull(ParamNameConst.ParamsNames.COPIED_FEEDBACK_SESSION_NAME, copiedFeedbackSessionName);
        Assumption.assertPostParamNotNull(ParamNameConst.ParamsNames.COPIED_COURSE_ID, copiedCourseId);
        Assumption.assertPostParamNotNull(ParamNameConst.ParamsNames.COURSE_ID, courseId);
        Assumption.assertPostParamNotNull(ParamNameConst.ParamsNames.FEEDBACK_SESSION_NAME, feedbackSessionName);

        InstructorAttributes instructor = logic.getInstructorForGoogleId(courseId, account.googleId);

        gateKeeper.verifyAccessible(
                instructor, logic.getCourse(courseId), ParamNameConst.ParamsNames.INSTRUCTOR_PERMISSION_MODIFY_SESSION);

        try {

            FeedbackSessionAttributes fs = logic.copyFeedbackSession(copiedFeedbackSessionName.trim(),
                                                                     copiedCourseId,
                                                                     feedbackSessionName,
                                                                     courseId,
                                                                     instructor.email);

            statusToUser.add(new StatusMessage(StatusMessageConst.StatusMessages.FEEDBACK_SESSION_COPIED, StatusMessageColor.SUCCESS));
            statusToAdmin =
                    "New Feedback Session <span class=\"bold\">(" + fs.getFeedbackSessionName() + ")</span> "
                    + "for Course <span class=\"bold\">[" + fs.getCourseId() + "]</span> created.<br>"
                    + "<span class=\"bold\">From:</span> " + fs.getStartTime()
                    + "<span class=\"bold\"> to</span> " + fs.getEndTime() + "<br>"
                    + "<span class=\"bold\">Session visible from:</span> " + fs.getSessionVisibleFromTime() + "<br>"
                    + "<span class=\"bold\">Results visible from:</span> " + fs.getResultsVisibleFromTime() + "<br><br>"
                    + "<span class=\"bold\">Instructions:</span> " + fs.getInstructions();

            //TODO: add a condition to include the status due to inconsistency problem of database
            //      (similar to the one below)
            return createRedirectResult(
                    new PageData(account, sessionToken).getInstructorFeedbackEditLink(
                            fs.getCourseId(), fs.getFeedbackSessionName()));

        } catch (EntityAlreadyExistsException e) {
            setStatusForException(e, StatusMessageConst.StatusMessages.FEEDBACK_SESSION_EXISTS);
        } catch (InvalidParametersException e) {
            setStatusForException(e);
        }

        RedirectResult redirectResult = createRedirectResult(Const.ActionURIs.INSTRUCTOR_FEEDBACK_SESSIONS_PAGE);
        redirectResult.responseParams.put(ParamNameConst.ParamsNames.USER_ID, account.googleId);
        return redirectResult;
    }

}
