package models;

public class Department
{
    public String deptId;
    public String deptName;
    public String building;
    public String headOfDept;

    public Department(String deptId, String deptName, String building, String headOfDept)
    {
        this.deptId     = deptId;
        this.deptName   = deptName;
        this.building   = building;
        this.headOfDept = headOfDept;
    }

    @Override public String toString()
    {
        return deptName + " (" + deptId + ")";
    }
}
