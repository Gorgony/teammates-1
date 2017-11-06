package teammates.ui.controller;

import teammates.common.datatransfer.attributes.InstructorAttributes;
import teammates.common.util.*;

public class InstructorCourseStudentDeleteAllAction extends Action {

    @Override
    public ActionResult execute() {

        String courseId = getRequestParamValue(ParamNameConst.ParamsNames.COURSE_ID);
        Assumption.assertPostParamNotNull(ParamNameConst.ParamsNames.COURSE_ID, courseId);

        InstructorAttributes instructor = logic.getInstructorForGoogleId(courseId, account.googleId);
        gateKeeper.verifyAccessible(
                instructor, logic.getCourse(courseId), ParamNameConst.ParamsNames.INSTRUCTOR_PERMISSION_MODIFY_STUDENT);

        logic.deleteAllStudentsInCourse(courseId);
        statusToUser.add(new StatusMessage(StatusMessageConst.StatusMessages.STUDENTS_DELETED, StatusMessageColor.SUCCESS));
        statusToAdmin = "All the Students "
                + "in Course <span class=\"bold\">[" + courseId + "]</span> are deleted.";

        RedirectResult result = createRedirectResult(Const.ActionURIs.INSTRUCTOR_COURSE_DETAILS_PAGE);
        result.addResponseParam(ParamNameConst.ParamsNames.COURSE_ID, courseId);
        return result;

    }

}
