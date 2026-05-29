import models.*;
import java.util.*;
import java.util.stream.Collectors;

public class QueryService
{
    private final UniversityDB db;

    public QueryService(UniversityDB db)
    {this.db = db;}

    // 1. Highest score in subject and student who got it
    public void query1(String subjectID)
    {
        db.getMarks().stream()
            .filter(m -> m.getSubjectID().equalsIgnoreCase(subjectID))
            .max(Comparator.comparingDouble(Mark::getMarksObtained))
            .ifPresentOrElse(m -> {
                String name = db.getStudents().stream()
                    .filter(s -> s.getStudentID().equals(m.getStudentID()))
                    .findFirst().map(Student::getName).orElse("Unknown");
                System.out.println("Highest: " + m.getMarksObtained() + " by " + name);
            }, () -> System.out.println("No records."));
    }

    // 2. Which teacher taught a given subject
    public void query2(String subjectID)
    {
        db.getSessions().stream()
            .filter(s -> s.getSubjectID().equalsIgnoreCase(subjectID))
            .findFirst()
            .flatMap(session -> db.getTeachers().stream()
                .filter(t -> t.getTeacherID().equals(session.getTeacherID()))
                .findFirst())
            .ifPresentOrElse(t -> System.out.println("Teacher for " + subjectID + ": " + t.getName()),
                () -> System.out.println("No teacher record found."));
    }

    // 3. Stats for a subject
    public void query3(String subjectID)
    {
        DoubleSummaryStatistics stats = db.getMarks().stream()
            .filter(m -> m.getSubjectID().equalsIgnoreCase(subjectID))
            .collect(Collectors.summarizingDouble(Mark::getMarksObtained));
        if (stats.getCount() > 0) {
            System.out.println("Avg: " + stats.getAverage() + " | Max: " + stats.getMax() + " | Min: " + stats.getMin());
        } else {
            System.out.println("No data.");
        }
    }

    // 4. Dept-wise student count
    public void query4()
    {
        db.getStudents().stream()
            .collect(Collectors.groupingBy(Student::getDeptID, Collectors.counting()))
            .forEach((dept, count) -> System.out.println(dept + ": " + count));
    }

    // 5. Department with most intensive classroom schedule (most sessions per week)
    public void query5()
    {
        db.getSessions().stream()
            .flatMap(session -> db.getTeachers().stream()
                .filter(t -> t.getTeacherID().equals(session.getTeacherID()))
                .map(Teacher::getDeptID))
            .collect(Collectors.groupingBy(deptID -> deptID, Collectors.counting()))
            .entrySet().stream()
            .max(Map.Entry.comparingByValue())
            .ifPresentOrElse(e -> System.out.println("Intensive Dept: " + e.getKey() + " with " + e.getValue() + " sessions"),
                () -> System.out.println("No data."));
    }

    // 6. Student report card
    public void query6(String studentID)
    {
        db.getMarks().stream()
            .filter(m -> m.getStudentID().equalsIgnoreCase(studentID))
            .forEach(m -> System.out.println("Sub: " + m.getSubjectID() + " | Mark: " + m.getMarksObtained()));
    }

    // 7. Find all teachers in a given department and their assigned subjects
    public void query7(String deptID)
    {
        db.getTeachers().stream()
            .filter(t -> t.getDeptID().equalsIgnoreCase(deptID))
            .forEach(t -> {
                String subs = db.getSessions().stream()
                    .filter(s -> s.getTeacherID().equals(t.getTeacherID()))
                    .map(ClassSession::getSubjectID)
                    .distinct()
                    .collect(Collectors.joining(", "));
                System.out.println("Teacher: " + t.getName() + " | Subjects: [" + (subs.isEmpty() ? "None" : subs) + "]");
            });
    }

    // 8. Which day of the week has the most packed schedule
    public void query8()
    {
        db.getSessions().stream()
            .collect(Collectors.groupingBy(ClassSession::getDayOfWeek, Collectors.counting()))
            .entrySet().stream()
            .max(Map.Entry.comparingByValue())
            .ifPresentOrElse(e -> System.out.println("Packed Day: " + e.getKey() + " (" + e.getValue() + " sessions)"),
                () -> System.out.println("No entries."));
    }

    // 9. Rank students by overall percentage
    public void query9()
    {
        db.getMarks().stream()
            .collect(Collectors.groupingBy(Mark::getStudentID, 
                Collectors.averagingDouble(m -> (m.getMarksObtained() / m.getMaxMarks()) * 100.0)))
            .entrySet().stream()
            .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
            .forEach(e -> System.out.println("Student: " + e.getKey() + " | " + String.format("%.2f", e.getValue()) + "%"));
    }

    // 10. Find teachers who teach more than 3 different subjects
    public void query10()
    {
        db.getSessions().stream()
            .collect(Collectors.groupingBy(ClassSession::getTeacherID, 
                Collectors.mapping(ClassSession::getSubjectID, Collectors.toSet())))
            .entrySet().stream()
            .filter(entry -> entry.getValue().size() > 3)
            .forEach(entry -> {
                String name = db.getTeachers().stream()
                    .filter(t -> t.getTeacherID().equals(entry.getKey()))
                    .findFirst().map(Teacher::getName).orElse("Unknown");
                System.out.println("Teacher: " + name + " teaches " + entry.getValue().size() + " subjects");
            });
    }
}