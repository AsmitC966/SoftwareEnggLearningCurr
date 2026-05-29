package models;


public class ClassSession
{
    public String sessionId, subjectId, teacherId, dayOfWeek, startTime, endTime, room;


    public ClassSession(String input_line)
    {
        String[] p = input_line.split(",");
        sessionId = p[0].trim(); subjectId = p[1].trim(); teacherId = p[2].trim();
        dayOfWeek = p[3].trim(); startTime = p[4].trim(); endTime = p[5].trim();
        room = p.length > 6 ? p[6].trim() : "TBA";
    }
}