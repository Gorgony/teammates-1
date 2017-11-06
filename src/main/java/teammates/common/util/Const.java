package teammates.common.util;

import java.util.Date;

import org.joda.time.DateTimeZone;

/**
 * Stores constants that are widely used across classes.
 * this class contains several nested classes, each containing a specific
 * category of constants.
 */
public final class Const {

    /*
     * This section holds constants that are defined as constants primarily
     * because they are repeated in many places.
     */
    public static final String EOL = System.getProperty("line.separator");
    public static final String HTML_BR_TAG = "<br>";

    public static final String USER_NOBODY_TEXT = "-";
    public static final String USER_UNKNOWN_TEXT = "Unknown user";
    public static final String TEAM_OF_EMAIL_OWNER = "'s Team";

    public static final String NONE_OF_THE_ABOVE = "None of the above";

    public static final String INSTRUCTOR_FEEDBACK_SESSION_VISIBLE_TIME_CUSTOM = "custom";
    public static final String INSTRUCTOR_FEEDBACK_SESSION_VISIBLE_TIME_ATOPEN = "atopen";
    public static final String INSTRUCTOR_FEEDBACK_SESSION_VISIBLE_TIME_NEVER = "never";

    public static final String INSTRUCTOR_FEEDBACK_RESULTS_VISIBLE_TIME_CUSTOM = "custom";
    public static final String INSTRUCTOR_FEEDBACK_RESULTS_VISIBLE_TIME_ATVISIBLE = "atvisible";
    public static final String INSTRUCTOR_FEEDBACK_RESULTS_VISIBLE_TIME_LATER = "later";
    public static final String INSTRUCTOR_FEEDBACK_RESULTS_VISIBLE_TIME_NEVER = "never";
    public static final String INSTRUCTOR_FEEDBACK_RESULTS_MISSING_RESPONSE = "No Response";

    public static final String STUDENT_COURSE_STATUS_YET_TO_JOIN = "Yet to join";
    public static final String STUDENT_COURSE_STATUS_JOINED = "Joined";
    public static final String STUDENT_PROFILE_FIELD_NOT_FILLED = "Not Specified";

    public static final String USER_NAME_FOR_SELF = "Myself";
    public static final String USER_TEAM_FOR_INSTRUCTOR = "Instructors";
    public static final String NO_SPECIFIC_RECIPIENT = "No specific recipient";
    public static final String NO_SPECIFIC_SECTION = "No specific section";

    public static final String DISPLAYED_NAME_FOR_SELF_IN_COMMENTS = "You";
    public static final String DISPLAYED_NAME_FOR_ANONYMOUS_PARTICIPANT = "Anonymous";

    public static final String ACTION_RESULT_FAILURE = "Servlet Action Failure";
    public static final String ACTION_RESULT_SYSTEM_ERROR_REPORT = "System Error Report";

    public static final int SIZE_LIMIT_PER_ENROLLMENT = 150;
    public static final int INSTRUCTOR_VIEW_RESPONSE_LIMIT = 8000;

    // for course sorting in instructorHomePage
    public static final String SORT_BY_COURSE_ID = "id";
    public static final String SORT_BY_COURSE_NAME = "name";
    public static final String SORT_BY_COURSE_CREATION_DATE = "createdAt";
    public static final String DEFAULT_SORT_CRITERIA = SORT_BY_COURSE_CREATION_DATE;

    public static final String DEFAULT_SECTION = "None";

    public static final String DEFAULT_TIMEZONE = DateTimeZone.UTC.getID();

    /*
     * These constants are used as variable values to mean that the variable
     * is in a 'special' state.
     */
    public static final int INT_UNINITIALIZED = -9999;
    public static final double DOUBLE_UNINITIALIZED = -9999.0;

    public static final int MAX_POSSIBLE_RECIPIENTS = -100;

    public static final int POINTS_EQUAL_SHARE = 100;
    public static final int POINTS_NOT_SURE = -101;
    public static final int POINTS_NOT_SUBMITTED = -999;

    public static final int VISIBILITY_TABLE_GIVER = 0;
    public static final int VISIBILITY_TABLE_RECIPIENT = 1;

    public static final String GENERAL_QUESTION = "%GENERAL%";
    public static final String USER_IS_TEAM = "%TEAM%";
    public static final String USER_IS_NOBODY = "%NOBODY%";
    public static final String USER_IS_MISSING = "%MISSING%";

    public static final Date TIME_REPRESENTS_FOLLOW_OPENING;
    public static final Date TIME_REPRESENTS_FOLLOW_VISIBLE;
    public static final Date TIME_REPRESENTS_NEVER;
    public static final Date TIME_REPRESENTS_LATER;
    public static final Date TIME_REPRESENTS_NOW;
    public static final Date TIME_REPRESENTS_DEFAULT_TIMESTAMP;

    public static final String ERROR_FEEDBACK_EMAIL_SUBJECT = "User-submitted Error Report";

    static {
        TIME_REPRESENTS_FOLLOW_OPENING = TimeHelper.convertToDate("1970-12-31 12:00 AM UTC");
        TIME_REPRESENTS_FOLLOW_VISIBLE = TimeHelper.convertToDate("1970-06-22 12:00 AM UTC");
        TIME_REPRESENTS_NEVER = TimeHelper.convertToDate("1970-11-27 12:00 AM UTC");
        TIME_REPRESENTS_LATER = TimeHelper.convertToDate("1970-01-01 12:00 AM UTC");
        TIME_REPRESENTS_NOW = TimeHelper.convertToDate("1970-02-14 12:00 AM UTC");
        TIME_REPRESENTS_DEFAULT_TIMESTAMP = TimeHelper.convertToDate("2011-01-01 12:00 AM UTC");
    }

    public static final String TIME_FORMAT_ISO_8601_UTC = "yyyy-MM-dd'T'HH:mm:ss'Z'";

    /*
     * Other Constants
     */

    private Const() {
        // Utility class containing constants
    }

    public static class FeedbackSessionResults {
        public static final String QUESTION_SORT_TYPE = "question";
        public static final String GRQ_SORT_TYPE = "giver-recipient-question";
        public static final String RGQ_SORT_TYPE = "recipient-giver-question";
        public static final String GQR_SORT_TYPE = "giver-question-recipient";
        public static final String RQG_SORT_TYPE = "recipient-question-giver";
    }

    public static class InstructorPermissionRoleNames {
        public static final String INSTRUCTOR_PERMISSION_ROLE_COOWNER = "Co-owner";
        public static final String INSTRUCTOR_PERMISSION_ROLE_MANAGER = "Manager";
        public static final String INSTRUCTOR_PERMISSION_ROLE_OBSERVER = "Observer";
        public static final String INSTRUCTOR_PERMISSION_ROLE_TUTOR = "Tutor";
        public static final String INSTRUCTOR_PERMISSION_ROLE_CUSTOM = "Custom";
    }

    public static class GenderTypes {
        public static final String MALE = "male";
        public static final String FEMALE = "female";
        public static final String OTHER = "other";
    }

    public static class SearchIndex {
        public static final String FEEDBACK_RESPONSE_COMMENT = "feedbackresponsecomment";
        public static final String STUDENT = "student";
        public static final String INSTRUCTOR = "instructor";
    }

    public static class SearchDocumentField {
        public static final String STUDENT_ATTRIBUTE = "studentAttribute";
        public static final String INSTRUCTOR_ATTRIBUTE = "instructorAttribute";
        public static final String FEEDBACK_RESPONSE_COMMENT_ATTRIBUTE = "frCommentAttibute";
        public static final String FEEDBACK_RESPONSE_COMMENT_GIVER_NAME = "frCommentGiverName";
        public static final String FEEDBACK_RESPONSE_COMMENT_GIVER_EMAIL = "frCommentGiverEmail";
        public static final String FEEDBACK_RESPONSE_ATTRIBUTE = "feedbackResponseAttibute";
        public static final String FEEDBACK_RESPONSE_GIVER_NAME = "feedbackResponseGiverName";
        public static final String FEEDBACK_RESPONSE_RECEIVER_NAME = "feedbackResponseReceiverName";
        public static final String FEEDBACK_QUESTION_ATTRIBUTE = "feedbackQuestionAttibute";
        public static final String FEEDBACK_SESSION_ATTRIBUTE = "feedbackSessionAttibute";
        public static final String SEARCHABLE_TEXT = "searchableText";
        public static final String CREATED_DATE = "createdDate";
        public static final String COURSE_ID = "courseId";
        public static final String GIVER_EMAIL = "giverEmail";
        public static final String GIVER_SECTION = "giverSection";
        public static final String RECIPIENT_EMAIL = "recipientEmail";
        public static final String RECIPIENT_SECTION = "recipientSection";
        public static final String IS_VISIBLE_TO_INSTRUCTOR = "isVisibleToInstructor";
        public static final String IS_VISIBLE_TO_RECEIVER = "isVisibleToReceiver";
        public static final String IS_VISIBLE_TO_GIVER = "isVisibleToGiver";
    }

    public static class ActionURIs {

        /* _PAGE/Page in the Action URI name means 'show page' */

        public static final String LOGOUT = "/logout";

        public static final String INSTRUCTOR_HOME_PAGE = "/page/instructorHomePage";
        public static final String INSTRUCTOR_COURSES_PAGE = "/page/instructorCoursesPage";
        public static final String INSTRUCTOR_COURSE_ADD = "/page/instructorCourseAdd";
        public static final String INSTRUCTOR_COURSE_DELETE = "/page/instructorCourseDelete";
        public static final String INSTRUCTOR_COURSE_ARCHIVE = "/page/instructorCourseArchive";
        public static final String INSTRUCTOR_COURSE_DETAILS_PAGE = "/page/instructorCourseDetailsPage";
        public static final String INSTRUCTOR_COURSE_EDIT_PAGE = "/page/instructorCourseEditPage";
        public static final String INSTRUCTOR_COURSE_EDIT_SAVE = "/page/instructorCourseEditSave";
        public static final String INSTRUCTOR_COURSE_STUDENT_DETAILS_PAGE = "/page/instructorCourseStudentDetailsPage";
        public static final String INSTRUCTOR_COURSE_STUDENT_DETAILS_EDIT = "/page/instructorCourseStudentDetailsEdit";
        public static final String INSTRUCTOR_COURSE_STUDENT_DETAILS_EDIT_SAVE =
                "/page/instructorCourseStudentDetailsEditSave";
        public static final String INSTRUCTOR_COURSE_STUDENT_DELETE = "/page/instructorCourseStudentDelete";
        public static final String INSTRUCTOR_COURSE_STUDENT_DELETE_ALL = "/page/instructorCourseStudentDeleteAll";
        public static final String INSTRUCTOR_COURSE_STUDENT_LIST_DOWNLOAD = "/page/instructorCourseStudentListDownload";
        public static final String INSTRUCTOR_COURSE_ENROLL_PAGE = "/page/instructorCourseEnrollPage";
        public static final String INSTRUCTOR_COURSE_ENROLL_SAVE = "/page/instructorCourseEnrollSave";
        public static final String INSTRUCTOR_COURSE_REMIND = "/page/instructorCourseRemind";
        public static final String INSTRUCTOR_COURSE_INSTRUCTOR_ADD = "/page/instructorCourseInstructorAdd";
        public static final String INSTRUCTOR_COURSE_INSTRUCTOR_EDIT_SAVE = "/page/instructorCourseInstructorEditSave";
        public static final String INSTRUCTOR_COURSE_INSTRUCTOR_DELETE = "/page/instructorCourseInstructorDelete";
        public static final String INSTRUCTOR_COURSE_JOIN = "/page/instructorCourseJoin";
        public static final String INSTRUCTOR_COURSE_JOIN_AUTHENTICATED = "/page/instructorCourseJoinAuthenticated";
        public static final String INSTRUCTOR_SEARCH_PAGE = "/page/instructorSearchPage";
        public static final String INSTRUCTOR_STUDENT_LIST_PAGE = "/page/instructorStudentListPage";
        public static final String INSTRUCTOR_STUDENT_LIST_AJAX_PAGE = "/page/instructorStudentListAjaxPage";

        public static final String INSTRUCTOR_STUDENT_RECORDS_PAGE = "/page/instructorStudentRecordsPage";
        public static final String INSTRUCTOR_STUDENT_RECORDS_AJAX_PAGE = "/page/instructorStudentRecordsAjaxPage";

        public static final String INSTRUCTOR_EDIT_STUDENT_FEEDBACK_PAGE = "/page/instructorEditStudentFeedbackPage";
        public static final String INSTRUCTOR_EDIT_STUDENT_FEEDBACK_SAVE = "/page/instructorEditStudentFeedbackSave";
        public static final String INSTRUCTOR_EDIT_INSTRUCTOR_FEEDBACK_PAGE = "/page/instructorEditInstructorFeedbackPage";
        public static final String INSTRUCTOR_EDIT_INSTRUCTOR_FEEDBACK_SAVE = "/page/instructorEditInstructorFeedbackSave";
        public static final String INSTRUCTOR_FEEDBACK_SESSIONS_PAGE = "/page/instructorFeedbackSessionsPage";
        public static final String INSTRUCTOR_FEEDBACK_ADD = "/page/instructorFeedbackAdd";
        public static final String INSTRUCTOR_FEEDBACK_COPY = "/page/instructorFeedbackCopy";
        public static final String INSTRUCTOR_FEEDBACK_DELETE = "/page/instructorFeedbackDelete";
        public static final String INSTRUCTOR_FEEDBACK_REMIND = "/page/instructorFeedbackRemind";
        public static final String INSTRUCTOR_FEEDBACK_REMIND_PARTICULAR_STUDENTS_PAGE =
                "/page/instructorFeedbackRemindParticularStudentsPage";
        public static final String INSTRUCTOR_FEEDBACK_REMIND_PARTICULAR_STUDENTS =
                "/page/instructorFeedbackRemindParticularStudents";
        public static final String INSTRUCTOR_FEEDBACK_PUBLISH = "/page/instructorFeedbackPublish";
        public static final String INSTRUCTOR_FEEDBACK_UNPUBLISH = "/page/instructorFeedbackUnpublish";
        public static final String INSTRUCTOR_FEEDBACK_EDIT_COPY_PAGE = "/page/instructorFeedbackEditCopyPage";
        public static final String INSTRUCTOR_FEEDBACK_EDIT_COPY = "/page/instructorFeedbackEditCopy";
        public static final String INSTRUCTOR_FEEDBACK_EDIT_PAGE = "/page/instructorFeedbackEditPage";
        public static final String INSTRUCTOR_FEEDBACK_EDIT_SAVE = "/page/instructorFeedbackEditSave";
        public static final String INSTRUCTOR_FEEDBACK_RESULTS_PAGE = "/page/instructorFeedbackResultsPage";
        public static final String INSTRUCTOR_FEEDBACK_RESULTS_DOWNLOAD = "/page/instructorFeedbackResultsDownload";
        public static final String INSTRUCTOR_FEEDBACK_PREVIEW_ASSTUDENT = "/page/instructorFeedbackPreviewAsStudent";
        public static final String INSTRUCTOR_FEEDBACK_PREVIEW_ASINSTRUCTOR = "/page/instructorFeedbackPreviewAsInstructor";

        public static final String INSTRUCTOR_FEEDBACK_QUESTION_ADD = "/page/instructorFeedbackQuestionAdd";
        public static final String INSTRUCTOR_FEEDBACK_QUESTION_COPY_PAGE = "/page/instructorFeedbackQuestionCopyPage";
        public static final String INSTRUCTOR_FEEDBACK_QUESTION_COPY = "/page/instructorFeedbackQuestionCopy";
        public static final String INSTRUCTOR_FEEDBACK_QUESTION_EDIT = "/page/instructorFeedbackQuestionEdit";
        public static final String INSTRUCTOR_FEEDBACK_QUESTION_VISIBILITY_MESSAGE =
                "/page/instructorFeedbackQuestionvisibilityMessage";

        public static final String INSTRUCTOR_FEEDBACK_RESPONSE_COMMENT_ADD = "/page/instructorFeedbackResponseCommentAdd";
        public static final String INSTRUCTOR_FEEDBACK_RESPONSE_COMMENT_EDIT = "/page/instructorFeedbackResponseCommentEdit";
        public static final String INSTRUCTOR_FEEDBACK_RESPONSE_COMMENT_DELETE =
                "/page/instructorFeedbackResponseCommentDelete";

        public static final String INSTRUCTOR_COURSE_STATS_PAGE = "/page/courseStatsPage";
        public static final String INSTRUCTOR_FEEDBACK_STATS_PAGE = "/page/feedbackSessionStatsPage";

        public static final String INSTRUCTOR_FEEDBACK_SUBMISSION_EDIT_PAGE = "/page/instructorFeedbackSubmissionEditPage";
        public static final String INSTRUCTOR_FEEDBACK_SUBMISSION_EDIT_SAVE = "/page/instructorFeedbackSubmissionEditSave";

        public static final String CREATE_IMAGE_UPLOAD_URL = "/page/createImageUploadUrl";
        public static final String IMAGE_UPLOAD = "/page/imageUpload";

        public static final String STUDENT_HOME_PAGE = "/page/studentHomePage";
        public static final String STUDENT_COURSE_JOIN = "/page/studentCourseJoin";
        public static final String STUDENT_COURSE_JOIN_NEW = "/page/studentCourseJoinAuthentication";
        public static final String STUDENT_COURSE_JOIN_AUTHENTICATED = "/page/studentCourseJoinAuthenticated";
        public static final String STUDENT_COURSE_DETAILS_PAGE = "/page/studentCourseDetailsPage";

        public static final String STUDENT_FEEDBACK_SUBMISSION_EDIT_PAGE = "/page/studentFeedbackSubmissionEditPage";
        public static final String STUDENT_FEEDBACK_SUBMISSION_EDIT_SAVE = "/page/studentFeedbackSubmissionEditSave";

        public static final String STUDENT_FEEDBACK_RESULTS_PAGE = "/page/studentFeedbackResultsPage";
        public static final String STUDENT_PROFILE_PAGE = "/page/studentProfilePage";
        public static final String STUDENT_PROFILE_EDIT_SAVE = "/page/studentProfileEditSave";
        public static final String STUDENT_PROFILE_PICTURE = "/page/studentProfilePic";
        public static final String STUDENT_PROFILE_PICTURE_UPLOAD = "/page/studentProfilePictureUpload";
        public static final String STUDENT_PROFILE_PICTURE_EDIT = "/page/studentProfilePictureEdit";
        public static final String STUDENT_PROFILE_CREATEUPLOADFORMURL = "/page/studentProfileCreateFormUrl";

        public static final String ADMIN_EMAIL_LOG_PAGE = "/admin/adminEmailLogPage";
        public static final String ADMIN_HOME_PAGE = "/admin/adminHomePage";
        public static final String ADMIN_INSTRUCTORACCOUNT_ADD = "/admin/adminInstructorAccountAdd";
        public static final String ADMIN_ACCOUNT_MANAGEMENT_PAGE = "/admin/adminAccountManagementPage";
        public static final String ADMIN_ACCOUNT_DETAILS_PAGE = "/admin/adminAccountDetailsPage";
        public static final String ADMIN_ACCOUNT_DELETE = "/admin/adminAccountDelete";
        public static final String ADMIN_EXCEPTION_TEST = "/admin/adminExceptionTest";
        public static final String ADMIN_ACTIVITY_LOG_PAGE = "/admin/adminActivityLogPage";
        public static final String ADMIN_SESSIONS_PAGE = "/admin/adminSessionsPage";
        public static final String ADMIN_SEARCH_PAGE = "/admin/adminSearchPage";
        public static final String ADMIN_EMAIL_COMPOSE_PAGE = "/admin/adminEmailComposePage";
        public static final String ADMIN_EMAIL_COMPOSE_SAVE = "/admin/adminEmailComposeSave";
        public static final String ADMIN_EMAIL_COMPOSE_SEND = "/admin/adminEmailComposeSend";
        public static final String ADMIN_EMAIL_SENT_PAGE = "/admin/adminEmailSentPage";
        public static final String ADMIN_EMAIL_TRASH_PAGE = "/admin/adminEmailTrashPage";
        public static final String ADMIN_EMAIL_TRASH_DELETE = "/admin/adminEmailTrashDelete";
        public static final String ADMIN_EMAIL_DRAFT_PAGE = "/admin/adminEmailDraftPage";
        public static final String ADMIN_EMAIL_MOVE_TO_TRASH = "/admin/adminEmailMoveToTrash";
        public static final String ADMIN_EMAIL_MOVE_OUT_TRASH = "/admin/adminEmailMoveOutTrash";
        public static final String ADMIN_EMAIL_IMAGE_UPLOAD = "/admin/adminEmailImageUpload";
        public static final String ADMIN_EMAIL_CREATE_IMAGE_UPLOAD_URL = "/admin/adminEmailCreateImageUploadUrl";

        public static final String ADMIN_EMAIL_GROUP_RECEIVER_LIST_UPLOAD = "/admin/adminEmailGroupReceiverListUpload";
        public static final String ADMIN_EMAIL_CREATE_GROUP_RECEIVER_LIST_UPLOAD_URL =
                "/admin/adminEmailCreateGroupReceiverListUploadUrl";

        public static final String PUBLIC_IMAGE_SERVE = "/public/publicImageServe";
        public static final String PUBLIC_EMAIL_FILE_SERVE = "/public/publicEmailImageServe";
        public static final String ADMIN_STUDENT_GOOGLE_ID_RESET = "/admin/adminStudentGoogleIdReset";

        public static final String AUTOMATED_LOG_COMPILATION = "/auto/compileLogs";
        public static final String AUTOMATED_FEEDBACK_OPENING_REMINDERS = "/auto/feedbackSessionOpeningReminders";
        public static final String AUTOMATED_FEEDBACK_CLOSED_REMINDERS = "/auto/feedbackSessionClosedReminders";
        public static final String AUTOMATED_FEEDBACK_CLOSING_REMINDERS = "/auto/feedbackSessionClosingReminders";
        public static final String AUTOMATED_FEEDBACK_PUBLISHED_REMINDERS = "/auto/feedbackSessionPublishedReminders";

        public static final String ERROR_FEEDBACK_SUBMIT = "/page/errorFeedbackSubmit";

        public static final String BACKDOOR = "/backdoor";

    }

    /**
     * Configurations for task queue.
     */
    public static class TaskQueue {

        public static final String ADMIN_PREPARE_EMAIL_ADDRESS_MODE_QUEUE_NAME = "admin-prepare-email-address-mode-queue";
        public static final String ADMIN_PREPARE_EMAIL_ADDRESS_MODE_WORKER_URL = "/worker/adminPrepareEmailAddressMode";

        public static final String ADMIN_PREPARE_EMAIL_GROUP_MODE_QUEUE_NAME = "admin-prepare-email-group-mode-queue";
        public static final String ADMIN_PREPARE_EMAIL_GROUP_MODE_WORKER_URL = "/worker/adminPrepareEmailGroupMode";

        public static final String ADMIN_SEND_EMAIL_QUEUE_NAME = "admin-send-email-queue";
        public static final String ADMIN_SEND_EMAIL_WORKER_URL = "/worker/adminSendEmail";

        public static final String FEEDBACK_RESPONSE_ADJUSTMENT_QUEUE_NAME = "feedback-response-adjustment-queue";
        public static final String FEEDBACK_RESPONSE_ADJUSTMENT_WORKER_URL = "/worker/feedbackResponseAdjustment";

        public static final String FEEDBACK_SESSION_PUBLISHED_EMAIL_QUEUE_NAME =
                "feedback-session-published-email-queue";
        public static final String FEEDBACK_SESSION_PUBLISHED_EMAIL_WORKER_URL =
                "/worker/feedbackSessionPublishedEmail";

        public static final String FEEDBACK_SESSION_REMIND_EMAIL_QUEUE_NAME = "feedback-session-remind-email-queue";
        public static final String FEEDBACK_SESSION_REMIND_EMAIL_WORKER_URL = "/worker/feedbackSessionRemindEmail";

        public static final String FEEDBACK_SESSION_REMIND_PARTICULAR_USERS_EMAIL_QUEUE_NAME =
                "feedback-session-remind-particular-users-email-queue";
        public static final String FEEDBACK_SESSION_REMIND_PARTICULAR_USERS_EMAIL_WORKER_URL =
                "/worker/feedbackSessionRemindParticularUsersEmail";

        public static final String FEEDBACK_SESSION_UNPUBLISHED_EMAIL_QUEUE_NAME =
                "feedback-session-unpublished-email-queue";
        public static final String FEEDBACK_SESSION_UNPUBLISHED_EMAIL_WORKER_URL =
                "/worker/feedbackSessionUnpublishedEmail";

        public static final String INSTRUCTOR_COURSE_JOIN_EMAIL_QUEUE_NAME = "instructor-course-join-email-queue";
        public static final String INSTRUCTOR_COURSE_JOIN_EMAIL_WORKER_URL = "/worker/instructorCourseJoinEmail";

        public static final String SEND_EMAIL_QUEUE_NAME = "send-email-queue";
        public static final String SEND_EMAIL_WORKER_URL = "/worker/sendEmail";

        public static final String STUDENT_COURSE_JOIN_EMAIL_QUEUE_NAME = "student-course-join-email-queue";
        public static final String STUDENT_COURSE_JOIN_EMAIL_WORKER_URL = "/worker/studentCourseJoinEmail";

    }

    public static class PublicActionNames {
        public static final String PUBLIC_IMAGE_SERVE_ACTION = "publicImageServeAction";
    }

    public static class PageNames {
        public static final String INSTRUCTOR_HOME_PAGE = "instructorHomePage";
        public static final String INSTRUCTOR_FEEDBACK_SESSIONS_PAGE = "instructorFeedbackSessionsPage";
        public static final String INSTRUCTOR_FEEDBACK_EDIT_PAGE = "instructorFeedbackEditPage";
        public static final String INSTRUCTOR_FEEDBACK_COPY = "instructorFeedbackCopy";
    }

    public static class ViewURIs {

        /* We omit adding the 'page' prefix to views because all of them are "pages" */

        public static final String INSTRUCTOR_HOME = "/jsp/instructorHome.jsp";
        public static final String INSTRUCTOR_HOME_AJAX_COURSE_TABLE = "/jsp/instructorHomeAjaxCourse.jsp";
        public static final String INSTRUCTOR_FEEDBACK_RESPONSE_COMMENTS_ADD =
                "/jsp/instructorFeedbackResponseCommentsAdd.jsp";
        public static final String INSTRUCTOR_COURSES = "/jsp/instructorCourses.jsp";
        public static final String INSTRUCTOR_COURSE_EDIT = "/jsp/instructorCourseEdit.jsp";
        public static final String INSTRUCTOR_COURSE_DETAILS = "/jsp/instructorCourseDetails.jsp";
        public static final String INSTRUCTOR_COURSE_STUDENT_DETAILS = "/jsp/instructorCourseStudentDetails.jsp";
        public static final String INSTRUCTOR_COURSE_STUDENT_EDIT = "/jsp/instructorCourseStudentEdit.jsp";
        public static final String INSTRUCTOR_COURSE_ENROLL = "/jsp/instructorCourseEnroll.jsp";
        public static final String INSTRUCTOR_COURSE_ENROLL_RESULT = "/jsp/instructorCourseEnrollResult.jsp";
        public static final String INSTRUCTOR_COURSE_JOIN_CONFIRMATION = "/jsp/instructorCourseJoinConfirmation.jsp";
        public static final String INSTRUCTOR_FEEDBACK_SESSIONS = "/jsp/instructorFeedbacks.jsp";
        public static final String INSTRUCTOR_FEEDBACK_COPY_MODAL = "/jsp/instructorFeedbackCopyModal.jsp";
        public static final String INSTRUCTOR_FEEDBACK_AJAX_REMIND_PARTICULAR_STUDENTS_MODAL =
                "/jsp/instructorFeedbackAjaxRemindParticularStudentsModal.jsp";
        public static final String INSTRUCTOR_FEEDBACK_EDIT = "/jsp/instructorFeedbackEdit.jsp";
        public static final String INSTRUCTOR_FEEDBACK_RESULTS_TOP = "/jsp/instructorFeedbackResultsTop.jsp";
        public static final String INSTRUCTOR_FEEDBACK_RESULTS_BOTTOM = "/jsp/instructorFeedbackResultsBottom.jsp";
        public static final String INSTRUCTOR_FEEDBACK_RESULTS_BY_GIVER_RECIPIENT_QUESTION =
                "/jsp/instructorFeedbackResultsByGiverRecipientQuestion.jsp";
        public static final String INSTRUCTOR_FEEDBACK_RESULTS_BY_RECIPIENT_GIVER_QUESTION =
                "/jsp/instructorFeedbackResultsByRecipientGiverQuestion.jsp";
        public static final String INSTRUCTOR_FEEDBACK_RESULTS_BY_GIVER_QUESTION_RECIPIENT =
                "/jsp/instructorFeedbackResultsByGiverQuestionRecipient.jsp";
        public static final String INSTRUCTOR_FEEDBACK_RESULTS_BY_RECIPIENT_QUESTION_GIVER =
                "/jsp/instructorFeedbackResultsByRecipientQuestionGiver.jsp";
        public static final String INSTRUCTOR_FEEDBACK_RESULTS_BY_QUESTION = "/jsp/instructorFeedbackResultsByQuestion.jsp";
        public static final String INSTRUCTOR_FEEDBACK_SUBMISSION_EDIT = "/jsp/instructorFeedbackSubmissionEdit.jsp";
        public static final String INSTRUCTOR_FEEDBACK_QUESTION_SUBMISSION_EDIT =
                "/jsp/instructorFeedbackQuestionSubmissionEdit.jsp";
        public static final String INSTRUCTOR_FEEDBACK_QUESTION_COPY_MODAL =
                "/jsp/instructorFeedbackQuestionCopyModal.jsp";
        public static final String INSTRUCTOR_SEARCH = "/jsp/instructorSearch.jsp";
        public static final String INSTRUCTOR_STUDENT_LIST = "/jsp/instructorStudentList.jsp";
        public static final String INSTRUCTOR_STUDENT_LIST_AJAX = "/jsp/instructorStudentListAjax.jsp";
        public static final String INSTRUCTOR_STUDENT_RECORDS = "/jsp/instructorStudentRecords.jsp";
        public static final String INSTRUCTOR_STUDENT_RECORDS_AJAX = "/jsp/instructorStudentRecordsAjax.jsp";

        public static final String STUDENT_HOME = "/jsp/studentHome.jsp";
        public static final String STUDENT_COURSE_JOIN_CONFIRMATION = "/jsp/studentCourseJoinConfirmation.jsp";
        public static final String STUDENT_COURSE_DETAILS = "/jsp/studentCourseDetails.jsp";
        public static final String STUDENT_FEEDBACK_SUBMISSION_EDIT = "/jsp/studentFeedbackSubmissionEdit.jsp";
        public static final String STUDENT_FEEDBACK_QUESTION_SUBMISSION_EDIT =
                "/jsp/studentFeedbackQuestionSubmissionEdit.jsp";
        public static final String STUDENT_FEEDBACK_RESULTS = "/jsp/studentFeedbackResults.jsp";
        public static final String STUDENT_PROFILE_PAGE = "/jsp/studentProfilePage.jsp";

        public static final String ADMIN_HOME = "/jsp/adminHome.jsp";
        public static final String ADMIN_ACCOUNT_MANAGEMENT = "/jsp/adminAccountManagement.jsp";
        public static final String ADMIN_SEARCH = "/jsp/adminSearch.jsp";
        public static final String ADMIN_EMAIL = "/jsp/adminEmail.jsp";
        public static final String ADMIN_ACTIVITY_LOG = "/jsp/adminActivityLog.jsp";
        public static final String ADMIN_ACTIVITY_LOG_AJAX = "/jsp/adminActivityLogAjax.jsp";
        public static final String ADMIN_ACCOUNT_DETAILS = "/jsp/adminAccountDetails.jsp";
        public static final String ADMIN_SESSIONS = "/jsp/adminSessions.jsp";
        public static final String ADMIN_EMAIL_LOG = "/jsp/adminEmailLog.jsp";
        public static final String ADMIN_EMAIL_LOG_AJAX = "/jsp/adminEmailLogAjax.jsp";

        public static final String GOOGLE_ACCOUNT_HINT = "/googleAccountHint.jsp";
        public static final String ENABLE_JS = "/enableJs.jsp";
        public static final String INVALID_ORIGIN = "/invalidOrigin.jsp";
        public static final String UNAUTHORIZED = "/unauthorized.jsp";
        public static final String ERROR_PAGE = "/errorPage.jsp";
        public static final String DEADLINE_EXCEEDED_ERROR_PAGE = "/deadlineExceededErrorPage.jsp";
        public static final String ENTITY_NOT_FOUND_PAGE = "/entityNotFoundPage.jsp";
        public static final String ACTION_NOT_FOUND_PAGE = "/pageNotFound.jsp";
        public static final String FEEDBACK_SESSION_NOT_VISIBLE = "/feedbackSessionNotVisible.jsp";

        public static final String JS_UNIT_TEST = "/test/allJsUnitTests.jsp";
        public static final String MASHUP = "/test/mashup.jsp";
        public static final String TABLE_SORT = "/test/tableSort.jsp";
        public static final String TIMEZONE = "/test/timezone.jsp";
    }

    /* These indicate status of an operation, but they are not shown to the user */
    public static class StatusCodes {

        // Backdoor responses
        public static final String BACKDOOR_STATUS_SUCCESS = "[BACKDOOR_STATUS_SUCCESS]";
        public static final String BACKDOOR_STATUS_FAILURE = "[BACKDOOR_STATUS_FAILURE]";

        // General Error codes
        public static final String ALREADY_JOINED = "ERRORCODE_ALREADY_JOINED";
        public static final String NULL_PARAMETER = "ERRORCODE_NULL_PARAMETER";
        public static final String INVALID_KEY = "ERRORCODE_INVALID_KEY";
        public static final String KEY_BELONGS_TO_DIFFERENT_USER = "ERRORCODE_KEY_BELONGS_TO_DIFFERENT_USER";

        // Error message used across DB level
        public static final String DBLEVEL_NULL_INPUT = "Supplied parameter was null";

        // POST parameter null message
        public static final String NULL_POST_PARAMETER = "The %s POST parameter is null%n";
    }

    public static class PlaceholderText {
        public static final String FEEDBACK_QUESTION = "A concise version of the question e.g. "
                + "&quot;How well did the team member communicate?&quot;";
        public static final String FEEDBACK_QUESTION_DESCRIPTION = "More details about the question e.g. &quot;In answering "
                + "the question, do consider communications made informally within the team, and formal communications with "
                + "the instructors and tutors.&quot;";
    }

    /**
     * These are constants that may be used in {@link ActivityLogEntry}.
     */
    public static class ActivityLog {
        public static final String TEAMMATESLOG = "TEAMMATESLOG";

        public static final String UNKNOWN = "Unknown";

        public static final String ROLE_ADMIN = "Admin";
        public static final String ROLE_INSTRUCTOR = "Instructor";
        public static final String ROLE_STUDENT = "Student";
        public static final String ROLE_AUTO = "Auto";
        public static final String ROLE_UNREGISTERED = "Unregistered";
        public static final String ROLE_MASQUERADE_POSTFIX = "(M)";

        public static final String PREFIX_STUDENT_PAGE = "student";
        public static final String PREFIX_INSTRUCTOR_PAGE = "instructor";
        public static final String PREFIX_AUTO_PAGE = "/auto";

        public static final String AUTH_NOT_LOGIN = "Unknown";

        public static final String FIELD_SEPARATOR = "|||";
        public static final String FIELD_CONNECTOR = "%";

        public static final String TIME_FORMAT_LOGID = "yyyyMMddHHmmssSS";

        public static final String TESTING_DATA_EMAIL_POSTFIX = ".tmt";

        public static final int TIME_TAKEN_EXPECTED = 10000;
        public static final int TIME_TAKEN_MODERATE = 20000;

        public static final String MESSAGE_ERROR_ACTION_NAME = "Error when getting ActionName for requestUrl : %1$s";
        public static final String MESSAGE_ERROR_LOG_MESSAGE_FORMAT = "Log message format not as expected: %1$s";
    }

    /**
     * These are constants that may be used in {@link EmailLogEntry}.
     */
    public static class EmailLog {
        public static final String TEAMMATES_EMAIL_LOG = "TEAMMATESEMAILLOG";
        public static final String FIELD_SEPARATOR = "|||";

        public static final String TEST_DATA_POSTFIX = ".tmt";

        public static final String ERROR_LOG_FORMAT = "Email log message is not in expected format."
                + " Raw log message: %1$s";
    }

}
