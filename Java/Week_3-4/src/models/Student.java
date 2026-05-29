package models;

public class Student
{
    private final String studentID;
    private final String name;
    private final String streamID;
    private final String deptID;
    private final int enrollmentYear;

    public Student(String studentID, String name, String streamID, String deptID, int enrollmentYear) {
        this.studentID = studentID;
        this.name = name;
        this.streamID = streamID;
        this.deptID = deptID;
        this.enrollmentYear = enrollmentYear;
    }

    public String getStudentID() {return studentID;}
    public String getName() {return name;}
    public String getStreamID() {return streamID;}
    public String getDeptID() {return deptID;}
    public int getEnrollmentYear() {return enrollmentYear;}

}
