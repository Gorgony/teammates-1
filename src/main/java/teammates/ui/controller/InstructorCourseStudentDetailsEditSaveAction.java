package teammates.ui.controller;

import java.util.Arrays;

import teammates.common.datatransfer.attributes.InstructorAttributes;
import teammates.common.datatransfer.attributes.StudentAttributes;
import teammates.common.exception.EnrollException;
import teammates.common.exception.EntityDoesNotExistException;
import teammates.common.exception.InvalidParametersException;
import teammates.common.exception.TeammatesException;
import teammates.common.util.*;
import teammates.logic.api.EmailGenerator;
import teammates.ui.pagedata.InstructorCourseStudentDetailsEditPageData;

public class InstructorCourseStudentDetailsEditSaveAction extends Action {

    private static final Logger log = Logger.getLogger();

    @Override
    public ActionResult execute() throws EntityDoesNotExistException {

        String courseId = getRequestParamValue(ParamNameConst.ParamsNames.COURSE_ID);
        Assumption.assertPostParamNotNull(ParamNameConst.ParamsNames.COURSE_ID, courseId);

        String studentEmail = getRequestParamValue(ParamNameConst.ParamsNames.STUDENT_EMAIL);
        Assumption.assertPostParamNotNull(ParamNameConst.ParamsNames.STUDENT_EMAIL, studentEmail);

        InstructorAttributes instructor = logic.getInstructorForGoogleId(courseId, account.googleId);
        gateKeeper.verifyAccessible(
                instructor, logic.getCourse(courseId), ParamNameConst.ParamsNames.INSTRUCTOR_PERMISSION_MODIFY_STUDENT);

        StudentAttributes student = logic.getStudentForEmail(courseId, studentEmail);

        if (student == null) {
            return redirectWithError(StatusMessageConst.StatusMessages.STUDENT_NOT_FOUND_FOR_EDIT,
                                     "Student <span class=\"bold\">" + studentEmail + "</span> in "
                                     + "Course <span class=\"bold\">[" + courseId + "]</span> not found.",
                                     courseId);
        }

        student.name = getRequestParamValue(ParamNameConst.ParamsNames.STUDENT_NAME);
        student.email = getRequestParamValue(ParamNameConst.ParamsNames.NEW_STUDENT_EMAIL);
        student.team = getRequestParamValue(ParamNameConst.ParamsNames.TEAM_NAME);
        student.section = getRequestParamValue(ParamNameConst.ParamsNames.SECTION_NAME);
        student.comments = getRequestParamValue(ParamNameConst.ParamsNames.COMMENTS);
        boolean hasSection = logic.hasIndicatedSections(courseId);

        student.name = SanitizationHelper.sanitizeName(student.name);
        student.email = SanitizationHelper.sanitizeEmail(student.email);
        student.team = SanitizationHelper.sanitizeName(student.team);
        student.section = SanitizationHelper.sanitizeName(student.section);
        student.comments = SanitizationHelper.sanitizeTextField(student.comments);

        try {
            StudentAttributes originalStudentAttribute = logic.getStudentForEmail(courseId, studentEmail);
            student.updateWithExistingRecord(originalStudentAttribute);

            boolean isSectionChanged = student.isSectionChanged(originalStudentAttribute);
            boolean isTeamChanged = student.isTeamChanged(originalStudentAttribute);
            boolean isEmailChanged = student.isEmailChanged(originalStudentAttribute);

            if (isSectionChanged) {
                logic.validateSectionsAndTeams(Arrays.asList(student), courseId);
            } else if (isTeamChanged) {
                logic.validateTeams(Arrays.asList(student), courseId);
            }

            logic.updateStudent(studentEmail, student);

            boolean isSessionSummarySendEmail = getRequestParamAsBoolean(ParamNameConst.ParamsNames.SESSION_SUMMARY_EMAIL_SEND_CHECK);
            if (isEmailChanged) {
                logic.resetStudentGoogleId(student.email, courseId);
                if (isSessionSummarySendEmail) {
                    try {
                        EmailWrapper email = new EmailGenerator().generateFeedbackSessionSummaryOfCourse(courseId, student);
                        emailSender.sendEmail(email);
                    } catch (Exception e) {
                        log.severe("Error while sending session summary email"
                                    + TeammatesException.toStringWithStackTrace(e));
                    }
                }
            }

            statusToUser.add(new StatusMessage(isSessionSummarySendEmail && isEmailChanged
                                ? StatusMessageConst.StatusMessages.STUDENT_EDITED_AND_EMAIL_SENT
                                : StatusMessageConst.StatusMessages.STUDENT_EDITED, StatusMessageColor.SUCCESS));

            statusToAdmin = "Student <span class=\"bold\">" + studentEmail + "'s</span> details in "
                            + "Course <span class=\"bold\">[" + courseId + "]</span> edited.<br>"
                            + "New Email: " + student.email + "<br>New Team: " + student.team + "<br>"
                            + "Comments: " + student.comments;

            RedirectResult result = createRedirectResult(Const.ActionURIs.INSTRUCTOR_COURSE_DETAILS_PAGE);
            result.addResponseParam(ParamNameConst.ParamsNames.COURSE_ID, courseId);
            return result;

        } catch (InvalidParametersException | EnrollException e) {
            setStatusForException(e);
            String newEmail = student.email;
            student.email = studentEmail;
            boolean isOpenOrPublishedEmailSentForTheCourse = logic.isOpenOrPublishedEmailSentForTheCourse(courseId);
            InstructorCourseStudentDetailsEditPageData data =
                    new InstructorCourseStudentDetailsEditPageData(account, sessionToken, student, newEmail, hasSection,
                            isOpenOrPublishedEmailSentForTheCourse);
            return createShowPageResult(Const.ViewURIs.INSTRUCTOR_COURSE_STUDENT_EDIT, data);
        }

    }

    private RedirectResult redirectWithError(String errorToUser, String errorToAdmin, String courseId) {
        statusToUser.add(new StatusMessage(errorToUser, StatusMessageColor.DANGER));
        statusToAdmin = errorToAdmin;
        isError = true;

        RedirectResult result = createRedirectResult(Const.ActionURIs.INSTRUCTOR_COURSE_DETAILS_PAGE);
        result.addResponseParam(ParamNameConst.ParamsNames.COURSE_ID, courseId);
        return result;
    }

}
