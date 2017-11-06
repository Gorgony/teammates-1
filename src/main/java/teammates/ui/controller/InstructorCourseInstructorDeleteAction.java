package teammates.ui.controller;

import java.util.List;

import teammates.common.datatransfer.attributes.InstructorAttributes;
import teammates.common.util.*;

/**
 * Action: deleting an instructor for a course by another instructor.
 */
public class InstructorCourseInstructorDeleteAction extends Action {

    @Override
    protected ActionResult execute() {

        String courseId = getRequestParamValue(ParamNameConst.ParamsNames.COURSE_ID);
        Assumption.assertPostParamNotNull(ParamNameConst.ParamsNames.COURSE_ID, courseId);
        String instructorEmail = getRequestParamValue(ParamNameConst.ParamsNames.INSTRUCTOR_EMAIL);
        Assumption.assertPostParamNotNull(ParamNameConst.ParamsNames.INSTRUCTOR_EMAIL, instructorEmail);

        InstructorAttributes instructor = logic.getInstructorForGoogleId(courseId, account.googleId);
        gateKeeper.verifyAccessible(
                instructor, logic.getCourse(courseId), ParamNameConst.ParamsNames.INSTRUCTOR_PERMISSION_MODIFY_INSTRUCTOR);

        /* Process deleting an instructor and setup status to be shown to user and admin */
        if (hasAlternativeInstructor(courseId, instructorEmail)) {
            logic.deleteInstructor(courseId, instructorEmail);

            statusToUser.add(new StatusMessage(StatusMessageConst.StatusMessages.COURSE_INSTRUCTOR_DELETED, StatusMessageColor.SUCCESS));
            statusToAdmin = "Instructor <span class=\"bold\"> " + instructorEmail + "</span>"
                + " in Course <span class=\"bold\">[" + courseId + "]</span> deleted.<br>";
        } else {
            isError = true;
            statusToUser.add(new StatusMessage(StatusMessageConst.StatusMessages.COURSE_INSTRUCTOR_DELETE_NOT_ALLOWED,
                                               StatusMessageColor.DANGER));
            statusToAdmin = "Instructor <span class=\"bold\"> " + instructorEmail + "</span>"
                    + " in Course <span class=\"bold\">[" + courseId + "]</span> could not be deleted "
                    + "as there is only one instructor left to be able to modify instructors.<br>";
        }

        /* Create redirection. It will redirect back to 'Courses' page if the instructor deletes himself */
        RedirectResult result = null;
        if (logic.isInstructorOfCourse(account.googleId, courseId)) {
            result = createRedirectResult(Const.ActionURIs.INSTRUCTOR_COURSE_EDIT_PAGE);
            result.addResponseParam(ParamNameConst.ParamsNames.COURSE_ID, courseId);
        } else {
            result = createRedirectResult(Const.ActionURIs.INSTRUCTOR_COURSES_PAGE);
        }

        return result;
    }

    /**
     * Returns true if there is a joined instructor (other than the instructor to delete)
     * with the privilege of modifying instructors.
     *
     * @param courseId                Id of the course
     * @param instructorToDeleteEmail Email of the instructor who is being deleted
     */
    private boolean hasAlternativeInstructor(String courseId, String instructorToDeleteEmail) {

        List<InstructorAttributes> instructors = logic.getInstructorsForCourse(courseId);

        for (InstructorAttributes instr : instructors) {

            boolean isAlternativeInstructor =
                        instr.isRegistered()
                        && !instr.getEmail().equals(instructorToDeleteEmail)
                        && instr.isAllowedForPrivilege(ParamNameConst.ParamsNames.INSTRUCTOR_PERMISSION_MODIFY_INSTRUCTOR);

            if (isAlternativeInstructor) {
                return true;
            }
        }

        return false;
    }
}
