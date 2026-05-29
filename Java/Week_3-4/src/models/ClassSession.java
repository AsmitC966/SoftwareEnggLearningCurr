package models;

public class ClassSession
{
    private final String sessionID;
    private final String subjectID;
    private final String teacherID;
    private final String dayOfWeek;
    private final String startTime;
    private final String endTime;
    private final String room;

    public ClassSession(String sessionID, String subjectID, String teacherID, String dayOfWeek, String startTime, String endTime, String room)
    {
        this.sessionID = sessionID;
        this.subjectID = subjectID;
        this.teacherID = teacherID;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
        this.room = room;
    }

    public String getSessionID() { return sessionID; }
    public String getSubjectID() { return subjectID; }
    public String getTeacherID() { return teacherID; }
    public String getDayOfWeek() { return dayOfWeek; }
    public String getStartTime() { return startTime; }
    public String getEndTime() { return endTime; }
    public String getRoom() { return room; }
}