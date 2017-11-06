package teammates.ui.controller;

import teammates.common.exception.EntityDoesNotExistException;
import teammates.common.util.Assumption;
import teammates.common.util.ParamNameConst;

public class InstructorCourseStudentListDownloadAction extends Action {

    @Override
    protected ActionResult execute() throws EntityDoesNotExistException {
        String courseId = getRequestParamValue(ParamNameConst.ParamsNames.COURSE_ID);
        Assumption.assertPostParamNotNull(ParamNameConst.ParamsNames.COURSE_ID, courseId);

        gateKeeper.verifyAccessible(
                logic.getInstructorForGoogleId(courseId, account.googleId),
                logic.getCourse(courseId));

        String fileContent = logic.getCourseStudentListAsCsv(courseId, account.googleId);
        String fileName = courseId + "_studentList";

        statusToAdmin = "Students data for Course " + courseId + " was downloaded";

        return createFileDownloadResult(fileName, fileContent);
    }

}
