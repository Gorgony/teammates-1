package teammates.ui.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import teammates.common.datatransfer.FeedbackSessionResultsBundle;
import teammates.common.datatransfer.attributes.FeedbackSessionAttributes;
import teammates.common.datatransfer.attributes.InstructorAttributes;
import teammates.common.datatransfer.attributes.SessionAttributes;
import teammates.common.datatransfer.attributes.StudentAttributes;
import teammates.common.exception.EntityDoesNotExistException;
import teammates.common.util.*;
import teammates.ui.pagedata.InstructorStudentRecordsAjaxPageData;

public class InstructorStudentRecordsAjaxPageAction extends Action {

    @Override
    public ActionResult execute() throws EntityDoesNotExistException {

        String courseId = getRequestParamValue(ParamNameConst.ParamsNames.COURSE_ID);
        Assumption.assertPostParamNotNull(ParamNameConst.ParamsNames.COURSE_ID, courseId);

        String studentEmail = getRequestParamValue(ParamNameConst.ParamsNames.STUDENT_EMAIL);
        Assumption.assertPostParamNotNull(ParamNameConst.ParamsNames.STUDENT_EMAIL, studentEmail);

        String targetSessionName = getRequestParamValue(ParamNameConst.ParamsNames.FEEDBACK_SESSION_NAME);
        Assumption.assertPostParamNotNull(ParamNameConst.ParamsNames.FEEDBACK_SESSION_NAME, targetSessionName);

        InstructorAttributes instructor = logic.getInstructorForGoogleId(courseId, account.googleId);

        gateKeeper.verifyAccessible(instructor, logic.getCourse(courseId));

        StudentAttributes student = logic.getStudentForEmail(courseId, studentEmail);
        if (student == null) {
            statusToUser.add(new StatusMessage(StatusMessageConst.StatusMessages.STUDENT_NOT_FOUND_FOR_RECORDS,
                                               StatusMessageColor.DANGER));
            isError = true;
            return createRedirectResult(Const.ActionURIs.INSTRUCTOR_HOME_PAGE);
        }

        List<FeedbackSessionAttributes> feedbacks = logic.getFeedbackSessionsListForInstructor(account.googleId, false);

        filterFeedbackSessions(courseId, feedbacks, instructor, student);

        List<SessionAttributes> sessions = new ArrayList<>();
        sessions.addAll(feedbacks);
        Collections.sort(sessions, SessionAttributes.DESCENDING_ORDER);

        List<FeedbackSessionResultsBundle> results = new ArrayList<>();
        for (SessionAttributes session : sessions) {
            if (session instanceof FeedbackSessionAttributes) {
                if (!targetSessionName.isEmpty() && targetSessionName.equals(session.getSessionName())) {
                    FeedbackSessionResultsBundle result = logic.getFeedbackSessionResultsForInstructor(
                                                    session.getSessionName(), courseId, instructor.email);
                    results.add(result);
                }
            } else {
                Assumption.fail("Unknown session type");
            }
        }
        statusToAdmin = "instructorStudentRecords Ajax Page Load<br>"
                      + "Viewing <span class=\"bold\">" + studentEmail + "'s</span> records "
                      + "for session <span class=\"bold\">[" + targetSessionName + "]</span> "
                      + "in course <span class=\"bold\">[" + courseId + "]</span>";

        InstructorStudentRecordsAjaxPageData data =
                                        new InstructorStudentRecordsAjaxPageData(account, student, sessionToken, results);

        return createShowPageResult(Const.ViewURIs.INSTRUCTOR_STUDENT_RECORDS_AJAX, data);
    }

    private void filterFeedbackSessions(String courseId, List<FeedbackSessionAttributes> feedbacks,
                                        InstructorAttributes currentInstructor, StudentAttributes student) {
        Iterator<FeedbackSessionAttributes> iterFs = feedbacks.iterator();
        while (iterFs.hasNext()) {
            FeedbackSessionAttributes tempFs = iterFs.next();
            if (!tempFs.getCourseId().equals(courseId)
                    || !currentInstructor.isAllowedForPrivilege(student.section, tempFs.getSessionName(),
                                              ParamNameConst.ParamsNames.INSTRUCTOR_PERMISSION_VIEW_SESSION_IN_SECTIONS)) {
                iterFs.remove();
            }
        }
    }

}
