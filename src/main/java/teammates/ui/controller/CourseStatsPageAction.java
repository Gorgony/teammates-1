package teammates.ui.controller;

import teammates.common.exception.EntityDoesNotExistException;
import teammates.common.util.Assumption;
import teammates.common.util.ParamNameConst;
import teammates.ui.pagedata.CourseStatsPageData;

public class CourseStatsPageAction extends Action {

    @Override
    protected ActionResult execute() throws EntityDoesNotExistException {
        String courseId = getRequestParamValue(ParamNameConst.ParamsNames.COURSE_ID);
        Assumption.assertPostParamNotNull(ParamNameConst.ParamsNames.COURSE_ID, courseId);

        CourseStatsPageData data = new CourseStatsPageData(account, sessionToken);

        gateKeeper.verifyInstructorPrivileges(account);

        data.courseDetails = logic.getCourseDetails(courseId);

        return createAjaxResult(data);
    }
}
