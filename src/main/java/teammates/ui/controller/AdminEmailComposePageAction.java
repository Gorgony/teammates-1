package teammates.ui.controller;

import teammates.common.util.*;
import teammates.ui.pagedata.AdminEmailComposePageData;

public class AdminEmailComposePageAction extends Action {

    @Override
    protected ActionResult execute() {

        gateKeeper.verifyAdminPrivileges(account);
        AdminEmailComposePageData data = new AdminEmailComposePageData(account, sessionToken);

        String idOfEmailToEdit = getRequestParamValue(ParamNameConst.ParamsNames.ADMIN_EMAIL_ID);

        boolean isEmailEdit = idOfEmailToEdit != null;

        if (isEmailEdit) {

            data.emailToEdit = logic.getAdminEmailById(idOfEmailToEdit);
            statusToAdmin =
                    data.emailToEdit == null
                    ? "adminEmailComposePage Page Load : " + StatusMessageConst.StatusMessages.EMAIL_NOT_FOUND
                    : "adminEmailComposePage Page Load : Edit Email "
                      + "[" + SanitizationHelper.sanitizeForHtml(data.emailToEdit.getSubject()) + "]";

            if (data.emailToEdit == null) {
                isError = true;
                statusToUser.add(new StatusMessage(StatusMessageConst.StatusMessages.EMAIL_NOT_FOUND, StatusMessageColor.WARNING));
            }

            return createShowPageResult(Const.ViewURIs.ADMIN_EMAIL, data);
        }
        statusToAdmin = "adminEmailComposePage Page Load";
        data.init();

        return createShowPageResult(Const.ViewURIs.ADMIN_EMAIL, data);
    }

}
