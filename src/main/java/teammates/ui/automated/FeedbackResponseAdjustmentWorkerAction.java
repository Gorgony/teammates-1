package teammates.ui.automated;

import java.util.List;
import java.util.Map;

import com.google.gson.reflect.TypeToken;

import teammates.common.datatransfer.StudentEnrollDetails;
import teammates.common.datatransfer.attributes.FeedbackResponseAttributes;
import teammates.common.datatransfer.attributes.FeedbackSessionAttributes;
import teammates.common.util.*;

/**
 * Task queue worker action: adjusts feedback responses in the database due to
 * change in student enrollment details of a course.
 */
public class FeedbackResponseAdjustmentWorkerAction extends AutomatedAction {

    private static final Logger log = Logger.getLogger();

    @Override
    protected String getActionDescription() {
        return null;
    }

    @Override
    protected String getActionMessage() {
        return null;
    }

    @Override
    public void execute() {
        String courseId = getRequestParamValue(ParamNameConst.ParamsNames.COURSE_ID);
        Assumption.assertPostParamNotNull(ParamNameConst.ParamsNames.COURSE_ID, courseId);

        String sessionName = getRequestParamValue(ParamNameConst.ParamsNames.FEEDBACK_SESSION_NAME);
        Assumption.assertPostParamNotNull(ParamNameConst.ParamsNames.FEEDBACK_SESSION_NAME, sessionName);

        String enrollmentDetails = getRequestParamValue(ParamNameConst.ParamsNames.ENROLLMENT_DETAILS);
        Assumption.assertPostParamNotNull(ParamNameConst.ParamsNames.ENROLLMENT_DETAILS, enrollmentDetails);

        log.info("Adjusting submissions for feedback session :" + sessionName + "in course : " + courseId);

        FeedbackSessionAttributes feedbackSession = logic.getFeedbackSession(sessionName, courseId);

        String errorString = "Error encountered while adjusting feedback session responses of %s in course %s: %s%n%s";

        if (feedbackSession == null) {
            log.severe(String.format(errorString, sessionName, courseId, "feedback session is null", ""));
            setForRetry();
            return;
        }

        List<FeedbackResponseAttributes> allResponses =
                logic.getFeedbackResponsesForSession(feedbackSession.getFeedbackSessionName(),
                                                     feedbackSession.getCourseId());
        List<StudentEnrollDetails> enrollmentList =
                JsonUtils.fromJson(enrollmentDetails, new TypeToken<List<StudentEnrollDetails>>(){}.getType());
        for (FeedbackResponseAttributes response : allResponses) {
            try {
                logic.adjustFeedbackResponseForEnrollments(enrollmentList, response);
            } catch (Exception e) {
                String url = HttpRequestHelper.getRequestedUrl(request);
                Map<String, String[]> params = HttpRequestHelper.getParameterMap(request);
                // no logged-in user for worker
                String logMessage = new LogMessageGenerator().generateActionFailureLogMessage(url, params, e, null);
                log.severe(String.format(errorString, sessionName, courseId, e.getMessage(), logMessage));
                setForRetry();
                return;
            }
        }
    }

}
