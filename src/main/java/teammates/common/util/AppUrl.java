package teammates.common.util;

/**
 * A specific implementation of {@link Url} used to encapsulate URLs of the application.
 */
public class AppUrl extends Url {

    public AppUrl(String url) {
        super(url);
        Assumption.assertTrue(url.startsWith("http")); // must either be http or https
    }

    public AppUrl withUserId(String userId) {
        return withParam(ParamNameConst.ParamsNames.USER_ID, userId);
    }

    public AppUrl withRegistrationKey(String key) {
        return withParam(ParamNameConst.ParamsNames.REGKEY, key);
    }

    public AppUrl withInstructorInstitution(String institute) {
        return withParam(ParamNameConst.ParamsNames.INSTRUCTOR_INSTITUTION, institute);
    }

    public AppUrl withCourseId(String courseId) {
        return withParam(ParamNameConst.ParamsNames.COURSE_ID, courseId);
    }

    public AppUrl withSessionName(String feedbackSessionName) {
        return withParam(ParamNameConst.ParamsNames.FEEDBACK_SESSION_NAME, feedbackSessionName);
    }

    public AppUrl withStudentEmail(String email) {
        return withParam(ParamNameConst.ParamsNames.STUDENT_EMAIL, email);
    }

    public AppUrl withInstructorId(String instructorId) {
        return withParam(ParamNameConst.ParamsNames.INSTRUCTOR_ID, instructorId);
    }

    public AppUrl withQuestionNumber(String questionNumber) {
        return withParam(ParamNameConst.ParamsNames.FEEDBACK_QUESTION_NUMBER, questionNumber);
    }

    public AppUrl withEnableSessionEditDetails(boolean shouldLoadInEditMode) {
        return withParam(ParamNameConst.ParamsNames.FEEDBACK_SESSION_ENABLE_EDIT, Boolean.toString(shouldLoadInEditMode));
    }

}
