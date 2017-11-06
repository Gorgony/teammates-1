package teammates.ui.controller;

import java.util.ArrayList;
import java.util.List;

import com.google.appengine.api.datastore.Text;

import teammates.common.datatransfer.FeedbackParticipantType;
import teammates.common.datatransfer.attributes.FeedbackQuestionAttributes;
import teammates.common.datatransfer.attributes.InstructorAttributes;
import teammates.common.datatransfer.questions.FeedbackQuestionDetails;
import teammates.common.datatransfer.questions.FeedbackQuestionType;
import teammates.common.exception.InvalidParametersException;
import teammates.common.util.*;
import teammates.ui.pagedata.PageData;

public class InstructorFeedbackQuestionAddAction extends Action {

    @Override
    protected ActionResult execute() {
        String courseId = getRequestParamValue(ParamNameConst.ParamsNames.COURSE_ID);
        String feedbackSessionName = getRequestParamValue(ParamNameConst.ParamsNames.FEEDBACK_SESSION_NAME);
        InstructorAttributes instructorDetailForCourse = logic.getInstructorForGoogleId(courseId, account.googleId);

        gateKeeper.verifyAccessible(instructorDetailForCourse,
                                    logic.getFeedbackSession(feedbackSessionName, courseId),
                                    false, ParamNameConst.ParamsNames.INSTRUCTOR_PERMISSION_MODIFY_SESSION);

        FeedbackQuestionAttributes feedbackQuestion = extractFeedbackQuestionData(instructorDetailForCourse.email);
        List<String> questionDetailsErrors = feedbackQuestion.getQuestionDetails().validateQuestionDetails();

        List<StatusMessage> questionDetailsErrorsMessages = new ArrayList<>();

        for (String error : questionDetailsErrors) {
            questionDetailsErrorsMessages.add(new StatusMessage(error, StatusMessageColor.DANGER));
        }

        RedirectResult redirectResult = createRedirectResult(new PageData(account, sessionToken)
                .getInstructorFeedbackEditLink(courseId, feedbackSessionName));

        if (!questionDetailsErrors.isEmpty()) {
            statusToUser.addAll(questionDetailsErrorsMessages);
            isError = true;

            return redirectResult;
        }

        String err = validateQuestionGiverRecipientVisibility(feedbackQuestion);

        if (!err.isEmpty()) {
            statusToUser.add(new StatusMessage(err, StatusMessageColor.DANGER));
            isError = true;
        }

        try {
            logic.createFeedbackQuestion(feedbackQuestion);
            statusToUser.add(new StatusMessage(StatusMessageConst.StatusMessages.FEEDBACK_QUESTION_ADDED, StatusMessageColor.SUCCESS));
            statusToAdmin = "Created Feedback Question for Feedback Session:<span class=\"bold\">("
                          + feedbackQuestion.feedbackSessionName + ")</span> for Course <span class=\"bold\">["
                          + feedbackQuestion.courseId + "]</span> created.<br>"
                          + "<span class=\"bold\">"
                          + feedbackQuestion.getQuestionDetails().getQuestionTypeDisplayName()
                          + ":</span> "
                          + SanitizationHelper.sanitizeForHtml(feedbackQuestion.getQuestionDetails().getQuestionText());
        } catch (InvalidParametersException e) {
            statusToUser.add(new StatusMessage(e.getMessage(), StatusMessageColor.DANGER));
            statusToAdmin = e.getMessage();
            isError = true;
        }
        return redirectResult;
    }

    private String validateQuestionGiverRecipientVisibility(FeedbackQuestionAttributes feedbackQuestion) {
        return InstructorFeedbackQuestionEditAction.validateQuestionGiverRecipientVisibility(feedbackQuestion);
    }

    private FeedbackQuestionAttributes extractFeedbackQuestionData(String creatorEmail) {
        FeedbackQuestionAttributes newQuestion = new FeedbackQuestionAttributes();

        newQuestion.creatorEmail = creatorEmail;

        newQuestion.courseId = getRequestParamValue(ParamNameConst.ParamsNames.COURSE_ID);
        Assumption.assertPostParamNotNull(ParamNameConst.ParamsNames.COURSE_ID, newQuestion.courseId);

        newQuestion.feedbackSessionName = getRequestParamValue(ParamNameConst.ParamsNames.FEEDBACK_SESSION_NAME);
        Assumption.assertPostParamNotNull(ParamNameConst.ParamsNames.FEEDBACK_SESSION_NAME, newQuestion.feedbackSessionName);

        String feedbackQuestionGiverType = getRequestParamValue(ParamNameConst.ParamsNames.FEEDBACK_QUESTION_GIVERTYPE);
        Assumption.assertPostParamNotNull(ParamNameConst.ParamsNames.FEEDBACK_QUESTION_GIVERTYPE, feedbackQuestionGiverType);
        newQuestion.giverType = FeedbackParticipantType.valueOf(feedbackQuestionGiverType);

        String feedbackQuestionRecipientType = getRequestParamValue(ParamNameConst.ParamsNames.FEEDBACK_QUESTION_RECIPIENTTYPE);
        Assumption.assertPostParamNotNull(ParamNameConst.ParamsNames.FEEDBACK_QUESTION_RECIPIENTTYPE, feedbackQuestionRecipientType);
        newQuestion.recipientType = FeedbackParticipantType.valueOf(feedbackQuestionRecipientType);

        String feedbackQuestionNumber = getRequestParamValue(ParamNameConst.ParamsNames.FEEDBACK_QUESTION_NUMBER);
        Assumption.assertPostParamNotNull(ParamNameConst.ParamsNames.FEEDBACK_QUESTION_NUMBER, feedbackQuestionNumber);
        newQuestion.questionNumber = Integer.parseInt(feedbackQuestionNumber);
        Assumption.assertTrue("Invalid question number", newQuestion.questionNumber >= 1);

        String numberOfEntityTypes = getRequestParamValue(ParamNameConst.ParamsNames.FEEDBACK_QUESTION_NUMBEROFENTITIESTYPE);
        Assumption.assertPostParamNotNull(ParamNameConst.ParamsNames.FEEDBACK_QUESTION_NUMBEROFENTITIESTYPE, numberOfEntityTypes);

        if ("custom".equals(numberOfEntityTypes)
                && (newQuestion.recipientType == FeedbackParticipantType.STUDENTS
                        || newQuestion.recipientType == FeedbackParticipantType.TEAMS)) {
            String numberOfEntities = getRequestParamValue(ParamNameConst.ParamsNames.FEEDBACK_QUESTION_NUMBEROFENTITIES);
            Assumption.assertPostParamNotNull(ParamNameConst.ParamsNames.FEEDBACK_QUESTION_NUMBEROFENTITIES, numberOfEntities);

            newQuestion.numberOfEntitiesToGiveFeedbackTo = Integer.parseInt(numberOfEntities);
        } else {
            newQuestion.numberOfEntitiesToGiveFeedbackTo = Const.MAX_POSSIBLE_RECIPIENTS;
        }

        newQuestion.showResponsesTo = FeedbackParticipantType.getParticipantListFromCommaSeparatedValues(
                getRequestParamValue(ParamNameConst.ParamsNames.FEEDBACK_QUESTION_SHOWRESPONSESTO));
        newQuestion.showGiverNameTo = FeedbackParticipantType.getParticipantListFromCommaSeparatedValues(
                getRequestParamValue(ParamNameConst.ParamsNames.FEEDBACK_QUESTION_SHOWGIVERTO));
        newQuestion.showRecipientNameTo = FeedbackParticipantType.getParticipantListFromCommaSeparatedValues(
                getRequestParamValue(ParamNameConst.ParamsNames.FEEDBACK_QUESTION_SHOWRECIPIENTTO));

        String questionType = getRequestParamValue(ParamNameConst.ParamsNames.FEEDBACK_QUESTION_TYPE);
        Assumption.assertPostParamNotNull(ParamNameConst.ParamsNames.FEEDBACK_QUESTION_TYPE, questionType);
        questionType = FeedbackQuestionType.standardizeIfConstSum(questionType);
        newQuestion.questionType = FeedbackQuestionType.valueOf(questionType);

        FeedbackQuestionDetails questionDetails = FeedbackQuestionDetails.createQuestionDetails(
                requestParameters, newQuestion.questionType);
        newQuestion.setQuestionDetails(questionDetails);

        String questionDescription = getRequestParamValue(ParamNameConst.ParamsNames.FEEDBACK_QUESTION_DESCRIPTION);
        newQuestion.setQuestionDescription(new Text(questionDescription));

        return newQuestion;
    }

}
