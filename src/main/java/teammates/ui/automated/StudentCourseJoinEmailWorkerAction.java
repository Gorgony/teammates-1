package teammates.ui.automated;

import teammates.common.datatransfer.attributes.CourseAttributes;
import teammates.common.datatransfer.attributes.StudentAttributes;
import teammates.common.util.Assumption;
import teammates.common.util.EmailWrapper;
import teammates.common.util.ParamNameConst;
import teammates.logic.api.EmailGenerator;

/**
 * Task queue worker action: sends registration email for a student of a course.
 */
public class StudentCourseJoinEmailWorkerAction extends AutomatedAction {

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
        String courseId = getRequestParamValue(ParamNameConst.ParamsNames.COURSE_ID);
        Assumption.assertPostParamNotNull(ParamNameConst.ParamsNames.COURSE_ID, courseId);
        String studentEmail = getRequestParamValue(ParamNameConst.ParamsNames.STUDENT_EMAIL);
        Assumption.assertPostParamNotNull(ParamNameConst.ParamsNames.STUDENT_EMAIL, studentEmail);
        String isRejoinString = getRequestParamValue(ParamNameConst.ParamsNames.IS_STUDENT_REJOINING);
        Assumption.assertPostParamNotNull(ParamNameConst.ParamsNames.IS_STUDENT_REJOINING, isRejoinString);
        boolean isRejoin = Boolean.parseBoolean(isRejoinString);

        CourseAttributes course = logic.getCourse(courseId);
        Assumption.assertNotNull(course);
        StudentAttributes student = logic.getStudentForEmail(courseId, studentEmail);
        Assumption.assertNotNull(student);

        EmailWrapper email = isRejoin
                ? new EmailGenerator().generateStudentCourseRejoinEmailAfterGoogleIdReset(course, student)
                : new EmailGenerator().generateStudentCourseJoinEmail(course, student);
        try {
            emailSender.sendEmail(email);
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error while sending email", e);
        }
    }

}
