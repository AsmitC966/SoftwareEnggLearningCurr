package models;


public class Teacher
{
    public String teacherId, name, deptId;

    public Teacher(String input_line)
    {
        String[] p = input_line.split(",");
        teacherId = p[0].trim(); 
        name = p[1].trim(); 
        deptId = p[2].trim();
    }
}