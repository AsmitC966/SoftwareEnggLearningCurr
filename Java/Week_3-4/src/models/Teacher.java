package models;

public class Teacher
{
    private final String teacherID;
    private final String name;
    private final String deptID;
    private final String specialization;

    public Teacher(String teacherID, String name, String deptID, String specialization)
    {
        this.teacherID = teacherID;
        this.name = name;
        this.deptID = deptID;
        this.specialization = specialization;
    }

    public String getTeacherID() { return teacherID; }
    public String getName() { return name; }
    public String getDeptID() { return deptID; }
    public String getSpecialization() { return specialization; }
}