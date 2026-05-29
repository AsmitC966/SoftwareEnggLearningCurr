package models;

public class Mark
{
    private final String studentID;
    private final String subjectID;
    private final int semester;
    private final double marksObtained;
    private final double maxMarks;

    public Mark(String studentID, String subjectID, int semester, double marksObtained, double maxMarks)
    {
        this.studentID = studentID;
        this.subjectID = subjectID;
        this.semester = semester;
        this.marksObtained = marksObtained;
        this.maxMarks = maxMarks;
    }

    public String getStudentID(){return studentID;}
    public String getSubjectID(){return subjectID;}
    public int getSemester(){return semester;}
    public double getMarksObtained(){return marksObtained;}
    public double getMaxMarks(){return maxMarks;}

    double getPercentage()
    {return (marksObtained / maxMarks) * 100.0;}
}
