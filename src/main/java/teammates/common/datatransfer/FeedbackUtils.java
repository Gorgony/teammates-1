package teammates.common.datatransfer;

import teammates.common.datatransfer.attributes.FeedbackQuestionAttributes;
import teammates.common.datatransfer.attributes.FeedbackResponseAttributes;
import teammates.common.datatransfer.attributes.InstructorAttributes;
import teammates.common.datatransfer.attributes.StudentAttributes;
import teammates.common.util.Const;
import teammates.common.util.Logger;

import java.util.*;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

/**
 * Created by njvan on 07-NOV-17.
 */
public class FeedbackUtils {

    public static boolean isFeedbackParticipantVisible(boolean isGiver, FeedbackResponseAttributes response,
                                                       Map<String, FeedbackQuestionAttributes> questions, Map<String, boolean[]> visibilityTable) {
        FeedbackQuestionAttributes question = questions.get(response.feedbackQuestionId);
        FeedbackParticipantType participantType;
        String responseId = response.getId();

        boolean isVisible;
        if (isGiver) {
            isVisible = visibilityTable.get(responseId)[Const.VISIBILITY_TABLE_GIVER];
            participantType = question.giverType;
        } else {
            isVisible = visibilityTable.get(responseId)[Const.VISIBILITY_TABLE_RECIPIENT];
            participantType = question.recipientType;
        }
        boolean isTypeSelf = participantType == FeedbackParticipantType.SELF;
        boolean isTypeNone = participantType == FeedbackParticipantType.NONE;

        return isVisible || isTypeSelf || isTypeNone;
    }

    /**
     * Returns true if the recipient from a response is visible to the current user.
     * Returns false otherwise.
     */
    public static boolean isRecipientVisible(FeedbackResponseAttributes response, Map<String, FeedbackQuestionAttributes> questions, Map<String, boolean[]> visibilityTable) {
        return isFeedbackParticipantVisible(false, response, questions, visibilityTable);
    }

    /**
     * Returns true if the giver from a response is visible to the current user.
     * Returns false otherwise.
     */
    public static boolean isGiverVisible(FeedbackResponseAttributes response, Map<String, FeedbackQuestionAttributes> questions, Map<String, boolean[]> visibilityTable) {
        return isFeedbackParticipantVisible(true, response, questions, visibilityTable);
    }

    /**
     * Returns a list of student emails, sorted by section name.
     */
    public static List<String> getSortedListOfStudentEmails(CourseRoster roster) {
        List<String> emailList = new ArrayList<>();
        List<StudentAttributes> students = roster.getStudents();
        StudentAttributes.sortBySectionName(students);
        for (StudentAttributes student : students) {
            emailList.add(student.email);
        }
        return emailList;
    }

    /**
     * Get a sorted list of teams for the feedback session.<br>
     * Instructors are not present as a team.
     */
    public static List<String> getSortedListOfTeams(Map<String, Set<String>> rosterTeamNameMembersTable) {
        List<String> teams = new ArrayList<>(rosterTeamNameMembersTable.keySet());
        teams.remove(Const.USER_TEAM_FOR_INSTRUCTOR);
        Collections.sort(teams);
        return teams;
    }

    /**
     * Returns a list of instructor emails, sorted alphabetically.
     */
    public static List<String> getSortedListOfInstructorEmails(CourseRoster roster) {
        List<String> emailList = new ArrayList<>();
        List<InstructorAttributes> instructors = roster.getInstructors();
        for (InstructorAttributes instructor : instructors) {
            emailList.add(instructor.email);
        }
        Collections.sort(emailList);
        return emailList;
    }

    public static List<String> getPossibleGivers(FeedbackQuestionAttributes fqa, CourseRoster roster, Map<String, Set<String>> rosterTeamNameMembersTable) {
        FeedbackParticipantType giverType = fqa.giverType;
        List<String> possibleGivers = new ArrayList<>();

        switch (giverType) {
            case STUDENTS:
                possibleGivers = getSortedListOfStudentEmails(roster);
                break;
            case INSTRUCTORS:
                possibleGivers = getSortedListOfInstructorEmails(roster);
                break;
            case TEAMS:
                possibleGivers = getSortedListOfTeams(rosterTeamNameMembersTable);
                break;
            case SELF:
                possibleGivers = new ArrayList<>();
                possibleGivers.add(fqa.creatorEmail);
                break;
            default:
                Logger.getLogger().severe("Invalid giver type specified");
                break;
        }

        return possibleGivers;
    }

    /**
     * Get a sorted list of team members, who are in the same team as the student,
     * EXCLUDING the student.
     *
     * @return a list of team members, excluding the original student
     * @see #getSortedListOfTeamMembersEmails
     */
    public static List<String> getSortedListOfTeamMembersEmailsExcludingSelf(StudentAttributes student, Map<String, Set<String>> rosterTeamNameMembersTable) {
        List<String> teamMembers = getSortedListOfTeamMembersEmails(student, rosterTeamNameMembersTable);
        String currentStudentEmail = student.email;
        teamMembers.remove(currentStudentEmail);
        return teamMembers;
    }

    /**
     * Get a sorted list of team members, who are in the same team as the student.<br>
     * This list includes the student.
     *
     * @return a list of team members, including the original student
     * @see #getSortedListOfTeamMembersEmailsExcludingSelf
     */
    public static List<String> getSortedListOfTeamMembersEmails(StudentAttributes student, Map<String, Set<String>> rosterTeamNameMembersTable) {
        String teamName = student.team;
        Set<String> teamMembersEmailsToNames = rosterTeamNameMembersTable.get(teamName);
        List<String> teamMembers = new ArrayList<>(teamMembersEmailsToNames);
        Collections.sort(teamMembers);
        return teamMembers;
    }
}
