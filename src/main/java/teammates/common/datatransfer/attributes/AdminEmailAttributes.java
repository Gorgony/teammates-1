package teammates.common.datatransfer.attributes;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.google.appengine.api.datastore.Text;

import teammates.common.util.*;
import teammates.storage.entity.AdminEmail;

public class AdminEmailAttributes extends EntityAttributes<AdminEmail> {
    // Required fields
    public List<String> addressReceiver;
    public List<String> groupReceiver;
    public String subject;
    public Text content;

    // Optional fields
    public Date sendDate;
    public Date createDate;
    public String emailId;
    public boolean isInTrashBin;

    AdminEmailAttributes() {
        createDate = Const.TIME_REPRESENTS_DEFAULT_TIMESTAMP;
        emailId = ParamNameConst.ParamsNames.ADMIN_EMAIL_ID;
    }

    /**
     * Creates a new AdminEmailAttributes with default values for optional fields.
     *
     * <p>Following default values are set to corresponding attributes:
     * <ul>
     * <li>{@code null} for {@code sendDate}</li>
     * <li>{@code Const.TIME_REPRESENTS_DEFAULT_TIMESTAMP} for {@code createDate}</li>
     * <li>{@code Const.ParamsNames.ADMIN_EMAIL_ID} for {@code emailId}</li>
     * <li>{@code false} for {@code isInTrashBin}</li>
     * </ul>
     */
    public static Builder builder(String subject, List<String> addressReceiver, List<String> groupReceiver, Text content) {
        return new Builder(subject, addressReceiver, groupReceiver, content);
    }

    public static AdminEmailAttributes valueOf(AdminEmail adminEmail) {
        return new Builder(adminEmail.getSubject(), adminEmail.getAddressReceiver(),
                        adminEmail.getGroupReceiver(), adminEmail.getContent())
                .withSendDate(adminEmail.getSendDate())
                .withCreateDate(adminEmail.getCreateDate())
                .withEmailId(adminEmail.getEmailId())
                .withIsInTrashBin(adminEmail.getIsInTrashBin())
                .build();
    }

    @Override
    public List<String> getInvalidityInfo() {

        FieldValidator validator = new FieldValidator();
        List<String> errors = new ArrayList<>();

        addNonEmptyError(validator.getInvalidityInfoForEmailContent(content), errors);

        addNonEmptyError(validator.getInvalidityInfoForEmailSubject(subject), errors);

        return errors;
    }

    @Override
    public AdminEmail toEntity() {
        return new AdminEmail(addressReceiver, groupReceiver, subject, content, sendDate);
    }

    @Override
    public String getIdentificationString() {
        return this.sendDate + "/" + this.subject;
    }

    @Override
    public String getEntityTypeAsString() {
        return "Admin Email";
    }

    @Override
    public String getBackupIdentifier() {
        return "Admin Email";
    }

    @Override
    public String getJsonString() {
        return JsonUtils.toJson(this, AdminEmail.class);
    }

    @Override
    public void sanitizeForSaving() {
        this.subject = SanitizationHelper.sanitizeTextField(subject);
        this.content = SanitizationHelper.sanitizeForRichText(content);
    }

    public String getEmailId() {
        return this.emailId;
    }

    public List<String> getAddressReceiver() {
        return this.addressReceiver;
    }

    public List<String> getGroupReceiver() {
        return this.groupReceiver;
    }

    public String getSubject() {
        return this.subject;
    }

    public Date getSendDate() {
        return this.sendDate;
    }

    public Date getCreateDate() {
        return this.createDate;
    }

    public String getContentValue() {
        return this.content.getValue();
    }

    public boolean getIsInTrashBin() {
        return this.isInTrashBin;
    }

    public String getSendDateForDisplay() {
        if (this.sendDate == null) {
            return "Draft";
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(this.sendDate);
        cal = TimeHelper.convertToUserTimeZone(cal, SystemParamsConst.SystemParams.ADMIN_TIME_ZONE_DOUBLE);

        return TimeHelper.formatTime12H(cal.getTime());
    }

    public String getCreateDateForDisplay() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(this.createDate);
        cal = TimeHelper.convertToUserTimeZone(cal, SystemParamsConst.SystemParams.ADMIN_TIME_ZONE_DOUBLE);

        return TimeHelper.formatTime12H(cal.getTime());
    }

    public String getFirstAddressReceiver() {
        return this.addressReceiver.get(0);
    }

    public String getFirstGroupReceiver() {
        return getGroupReceiver().get(0);
    }

    public static class Builder {
        private final AdminEmailAttributes adminEmailAttributes;

        public Builder(String subject, List<String> addressReceiver, List<String> groupReceiver, Text content) {

            Assumption.assertNotNull(Const.StatusCodes.NULL_PARAMETER, subject);
            Assumption.assertNotNull(Const.StatusCodes.NULL_PARAMETER, addressReceiver);
            Assumption.assertNotNull(Const.StatusCodes.NULL_PARAMETER, groupReceiver);
            Assumption.assertNotNull(Const.StatusCodes.NULL_PARAMETER, content);

            adminEmailAttributes = new AdminEmailAttributes();
            adminEmailAttributes.addressReceiver = addressReceiver;
            adminEmailAttributes.groupReceiver = groupReceiver;
            adminEmailAttributes.subject = subject;
            adminEmailAttributes.content = content;
        }

        public Builder withSendDate(Date sendDate) {
            if (sendDate != null) {
                adminEmailAttributes.sendDate = sendDate;
            }
            return this;
        }

        public Builder withCreateDate(Date createDate) {
            if (createDate != null) {
                adminEmailAttributes.createDate = createDate;
            }
            return this;
        }

        public Builder withEmailId(String emailId) {
            if (emailId != null) {
                adminEmailAttributes.emailId = emailId;
            }
            return this;
        }

        public Builder withIsInTrashBin(boolean isInTrashBin) {
            adminEmailAttributes.isInTrashBin = isInTrashBin;
            return this;
        }

        public AdminEmailAttributes build() {
            return adminEmailAttributes;
        }
    }
}
