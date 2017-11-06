package teammates.test.cases.action;

import org.testng.annotations.Test;

import teammates.common.datatransfer.FeedbackParticipantType;
import teammates.common.datatransfer.attributes.FeedbackQuestionAttributes;
import teammates.common.datatransfer.attributes.FeedbackSessionAttributes;
import teammates.common.util.Const;
import teammates.common.util.ParamNameConst;
import teammates.logic.core.FeedbackQuestionsLogic;
import teammates.ui.controller.AjaxResult;
import teammates.ui.controller.InstructorFeedbackQuestionVisibilityMessageAction;

/**
 * SUT: {@link InstructorFeedbackQuestionVisibilityMessageAction}.
 */
public class InstructorFeedbackQuestionVisibilityMessageActionTest extends BaseActionTest {

    @Override
    protected String getActionUri() {
        return Const.ActionURIs.INSTRUCTOR_FEEDBACK_QUESTION_VISIBILITY_MESSAGE;
    }

    @Override
    @Test
    public void testExecuteAndPostProcess() {
        String instructor1OfCourse1 = typicalBundle.instructors.get("instructor1OfCourse1").googleId;

        gaeSimulation.loginAsInstructor(instructor1OfCourse1);

        FeedbackSessionAttributes fs = typicalBundle.feedbackSessions.get("session1InCourse1");
        FeedbackQuestionAttributes fq = FeedbackQuestionsLogic
                                            .inst()
                                            .getFeedbackQuestion(fs.getFeedbackSessionName(), fs.getCourseId(), 1);

        ______TS("Typical Case - max -> constructed params");

        String[] typicalParams = new String[]{
                ParamNameConst.ParamsNames.COURSE_ID, fs.getCourseId(),
                ParamNameConst.ParamsNames.FEEDBACK_SESSION_NAME, fs.getFeedbackSessionName(),
                ParamNameConst.ParamsNames.FEEDBACK_QUESTION_GIVERTYPE, FeedbackParticipantType.STUDENTS.toString(),
                ParamNameConst.ParamsNames.FEEDBACK_QUESTION_RECIPIENTTYPE, FeedbackParticipantType.STUDENTS.toString(),
                ParamNameConst.ParamsNames.FEEDBACK_QUESTION_NUMBER, "0",
                ParamNameConst.ParamsNames.FEEDBACK_QUESTION_TYPE, "TEXT",
                ParamNameConst.ParamsNames.FEEDBACK_QUESTION_TEXT, "question",
                ParamNameConst.ParamsNames.FEEDBACK_QUESTION_NUMBEROFENTITIESTYPE, "max",
                ParamNameConst.ParamsNames.FEEDBACK_QUESTION_SHOWRESPONSESTO, FeedbackParticipantType.RECEIVER.toString(),
                ParamNameConst.ParamsNames.FEEDBACK_QUESTION_SHOWGIVERTO, FeedbackParticipantType.RECEIVER.toString(),
                ParamNameConst.ParamsNames.FEEDBACK_QUESTION_SHOWRECIPIENTTO, FeedbackParticipantType.RECEIVER.toString(),
                ParamNameConst.ParamsNames.FEEDBACK_QUESTION_EDITTYPE, "edit",
                ParamNameConst.ParamsNames.FEEDBACK_QUESTION_ID, fq.getId()
        };

        InstructorFeedbackQuestionVisibilityMessageAction a = getAction(typicalParams);
        AjaxResult r = getAjaxResult(a);

        assertFalse(r.isError);

        ______TS("Custom Case Students - constructed params");

        String[] customParams = new String[]{
                ParamNameConst.ParamsNames.COURSE_ID, fs.getCourseId(),
                ParamNameConst.ParamsNames.FEEDBACK_SESSION_NAME, fs.getFeedbackSessionName(),
                ParamNameConst.ParamsNames.FEEDBACK_QUESTION_GIVERTYPE, FeedbackParticipantType.STUDENTS.toString(),
                ParamNameConst.ParamsNames.FEEDBACK_QUESTION_RECIPIENTTYPE, FeedbackParticipantType.STUDENTS.toString(),
                ParamNameConst.ParamsNames.FEEDBACK_QUESTION_NUMBER, "1",
                ParamNameConst.ParamsNames.FEEDBACK_QUESTION_TYPE, "TEXT",
                ParamNameConst.ParamsNames.FEEDBACK_QUESTION_TEXT, "question",
                ParamNameConst.ParamsNames.FEEDBACK_QUESTION_NUMBEROFENTITIESTYPE, "custom",
                ParamNameConst.ParamsNames.FEEDBACK_QUESTION_NUMBEROFENTITIES, "2",
                ParamNameConst.ParamsNames.FEEDBACK_QUESTION_SHOWRESPONSESTO, FeedbackParticipantType.RECEIVER.toString(),
                ParamNameConst.ParamsNames.FEEDBACK_QUESTION_SHOWGIVERTO, FeedbackParticipantType.RECEIVER.toString(),
                ParamNameConst.ParamsNames.FEEDBACK_QUESTION_SHOWRECIPIENTTO, FeedbackParticipantType.RECEIVER.toString(),
                ParamNameConst.ParamsNames.FEEDBACK_QUESTION_EDITTYPE, "edit",
                ParamNameConst.ParamsNames.FEEDBACK_QUESTION_ID, fq.getId()
        };

        a = getAction(customParams);
        r = getAjaxResult(a);

        assertFalse(r.isError);

        ______TS("Custom Case Teams - data bundle params");

        fs = typicalBundle.feedbackSessions.get("session2InCourse1");
        fq = FeedbackQuestionsLogic.inst().getFeedbackQuestion(fs.getFeedbackSessionName(), fs.getCourseId(), 1);

        customParams = new String[]{
                ParamNameConst.ParamsNames.COURSE_ID, fs.getCourseId(),
                ParamNameConst.ParamsNames.FEEDBACK_SESSION_NAME, fs.getFeedbackSessionName(),
                ParamNameConst.ParamsNames.FEEDBACK_QUESTION_GIVERTYPE, FeedbackParticipantType.TEAMS.toString(),
                ParamNameConst.ParamsNames.FEEDBACK_QUESTION_RECIPIENTTYPE, FeedbackParticipantType.TEAMS.toString(),
                ParamNameConst.ParamsNames.FEEDBACK_QUESTION_NUMBER, "1",
                ParamNameConst.ParamsNames.FEEDBACK_QUESTION_TYPE, "TEXT",
                ParamNameConst.ParamsNames.FEEDBACK_QUESTION_TEXT, "question",
                ParamNameConst.ParamsNames.FEEDBACK_QUESTION_NUMBEROFENTITIESTYPE, "custom",
                ParamNameConst.ParamsNames.FEEDBACK_QUESTION_NUMBEROFENTITIES, "2",
                ParamNameConst.ParamsNames.FEEDBACK_QUESTION_SHOWRESPONSESTO, FeedbackParticipantType.RECEIVER.toString(),
                ParamNameConst.ParamsNames.FEEDBACK_QUESTION_SHOWGIVERTO, FeedbackParticipantType.RECEIVER.toString(),
                ParamNameConst.ParamsNames.FEEDBACK_QUESTION_SHOWRECIPIENTTO, FeedbackParticipantType.RECEIVER.toString(),
                ParamNameConst.ParamsNames.FEEDBACK_QUESTION_EDITTYPE, "edit",
                ParamNameConst.ParamsNames.FEEDBACK_QUESTION_ID, fq.getId()
        };

        a = getAction(customParams);
        r = getAjaxResult(a);

        assertFalse(r.isError);

        ______TS("Custom Case Instructor - data bundle params");

        fs = typicalBundle.feedbackSessions.get("gracePeriodSession");
        fq = FeedbackQuestionsLogic.inst().getFeedbackQuestion(fs.getFeedbackSessionName(), fs.getCourseId(), 1);

        customParams = new String[]{
                ParamNameConst.ParamsNames.COURSE_ID, fs.getCourseId(),
                ParamNameConst.ParamsNames.FEEDBACK_SESSION_NAME, fs.getFeedbackSessionName(),
                ParamNameConst.ParamsNames.FEEDBACK_QUESTION_GIVERTYPE, FeedbackParticipantType.INSTRUCTORS.toString(),
                ParamNameConst.ParamsNames.FEEDBACK_QUESTION_RECIPIENTTYPE, FeedbackParticipantType.INSTRUCTORS.toString(),
                ParamNameConst.ParamsNames.FEEDBACK_QUESTION_NUMBER, "2",
                ParamNameConst.ParamsNames.FEEDBACK_QUESTION_TYPE, "TEXT",
                ParamNameConst.ParamsNames.FEEDBACK_QUESTION_TEXT, "question",
                ParamNameConst.ParamsNames.FEEDBACK_QUESTION_NUMBEROFENTITIESTYPE, "custom",
                ParamNameConst.ParamsNames.FEEDBACK_QUESTION_NUMBEROFENTITIES, "1",
                ParamNameConst.ParamsNames.FEEDBACK_QUESTION_SHOWRESPONSESTO, FeedbackParticipantType.RECEIVER.toString(),
                ParamNameConst.ParamsNames.FEEDBACK_QUESTION_SHOWGIVERTO, FeedbackParticipantType.RECEIVER.toString(),
                ParamNameConst.ParamsNames.FEEDBACK_QUESTION_SHOWRECIPIENTTO, FeedbackParticipantType.RECEIVER.toString(),
                ParamNameConst.ParamsNames.FEEDBACK_QUESTION_EDITTYPE, "edit",
                ParamNameConst.ParamsNames.FEEDBACK_QUESTION_ID, fq.getId()
        };

        a = getAction(customParams);
        r = getAjaxResult(a);

        assertFalse(r.isError);

        ______TS("Private case, empty participant list - data bundle params");

        String instructor1OfCourse2 = typicalBundle.instructors.get("instructor1OfCourse2").googleId;

        gaeSimulation.logoutUser(); // log out of instructor 1
        gaeSimulation.loginAsInstructor(instructor1OfCourse2);

        fs = typicalBundle.feedbackSessions.get("session1InCourse2");
        fq = FeedbackQuestionsLogic.inst().getFeedbackQuestion(fs.getFeedbackSessionName(), fs.getCourseId(), 1);

        String[] privateParams = new String[]{
                ParamNameConst.ParamsNames.COURSE_ID, fs.getCourseId(),
                ParamNameConst.ParamsNames.FEEDBACK_SESSION_NAME, fs.getFeedbackSessionName(),
                ParamNameConst.ParamsNames.FEEDBACK_QUESTION_GIVERTYPE, FeedbackParticipantType.INSTRUCTORS.toString(),
                ParamNameConst.ParamsNames.FEEDBACK_QUESTION_RECIPIENTTYPE, FeedbackParticipantType.STUDENTS.toString(),
                ParamNameConst.ParamsNames.FEEDBACK_QUESTION_NUMBER, "1",
                ParamNameConst.ParamsNames.FEEDBACK_QUESTION_TYPE, "TEXT",
                ParamNameConst.ParamsNames.FEEDBACK_QUESTION_TEXT, "question",
                ParamNameConst.ParamsNames.FEEDBACK_QUESTION_NUMBEROFENTITIESTYPE, "custom",
                ParamNameConst.ParamsNames.FEEDBACK_QUESTION_NUMBEROFENTITIES, "10",
                ParamNameConst.ParamsNames.FEEDBACK_QUESTION_SHOWRESPONSESTO, "",
                ParamNameConst.ParamsNames.FEEDBACK_QUESTION_SHOWGIVERTO, "",
                ParamNameConst.ParamsNames.FEEDBACK_QUESTION_SHOWRECIPIENTTO, "",
                ParamNameConst.ParamsNames.FEEDBACK_QUESTION_EDITTYPE, "edit",
                ParamNameConst.ParamsNames.FEEDBACK_QUESTION_ID, fq.getId()
        };

        a = getAction(privateParams);
        r = getAjaxResult(a);

        assertFalse(r.isError);

        ______TS("Private case, null participant list - constructed params");

        privateParams = new String[]{
                ParamNameConst.ParamsNames.COURSE_ID, fs.getCourseId(),
                ParamNameConst.ParamsNames.FEEDBACK_SESSION_NAME, fs.getFeedbackSessionName(),
                ParamNameConst.ParamsNames.FEEDBACK_QUESTION_GIVERTYPE, FeedbackParticipantType.INSTRUCTORS.toString(),
                ParamNameConst.ParamsNames.FEEDBACK_QUESTION_RECIPIENTTYPE, FeedbackParticipantType.STUDENTS.toString(),
                ParamNameConst.ParamsNames.FEEDBACK_QUESTION_NUMBER, "1",
                ParamNameConst.ParamsNames.FEEDBACK_QUESTION_TYPE, "TEXT",
                ParamNameConst.ParamsNames.FEEDBACK_QUESTION_TEXT, "question",
                ParamNameConst.ParamsNames.FEEDBACK_QUESTION_NUMBEROFENTITIESTYPE, "custom",
                ParamNameConst.ParamsNames.FEEDBACK_QUESTION_NUMBEROFENTITIES, "10",
                ParamNameConst.ParamsNames.FEEDBACK_QUESTION_SHOWRESPONSESTO, null,
                ParamNameConst.ParamsNames.FEEDBACK_QUESTION_SHOWGIVERTO, null,
                ParamNameConst.ParamsNames.FEEDBACK_QUESTION_SHOWRECIPIENTTO, null,
                ParamNameConst.ParamsNames.FEEDBACK_QUESTION_EDITTYPE, "edit",
                ParamNameConst.ParamsNames.FEEDBACK_QUESTION_ID, fq.getId()
        };

        a = getAction(privateParams);
        r = getAjaxResult(a);

        assertFalse(r.isError);
    }

    @Override
    protected InstructorFeedbackQuestionVisibilityMessageAction getAction(String... params) {
        return (InstructorFeedbackQuestionVisibilityMessageAction) gaeSimulation.getActionObject(getActionUri(), params);
    }

    @Override
    @Test
    protected void testAccessControl() throws Exception {
        //TODO: implement this
    }
}
