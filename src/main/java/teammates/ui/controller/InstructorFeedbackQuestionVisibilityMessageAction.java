package teammates.ui.controller;

import java.util.List;

import teammates.common.datatransfer.FeedbackParticipantType;
import teammates.common.datatransfer.attributes.FeedbackQuestionAttributes;
import teammates.common.datatransfer.questions.FeedbackQuestionType;
import teammates.common.util.Assumption;
import teammates.common.util.Const;
import teammates.common.util.ParamNameConst;
import teammates.ui.pagedata.InstructorFeedbackQuestionVisibilityMessagePageData;

public class InstructorFeedbackQuestionVisibilityMessageAction extends Action {
    @Override
    protected ActionResult execute() {
        FeedbackQuestionAttributes feedbackQuestion = extractFeedbackQuestionData(account.email);

        List<String> message = feedbackQuestion.getVisibilityMessage();

        InstructorFeedbackQuestionVisibilityMessagePageData data =
                new InstructorFeedbackQuestionVisibilityMessagePageData(account, sessionToken);
        data.visibilityMessage = message;

        return createAjaxResult(data);
    }

    private FeedbackQuestionAttributes extractFeedbackQuestionData(String creatorEmail) {
        FeedbackQuestionAttributes newQuestion = new FeedbackQuestionAttributes();

        newQuestion.creatorEmail = creatorEmail;

        String feedbackQuestionGiverType = getRequestParamValue(ParamNameConst.ParamsNames.FEEDBACK_QUESTION_GIVERTYPE);

        Assumption.assertPostParamNotNull(ParamNameConst.ParamsNames.FEEDBACK_QUESTION_GIVERTYPE, feedbackQuestionGiverType);

        newQuestion.giverType = FeedbackParticipantType.valueOf(feedbackQuestionGiverType);

        String feedbackQuestionRecipientType = getRequestParamValue(ParamNameConst.ParamsNames.FEEDBACK_QUESTION_RECIPIENTTYPE);

        Assumption.assertPostParamNotNull(ParamNameConst.ParamsNames.FEEDBACK_QUESTION_RECIPIENTTYPE, feedbackQuestionRecipientType);

        newQuestion.recipientType = FeedbackParticipantType.valueOf(feedbackQuestionRecipientType);

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
        newQuestion.removeIrrelevantVisibilityOptions();

        return newQuestion;
    }

}
