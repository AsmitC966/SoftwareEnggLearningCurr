package models;

public class Department
{
    public String deptId, deptName, building, headOfDept;
    
    public Department(String input_line)
    {
        String[] p = input_line.split(",");
        deptId = p[0].trim(); deptName = p[1].trim();
        building = p.length > 2 ? p[2].trim() : "";
        headOfDept = p.length > 3 ? p[3].trim() : "";
    }
}