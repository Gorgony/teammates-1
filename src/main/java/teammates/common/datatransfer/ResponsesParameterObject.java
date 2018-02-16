package teammates.common.datatransfer;

import teammates.common.datatransfer.attributes.FeedbackResponseAttributes;
import teammates.common.datatransfer.attributes.FeedbackResponseCommentAttributes;

import java.util.List;
import java.util.Map;

public class ResponsesParameterObject {
    private final List<FeedbackResponseAttributes> responses;
    private final FeedbackSessionResponseStatus responseStatus;
    private final Map<String, List<FeedbackResponseCommentAttributes>> responseComments;

    public ResponsesParameterObject(List<FeedbackResponseAttributes> responses, FeedbackSessionResponseStatus responseStatus, Map<String, List<FeedbackResponseCommentAttributes>> responseComments) {
        this.responses = responses;
        this.responseStatus = responseStatus;
        this.responseComments = responseComments;
    }

    public List<FeedbackResponseAttributes> getResponses() {
        return responses;
    }

    public FeedbackSessionResponseStatus getResponseStatus() {
        return responseStatus;
    }

    public Map<String, List<FeedbackResponseCommentAttributes>> getResponseComments() {
        return responseComments;
    }
}
