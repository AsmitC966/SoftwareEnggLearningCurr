import models.Student;
import models.StudentMark;
import models.Teacher;
import models.TeacherAssignment;
import models.Department;
import models.Stream;
import models.ClassSession;
import models.Subject;
//probably didn't need all imported

/*
    Like the first week1 project, read through the full file once to get an idea of the size
    then proceed
 
    We load every row into plain objects coz like each row is an entry for an entity after all..


    Queries well...just predefine them as functions
    Loop through the marks array, match the student ID type stuff
    
    looping through assignments, taking teacher ID, then loop again to find the subject name.


    Group and Sort...no inbuilt methods but we can run them parallely with the same loop
    keep two arrays side-by-side (one for IDs/names,one for counts/scores) and 
    swap them together when we bubble sort.
 */

public class Main {

    public static void main(String[] args) {
        try {
            System.out.println("Loading data--");
            
            // Load all 8 CSV files
            Department[] departments = DataLoader.loadDepts("data/departments.csv");
            Stream[] streams = DataLoader.loadStreams("data/streams.csv");
            Student[] students = DataLoader.loadStudents("data/students.csv");
            Subject[] subjects = DataLoader.loadSubjects("data/subjects.csv");
            StudentMark[] marks = DataLoader.loadMarks("data/student_marks.csv");
            Teacher[] teachers = DataLoader.loadTeachers("data/teachers.csv");
            ClassSession[] sessions = DataLoader.loadSessions("data/class_sessions.csv");
            TeacherAssignment[] assignments = DataLoader.loadAssignments("data/teacher_assignments.csv");

            System.out.println("Loaded " + students.length + " students & " + marks.length + " marks\n");

            /*
                I know how to make this user defined but for ease of getting results and quick debugging 
                imma keep it predefined
            */
        

            Queries.highestScoreInSubject(marks, students, "SUB01");
            Queries.findTeacherForSubject(assignments, teachers, "SUB01");
            Queries.subjectStats(marks, "SUB01");
            Queries.deptStudentCount(students);

            Queries.MostIntensiveDept(sessions, subjects);
            Queries.StudentSubjects(marks, subjects, "ST001");
            Queries.DeptTeachersSubjects(teachers, assignments, subjects, "DO4");
            Queries.BusiestDay(sessions);
            Queries.RankStudents(students, marks);
            Queries.TeachersWithManySubjects(teachers, assignments);
            
        } catch(Exception e)
        {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}