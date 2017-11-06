package teammates.ui.controller;

import teammates.common.datatransfer.attributes.CourseAttributes;
import teammates.common.datatransfer.attributes.InstructorAttributes;
import teammates.common.exception.EntityDoesNotExistException;
import teammates.common.exception.InvalidParametersException;
import teammates.common.util.*;

public class InstructorCourseEditSaveAction extends Action {
    @Override
    protected ActionResult execute() throws EntityDoesNotExistException {
        String courseId = getRequestParamValue(ParamNameConst.ParamsNames.COURSE_ID);
        Assumption.assertPostParamNotNull(ParamNameConst.ParamsNames.COURSE_ID, courseId);

        String courseName = getRequestParamValue(ParamNameConst.ParamsNames.COURSE_NAME);
        Assumption.assertPostParamNotNull(ParamNameConst.ParamsNames.COURSE_NAME, courseName);

        String courseTimeZone = getRequestParamValue(ParamNameConst.ParamsNames.COURSE_TIME_ZONE);
        Assumption.assertPostParamNotNull(ParamNameConst.ParamsNames.COURSE_TIME_ZONE, courseTimeZone);

        InstructorAttributes instructor = logic.getInstructorForGoogleId(courseId, account.googleId);
        gateKeeper.verifyAccessible(instructor, logic.getCourse(courseId),
                                    ParamNameConst.ParamsNames.INSTRUCTOR_PERMISSION_MODIFY_COURSE);

        CourseAttributes courseToEdit = new CourseAttributes(courseId, courseName, courseTimeZone);

        try {
            logic.updateCourse(courseToEdit);

            statusToUser.add(new StatusMessage(StatusMessageConst.StatusMessages.COURSE_EDITED, StatusMessageColor.SUCCESS));
            statusToAdmin = "Course name for Course <span class=\"bold\">[" + courseId + "]</span> edited.<br>"
                          + "New name: " + courseName;
        } catch (InvalidParametersException e) {
            setStatusForException(e);
        }

        RedirectResult result = createRedirectResult(Const.ActionURIs.INSTRUCTOR_COURSE_EDIT_PAGE);
        result.addResponseParam(ParamNameConst.ParamsNames.COURSE_ID, courseId);
        return result;
    }
}
