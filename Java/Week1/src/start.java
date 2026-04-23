import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

class start
{
    static void printMenu()
    {
        try
        {
            Scanner sc = new Scanner(new File("data/menu.txt"));
            while (sc.hasNextLine())
                System.out.println(sc.nextLine());
            sc.close();
        }
        catch (FileNotFoundException e)
        {
            System.err.println("menu.txt not found");
        }
    }

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        // Loading the data
        loading load = new loading();
        System.out.println("Enter path for student record");
        String[][] students = load.read(sc.nextLine());

        System.out.println("Enter path for courses record");
        String[][] courses = load.read(sc.nextLine());

        System.out.println("Enter path for marks record");
        String[][] marks = load.read(sc.nextLine());

        working w = new working();
        w.marks    = marks;
        w.students = students;
        w.courses  = courses;

        int choice = 0;

        while (choice != 5)
        {
            printMenu();

            // Guard against non-integer menu input
            if (!sc.hasNextInt())
            {
                System.err.println("Invalid input. Please enter a number.");
                sc.next(); // discard bad token
                continue;
            }
            choice = sc.nextInt();

            switch (choice)
            {
                case 1:
                    w.PassFail();
                    break;

                case 2:
                    w.grade();
                    break;

                case 3:
                {
                    System.out.print("Enter student ID: ");
                    String id = sc.next();
                    System.out.printf("GPA: %.2f%n", w.gpa(id));
                    break;
                }

                case 4:
                {
                    System.out.print("Enter department (CSE/AIML/DS): ");
                    String dept = sc.next();
                    w.deptAvg(dept);
                    break;
                }

                case 5:
                    System.out.println("Exiting program.");
                    break;

                default:
                    System.out.println("Invalid choice. Please enter 1-5.");
            }
        }

        sc.close();
    }
}