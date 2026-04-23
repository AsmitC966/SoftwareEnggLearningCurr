/*
    I have to assume that the index or column 
    will be same for the csv files

    meaning, 1st column will always be student_id

    having a pre defined format basically
*/

public class working
{
    String[][] marks;
    String[][] courses;
    String[][] students;

    // Helper: safely parse an integer, returns -1 on failure
    private int parseMarks(String val, String context)
    {
        if (val==null || val.trim().isEmpty())
        {
            System.err.println(" Missing marks value in row: " + context);
            return -1;
        }
        try
        {
            int m=Integer.parseInt(val.trim());
            if (m<0 || m>100)
            {
                System.err.println(" Out-of-range marks (" + m + ") in row: " + context);
                return -1;
            }
            return m;
        }
        catch (NumberFormatException e)
        {
            System.err.println(" Non-numeric marks (\"" + val.trim() + "\") in row: " + context);
            return -1;
        }
    }

    // Helper: safely parse credits, returns 0 on failure
    private int parseCredits(String val, String courseID)
    {
        if (val == null || val.trim().isEmpty())
        {
            System.err.println(" Missing credits for course: " + courseID);
            return 0;
        }
        try
        {
            int c=Integer.parseInt(val.trim());
            if (c<=0)
            {
                System.err.println(" Invalid credits (" + c + ") for course: " + courseID);
                return 0;
            }
            return c;
        }
        catch (NumberFormatException e)
        {
            System.err.println(" Non-numeric credits for course: " + courseID);
            return 0;
        }
    }

    /////////////////////////////////////////////////////////////////////////////////

    void PassFail()
    {
        System.out.println("Student_ID\tCourse_ID\tMarks\tResult");
        System.out.println("_______________________________________________________________");

        for (int i = 0; i < marks.length; i++) // i=0 since header is already stripped by loading
        {
            if (marks[i] == null || marks[i].length < 3)
            {
                System.err.println(" Incomplete row at index " + i);
                continue;
            }

            String stud_ID  = marks[i][0].trim();
            String course_ID = marks[i][1].trim();
            String context  = "student=" + stud_ID + ", course=" + course_ID;

            int m = parseMarks(marks[i][2], context);
            if (m == -1) continue; // invalid row, already reported

            String result = (m >= 50) ? "Pass" : "Fail";
            System.out.println(stud_ID + "\t\t" + course_ID + "\t\t" + m + "\t\t" + result);
        }
    }

    /////////////////////////////////////////////////////////////////////////////////

    void grade()
    {
        System.out.println("Student_ID\tCourse_ID\tMarks\tGrade");
        System.out.println("_______________________________________________________________");

        for (int i = 0; i < marks.length; i++)
        {
            if (marks[i] == null || marks[i].length < 3)
            {
                System.err.println(" Incomplete row at index " + i);
                continue;
            }

            String stud_ID  = marks[i][0].trim();
            String course_ID = marks[i][1].trim();
            String context  = "student=" + stud_ID + ", course=" + course_ID;

            int m = parseMarks(marks[i][2], context);
            if (m == -1) continue;

            String g;
            if      (m >= 90) g = "A";
            else if (m >= 75) g = "B";
            else if (m >= 60) g = "C";
            else if (m >= 55) g = "D";
            else if (m >= 50) g = "E";
            else              g = "F";

            System.out.println(stud_ID + "\t\t" + course_ID + "\t\t" + m + "\t\t" + g);
        }
    }

    ///////////////////////////////////////////////////////////////////////////////

    double gpa(String stud_id)
    {
        int totCred = 0, weight = 0;

        for (int i = 0; i < marks.length; i++)
        {
            if (marks[i] == null || marks[i].length < 3) continue;

            if (!marks[i][0].trim().equals(stud_id)) continue;

            String context = "student=" + stud_id + ", course=" + marks[i][1].trim();
            int score = parseMarks(marks[i][2], context);
            if (score == -1) continue; // skip bad row

            int gp;
            if      (score >= 90) gp = 10;
            else if (score >= 75) gp = 8;
            else if (score >= 60) gp = 6;
            else if (score >= 55) gp = 5;
            else if (score >= 50) gp = 4;
            else                  gp = 0;

            String courseID = marks[i][1].trim();
            int cred = 0;
            for (int j = 0; j < courses.length; j++)
            {
                if (courses[j] == null || courses[j].length < 3) continue;
                if (courses[j][0].trim().equals(courseID))
                {
                    cred = parseCredits(courses[j][2], courseID);
                    break;
                }
            }

            weight   += gp * cred;
            totCred  += cred;
        }

        if (totCred == 0)
        {
            System.err.println(" Student \"" + stud_id + "\" not found or has no valid marks.");
            return 0.0;
        }

        return (double) weight / totCred;
    }

    //////////////////////////////////////////////////////////////////////////////////

    void deptAvg(String dept)
    {
        double totGPA = 0;
        int count = 0;

        for (int i = 0; i < students.length; i++)
        {
            if (students[i] == null || students[i].length < 4)
            {
                System.err.println("Incomplete student row at index" + i);//error with csv format so ignoring
                continue;
            }

            if (students[i][3].trim().equals(dept))
            {
                String stud_ID = students[i][0].trim();
                double g = gpa(stud_ID);
                if (g > 0) // only count students with valid GPA
                {
                    totGPA += g;
                    count++;
                }
            }
        }

        if (count == 0)
        {
            System.err.println("  Department \"" + dept + "\" not found or has no valid data.");
            return;
        }

        System.out.println("AVG GPA of "+dept+" = "+(totGPA / count));
    }
}   