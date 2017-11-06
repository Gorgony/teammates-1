package teammates.ui.controller;

import teammates.common.util.*;

public class InstructorFeedbackDeleteAction extends Action {

    @Override
    protected ActionResult execute() {

        String courseId = getRequestParamValue(ParamNameConst.ParamsNames.COURSE_ID);
        String feedbackSessionName = getRequestParamValue(ParamNameConst.ParamsNames.FEEDBACK_SESSION_NAME);
        String nextUrl = getRequestParamValue(ParamNameConst.ParamsNames.NEXT_URL);

        Assumption.assertPostParamNotNull(ParamNameConst.ParamsNames.COURSE_ID, courseId);
        Assumption.assertPostParamNotNull(ParamNameConst.ParamsNames.FEEDBACK_SESSION_NAME, feedbackSessionName);

        if (nextUrl == null) {
            nextUrl = Const.ActionURIs.INSTRUCTOR_FEEDBACK_SESSIONS_PAGE;
        }

        gateKeeper.verifyAccessible(
                logic.getInstructorForGoogleId(courseId, account.googleId),
                logic.getFeedbackSession(feedbackSessionName, courseId),
                false,
                ParamNameConst.ParamsNames.INSTRUCTOR_PERMISSION_MODIFY_SESSION);

        logic.deleteFeedbackSession(feedbackSessionName, courseId);
        statusToUser.add(new StatusMessage(StatusMessageConst.StatusMessages.FEEDBACK_SESSION_DELETED, StatusMessageColor.SUCCESS));
        statusToAdmin = "Feedback Session <span class=\"bold\">[" + feedbackSessionName + "]</span> "
                        + "from Course: <span class=\"bold\">[" + courseId + " deleted.";

        return createRedirectResult(nextUrl);
    }

}
