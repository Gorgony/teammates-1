package teammates.ui.controller;

import teammates.common.datatransfer.attributes.FeedbackSessionAttributes;
import teammates.common.datatransfer.attributes.InstructorAttributes;
import teammates.common.exception.EntityDoesNotExistException;
import teammates.common.util.Assumption;
import teammates.common.util.ParamNameConst;
import teammates.ui.pagedata.FeedbackSessionStatsPageData;

public class FeedbackSessionStatsPageAction extends Action {

    @Override
    protected ActionResult execute() throws EntityDoesNotExistException {
        String courseId = getRequestParamValue(ParamNameConst.ParamsNames.COURSE_ID);
        Assumption.assertPostParamNotNull(ParamNameConst.ParamsNames.COURSE_ID, courseId);

        String feedbackSessionName = getRequestParamValue(ParamNameConst.ParamsNames.FEEDBACK_SESSION_NAME);
        Assumption.assertPostParamNotNull(ParamNameConst.ParamsNames.FEEDBACK_SESSION_NAME, feedbackSessionName);

        FeedbackSessionStatsPageData data = new FeedbackSessionStatsPageData(account, sessionToken);

        FeedbackSessionAttributes fsa = logic.getFeedbackSession(feedbackSessionName, courseId);

        InstructorAttributes instructor = logic.getInstructorForGoogleId(courseId, account.googleId);

        gateKeeper.verifyAccessible(instructor, fsa, false);

        data.sessionDetails = logic.getFeedbackSessionDetails(feedbackSessionName, courseId);

        return createAjaxResult(data);
    }
}
