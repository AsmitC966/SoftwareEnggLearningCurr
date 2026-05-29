package models;

public class Subject
{
    public String subjectId;
    public String subjectName;
    public String deptId;
    public int    credits;

    public Subject(String subjectId, String subjectName, String deptId, int credits)
    {
        this.subjectId   = subjectId;
        this.subjectName = subjectName;
        this.deptId      = deptId;
        this.credits     = credits;
    }

    @Override public String toString()
    {return subjectName + " [" + subjectId + "]";}
}
