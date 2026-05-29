import models.*;
import java.util.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class UniversityDB
{
    private List<Student> students = new ArrayList<>();
    private List<Mark> marks = new ArrayList<>();
    private List<Teacher> teachers = new ArrayList<>();
    private List<ClassSession> sessions = new ArrayList<>();

    public void loadData(String dataDir)
    {
        try
        {
            // 1. Students 0, 1, 2, 3, 4
            this.students = Files.lines(Paths.get(dataDir, "students.csv"))
                .skip(1)
                .map(line -> line.split(","))
                .filter(p -> p.length >= 5) // Skip malformed rows or blank trailing lines
                .map(p -> new Student(p[0].trim(), p[1].trim(), p[2].trim(), p[3].trim(), Integer.parseInt(p[4].trim())))
                .collect(Collectors.toList());

            // 2. Marks 0, 1, 2, 3, 4
            this.marks = Files.lines(Paths.get(dataDir, "student_marks.csv"))
                .skip(1)
                .map(line -> line.split(","))
                .filter(t -> t.length >= 5)
                .map(t -> new Mark(t[0].trim(), t[1].trim(), Integer.parseInt(t[2].trim()), 
                                   Double.parseDouble(t[3].trim()), Double.parseDouble(t[4].trim())))
                .collect(Collectors.toList());

            // 3. Teachers 0, 1, 2, 3
            this.teachers = Files.lines(Paths.get(dataDir, "teachers.csv"))
                .skip(1)
                .map(line -> line.split(","))
                .filter(t -> t.length >= 4) // Prevents the 'Index 3 out of bounds' crash!
                .map(t -> new Teacher(t[0].trim(), t[1].trim(), t[2].trim(), t[3].trim()))
                .collect(Collectors.toList());

            // 4. Class Sessions 0 to 6
            this.sessions = Files.lines(Paths.get(dataDir, "class_sessions.csv"))
                .skip(1)
                .map(line -> line.split(","))
                .filter(s -> s.length >= 7)
                .map(s -> new ClassSession(s[0].trim(), s[1].trim(), s[2].trim(), s[3].trim(), s[4].trim(), s[5].trim(), s[6].trim()))
                .collect(Collectors.toList());

        } catch (Exception e) {
            System.err.println("Error loading data: " + e.getMessage());
            e.printStackTrace(); // Optional: Prints exactly which line failed if a different error pops up
        }
    }
    
    public List<Student> getStudents() { return students; }
    public List<Mark> getMarks() { return marks; }
    public List<Teacher> getTeachers() { return teachers; }
    public List<ClassSession> getSessions() { return sessions; }
}