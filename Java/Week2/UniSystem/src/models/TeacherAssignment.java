package models;

public class TeacherAssignment
{
    public String teacherId, subjectId;
    public int semester;

    public TeacherAssignment(String input_line)
    {
        String[] p = input_line.split(",");
        teacherId = p[0].trim(); subjectId = p[1].trim();
        semester = Integer.parseInt(p[2].trim());
    }
}