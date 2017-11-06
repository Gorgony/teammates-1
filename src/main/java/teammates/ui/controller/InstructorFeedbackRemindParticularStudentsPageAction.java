package teammates.ui.controller;

import teammates.common.datatransfer.FeedbackSessionResponseStatus;
import teammates.common.datatransfer.attributes.FeedbackSessionAttributes;
import teammates.common.datatransfer.attributes.InstructorAttributes;
import teammates.common.exception.EntityDoesNotExistException;
import teammates.common.util.Assumption;
import teammates.common.util.Const;
import teammates.common.util.ParamNameConst;
import teammates.ui.pagedata.InstructorFeedbackRemindParticularStudentsPageData;

public class InstructorFeedbackRemindParticularStudentsPageAction extends Action {

    @Override
    protected ActionResult execute() throws EntityDoesNotExistException {
        String courseId = getRequestParamValue(ParamNameConst.ParamsNames.COURSE_ID);
        Assumption.assertPostParamNotNull(ParamNameConst.ParamsNames.COURSE_ID, courseId);

        String feedbackSessionName = getRequestParamValue(ParamNameConst.ParamsNames.FEEDBACK_SESSION_NAME);
        Assumption.assertPostParamNotNull(ParamNameConst.ParamsNames.FEEDBACK_SESSION_NAME, feedbackSessionName);

        FeedbackSessionAttributes fsa = logic.getFeedbackSession(feedbackSessionName, courseId);
        InstructorAttributes instructor = logic.getInstructorForGoogleId(courseId, account.googleId);
        gateKeeper.verifyAccessible(instructor, fsa, false);

        FeedbackSessionResponseStatus fsResponseStatus =
                logic.getFeedbackSessionResponseStatus(feedbackSessionName, courseId);

        InstructorFeedbackRemindParticularStudentsPageData data =
                new InstructorFeedbackRemindParticularStudentsPageData(account, sessionToken, fsResponseStatus,
                                                                       courseId, feedbackSessionName);

        return createShowPageResult(Const.ViewURIs.INSTRUCTOR_FEEDBACK_AJAX_REMIND_PARTICULAR_STUDENTS_MODAL, data);
    }

}
