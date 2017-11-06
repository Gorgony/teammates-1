package teammates.common.util;

/**
 * Created by njvan on 07-Nov-17.
 */
public class ToolTipConst {
    /* Text displayed to the user when the mouse hover over certain elements in
         * the UI.
         */
    public static class Tooltips {

        public static final String COURSE_ENROLL = "Enroll student into the course";
        public static final String COURSE_DETAILS = "View, edit and send invitation emails to the students in the course";
        public static final String COURSE_EDIT = "Edit Course information and instructor list";
        public static final String COURSE_DELETE = "Delete the course and its corresponding students and sessions";
        public static final String COURSE_ARCHIVE =
                "Archive the course so that it will not be shown in the home page any more "
                        + "(you can still access it from the 'Courses' tab)";
        public static final String COURSE_ADD_FEEDBACKSESSION = "Add a feedback session for the course";
        public static final String CLAIMED = "This is the student's own estimation of his/her contributions";
        public static final String PERCEIVED =
                "This is the average of what other team members think this student contributed";

        public static final String COURSE_INFO_EDIT = "Edit course name";
        public static final String COURSE_INSTRUCTOR_EDIT = "Edit instructor details";
        public static final String COURSE_INSTRUCTOR_CANCEL_EDIT = "Cancel editing instructor details";
        public static final String COURSE_INSTRUCTOR_DELETE = "Delete the instructor from the course";
        public static final String COURSE_INSTRUCTOR_REMIND = "Send invitation email to the instructor";

        public static final String COURSE_STUDENT_DETAILS = "View the details of the student";
        public static final String COURSE_STUDENT_EDIT =
                "Use this to edit the details of this student. <br>To edit multiple students"
                        + " in one go, you can use the enroll page: <br>"
                        + "Simply enroll students using the updated data and existing data will be updated accordingly";
        public static final String COURSE_STUDENT_REMIND =
                "Email an invitation to the student requesting him/her to join the course using his/her "
                        + "Google Account. Note: Students can use TEAMMATES without ‘joining’, "
                        + "but a joined student can access extra features e.g. set up a user profile";
        public static final String COURSE_STUDENT_DELETE =
                "Delete the student and the corresponding submissions from the course";
        public static final String COURSE_STUDENT_RECORDS = "View all data about this student";

        public static final String COURSE_REMIND =
                "Email an invitation to all students yet to join requesting them to join the course "
                        + "using their Google Accounts. Note: Students can use TEAMMATES without ‘joining’, "
                        + "but a joined student can access extra features e.g. set up a user profile";

        public static final String EVALUEE_DESCRIPTION = "The party being evaluated or given feedback to";
        public static final String INSTRUCTOR_DISPLAYED_TO_STUDENT =
                "If this is unselected, the instructor will be completely invisible to students."
                        + " E.g. to give access to a colleague for ‘auditing’ your course";

        public static final String INSTRUCTOR_DISPLAYED_AS =
                "Specify the role of this instructor in this course as shown to the students";

        public static final String STUDENT_COURSE_DETAILS = "View and edit information regarding your team";

        public static final String STUDENT_FEEDBACK_SESSION_STATUS_AWAITING =
                "The session is not open for submission at this time. It is expected to open later.";
        public static final String STUDENT_FEEDBACK_SESSION_STATUS_PENDING =
                "The feedback session is yet to be completed by you.";
        public static final String STUDENT_FEEDBACK_SESSION_STATUS_SUBMITTED =
                "You have submitted your feedback for this session.";
        public static final String STUDENT_FEEDBACK_SESSION_STATUS_CLOSED =
                "<br>The session is now closed for submissions.";
        public static final String STUDENT_FEEDBACK_SESSION_STATUS_PUBLISHED =
                "The responses for the session have been published and can now be viewed.";
        public static final String STUDENT_FEEDBACK_SESSION_STATUS_NOT_PUBLISHED =
                "The responses for the session have not yet been published and cannot be viewed.";
        public static final String STUDENT_FEEDBACK_SESSION_STATUS_NEVER_PUBLISHED =
                "The instructor has set the results for this feedback session to not be published.";

        public static final String FEEDBACK_CONTRIBUTION_DIFF = "Perceived Contribution - Claimed Contribution";
        public static final String FEEDBACK_CONTRIBUTION_POINTS_RECEIVED =
                "The list of points that this student received from others";

        public static final String FEEDBACK_CONTRIBUTION_NOT_AVAILABLE =
                "Not Available: There is no data for this or the data is not enough";
        public static final String FEEDBACK_CONTRIBUTION_NOT_SURE = "Not sure about the contribution";

        public static final String FEEDBACK_SESSION_COURSE =
                "Please select the course for which the feedback session is to be created.";
        public static final String FEEDBACK_SESSION_INPUT_NAME =
                "Enter the name of the feedback session e.g. Feedback Session 1.";
        public static final String FEEDBACK_SESSION_STARTDATE =
                "Please select the date and time for which users can start submitting responses for the feedback session.";
        public static final String FEEDBACK_SESSION_ENDDATE =
                "Please select the date and time after which the feedback session "
                        + "will no longer accept submissions from users.";
        public static final String FEEDBACK_SESSION_VISIBLEDATE =
                "Select this option to enter in a custom date and time for which "
                        + "the feedback session will become visible.<br>"
                        + "Note that you can make a session visible before it is open for submissions "
                        + "so that users can preview the questions.";
        public static final String FEEDBACK_SESSION_PUBLISHDATE =
                "Select this option to enter in a custom date and time for which</br>"
                        + "the responses for this feedback session will become visible.";
        public static final String FEEDBACK_SESSION_SESSIONVISIBLELABEL =
                "Please select when you want the questions for the feedback session to be visible to "
                        + "users who need to participate. "
                        + "Note that users cannot submit their responses until the submissions opening time set below.";
        public static final String FEEDBACK_SESSION_SESSIONVISIBLEATOPEN =
                "Select this option to have the feedback session become visible "
                        + "when it is open for submissions (as selected above).";
        public static final String FEEDBACK_SESSION_SESSIONVISIBLENEVER =
                "Select this option if you want the feedback session to be private. "
                        + "A private session is never visible to anyone. "
                        + "Private sessions can be used to record your own comments about others, for your own reference.";
        public static final String FEEDBACK_SESSION_RESULTSVISIBLELABEL =
                "Please select when the responses for the feedback session will be visible to the designated recipients."
                        + "<br>You can select the response visibility for each type of user and question later.";
        public static final String FEEDBACK_SESSION_RESULTSVISIBLECUSTOM =
                "Select this option to use a custom time for when the responses of the feedback session<br>"
                        + "will be visible to the designated recipients.";
        public static final String FEEDBACK_SESSION_RESULTSVISIBLEATVISIBLE =
                "Select this option to have the feedback responses be immediately visible<br>"
                        + "when the session becomes visible to users.";
        public static final String FEEDBACK_SESSION_RESULTSVISIBLELATER =
                "Select this option if you intend to manually publish the responses for this session later on.";
        public static final String FEEDBACK_SESSION_RESULTSVISIBLENEVER =
                "Select this option if you intend never to publish the responses.";
        public static final String FEEDBACK_SESSION_SENDOPENEMAIL =
                "Select this option to automatically send an email to students to notify them "
                        + "when the session is open for submission.";
        public static final String FEEDBACK_SESSION_SENDCLOSINGEMAIL =
                "Select this option to automatically send an email to students to remind them to submit "
                        + "24 hours before the end of the session.";
        public static final String FEEDBACK_SESSION_SENDPUBLISHEDEMAIL =
                "Select this option to automatically send an email to students to notify them "
                        + "when the session results is published.";
        public static final String FEEDBACK_SESSION_INSTRUCTIONS =
                "Enter instructions for this feedback session. e.g. Avoid comments which are too critical.<br> "
                        + "It will be displayed at the top of the page when users respond to the session.";
        public static final String FEEDBACK_SESSION_STATUS_PRIVATE = "This is a private session. Nobody can see it but you.";
        public static final String FEEDBACK_SESSION_STATUS_VISIBLE = ", is visible";
        public static final String FEEDBACK_SESSION_STATUS_AWAITING = ", and is waiting to open";
        public static final String FEEDBACK_SESSION_STATUS_OPEN = ", and is open for submissions";
        public static final String FEEDBACK_SESSION_STATUS_CLOSED = ", and has ended";
        public static final String FEEDBACK_SESSION_STATUS_PUBLISHED = "The responses for this session are visible.";
        public static final String FEEDBACK_SESSION_STATUS_NOT_PUBLISHED = "The responses for this session are not visible.";
        public static final String FEEDBACK_SESSION_PUBLISHED_STATUS_PRIVATE_SESSION =
                "This feedback session is not published as it is private and only visible to you.";
        public static final String FEEDBACK_SESSION_STATUS_NEVER_PUBLISHED =
                "The responses for this feedback session have been set to never get published.";

        public static final String FEEDBACK_SESSION_INPUT_TIMEZONE =
                "You should not need to change this as your timezone is auto-detected. <br><br>"
                        + "However, note that daylight saving is not taken into account i.e. if you are in UTC -8:00 and there is "
                        + "daylight saving, you should choose UTC -7:00 and its corresponding timings.";

        public static final String FEEDBACK_SESSION_INPUT_GRACEPERIOD =
                "Please select the amount of time that the system will continue accepting <br>"
                        + "submissions after the specified deadline.";

        public static final String FEEDBACK_SESSION_RESPONSE_RATE = "Number of students submitted / Class size";
        public static final String FEEDBACK_SESSION_RESULTS = "View the submitted responses for this feedback session";
        public static final String FEEDBACK_SESSION_EDIT = "Edit feedback session details";
        public static final String FEEDBACK_SESSION_COPY = "Copy feedback session details";
        public static final String FEEDBACK_SESSION_REMIND =
                "Send e-mails to remind students and instructors who have not submitted their feedbacks to do so";
        public static final String FEEDBACK_SESSION_DELETE = "Delete the feedback session";
        public static final String FEEDBACK_SESSION_SUBMIT = "Start submitting feedback";
        public static final String FEEDBACK_SESSION_PUBLISH = "Make session responses available for viewing";
        public static final String FEEDBACK_SESSION_UNPUBLISH = "Make responses no longer visible";
        public static final String FEEDBACK_SESSION_AWAITING = "This session is not yet opened";
        public static final String FEEDBACK_SESSION_EDIT_SUBMITTED_RESPONSE = "Edit submitted feedback";
        public static final String FEEDBACK_SESSION_VIEW_SUBMITTED_RESPONSE = "View submitted feedback";
        public static final String FEEDBACK_SESSION_RECIPIENT = "Who the feedback is about";
        public static final String FEEDBACK_SESSION_GIVER = "Who will give feedback";

        public static final String FEEDBACK_SESSION_EDIT_SAVE =
                "You can save your responses at any time and come back later to continue.";

        public static final String FEEDBACK_SESSION_MODERATE_FEEDBACK = "Edit the responses given by this student";

        public static final String FEEDBACK_PREVIEW_ASSTUDENT =
                "View how this session would look like to a student who is submitting feedback.<br>"
                        + "Preview is unavailable if the course has yet to have any student enrolled.";
        public static final String FEEDBACK_PREVIEW_ASINSTRUCTOR =
                "View how this session would look like to an instructor who is submitting feedback.<br>"
                        + "Only instructors with submit privileges are included in the list.";

        public static final String FEEDBACK_QUESTION_INPUT_INSTRUCTIONS =
                "Please enter the question for users to give feedback about. "
                        + "e.g. What is the biggest weakness of the presented product?";
        public static final String FEEDBACK_QUESTION_EDIT =
                "Edit the existing question. Do remember to save the changes before moving on to editing another question.";
        public static final String FEEDBACK_QUESTION_DISCARDCHANGES =
                "Discard any unsaved edits and revert back to original question.";
        public static final String FEEDBACK_QUESTION_CANCEL_NEW =
                "Cancel adding new question. No new question will be added to the feedback session.";
        public static final String FEEDBACK_QUESTION_INPUT_DESCRIPTION =
                "Please enter the description of the question.";
        public static final String FEEDBACK_QUESTION_CONSTSUMPOINTS_OPTION =
                "Respondents will have to distribute the total points specified here among the options, "
                        + "e.g. if you specify 100 points here and there are 3 options, "
                        + "respondents will have to distribute 100 points among 3 options.";
        public static final String FEEDBACK_QUESTION_CONSTSUMPOINTS_RECIPIENT =
                "Respondents will have to distribute the total points specified here among the recipients, "
                        + "e.g. if you specify 100 points here and there are 3 recipients, "
                        + "respondents will have to distribute 100 points among 3 recipients.";
        public static final String FEEDBACK_QUESTION_CONSTSUMPOINTSFOREACHOPTION =
                "The number of points to distribute will vary based on the number of options, "
                        + "e.g. if you specify 100 points here and there are 3 options, "
                        + "the total number of points to distribute among 3 options will be 300 (i.e. 100 x 3).";
        public static final String FEEDBACK_QUESTION_CONSTSUMPOINTSFOREACHRECIPIENT =
                "The number of points to distribute will vary based on the number of recipients, "
                        + "e.g. if you specify 100 points here and there are 3 recipients, "
                        + "the total number of points to distribute among 3 recipients will be 300 (i.e. 100 x 3).";
        public static final String FEEDBACK_QUESTION_NUMSCALE_MAX = "Maximum acceptable response value";
        public static final String FEEDBACK_QUESTION_NUMSCALE_STEP = "Value to be increased/decreased each step";
        public static final String FEEDBACK_QUESTION_NUMSCALE_MIN = "Minimum acceptable response value";
        public static final String FEEDBACK_QUESTION_RUBRIC_ASSIGN_WEIGHTS =
                "Assign weights to the columns for calculating statistics.";

        public static final String STUDENT_PROFILE_PICTURE = "Upload a profile picture";
        public static final String STUDENT_PROFILE_SHORTNAME = "This is the name you prefer to be called by";
        public static final String STUDENT_PROFILE_EMAIL = "This is a long term contact email";
        public static final String STUDENT_PROFILE_INSTITUTION = "This is the institution that you represent";
        public static final String STUDENT_PROFILE_NATIONALITY = "This is your nationality";
        public static final String STUDENT_PROFILE_MOREINFO = "You may specify miscellaneous info about yourself "
                + "e.g. links to home page, online CV, portfolio etc.";

        public static final String VISIBILITY_OPTIONS_RECIPIENT = "Control what feedback recipient(s) can view";
        public static final String VISIBILITY_OPTIONS_GIVER_TEAM_MEMBERS =
                "Control what team members of feedback giver can view";
        public static final String VISIBILITY_OPTIONS_RECIPIENT_TEAM_MEMBERS =
                "Control what team members of feedback recipients can view";
        public static final String VISIBILITY_OPTIONS_OTHER_STUDENTS = "Control what other students can view";
        public static final String VISIBILITY_OPTIONS_INSTRUCTORS = "Control what instructors can view";

        public static final String COMMENT_ADD = "Add comment";
        public static final String COMMENT_EDIT = "Edit this comment";
        public static final String COMMENT_DELETE = "Delete this comment";

        public static final String SEARCH_STUDENT = "Search for student's information, e.g. name, email";

        public static final String ACTION_NOT_ALLOWED = "You do not have the permissions to access this feature";
    }
}
