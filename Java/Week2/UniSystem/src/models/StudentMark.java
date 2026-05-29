package models;


public class StudentMark
{
    public String studentId, subjectId;
    public int semester, marksObtained, maxMarks;

    public StudentMark(String input_line)
    {
        String[] p = input_line.split(",");
        studentId = p[0].trim();
        subjectId = p[1].trim();
        semester = Integer.parseInt(p[2].trim());
        marksObtained = Integer.parseInt(p[3].trim());
        maxMarks = Integer.parseInt(p[4].trim());
    }
}