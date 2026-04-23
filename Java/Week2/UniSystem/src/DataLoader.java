import java.io.*;
import models.*;

public class DataLoader
{
    private static int countLines(String path) throws IOException
    {
        try (BufferedReader r = new BufferedReader(new FileReader(path)))
        {
            int c = 0; while (r.readLine() != null) c++;
            return c - 1; // exclude header
        }
    }

    public static Department[] loadDepts(String p) throws IOException
    {
        Department[] a = new Department[countLines(p)];
        try (BufferedReader r = new BufferedReader(new FileReader(p)))
        {
            r.readLine(); for(int i=0; i<a.length; i++) a[i] = new Department(r.readLine());
        } 
        return a;
    }
    
    public static Stream[] loadStreams(String p) throws IOException
    {
        Stream[] a = new Stream[countLines(p)];
        try (BufferedReader r = new BufferedReader(new FileReader(p)))
        {
            r.readLine(); for(int i=0; i<a.length; i++) a[i] = new Stream(r.readLine());
        } 
        return a;
    }
    
    public static Student[] loadStudents(String p) throws IOException
    {
        Student[] a = new Student[countLines(p)];
        try (BufferedReader r = new BufferedReader(new FileReader(p)))
        {
            r.readLine(); for(int i=0; i<a.length; i++) a[i] = new Student(r.readLine());
        } 
        return a;
    }

    public static Subject[] loadSubjects(String p) throws IOException
    {
        Subject[] a = new Subject[countLines(p)];
        try (BufferedReader r = new BufferedReader(new FileReader(p)))
        {
            r.readLine(); for(int i=0; i<a.length; i++) a[i] = new Subject(r.readLine());
        }
        return a;
    }

    public static StudentMark[] loadMarks(String p) throws IOException
    {
        StudentMark[] a = new StudentMark[countLines(p)];
        try (BufferedReader r = new BufferedReader(new FileReader(p)))
        {
            r.readLine(); for(int i=0; i<a.length; i++) a[i] = new StudentMark(r.readLine());
        } 
        return a;
    }

    public static Teacher[] loadTeachers(String p) throws IOException
    {
        Teacher[] a = new Teacher[countLines(p)];
        try (BufferedReader r = new BufferedReader(new FileReader(p)))
        {
            r.readLine(); for(int i=0; i<a.length; i++) a[i] = new Teacher(r.readLine());
        } 
        return a;
    }

    public static ClassSession[] loadSessions(String p) throws IOException
    {
        ClassSession[] a = new ClassSession[countLines(p)];
        try (BufferedReader r = new BufferedReader(new FileReader(p)))
        {
            r.readLine(); for(int i=0; i<a.length; i++) a[i] = new ClassSession(r.readLine());
        }
        return a;
    }

    public static TeacherAssignment[] loadAssignments(String p) throws IOException
    {
        TeacherAssignment[] a = new TeacherAssignment[countLines(p)];
        try (BufferedReader r = new BufferedReader(new FileReader(p)))
        {
            r.readLine(); for(int i=0; i<a.length; i++) a[i] = new TeacherAssignment(r.readLine());
        } 
        return a;
    }
}