<%@ tag description="instructorHome - Student search bar" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ tag import="teammates.common.util.Const" %>
<%@ tag import="teammates.common.util.ToolTipConst" %>
<%@ tag import="teammates.common.util.ParamNameConst" %>
<div class="well well-plain">
  <div class="row">
    <div class="col-md-12">
      <form method="get" action="${data.instructorSearchLink}"
          name="search_form">
        <div class="input-group">
          <input type="text" id="searchbox"
              title="<%= ToolTipConst.Tooltips.SEARCH_STUDENT %>"
              name="<%= ParamNameConst.ParamsNames.SEARCH_KEY %>"
              class="form-control"
              data-toggle="tooltip"
              data-placement="top"
              placeholder="e.g. Charles Shultz, charles@gmail.com">
          <span class="input-group-btn">
            <button class="btn btn-default" type="submit" value="Search" id="buttonSearch">
              Search
            </button>
          </span>
        </div>
        <input type="hidden" name="<%= ParamNameConst.ParamsNames.SEARCH_STUDENTS %>" value="true">
        <input type="hidden" name="<%= ParamNameConst.ParamsNames.SEARCH_COMMENTS_FOR_RESPONSES %>" value="false">
        <input type="hidden" name="<%= ParamNameConst.ParamsNames.USER_ID %>" value="${data.account.googleId}">
      </form>
    </div>
  </div>
</div>
