package models;


public class Subject
{
    public String subjectId, subjectName, deptId;
    public int credits;
    
    public Subject(String input_line)
    {
        String[] p = input_line.split(",");
        subjectId = p[0].trim(); subjectName = p[1].trim(); deptId = p[2].trim();
        credits = p.length > 3 ? Integer.parseInt(p[3].trim()) : 0;
    }
}
