package teammates.ui.automated;

import teammates.common.exception.TeammatesException;
import teammates.common.util.Assumption;
import teammates.common.util.EmailWrapper;
import teammates.common.util.Logger;
import teammates.common.util.ParamNameConst;

/**
 * Task queue worker action: sends queued email.
 */
public class SendEmailWorkerAction extends AutomatedAction {

    private static final Logger log = Logger.getLogger();

    @Override
    protected String getActionDescription() {
        return null;
    }

    @Override
    protected String getActionMessage() {
        return null;
    }

    @Override
    public void execute() {
        String emailSubject = getRequestParamValue(ParamNameConst.ParamsNames.EMAIL_SUBJECT);
        Assumption.assertPostParamNotNull(ParamNameConst.ParamsNames.EMAIL_SUBJECT, emailSubject);

        String emailContent = getRequestParamValue(ParamNameConst.ParamsNames.EMAIL_CONTENT);
        Assumption.assertPostParamNotNull(ParamNameConst.ParamsNames.EMAIL_CONTENT, emailContent);

        String emailSenderEmail = getRequestParamValue(ParamNameConst.ParamsNames.EMAIL_SENDER);
        Assumption.assertPostParamNotNull(ParamNameConst.ParamsNames.EMAIL_SENDER, emailSenderEmail);

        String emailSenderName = getRequestParamValue(ParamNameConst.ParamsNames.EMAIL_SENDERNAME);

        String emailReceiver = getRequestParamValue(ParamNameConst.ParamsNames.EMAIL_RECEIVER);
        Assumption.assertPostParamNotNull(ParamNameConst.ParamsNames.EMAIL_RECEIVER, emailReceiver);

        String emailReply = getRequestParamValue(ParamNameConst.ParamsNames.EMAIL_REPLY_TO_ADDRESS);
        Assumption.assertPostParamNotNull(ParamNameConst.ParamsNames.EMAIL_REPLY_TO_ADDRESS, emailReply);

        EmailWrapper message = new EmailWrapper();
        message.setRecipient(emailReceiver);
        message.setSenderEmail(emailSenderEmail);
        if (emailSenderName != null) {
            message.setSenderName(emailSenderName);
        }
        message.setContent(emailContent);
        message.setSubject(emailSubject);
        message.setReplyTo(emailReply);

        try {
            emailSender.sendEmail(message);
        } catch (Exception e) {
            log.severe("Error while sending email via servlet: " + TeammatesException.toStringWithStackTrace(e));
            setForRetry();
        }
    }

}
