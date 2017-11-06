package teammates.ui.controller;

import teammates.common.datatransfer.FeedbackSessionQuestionsBundle;
import teammates.common.datatransfer.attributes.FeedbackSessionAttributes;
import teammates.common.exception.EntityDoesNotExistException;
import teammates.common.util.*;
import teammates.ui.pagedata.FeedbackSubmissionEditPageData;

public abstract class FeedbackSubmissionEditPageAction extends Action {
    protected String courseId;
    protected String feedbackSessionName;
    protected FeedbackSubmissionEditPageData data;

    @Override
    protected ActionResult execute() throws EntityDoesNotExistException {
        courseId = getRequestParamValue(ParamNameConst.ParamsNames.COURSE_ID);
        feedbackSessionName = getRequestParamValue(ParamNameConst.ParamsNames.FEEDBACK_SESSION_NAME);
        Assumption.assertPostParamNotNull(ParamNameConst.ParamsNames.COURSE_ID, courseId);
        Assumption.assertPostParamNotNull(ParamNameConst.ParamsNames.FEEDBACK_SESSION_NAME, feedbackSessionName);

        if (!isSpecificUserJoinedCourse()) {
            return createPleaseJoinCourseResponse(courseId);
        }

        FeedbackSessionAttributes feedbackSession = logic.getFeedbackSession(feedbackSessionName, courseId);

        if (feedbackSession == null) {
            statusToUser.add(new StatusMessage(StatusMessageConst.StatusMessages.FEEDBACK_SESSION_DELETED_NO_ACCESS,
                                               StatusMessageColor.WARNING));

            return createSpecificRedirectResult();
        }

        verifyAccessibleForSpecificUser(feedbackSession);

        String regKey = getRequestParamValue(ParamNameConst.ParamsNames.REGKEY);
        String email = getRequestParamValue(ParamNameConst.ParamsNames.STUDENT_EMAIL);

        String userEmailForCourse = getUserEmailForCourse();
        data = new FeedbackSubmissionEditPageData(account, student, sessionToken);
        data.bundle = getDataBundle(userEmailForCourse);

        data.setSessionOpenForSubmission(isSessionOpenForSpecificUser(data.bundle.feedbackSession));

        setStatusToAdmin();

        if (!data.isSessionOpenForSubmission()) {
            statusToUser.add(new StatusMessage(StatusMessageConst.StatusMessages.FEEDBACK_SUBMISSIONS_NOT_OPEN,
                                               StatusMessageColor.WARNING));
        }

        data.init(regKey, email, courseId);

        return createSpecificShowPageResult();
    }

    protected abstract boolean isSpecificUserJoinedCourse();

    protected abstract void verifyAccessibleForSpecificUser(FeedbackSessionAttributes fsa);

    protected abstract String getUserEmailForCourse();

    protected abstract FeedbackSessionQuestionsBundle getDataBundle(String userEmailForCourse)
            throws EntityDoesNotExistException;

    protected abstract boolean isSessionOpenForSpecificUser(FeedbackSessionAttributes session);

    protected abstract void setStatusToAdmin();

    protected abstract ShowPageResult createSpecificShowPageResult();

    protected abstract RedirectResult createSpecificRedirectResult() throws EntityDoesNotExistException;
}
