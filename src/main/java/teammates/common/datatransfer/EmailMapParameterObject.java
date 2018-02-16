package teammates.common.datatransfer;

import java.util.Map;

public class EmailMapParameterObject {
    private final Map<String, String> emailNameTable;
    private final Map<String, String> emailLastNameTable;
    private final Map<String, String> emailTeamNameTable;

    public EmailMapParameterObject(Map<String, String> emailNameTable, Map<String, String> emailLastNameTable, Map<String, String> emailTeamNameTable) {
        this.emailNameTable = emailNameTable;
        this.emailLastNameTable = emailLastNameTable;
        this.emailTeamNameTable = emailTeamNameTable;
    }

    public Map<String, String> getEmailNameTable() {
        return emailNameTable;
    }

    public Map<String, String> getEmailLastNameTable() {
        return emailLastNameTable;
    }

    public Map<String, String> getEmailTeamNameTable() {
        return emailTeamNameTable;
    }
}
