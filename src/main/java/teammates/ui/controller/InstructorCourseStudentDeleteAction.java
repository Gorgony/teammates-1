package teammates.ui.controller;

import teammates.common.datatransfer.attributes.InstructorAttributes;
import teammates.common.util.*;

public class InstructorCourseStudentDeleteAction extends Action {

    @Override
    public ActionResult execute() {

        String courseId = getRequestParamValue(ParamNameConst.ParamsNames.COURSE_ID);
        Assumption.assertPostParamNotNull(ParamNameConst.ParamsNames.COURSE_ID, courseId);

        String studentEmail = getRequestParamValue(ParamNameConst.ParamsNames.STUDENT_EMAIL);
        Assumption.assertPostParamNotNull(ParamNameConst.ParamsNames.STUDENT_EMAIL, studentEmail);

        InstructorAttributes instructor = logic.getInstructorForGoogleId(courseId, account.googleId);
        gateKeeper.verifyAccessible(
                instructor, logic.getCourse(courseId), ParamNameConst.ParamsNames.INSTRUCTOR_PERMISSION_MODIFY_STUDENT);

        logic.deleteStudent(courseId, studentEmail);
        statusToUser.add(new StatusMessage(StatusMessageConst.StatusMessages.STUDENT_DELETED, StatusMessageColor.SUCCESS));
        statusToAdmin = "Student <span class=\"bold\">" + studentEmail
                      + "</span> in Course <span class=\"bold\">[" + courseId + "]</span> deleted.";

        RedirectResult result = createRedirectResult(Const.ActionURIs.INSTRUCTOR_COURSE_DETAILS_PAGE);
        result.addResponseParam(ParamNameConst.ParamsNames.COURSE_ID, courseId);
        return result;

    }

}
