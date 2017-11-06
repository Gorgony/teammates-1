package teammates.ui.controller;

import teammates.common.datatransfer.attributes.StudentProfileAttributes;
import teammates.common.exception.EntityDoesNotExistException;
import teammates.common.exception.InvalidParametersException;
import teammates.common.util.*;

/**
 * Action: saves the new profile details given by a student.
 *         A purely Action based URI as it redirects back to
 *         StudentProfilePageAction once completed
 */
public class StudentProfileEditSaveAction extends Action {

    @Override
    protected ActionResult execute() throws EntityDoesNotExistException {
        try {
            account.studentProfile = extractProfileData();
            logic.updateStudentProfile(account.studentProfile);
            statusToUser.add(new StatusMessage(StatusMessageConst.StatusMessages.STUDENT_PROFILE_EDITED, StatusMessageColor.SUCCESS));
            statusToAdmin = "Student Profile for <span class=\"bold\">(" + account.googleId
                          + ")</span> edited.<br>"
                          + SanitizationHelper.sanitizeForHtmlTag(account.studentProfile.toString());
        } catch (InvalidParametersException ipe) {
            setStatusForException(ipe);
        }
        return createRedirectResult(Const.ActionURIs.STUDENT_PROFILE_PAGE);
    }

    private void validatePostParameters(StudentProfileAttributes studentProfile) {
        Assumption.assertPostParamNotNull(ParamNameConst.ParamsNames.STUDENT_SHORT_NAME, studentProfile.shortName);
        Assumption.assertPostParamNotNull(ParamNameConst.ParamsNames.STUDENT_EMAIL, studentProfile.email);
        Assumption.assertPostParamNotNull(ParamNameConst.ParamsNames.STUDENT_GENDER, studentProfile.gender);
        Assumption.assertPostParamNotNull(ParamNameConst.ParamsNames.STUDENT_NATIONALITY, studentProfile.nationality);
        Assumption.assertPostParamNotNull(ParamNameConst.ParamsNames.STUDENT_PROFILE_INSTITUTION, studentProfile.institute);
        Assumption.assertPostParamNotNull(ParamNameConst.ParamsNames.STUDENT_PROFILE_MOREINFO, studentProfile.moreInfo);
    }

    private StudentProfileAttributes extractProfileData() {
        StudentProfileAttributes editedProfile = StudentProfileAttributes.builder().build();

        editedProfile.googleId = account.googleId;
        editedProfile.shortName = getRequestParamValue(ParamNameConst.ParamsNames.STUDENT_SHORT_NAME);
        editedProfile.email = getRequestParamValue(ParamNameConst.ParamsNames.STUDENT_PROFILE_EMAIL);
        editedProfile.institute = getRequestParamValue(ParamNameConst.ParamsNames.STUDENT_PROFILE_INSTITUTION);
        editedProfile.nationality = getRequestParamValue(ParamNameConst.ParamsNames.STUDENT_NATIONALITY);
        if ("".equals(editedProfile.nationality)) {
            editedProfile.nationality = getRequestParamValue("existingNationality");
        }
        editedProfile.gender = getRequestParamValue(ParamNameConst.ParamsNames.STUDENT_GENDER);
        editedProfile.moreInfo = getRequestParamValue(ParamNameConst.ParamsNames.STUDENT_PROFILE_MOREINFO);
        editedProfile.pictureKey = "";

        preprocessParameters(editedProfile);
        validatePostParameters(editedProfile);

        return editedProfile;
    }

    private void preprocessParameters(StudentProfileAttributes studentProfile) {
        studentProfile.shortName = StringHelper.trimIfNotNull(studentProfile.shortName);
        studentProfile.email = StringHelper.trimIfNotNull(studentProfile.email);
        studentProfile.gender = StringHelper.trimIfNotNull(studentProfile.gender);
        studentProfile.nationality = StringHelper.trimIfNotNull(studentProfile.nationality);
        studentProfile.institute = StringHelper.trimIfNotNull(studentProfile.institute);
        studentProfile.moreInfo = StringHelper.trimIfNotNull(studentProfile.moreInfo);
    }

}
