import models.StudentMark;
import models.Teacher;
import models.TeacherAssignment;
import models.ClassSession;
import models.Student;
import models.Subject;

public class Queries 
{

    // Query #1: Highest score in a subject + student name
    public static void highestScoreInSubject(StudentMark[] marks, Student[] students, String subjectId)
    {
        int maxScore = -1;
        String topStudentId = null;

        for (StudentMark m:marks) //enhanced loop
        {
            if (m.subjectId.equals(subjectId) && m.marksObtained>maxScore)
            {
                maxScore = m.marksObtained;
                topStudentId = m.studentId;
            }
        }

        // Join: find student name (another linear search)
        String studentName="Not Found";
        for (Student s : students)
        {
            if (s.studentId.equals(topStudentId))
            {
                studentName = s.name;
                break;
            }
        }
        System.out.println("Q1 | Highest in " + subjectId + ": " + maxScore + " by  " + studentName);
    }

    //Query #2: Which Teacher taught that subject
    public static void findTeacherForSubject(TeacherAssignment[] assignments, Teacher[] teachers, String subjectId)
    {
        String teacherId=null;

        for (TeacherAssignment assignment:assignments)//find teacher_id from assignment
        {
            if (assignment.subjectId.equals(subjectId))
            {
                teacherId=assignment.teacherId;
                break;//found
            }
        }

        if (teacherId==null)
        {
            System.out.println("Q2 | Subject " + subjectId + " not found in assignments.");
            return;
        }


        String teacherName = "Unknown"; //find teacher name
        for (Teacher t:teachers)
        {
            if (t.teacherId.equals(teacherId))
            {
                teacherName = t.name;
                break;
            }
        }
        System.out.println("Q2 | Subject " + subjectId + " is taught by " + teacherName);
    }

    // Query #3: Avg, Max, Min for a subject
    public static void subjectStats(StudentMark[] marks, String subjectId)
    {
        int sum=0, count=0, max=-1, min=101;

        for (StudentMark m:marks)
        {
            if (m.subjectId.equals(subjectId))
            {
                sum += m.marksObtained;
                count++;
                if (m.marksObtained > max) max = m.marksObtained;
                if (m.marksObtained < min) min = m.marksObtained;
            }
        }

        double avg=count>0 ? (double)sum/count : 0;
        System.out.println("Q3 | "+subjectId+" | Avg: "+avg+" | Max:"+max+" | Min: "+min);
    }

    // Query #4: Department-wise student count
    public static void deptStudentCount(Student[] students)
    {
        String[] deptIds = new String[students.length];
        int[] counts = new int[students.length];
        int uniqueDepts = 0;

        for (Student s:students)
        {
            boolean found = false;
            for (int i=0;i<uniqueDepts;i++)
            {
                if (deptIds[i].equals(s.deptId))
                {
                    counts[i]++;
                    found = true;
                    break;
                }
            }

            if (!found)
            {
                deptIds[uniqueDepts] = s.deptId;
                counts[uniqueDepts] = 1;
                uniqueDepts++;
            }
        }

        System.out.println("Q4 | Department Student Counts:");

        for (int i=0;i<uniqueDepts;i++)
            System.out.println("  " + deptIds[i] + ": " + counts[i] + " students");
    }

    // Query #5: Which department has the most intensive classroom schedule? (most sessions per week)
    public static void MostIntensiveDept(ClassSession[] sessions, Subject[] subjects)
    {
        // Parallel arrays to track department counts manually
        String[] deptIds=new String[subjects.length];
        int[] counts=new int[subjects.length];
        int uniqueDepts=0;

        // Count sessions per department
        for (ClassSession session:sessions)
        {
            // Find the department ID for this session's subject
            String deptId=null;
            for (Subject subject:subjects)
            {
                if (subject.subjectId.equals(session.subjectId))
                {
                    deptId = subject.deptId;
                    break;
                }
            }
            if (deptId==null) continue;

            // Check if unique dept
            boolean found=false;
            for (int i=0;i<uniqueDepts;i++)
            {
                if (deptIds[i].equals(deptId))
                {
                    counts[i]++;
                    found=true;
                    break;
                }
            }

            // If new department, add it to arrays
            if (!found)
            {
                deptIds[uniqueDepts]=deptId;
                counts[uniqueDepts]=1;
                uniqueDepts++;
            }
        }

        // Find the department with the maximum count
        int maxIndex=0;
        
        for (int i=1;i<uniqueDepts;i++)
        {
            if (counts[i]>counts[maxIndex])
                maxIndex=i;
        }

        System.out.println("Q5 | Most intensive dept: "+deptIds[maxIndex]+" ("+counts[maxIndex]+" sessions)");
    }

    // Query #6: List all subjects a given student is enrolled in with their marks
    public static void StudentSubjects(StudentMark[] marks, Subject[] subjects, String studentId)
    {
        System.out.println("Q6 | Subjects for "+studentId+":");
        boolean foundAny = false;

        for(StudentMark mark:marks)
        {
            if(mark.studentId.equals(studentId))
            {
                foundAny=true;

                // Find the subject name for this mark
                String subjectName="Unknown";
                for (Subject subject:subjects)
                {
                    if (subject.subjectId.equals(mark.subjectId))
                    {
                        subjectName = subject.subjectName;
                        break;
                    }
                }

                System.out.println("  - " +subjectName+": "+mark.marksObtained+"/"+mark.maxMarks);
            }
        }

        if (!foundAny) {
            System.out.println("  No marks found for this student.");
        }
    }

    // Query #7: Find all teachers in a given department and their assigned subjects
    public static void DeptTeachersSubjects(Teacher[] teachers, TeacherAssignment[] assignments, Subject[] subjects, String deptId)
    {
        System.out.println("Q7 | Teachers in "+deptId+":");
        boolean foundAny = false;

        for(Teacher teacher:teachers)
        {
            if(teacher.deptId.equals(deptId))
            {
                foundAny=true;
                System.out.print("  "+teacher.name+" -> ");

                boolean firstSubject=true;
                for(TeacherAssignment assignment:assignments)
                {
                    if(assignment.teacherId.equals(teacher.teacherId))
                    {
                        // Find subject name for this assignment
                        for(Subject subject:subjects)
                        {
                            if (subject.subjectId.equals(assignment.subjectId))
                            {
                                if (!firstSubject)
                                    System.out.print(", ");

                                System.out.print(subject.subjectName);
                                firstSubject = false;
                                break;
                            }
                        }
                    }
                }
                System.out.println();
            }
        }

        if (!foundAny)
            System.out.println("  No teachers found for this department.");
    }

    // Query #8: Which day of the week has the most packed schedule across all departments?
    public static void BusiestDay(ClassSession[] sessions)
    {
        // Parallel arrays to track day counts manually
        String[] days=new String[sessions.length];
        int[] counts=new int[sessions.length];
        int uniqueDays=0;

        // Step 1: Count sessions per day
        for(ClassSession session:sessions)
        {
            boolean found=false;
            for (int i=0;i<uniqueDays;i++)
            {
                if(days[i].equals(session.dayOfWeek))
                {
                    counts[i]++;
                    found=true;
                    break;
                }
            }
            if(!found)
            {
                days[uniqueDays]=session.dayOfWeek;
                counts[uniqueDays]=1;
                uniqueDays++;
            }
        }

        // Step 2: Find the day with the maximum count
        int maxIndex = 0;
        for (int i=1;i<uniqueDays;i++)
        {
            if(counts[i]>counts[maxIndex])
                maxIndex = i;
        }

        System.out.println("Q8 | Busiest day: " +days[maxIndex]+" ("+counts[maxIndex]+" sessions)");
    }

    // Query #9: Rank students by overall percentage across all subjects
    public static void RankStudents(Student[] students, StudentMark[] marks)
    {
        // Parallel arrays to hold names and percentages for sorting
        String[] names=new String[students.length];
        double[] percentages=new double[students.length];

        // Find percentage for each student
        for (int i=0; i<students.length;i++)
        {
            names[i]=students[i].name;
            String studentId=students[i].studentId;
            double totalObtained=0;
            double totalMax=0;

            for(StudentMark mark:marks)
            {
                if (mark.studentId.equals(studentId))
                {
                    totalObtained +=mark.marksObtained;
                    totalMax +=mark.maxMarks;
                }
            }

            percentages[i]=(totalMax>0) ? (totalObtained/totalMax)*100 : 0;
        }

        // Desc. BUbble Sort then swap both arrays together to keep data linked
        for (int i=0; i<percentages.length-1;i++)
        {
            for (int j=0;j<percentages.length-i-1;j++)
            {
                if (percentages[j]<percentages[j+1])
                {
                    // Swap percentages
                    double tempPct=percentages[j];
                    percentages[j]=percentages[j+1];
                    percentages[j+1]=tempPct;

                    // Swap names (keep them in sync)
                    String tempName=names[j];
                    names[j]=names[j+1];
                    names[j+1]=tempName;
                }
            }
        }

        // Step 3: Top 10 rankings
        System.out.println("Q9 | Student Rankings (Top 10):");
        int limit = Math.min(10, names.length);

        for (int i = 0; i < limit; i++)
            System.out.println(i + 1+" "+names[i]+" "+percentages[i]);
    }

    // Query #10: Find teachers who teach more than 3 different subjects
    public static void TeachersWithManySubjects(Teacher[] teachers, TeacherAssignment[] assignments)
    {
        System.out.println("Q10 | Teachers with >3 subjects:");
        boolean foundAny = false;

        for (Teacher teacher:teachers)
        {
            // Unique subject of teacher
            String[] subjectIds = new String[assignments.length];
            int uniqueCount = 0;

            for (TeacherAssignment assignment:assignments)
            {
                if (assignment.teacherId.equals(teacher.teacherId))
                {
                    // Ignoring duplicate
                    boolean alreadyCounted = false;
                    for (int k = 0; k < uniqueCount; k++)
                    {
                        if (subjectIds[k].equals(assignment.subjectId))
                        {
                            alreadyCounted = true;
                            break;
                        }
                    }

                    //Add new sub
                    if (!alreadyCounted) {
                        subjectIds[uniqueCount]=assignment.subjectId;
                        uniqueCount++;
                    }
                }
            }

            // Print if teacher has more than 3 unique subjects
            if (uniqueCount > 3)
            {
                foundAny = true;
                System.out.println("  "+teacher.name+" teaches "+uniqueCount+" subjects.");
            }
        }

        if (!foundAny)
            System.out.println("  None found.");
    }
}