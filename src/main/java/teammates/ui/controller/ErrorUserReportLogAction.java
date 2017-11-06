package teammates.ui.controller;

import teammates.common.util.*;
import teammates.ui.pagedata.PageData;

public class ErrorUserReportLogAction extends Action {

    private static final Logger log = Logger.getLogger();
    private String emailSubject;
    private String emailContent;
    private String requestedUrl;

    @Override
    protected ActionResult execute() {
        emailContent = getRequestParamValue(ParamNameConst.ParamsNames.ERROR_FEEDBACK_EMAIL_CONTENT);
        Assumption.assertPostParamNotNull(ParamNameConst.ParamsNames.ERROR_FEEDBACK_EMAIL_CONTENT, emailContent);
        emailSubject = getRequestParamValue(ParamNameConst.ParamsNames.ERROR_FEEDBACK_EMAIL_SUBJECT);
        Assumption.assertPostParamNotNull(ParamNameConst.ParamsNames.ERROR_FEEDBACK_EMAIL_SUBJECT, emailSubject);
        requestedUrl = getRequestParamValue(ParamNameConst.ParamsNames.ERROR_FEEDBACK_URL_REQUESTED);
        log.severe(getUserErrorReportLogMessage());
        PageData data = new PageData(account, sessionToken);
        statusToUser.add(new StatusMessage(StatusMessageConst.StatusMessages.ERROR_FEEDBACK_SUBMIT_SUCCESS,
                StatusMessageColor.SUCCESS));
        return createAjaxResult(data);
    }

    /**
     * Returns the formatted log message with {@code emailSubject} & {@code emailContent}.
     */
    public String getUserErrorReportLogMessage() {
        return "====== USER FEEDBACK ABOUT ERROR ====== \n"
                + "REQUESTED URL: " + requestedUrl + "\n"
                + "ACCOUNT DETAILS: " + account.toString() + "\n"
                + "SUBJECT: " + emailSubject + "\n"
                + "FEEDBACK: " + emailContent;
    }

}
