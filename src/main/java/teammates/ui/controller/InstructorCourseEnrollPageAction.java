package teammates.ui.controller;

import teammates.common.datatransfer.attributes.InstructorAttributes;
import teammates.common.util.*;
import teammates.ui.pagedata.InstructorCourseEnrollPageData;

/**
 * Action: showing page to enroll students into a course for an instructor.
 */
public class InstructorCourseEnrollPageAction extends Action {

    @Override
    public ActionResult execute() {
        String courseId = getRequestParamValue(ParamNameConst.ParamsNames.COURSE_ID);
        String studentsInfo = getRequestParamValue(ParamNameConst.ParamsNames.STUDENTS_ENROLLMENT_INFO);

        Assumption.assertPostParamNotNull(ParamNameConst.ParamsNames.COURSE_ID, courseId);

        InstructorAttributes instructor = logic.getInstructorForGoogleId(courseId, account.googleId);
        gateKeeper.verifyAccessible(
                instructor, logic.getCourse(courseId), ParamNameConst.ParamsNames.INSTRUCTOR_PERMISSION_MODIFY_STUDENT);

        /* Setup page data for 'Enroll' page of a course */
        InstructorCourseEnrollPageData pageData =
                new InstructorCourseEnrollPageData(account, sessionToken, courseId, studentsInfo);

        statusToAdmin = String.format(StatusMessageConst.StatusMessages.ADMIN_LOG_INSTRUCTOR_COURSE_ENROLL_PAGE_LOAD,
                                      courseId);
        addDataLossWarningToStatusToUser(courseId);

        return createShowPageResult(Const.ViewURIs.INSTRUCTOR_COURSE_ENROLL, pageData);
    }

    private void addDataLossWarningToStatusToUser(String courseId) {
        if (hasExistingResponses(courseId)) {
            statusToUser.add(new StatusMessage(StatusMessageConst.StatusMessages.COURSE_ENROLL_POSSIBLE_DATA_LOSS,
                                               StatusMessageColor.WARNING));
        }
    }

    private boolean hasExistingResponses(String courseId) {
        return logic.hasResponsesForCourse(courseId);
    }

}
