package teammates.common.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by njvan on 07-Nov-17.
 */
public class SystemParamsConst {
        public static class SystemParams {

            public static final String ENCODING = "UTF8";
            public static final int NUMBER_OF_HOURS_BEFORE_CLOSING_ALERT = 24;

            /**
             * This is the limit after which TEAMMATES will send error message.
             * Must be within the range of int
             */
            public static final int MAX_PROFILE_PIC_SIZE = 5000000;

            /**
             * This is the limit given to Blobstore API, beyond which an ugly error page is shown.
             */
            public static final long MAX_FILE_LIMIT_FOR_BLOBSTOREAPI = 11000000;

            /**
             * e.g. "2014-04-01 11:59 PM UTC"
             */
            public static final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd h:mm a Z";

            /**
             * Number to trim the Google ID when displaying to the user.
             */
            public static final int USER_ID_MAX_DISPLAY_LENGTH = 23;

            /* Field sizes and error messages for invalid fields can be found
             * in the FieldValidator class.
             */
            public static final String ADMIN_TIME_ZONE = "Asia/Singapore";
            public static final double ADMIN_TIME_ZONE_DOUBLE = 8.0;

            public static final TimeZone TIME_ZONE = TimeZone.getTimeZone("UTC");

            public static final String DEFAULT_PROFILE_PICTURE_PATH = "/images/profile_picture_default.png";

            public static final List<String> PAGES_REQUIRING_ORIGIN_VALIDATION = Collections.unmodifiableList(
                    Arrays.asList(
                            Const.ActionURIs.ADMIN_ACCOUNT_DELETE,
                            Const.ActionURIs.ADMIN_EMAIL_COMPOSE_SAVE,
                            Const.ActionURIs.ADMIN_EMAIL_COMPOSE_SEND,
                            Const.ActionURIs.ADMIN_EMAIL_CREATE_GROUP_RECEIVER_LIST_UPLOAD_URL,
                            Const.ActionURIs.ADMIN_EMAIL_CREATE_IMAGE_UPLOAD_URL,
                            Const.ActionURIs.ADMIN_EMAIL_GROUP_RECEIVER_LIST_UPLOAD,
                            Const.ActionURIs.ADMIN_EMAIL_IMAGE_UPLOAD,
                            Const.ActionURIs.ADMIN_EMAIL_MOVE_OUT_TRASH,
                            Const.ActionURIs.ADMIN_EMAIL_MOVE_TO_TRASH,
                            Const.ActionURIs.ADMIN_EMAIL_TRASH_DELETE,
                            Const.ActionURIs.ADMIN_INSTRUCTORACCOUNT_ADD,
                            Const.ActionURIs.ADMIN_STUDENT_GOOGLE_ID_RESET,
                            Const.ActionURIs.CREATE_IMAGE_UPLOAD_URL,
                            Const.ActionURIs.IMAGE_UPLOAD,
                            Const.ActionURIs.INSTRUCTOR_COURSE_ADD,
                            Const.ActionURIs.INSTRUCTOR_COURSE_ARCHIVE,
                            Const.ActionURIs.INSTRUCTOR_COURSE_DELETE,
                            Const.ActionURIs.INSTRUCTOR_COURSE_EDIT_SAVE,
                            Const.ActionURIs.INSTRUCTOR_COURSE_ENROLL_SAVE,
                            Const.ActionURIs.INSTRUCTOR_COURSE_INSTRUCTOR_ADD,
                            Const.ActionURIs.INSTRUCTOR_COURSE_INSTRUCTOR_DELETE,
                            Const.ActionURIs.INSTRUCTOR_COURSE_INSTRUCTOR_EDIT_SAVE,
                            Const.ActionURIs.INSTRUCTOR_COURSE_REMIND,
                            Const.ActionURIs.INSTRUCTOR_COURSE_STUDENT_DELETE,
                            Const.ActionURIs.INSTRUCTOR_COURSE_STUDENT_DELETE_ALL,
                            Const.ActionURIs.INSTRUCTOR_COURSE_STUDENT_DETAILS_EDIT_SAVE,
                            Const.ActionURIs.INSTRUCTOR_EDIT_INSTRUCTOR_FEEDBACK_SAVE,
                            Const.ActionURIs.INSTRUCTOR_EDIT_STUDENT_FEEDBACK_SAVE,
                            Const.ActionURIs.INSTRUCTOR_FEEDBACK_ADD,
                            Const.ActionURIs.INSTRUCTOR_FEEDBACK_COPY,
                            Const.ActionURIs.INSTRUCTOR_FEEDBACK_DELETE,
                            Const.ActionURIs.INSTRUCTOR_FEEDBACK_EDIT_COPY,
                            Const.ActionURIs.INSTRUCTOR_FEEDBACK_EDIT_SAVE,
                            Const.ActionURIs.INSTRUCTOR_FEEDBACK_PUBLISH,
                            Const.ActionURIs.INSTRUCTOR_FEEDBACK_QUESTION_ADD,
                            Const.ActionURIs.INSTRUCTOR_FEEDBACK_QUESTION_COPY,
                            Const.ActionURIs.INSTRUCTOR_FEEDBACK_QUESTION_EDIT,
                            Const.ActionURIs.INSTRUCTOR_FEEDBACK_REMIND,
                            Const.ActionURIs.INSTRUCTOR_FEEDBACK_REMIND_PARTICULAR_STUDENTS,
                            Const.ActionURIs.INSTRUCTOR_FEEDBACK_RESPONSE_COMMENT_ADD,
                            Const.ActionURIs.INSTRUCTOR_FEEDBACK_RESPONSE_COMMENT_DELETE,
                            Const.ActionURIs.INSTRUCTOR_FEEDBACK_RESPONSE_COMMENT_EDIT,
                            Const.ActionURIs.INSTRUCTOR_FEEDBACK_SUBMISSION_EDIT_SAVE,
                            Const.ActionURIs.INSTRUCTOR_FEEDBACK_UNPUBLISH,
                            Const.ActionURIs.STUDENT_FEEDBACK_SUBMISSION_EDIT_SAVE,
                            Const.ActionURIs.STUDENT_PROFILE_CREATEUPLOADFORMURL,
                            Const.ActionURIs.STUDENT_PROFILE_EDIT_SAVE,
                            Const.ActionURIs.STUDENT_PROFILE_PICTURE_EDIT,
                            Const.ActionURIs.STUDENT_PROFILE_PICTURE_UPLOAD));

            public static final List<String> PAGES_ACCESSIBLE_WITHOUT_GOOGLE_LOGIN = Collections.unmodifiableList(
                    Arrays.asList(
                            Const.ActionURIs.STUDENT_COURSE_JOIN,
                            Const.ActionURIs.STUDENT_COURSE_JOIN_NEW,
                            Const.ActionURIs.STUDENT_FEEDBACK_RESULTS_PAGE,
                            Const.ActionURIs.STUDENT_FEEDBACK_SUBMISSION_EDIT_PAGE,
                            Const.ActionURIs.STUDENT_FEEDBACK_SUBMISSION_EDIT_SAVE,
                            Const.ActionURIs.ERROR_FEEDBACK_SUBMIT));

            public static final List<String> PAGES_ACCESSIBLE_WITHOUT_REGISTRATION = Collections.unmodifiableList(
                    Arrays.asList(
                            Const.ActionURIs.STUDENT_COURSE_JOIN_AUTHENTICATED,
                            Const.ActionURIs.STUDENT_HOME_PAGE,
                            Const.ActionURIs.INSTRUCTOR_COURSE_JOIN,
                            Const.ActionURIs.INSTRUCTOR_COURSE_JOIN_AUTHENTICATED));

            public static final List<String> LEGACY_PAGES_WITH_REDUCED_SECURITY = Collections.unmodifiableList(
                    Arrays.asList(Const.ActionURIs.STUDENT_COURSE_JOIN));

            public static final String COURSE_BACKUP_LOG_MSG = "Recently modified course::";

        }
}
