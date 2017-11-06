<%@ tag description="instructorCourses - form which is currently used to load the courses table by ajax." %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ tag import="teammates.common.util.Const" %>
<%@ tag import="teammates.common.util.FieldValidator" %>
<%@ tag import="teammates.common.util.ParamNameConst" %>

<form style="display:none;" id="ajaxForCourses" class="ajaxForCoursesForm"
    action="<%= Const.ActionURIs.INSTRUCTOR_COURSES_PAGE %>">
  <input type="hidden"
      name="<%= ParamNameConst.ParamsNames.USER_ID %>"
      value="${data.account.googleId}">
  <input type="hidden"
      name="<%= ParamNameConst.ParamsNames.IS_USING_AJAX %>"
      value="on">
</form>
