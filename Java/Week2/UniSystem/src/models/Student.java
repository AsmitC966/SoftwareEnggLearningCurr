package models;

public class Student
{
    public String studentId, name, streamId, deptId;
    
    public int enrollmentYear;
    public Student(String input_line)
    {
        String[] p = input_line.split(",");
        studentId = p[0].trim(); name = p[1].trim(); streamId = p[2].trim();
        deptId = p[3].trim(); enrollmentYear = Integer.parseInt(p[4].trim());
    }
}