package teammates.ui.controller;

import teammates.common.datatransfer.attributes.InstructorAttributes;
import teammates.common.datatransfer.attributes.StudentAttributes;
import teammates.common.datatransfer.attributes.StudentProfileAttributes;
import teammates.common.exception.EntityDoesNotExistException;
import teammates.common.util.*;
import teammates.ui.pagedata.InstructorCourseStudentDetailsPageData;

public class InstructorCourseStudentDetailsPageAction extends Action {

    @Override
    public ActionResult execute() throws EntityDoesNotExistException {

        String courseId = getRequestParamValue(ParamNameConst.ParamsNames.COURSE_ID);
        Assumption.assertPostParamNotNull(ParamNameConst.ParamsNames.COURSE_ID, courseId);

        String studentEmail = getRequestParamValue(ParamNameConst.ParamsNames.STUDENT_EMAIL);
        Assumption.assertPostParamNotNull(ParamNameConst.ParamsNames.STUDENT_EMAIL, studentEmail);

        StudentAttributes student = logic.getStudentForEmail(courseId, studentEmail);
        if (student == null) {
            statusToUser.add(new StatusMessage(StatusMessageConst.StatusMessages.STUDENT_NOT_FOUND_FOR_COURSE_DETAILS,
                                               StatusMessageColor.DANGER));
            isError = true;
            return createRedirectResult(Const.ActionURIs.INSTRUCTOR_HOME_PAGE);
        }
        InstructorAttributes instructor = logic.getInstructorForGoogleId(courseId, account.googleId);
        gateKeeper.verifyAccessible(instructor, logic.getCourse(courseId), student.section,
                                    ParamNameConst.ParamsNames.INSTRUCTOR_PERMISSION_VIEW_STUDENT_IN_SECTIONS);

        boolean hasSection = logic.hasIndicatedSections(courseId);

        StudentProfileAttributes studentProfile = loadStudentProfile(student, instructor);

        InstructorCourseStudentDetailsPageData data =
                new InstructorCourseStudentDetailsPageData(account, sessionToken, student, studentProfile,
                                                           hasSection);

        statusToAdmin = "instructorCourseStudentDetails Page Load<br>"
                        + "Viewing details for Student <span class=\"bold\">" + studentEmail
                        + "</span> in Course <span class=\"bold\">[" + courseId + "]</span>";

        return createShowPageResult(Const.ViewURIs.INSTRUCTOR_COURSE_STUDENT_DETAILS, data);

    }

    private StudentProfileAttributes loadStudentProfile(StudentAttributes student, InstructorAttributes currentInstructor) {
        StudentProfileAttributes studentProfile = null;
        boolean isInstructorAllowedToViewStudent = currentInstructor.isAllowedForPrivilege(student.section,
                                                        ParamNameConst.ParamsNames.INSTRUCTOR_PERMISSION_VIEW_STUDENT_IN_SECTIONS);
        boolean isStudentWithProfile = !student.googleId.isEmpty();
        if (isInstructorAllowedToViewStudent && isStudentWithProfile) {
            studentProfile = logic.getStudentProfile(student.googleId);
            Assumption.assertNotNull(studentProfile);

            return studentProfile;
        }

        // this means that the user is returning to the page and is not the first time
        boolean hasExistingStatus = !statusToUser.isEmpty()
                                        || session.getAttribute(ParamNameConst.ParamsNames.STATUS_MESSAGES_LIST) != null;
        if (!isStudentWithProfile && !hasExistingStatus) {
            statusToUser.add(new StatusMessage(StatusMessageConst.StatusMessages.STUDENT_NOT_JOINED_YET_FOR_RECORDS,
                                               StatusMessageColor.WARNING));
        }
        if (!isInstructorAllowedToViewStudent && !hasExistingStatus) {
            statusToUser.add(new StatusMessage(StatusMessageConst.StatusMessages.STUDENT_PROFILE_UNACCESSIBLE_TO_INSTRUCTOR,
                                               StatusMessageColor.WARNING));
        }
        return null;
    }
}
