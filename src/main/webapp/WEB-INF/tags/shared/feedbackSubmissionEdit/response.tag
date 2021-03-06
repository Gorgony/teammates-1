<%@ tag description="questionWithResponses.tag - Display question with responses" %>
<%@ tag import="teammates.common.util.Const"%>
<%@ tag import="teammates.common.util.ToolTipConst" %>
<%@ tag import="teammates.common.util.ParamNameConst" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ attribute name="questionWithResponses" type="teammates.ui.template.StudentFeedbackSubmissionEditQuestionsWithResponses" required="true" %>
<%@ attribute name="response" type="teammates.ui.template.FeedbackSubmissionEditResponse" required="true" %>
<%@ attribute name="isSessionOpenForSubmission" type="java.lang.Boolean" required="true" %>

<c:set var="isNumResponsesMax" value="${questionWithResponses.numOfResponseBoxes eq questionWithResponses.maxResponsesPossible}"/>
<c:set var="isRecipientNameHidden" value="${questionWithResponses.question.recipientNameHidden}"/>
<c:set var="recipientType" value="${questionWithResponses.question.recipientType}"/>

<c:choose>
  <c:when test="${isRecipientNameHidden}"><c:set var="divClassType" value="col-sm-12"/></c:when>
  <c:when test="${isNumResponsesMax}"><c:set var="divClassType" value="col-sm-9"/></c:when>
  <c:otherwise><c:set var="divClassType" value="col-sm-7"/></c:otherwise>
</c:choose>

<c:set var="autoWidth" value="" />
<c:if test="${questionWithResponses.question.questionTypeConstsum}">
  <c:set var="autoWidth" value="width-auto" />
</c:if>

<br>
<div class="form-group margin-0">
  <div ${isNumResponsesMax ? 'class="col-sm-3 form-inline mobile-align-left"' : 'class="col-sm-5 form-inline mobile-align-left"'}
      ${isRecipientNameHidden ?  'style="display:none"' : 'style="text-align:right"'}>

    <label for="input">
      <span data-toggle="tooltip"
        data-placement="top"
        title="<%= ToolTipConst.Tooltips.EVALUEE_DESCRIPTION %>">
        Evaluee
      </span>
      <c:choose>
        <c:when test="${recipientType == 'STUDENT'}"> (Student)</c:when>
        <c:when test="${recipientType == 'INSTRUCTOR'}"> (Instructor)</c:when>
        <c:when test="${recipientType == 'TEAM'}"> (Team)</c:when>
      </c:choose>:
    </label>

    <select class="participantSelect middlealign<c:if test="${not response.existingResponse}"> newResponse</c:if> form-control"
        name="<%= ParamNameConst.ParamsNames.FEEDBACK_RESPONSE_RECIPIENT %>-${questionWithResponses.question.qnIndx}-${response.responseIndx}"
        ${isNumResponsesMax ? 'style="display:none;max-width:125px"' : 'style="width:275px;max-width:275px"'}
        ${isSessionOpenForSubmission ? '' : 'disabled' }>

      <c:forEach items="${response.recipientOptionsForQuestion}" var="option">
        ${option}
      </c:forEach>
    </select>
  </div>
  <div class="${divClassType}<c:if test="${questionWithResponses.question.questionTypeConstsum}"> width-auto</c:if>">
    ${response.submissionFormHtml}
    <c:if test="${response.existingResponse}">
      <input type="hidden"
          name="<%= ParamNameConst.ParamsNames.FEEDBACK_RESPONSE_ID %>-${questionWithResponses.question.qnIndx}-${response.responseIndx}"
          value="<c:out value="${response.responseId}"/>">
    </c:if>
  </div>
</div>
