package teammates.ui.controller;

import teammates.common.datatransfer.attributes.InstructorAttributes;
import teammates.common.datatransfer.attributes.StudentAttributes;
import teammates.common.util.Const;
import teammates.common.util.StatusMessage;
import teammates.common.util.StatusMessageColor;

public class AdminAccountDeleteAction extends Action {

    @Override
    protected ActionResult execute() {

        gateKeeper.verifyAdminPrivileges(account);

        String instructorId = getRequestParamValue(Const.ParamsNames.INSTRUCTOR_ID);
        String courseId = getRequestParamValue(Const.ParamsNames.COURSE_ID);
        String account = getRequestParamValue("account");

        if (courseId == null && account == null) {
            return deleteInstructorStatus(instructorId);
        }

        if (courseId == null && account != null) {
            return deleteInstructorAccount(instructorId);
        }

        String studentId = getRequestParamValue(Const.ParamsNames.STUDENT_ID);
        if (courseId != null && studentId != null) {
            return removeStudentFromCourse(instructorId, courseId, studentId);
        }

        return removeInstructorFromCourse(instructorId, courseId);
    }

    private ActionResult removeInstructorFromCourse(String instructorId, String courseId) {
        InstructorAttributes instructor = logic.getInstructorForGoogleId(courseId, instructorId);
        logic.deleteInstructor(courseId, instructor.email);
        statusToUser.add(new StatusMessage(Const.StatusMessages.INSTRUCTOR_REMOVED_FROM_COURSE, StatusMessageColor.SUCCESS));
        statusToAdmin = "Instructor <span class=\"bold\">" + instructorId
                + "</span> has been deleted from Course<span class=\"bold\">[" + courseId + "]</span>";
        return createRedirectResult(Const.ActionURIs.ADMIN_ACCOUNT_DETAILS_PAGE + "?instructorid=" + instructorId);
    }

    private ActionResult removeStudentFromCourse(String instructorId, String courseId, String studentId) {
        StudentAttributes student = logic.getStudentForGoogleId(courseId, studentId);
        logic.deleteStudent(courseId, student.email);
        statusToUser.add(new StatusMessage(Const.StatusMessages.STUDENT_DELETED,
                StatusMessageColor.SUCCESS));
        statusToAdmin = "Instructor <span class=\"bold\">" + instructorId
                + "</span>'s student status in Course"
                + "<span class=\"bold\">[" + courseId + "]</span> has been deleted";
        return createRedirectResult(Const.ActionURIs.ADMIN_ACCOUNT_DETAILS_PAGE + "?instructorid=" + studentId);
    }

    private ActionResult deleteInstructorAccount(String instructorId) {
        logic.deleteAccount(instructorId);
        statusToUser.add(new StatusMessage(Const.StatusMessages.INSTRUCTOR_ACCOUNT_DELETED, StatusMessageColor.SUCCESS));
        statusToAdmin = "Instructor Account for <span class=\"bold\">" + instructorId + "</span> has been deleted.";
        return createRedirectResult(Const.ActionURIs.ADMIN_ACCOUNT_MANAGEMENT_PAGE);
    }

    private ActionResult deleteInstructorStatus(String instructorId) {
        logic.downgradeInstructorToStudentCascade(instructorId);
        statusToUser.add(new StatusMessage(Const.StatusMessages.INSTRUCTOR_STATUS_DELETED, StatusMessageColor.SUCCESS));
        statusToAdmin = "Instructor Status for <span class=\"bold\">" + instructorId + "</span> has been deleted.";
        return createRedirectResult(Const.ActionURIs.ADMIN_ACCOUNT_MANAGEMENT_PAGE);
    }

}
