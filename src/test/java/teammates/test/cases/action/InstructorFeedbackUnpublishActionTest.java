package teammates.test.cases.action;

import java.util.Date;
import java.util.Map;

import org.testng.annotations.Test;

import teammates.common.datatransfer.attributes.FeedbackSessionAttributes;
import teammates.common.exception.NullPostParameterException;
import teammates.common.util.*;
import teammates.storage.api.FeedbackSessionsDb;
import teammates.ui.controller.InstructorFeedbackUnpublishAction;
import teammates.ui.controller.RedirectResult;

/**
 * SUT: {@link InstructorFeedbackUnpublishAction}.
 */
public class InstructorFeedbackUnpublishActionTest extends BaseActionTest {

    @Override
    protected String getActionUri() {
        return Const.ActionURIs.INSTRUCTOR_FEEDBACK_UNPUBLISH;
    }

    @Override
    @Test
    public void testExecuteAndPostProcess() throws Exception {
        gaeSimulation.loginAsInstructor(typicalBundle.instructors.get("instructor1OfCourse1").googleId);
        FeedbackSessionAttributes session = typicalBundle.feedbackSessions.get("session2InCourse1");

        String[] paramsNormal = {
                ParamNameConst.ParamsNames.COURSE_ID, session.getCourseId(),
                ParamNameConst.ParamsNames.FEEDBACK_SESSION_NAME, session.getFeedbackSessionName()
        };
        String[] paramsWithNullCourseId = {
                ParamNameConst.ParamsNames.FEEDBACK_SESSION_NAME, session.getFeedbackSessionName()
        };
        String[] paramsWithNullFeedbackSessionName = {
                ParamNameConst.ParamsNames.COURSE_ID, session.getCourseId()
        };

        ______TS("Typical successful case: session unpublishable");

        makeFeedbackSessionPublished(session);

        InstructorFeedbackUnpublishAction unpublishAction = getAction(paramsNormal);
        RedirectResult result = getRedirectResult(unpublishAction);

        String expectedDestination = getPageResultDestination(
                Const.ActionURIs.INSTRUCTOR_FEEDBACK_SESSIONS_PAGE, false, "idOfInstructor1OfCourse1");
        assertEquals(expectedDestination, result.getDestinationWithParams());
        assertEquals(StatusMessageConst.StatusMessages.FEEDBACK_SESSION_UNPUBLISHED, result.getStatusMessage());
        assertFalse(result.isError);

        verifySpecifiedTasksAdded(unpublishAction, Const.TaskQueue.FEEDBACK_SESSION_UNPUBLISHED_EMAIL_QUEUE_NAME, 1);

        TaskWrapper taskAdded = unpublishAction.getTaskQueuer().getTasksAdded().get(0);
        Map<String, String[]> paramMap = taskAdded.getParamMap();
        assertEquals(session.getCourseId(), paramMap.get(ParamNameConst.ParamsNames.EMAIL_COURSE)[0]);
        assertEquals(session.getSessionName(), paramMap.get(ParamNameConst.ParamsNames.EMAIL_FEEDBACK)[0]);

        ______TS("Unsuccessful case 1: params with null course id");

        String errorMessage = "";
        unpublishAction = getAction(paramsWithNullCourseId);

        try {
            unpublishAction.executeAndPostProcess();
            signalFailureToDetectException("NullPostParameterException expected");
        } catch (NullPostParameterException e) {
            errorMessage = e.getMessage();
        }

        assertEquals(
                String.format(Const.StatusCodes.NULL_POST_PARAMETER, ParamNameConst.ParamsNames.COURSE_ID),
                errorMessage);

        ______TS("Unsuccessful case 2: params with null feedback session name");

        errorMessage = "";
        unpublishAction = getAction(paramsWithNullFeedbackSessionName);

        try {
            unpublishAction.executeAndPostProcess();
            signalFailureToDetectException("NullPostParameterException expected");
        } catch (NullPostParameterException e) {
            errorMessage = e.getMessage();
        }

        assertEquals(
                String.format(Const.StatusCodes.NULL_POST_PARAMETER, ParamNameConst.ParamsNames.FEEDBACK_SESSION_NAME),
                errorMessage);

        ______TS("Unsuccessful case 3: trying to unpublish a session not currently published");

        makeFeedbackSessionUnpublished(session);

        unpublishAction = getAction(paramsNormal);
        result = getRedirectResult(unpublishAction);

        expectedDestination = getPageResultDestination(
                Const.ActionURIs.INSTRUCTOR_FEEDBACK_SESSIONS_PAGE, true, "idOfInstructor1OfCourse1");
        assertEquals(expectedDestination, result.getDestinationWithParams());
        assertEquals("Error unpublishing feedback session: Session has already been unpublished.",
                     result.getStatusMessage());
        assertTrue(result.isError);

        verifyNoTasksAdded(unpublishAction);

        makeFeedbackSessionPublished(session);
    }

    private void modifyFeedbackSessionPublishState(FeedbackSessionAttributes session, boolean isPublished) throws Exception {
        // startTime < endTime <= resultsVisibleFromTime
        Date startTime = TimeHelper.getDateOffsetToCurrentTime(-2);
        Date endTime = TimeHelper.getDateOffsetToCurrentTime(-1);
        Date resultsVisibleFromTimeForPublishedSession = TimeHelper.getDateOffsetToCurrentTime(-1);

        session.setStartTime(startTime);
        session.setEndTime(endTime);

        if (isPublished) {
            session.setResultsVisibleFromTime(resultsVisibleFromTimeForPublishedSession);
            assertTrue(session.isPublished());
        } else {
            session.setResultsVisibleFromTime(Const.TIME_REPRESENTS_LATER);
            assertFalse(session.isPublished());
        }

        session.setSentPublishedEmail(true);

        new FeedbackSessionsDb().updateFeedbackSession(session);
    }

    private void makeFeedbackSessionPublished(FeedbackSessionAttributes session) throws Exception {
        modifyFeedbackSessionPublishState(session, true);
    }

    private void makeFeedbackSessionUnpublished(FeedbackSessionAttributes session) throws Exception {
        modifyFeedbackSessionPublishState(session, false);
    }

    @Override
    protected InstructorFeedbackUnpublishAction getAction(String... params) {
        return (InstructorFeedbackUnpublishAction) gaeSimulation.getActionObject(getActionUri(), params);
    }

    @Override
    @Test
    protected void testAccessControl() throws Exception {
        FeedbackSessionAttributes session = typicalBundle.feedbackSessions.get("session1InCourse1");

        makeFeedbackSessionPublished(session); //we have to revert to the closed state

        String[] submissionParams = new String[]{
                ParamNameConst.ParamsNames.COURSE_ID, session.getCourseId(),
                ParamNameConst.ParamsNames.FEEDBACK_SESSION_NAME, session.getFeedbackSessionName()
        };

        verifyUnaccessibleWithoutLogin(submissionParams);
        verifyUnaccessibleForUnregisteredUsers(submissionParams);
        verifyUnaccessibleForStudents(submissionParams);
        verifyUnaccessibleForInstructorsOfOtherCourses(submissionParams);
        verifyUnaccessibleWithoutModifySessionPrivilege(submissionParams);
        verifyAccessibleForInstructorsOfTheSameCourse(submissionParams);

        makeFeedbackSessionPublished(session); //we have to revert to the closed state

        verifyAccessibleForAdminToMasqueradeAsInstructor(submissionParams);

        makeFeedbackSessionUnpublished(session); //we have to revert to the closed state
    }
}
