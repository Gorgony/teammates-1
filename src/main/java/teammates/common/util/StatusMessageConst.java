package teammates.common.util;

/**
 * Created by njvan on 07-Nov-17.
 */
public class StatusMessageConst {
    /* These are status messages that may be shown to the user */
    public static class StatusMessages {

        public static final String IMAGE_TOO_LARGE = "The uploaded image was too large. ";
        public static final String FILE_NOT_A_PICTURE = "The file that you have uploaded is not a picture. ";
        public static final String NO_IMAGE_GIVEN = "Please specify a image to be uploaded.";
        public static final String EMAIL_NOT_FOUND = "The requested email was not found";
        public static final String DUPLICATE_EMAIL_INFO = "Same email address as the student in line";
        public static final String EMAIL_DRAFT_SAVED = "Email draft has been saved";

        public static final String NOT_A_RECEIVER_LIST_FILE =
                "The file that you have uploaded is not a receiver list file. ";
        public static final String NO_GROUP_RECEIVER_LIST_FILE_GIVEN = "Please specify a receiver list file to be uploaded.";

        public static final String STUDENT_FIRST_TIME =
                "<div class=\"align-left\">"
                        + "<div class=\"align-center text-color-red text-bold\">"
                        + "Ooops! Your Google account is not known to TEAMMATES"
                        + "</div>"
                        + "<br>"
                        + "To access a course on TEAMMATES, you need to wait until an instructor adds you to "
                        + "that course. As a confirmation, TEAMMATES will send you instructions on how to access "
                        + "that particular course."
                        + "<br><br>"
                        + "If you 'joined' the course in TEAMMATES using a Google account before, but cannot login "
                        + "anymore, these are the possible reasons:"
                        + "<ol>"
                        + "<li>"
                        + "You used a different Google account to access TEAMMATES in the past. "
                        + "In that case, you need to use the same Google account to access TEAMMATES again. "
                        + "Logout and re-login using the other Google account. "
                        + "If you don't remember which Google account you used previously, "
                        + "email us from the same email account to which you receive TEAMMATES emails."
                        + "</li>"
                        + "<li>"
                        + "You changed the primary email from a non-Gmail address to a Gmail address recently. "
                        + "In that case, <a href=\"/contact.jsp\">email us</a> "
                        + "so that we can reconfigure your account to use the new Gmail address."
                        + "</li>"
                        + "<li>"
                        + "You joined this course just a few seconds ago and your data "
                        + "may be still in the process of propagating through our servers. "
                        + "In that case, please click on the "
                        + "<a href=" + Const.ActionURIs.STUDENT_HOME_PAGE + ">Home</a> link above in a few minutes. "
                        + "</li>"
                        + "</ol>"
                        + "</div>";

        public static final String COURSE_ADDED =
                "The course has been added. Click <a href=\"${courseEnrollLink}\">here</a> to add students to the course "
                        + "or click <a href=\"${courseEditLink}\">here</a> to add other instructors.<br>"
                        + "If you don't see the course in the list below, please refresh the page after a few moments.";
        public static final String COURSE_EXISTS =
                "A course by the same ID already exists in the system, possibly created by another user. "
                        + "Please choose a different course ID";
        public static final String COURSE_EDITED = "The course has been edited.";
        public static final String COURSE_ARCHIVED =
                "The course %s has been archived. It will not appear in the home page any more.";
        // TODO: Let undo process to be in the Course page for now.
        // Should implement to be able to undo the archiving from the home page later.
        public static final String COURSE_ARCHIVED_FROM_HOMEPAGE =
                COURSE_ARCHIVED + " You can access archived courses from the 'Courses' tab.<br>"
                        + "Go there to undo the archiving and bring the course back to the home page.";
        public static final String COURSE_UNARCHIVED = "The course %s has been unarchived.";
        public static final String COURSE_DELETED = "The course %s has been deleted.";
        public static final String COURSE_EMPTY =
                "You have not created any courses yet. Use the form above to create a course.";
        public static final String COURSE_EMPTY_IN_INSTRUCTOR_FEEDBACKS =
                "You have not created any courses yet, or you have no active courses. Go <a href=\""
                        + Const.ActionURIs.INSTRUCTOR_COURSES_PAGE + "${user}\">here</a> to create or unarchive a course.";
        public static final String COURSE_REMINDER_SENT_TO = "An email has been sent to ";
        public static final String COURSE_REMINDERS_SENT = "Emails have been sent to unregistered students.";

        public static final String COURSE_ENROLL_POSSIBLE_DATA_LOSS = "There are existing feedback responses "
                + "for this course. Modifying records of enrolled students will result in some existing "
                + "responses from those modified students to be deleted. You may wish to download the data "
                + "before you make the changes.";
        public static final String COURSE_ENROLL_STUDENTS_ERROR = "Errors on %d student(s):";
        public static final String COURSE_ENROLL_STUDENTS_ADDED = "%d student(s) added:";
        public static final String COURSE_ENROLL_STUDENTS_MODIFIED = "%d student(s) modified:";
        public static final String COURSE_ENROLL_STUDENTS_UNMODIFIED = "%d student(s) updated with no changes:";
        public static final String COURSE_ENROLL_STUDENTS_NOT_IN_LIST = "%d student(s) remain unmodified:";
        public static final String COURSE_ENROLL_STUDENTS_UNKNOWN = "%d student(s) with unknown enrolment status:";

        public static final String TEAM_INVALID_SECTION_EDIT =
                "The team \"%s\" is in multiple sections. "
                        + "The team ID should be unique across the entire course "
                        + "and a team cannot be spread across multiple sections.<br>";
        public static final String SECTION_QUOTA_EXCEED =
                "You are trying enroll more than 100 students in section \"%s\". "
                        + "To avoid performance problems, please do not enroll more than 100 students in a single section.<br>";
        public static final String QUOTA_PER_ENROLLMENT_EXCEED =
                "You are trying to enroll more than 100 students. "
                        + "To avoid performance problems, please enroll no more than 100 students at a time.";

        public static final String COURSE_INSTRUCTOR_ADDED = "The instructor %s has been added successfully. "
                + "An email containing how to 'join' this course will be sent to %s in a few minutes.";
        public static final String COURSE_INSTRUCTOR_EXISTS =
                "An instructor with the same email address already exists in the course.";
        public static final String COURSE_INSTRUCTOR_EDITED = "The changes to the instructor %s has been updated.";
        public static final String COURSE_INSTRUCTOR_DELETED = "The instructor has been deleted from the course.";
        public static final String COURSE_INSTRUCTOR_DELETE_NOT_ALLOWED =
                "The instructor you are trying to delete is the last instructor in the course. "
                        + "Deleting the last instructor from the course is not allowed.";

        public static final String JOIN_COURSE_KEY_BELONGS_TO_DIFFERENT_USER =
                "The join link used belongs to a different user whose Google ID is "
                        + "%s (only part of the Google ID is shown to protect privacy). "
                        + "If that Google ID is owned by you, please logout and re-login "
                        + "using that Google account. If it doesn’t belong to you, please "
                        + "<a href=\"mailto:" + Config.SUPPORT_EMAIL + "?"
                        + "body=Your name:%%0AYour course:%%0AYour university:\">"
                        + "contact us</a> so that we can investigate.";
        public static final String JOIN_COURSE_GOOGLE_ID_BELONGS_TO_DIFFERENT_USER =
                "The Google ID %s belongs to an existing user in the course."
                        + "Please login again using a different Google account, and try to join the course again.";

        public static final String STUDENT_GOOGLEID_RESET = "The student's google id has been reset";
        public static final String STUDENT_GOOGLEID_RESET_FAIL =
                "An error occurred when trying to reset student's google id";

        public static final String STUDENT_EVENTUAL_CONSISTENCY =
                "If the student was created during the last few minutes, "
                        + "try again in a few more minutes as the student may still be being saved.";

        public static final String STUDENT_EDITED = "The student has been edited successfully.";
        public static final String STUDENT_EDITED_AND_EMAIL_SENT = STUDENT_EDITED
                + " A summary of the course has been sent to the new email.";
        public static final String STUDENT_NOT_FOUND_FOR_EDIT =
                "The student you tried to edit does not exist. " + STUDENT_EVENTUAL_CONSISTENCY;
        public static final String STUDENT_DELETED = "The student has been removed from the course";
        public static final String STUDENTS_DELETED = "All the students have been removed from the course";
        public static final String STUDENT_PROFILE_EDITED = "Your profile has been edited successfully";
        public static final String STUDENT_PROFILE_PICTURE_SAVED = "Your profile picture has been saved successfully";
        public static final String STUDENT_PROFILE_PIC_TOO_LARGE = "The uploaded profile picture was too large. "
                + "Please try again with a smaller picture.";
        public static final String STUDENT_PROFILE_PIC_SERVICE_DOWN = "We were unable to upload your picture at this time. "
                + "Please try again after some time";
        public static final String STUDENT_EMAIL_TAKEN_MESSAGE =
                "Trying to update to an email that is already used by: %s/%s";

        public static final String FEEDBACK_SESSION_ADDED =
                "The feedback session has been added. "
                        + "Click the \"Add New Question\" button below to begin adding questions for the feedback session.";
        public static final String FEEDBACK_SESSION_ADD_DB_INCONSISTENCY =
                "If you do not see existing feedback sessions in the list below, "
                        + "please refresh the page after a few moments";
        public static final String FEEDBACK_SESSION_COPIED =
                "The feedback session has been copied. Please modify settings/questions as necessary.";
        public static final String FEEDBACK_SESSION_COPY_NONESELECTED =
                "You have not selected any course to copy the feedback session to";
        public static final String FEEDBACK_SESSION_COPY_ALREADYEXISTS =
                "A feedback session with the name \"%s\" already exists in the following course(s): %s.";
        public static final String FEEDBACK_SESSION_EDITED = "The feedback session has been updated.";
        public static final String FEEDBACK_SESSION_END_TIME_EARLIER_THAN_START_TIME =
                "The end time for this feedback session cannot be earlier than the start time.";
        public static final String FEEDBACK_SESSION_DELETED = "The feedback session has been deleted.";
        public static final String FEEDBACK_SESSION_DELETED_NO_ACCESS =
                "The feedback session has been deleted and is no longer accessible.";
        public static final String FEEDBACK_SESSION_PUBLISHED =
                "The feedback session has been published. "
                        + "Please allow up to 1 hour for all the notification emails to be sent out.";
        public static final String FEEDBACK_SESSION_UNPUBLISHED = "The feedback session has been unpublished.";
        public static final String FEEDBACK_SESSION_REMINDERSSENT =
                "Reminder e-mails have been sent out to those students and instructors. "
                        + "Please allow up to 1 hour for all the notification emails to be sent out.";
        public static final String FEEDBACK_SESSION_REMINDERSSESSIONNOTOPEN =
                "The feedback session is not open for submissions. "
                        + "You cannot send reminders for a session that is not open.";
        public static final String FEEDBACK_SESSION_REMINDERSEMPTYRECIPIENT = "You have not selected any student to remind.";
        public static final String FEEDBACK_SESSION_EXISTS =
                "A feedback session by this name already exists under this course";
        public static final String FEEDBACK_SESSION_EMPTY =
                "You have not created any sessions yet. Use the form above to create a session.";

        public static final String FEEDBACK_QUESTION_ADDED = "The question has been added to this feedback session.";
        public static final String FEEDBACK_QUESTION_EDITED = "The changes to the question have been updated.";
        public static final String FEEDBACK_QUESTION_DELETED = "The question has been deleted.";
        public static final String FEEDBACK_QUESTION_EMPTY =
                "You have not created any questions for this feedback session yet. "
                        + "Click the button below to add a feedback question.";
        public static final String FEEDBACK_QUESTION_NUMBEROFENTITIESINVALID =
                "Please enter the maximum number of recipients each respondents should give feedback to.";
        public static final String FEEDBACK_QUESTION_TEXTINVALID =
                "Please enter a valid question. The question text cannot be empty.";

        public static final String FEEDBACK_RESPONSES_SAVED = "All responses submitted successfully!";
        public static final String FEEDBACK_RESPONSES_MISSING_RECIPIENT =
                "You did not specify a recipient for your response in question %s.";
        public static final String FEEDBACK_RESPONSES_WRONG_QUESTION_TYPE =
                "Incorrect question type for response in question %s.";
        public static final String FEEDBACK_RESPONSES_INVALID_ID = "You are modifying an invalid response in question %s";
        public static final String FEEDBACK_RESPONSES_MSQ_MIN_CHECK = "Minimum selectable choices for question %d is %d.";
        public static final String FEEDBACK_RESPONSES_MSQ_MAX_CHECK = "Maximum selectable choices for question %d is %d.";

        public static final String FEEDBACK_RESPONSE_COMMENT_EMPTY = "Comment cannot be empty";
        public static final String FEEDBACK_RESPONSE_INVALID_RECIPIENT =
                "Trying to update recipient to an invalid recipient for question %d.";

        public static final String FEEDBACK_SUBMISSIONS_NOT_OPEN =
                "<strong>The feedback session is currently not open for submissions.</strong> "
                        + "You can view the questions and any submitted responses for this feedback session "
                        + "but cannot submit new responses.";

        public static final String FEEDBACK_RESULTS_SOMETHINGNEW =
                "You have received feedback from others. Please see below.";
        public static final String FEEDBACK_RESULTS_NOTHINGNEW =
                "You have not received any new feedback but you may review your own submissions below.";
        public static final String FEEDBACK_RESULTS_SECTIONVIEWWARNING =
                "This session seems to have a large number of responses. "
                        + "It is recommended to view the results one question/section at a time. "
                        + "To view responses for a particular question, click on the question below. "
                        + "To view response for a particular section, click the 'Edit View' button above and choose a section.";
        public static final String FEEDBACK_RESULTS_QUESTIONVIEWWARNING =
                "This session seems to have a large number of responses. "
                        + "It is recommended to view the results for one question at a time. "
                        + "To view responses for a particular question, click on the question below.";
        public static final String ENROLL_LINE_EMPTY = "Please input at least one student detail.";
        public static final String ENROLL_LINES_PROBLEM_DETAIL_PREFIX = "&bull;";
        public static final String ENROLL_LINES_PROBLEM =
                "<p><span class=\"bold\">Problem in line : <span class=\"invalidLine\">%s</span></span>"
                        + "<br><span class=\"problemDetail\">" + ENROLL_LINES_PROBLEM_DETAIL_PREFIX + " %s</span></p>";

        public static final String EVENTUAL_CONSISTENCY_MESSAGE_STUDENT =
                "You have successfully joined the course %1$s. "
                        + "<br>Updating of the course data on our servers is currently in progress "
                        + "and will be completed in a few minutes. "
                        + "<br>Please refresh this page in a few minutes to see the course %1$s in the list below.";

        public static final String NULL_POST_PARAMETER_MESSAGE =
                "You have been redirected to this page due to a possible expiry of the previous login."
                        + "<br>If you have previously typed some data and wish to retrieve it, "
                        + "you may use the 'Back' button of your Browser to navigate to the "
                        + "previous page containing the data you typed in.";

        public static final String INSTRUCTOR_STATUS_DELETED = "The Instructor status has been deleted";
        public static final String INSTRUCTOR_ACCOUNT_DELETED = "The Account has been deleted";
        public static final String INSTRUCTOR_REMOVED_FROM_COURSE = "The Instructor has been removed from the Course";

        public static final String INSTRUCTOR_COURSE_EMPTY =
                "There are no students in this course. Click <a href=\"%s\">here</a> to enroll students.";
        public static final String INSTRUCTOR_PERSISTENCE_ISSUE =
                "Account creation is still in progress. Please reload the page"
                        + " after sometime.";
        public static final String INSTRUCTOR_NO_MODIFY_PERMISSION_FOR_ACTIVE_COURSES_SESSIONS =
                "No permission to modify any sessions in un-archived courses";
        public static final String INSTRUCTOR_NO_ACTIVE_COURSES = "No un-archived courses";
        public static final String INSTRUCTOR_NO_COURSE_AND_STUDENTS =
                "There are no course or students information to be displayed";
        public static final String INSTRUCTOR_NO_STUDENT_RECORDS = "No records were found for this student";
        public static final String INSTRUCTOR_SEARCH_NO_RESULTS = "No results found.";
        public static final String INSTRUCTOR_SEARCH_TIPS =
                "Search Tips:<br>"
                        + "<ul>"
                        + "<li>Put more keywords to search for more precise results.</li>"
                        + "<li>Put quotation marks around words <b>\"[any word]\"</b>"
                        + " to search for an exact phrase in an exact order.</li>"
                        + "</ul>";

        public static final String HINT_FOR_NEW_INSTRUCTOR = "New to TEAMMATES? You may wish to have a look at our "
                + "<a href=\"/instructorHelp.jsp#gs\" target=\"_blank\">Getting Started Guide</a>.<br>A video tour"
                + " is also available in our <a href=\"/\" target=\"_blank\">home page</a>.";

        public static final String NEW_INSTRUCTOR_TEXT_MESSAGE = "New to TEAMMATES? You may wish to have a look at our "
                + "Getting Started Guide.\n"
                + "A video tour is also available in our home page.";

        public static final String HINT_FOR_NO_SESSIONS_STUDENT =
                "Currently, there are no open feedback sessions in the course %s. "
                        + "When a session is open for submission you will be notified.";
        public static final String HREF_END = "\">here</a>.";
        public static final String STUDENT_UPDATE_PROFILE =
                "Meanwhile, you can update your profile <a href=\"" + Const.ActionURIs.STUDENT_PROFILE_PAGE + HREF_END;
        public static final String HREF_START = "<a href=\"";
        public static final String STUDENT_UPDATE_PROFILE_SHORTNAME =
                "Meanwhile, you can provide a name that you would prefer to be called by "
                        + HREF_START + Const.ActionURIs.STUDENT_PROFILE_PAGE + HREF_END;
        public static final String STUDENT_UPDATE_PROFILE_EMAIL =
                "Meanwhile, you can provide an email for your instructors to contact you beyond graduation "
                        + HREF_START + Const.ActionURIs.STUDENT_PROFILE_PAGE + HREF_END;
        public static final String STUDENT_UPDATE_PROFILE_PICTURE =
                "Meanwhile, you can upload a profile picture "
                        + HREF_START + Const.ActionURIs.STUDENT_PROFILE_PAGE + HREF_END;
        public static final String STUDENT_UPDATE_PROFILE_MOREINFO =
                "Meanwhile, you can provide more information about yourself "
                        + HREF_START + Const.ActionURIs.STUDENT_PROFILE_PAGE + HREF_END;
        public static final String STUDENT_UPDATE_PROFILE_NATIONALITY =
                "Meanwhile, you can provide your nationality "
                        + HREF_START + Const.ActionURIs.STUDENT_PROFILE_PAGE + HREF_END;

        // Messages that are templates only
        /**
         * Template String. Parameters: Student's name, Course ID
         */
        public static final String STUDENT_COURSE_JOIN_SUCCESSFUL = "You have been successfully added to the course %s.";

        /**
         * Template String. Parameters:  Course ID
         */
        public static final String NON_EXISTENT_STUDENT_ATTEMPTING_TO_JOIN_COURSE =
                "Unable to join course %s as you are currently not in the student list of that course. "
                        + "Please contact your course instructor for assistance.";
        public static final String STUDENT_PROFILE_NOT_A_PICTURE = "The file that you have uploaded is not a picture. "
                + "Please upload a picture (usually it ends with .jpg or .png)";
        public static final String STUDENT_PROFILE_NO_PICTURE_GIVEN = "Please specify a file to be uploaded.";
        public static final String STUDENT_NOT_FOUND_FOR_RECORDS =
                "The student you tried to view records for does not exist. " + STUDENT_EVENTUAL_CONSISTENCY;
        public static final String STUDENT_NOT_FOUND_FOR_COURSE_DETAILS =
                "The student you tried to view details for does not exist. " + STUDENT_EVENTUAL_CONSISTENCY;
        public static final String STUDENT_PROFILE_PICTURE_EDIT_FAILED =
                "The photo that was edited did not belong to the user. "
                        + "Please upload another picture to begin editing";
        public static final String STUDENT_NOT_JOINED_YET_FOR_RECORDS =
                "Normally, we would show the student’s profile here. "
                        + "However, this student has not created a profile yet";
        public static final String STUDENT_PROFILE_UNACCESSIBLE_TO_INSTRUCTOR =
                "Normally, we would show the student’s profile here. "
                        + "However, you do not have access to view this student's profile";

        public static final String UNREGISTERED_STUDENT_MESSAGE = "You may submit feedback for sessions "
                + "that are currently open and view results without logging in. To access other features "
                + "you need <a href='%s' class='link'>to login using a Google account</a> (recommended).";
        public static final String UNREGISTERED_STUDENT = "You are submitting feedback as "
                + "<span class='text-danger text-bold text-large'>%s</span>. "
                + UNREGISTERED_STUDENT_MESSAGE;
        public static final String UNREGISTERED_STUDENT_RESULTS = "You are viewing feedback results as "
                + "<span class='text-danger text-bold text-large'>%s</span>. "
                + UNREGISTERED_STUDENT_MESSAGE;
        public static final String ADMIN_LOG_INSTRUCTOR_COURSE_ENROLL_PAGE_LOAD =
                "instructorCourseEnroll Page Load" + Const.HTML_BR_TAG
                        + "Enrollment for Course <span class=\"bold\">[%s]</span>";

        public static final String ERROR_FEEDBACK_SUBMIT_SUCCESS = "Your error report has been recorded. "
                + "We will follow up with you in due course, usually, within 24 hours.";
    }
}
