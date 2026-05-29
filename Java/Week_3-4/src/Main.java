import java.util.Scanner;

public class Main
{
    public static void main(String[] args) {
        String dataDirectory = "./data";

        UniversityDB db = new UniversityDB();
        db.loadData(dataDirectory);

        QueryService queryService = new QueryService(db);
        Scanner scanner = new Scanner(System.in);
        
        while (true)
        {
            System.out.println("\n==============================================");
            System.out.println("       UNIVERSITY SYSTEM STREAMS MENU         ");
            System.out.println("==============================================");
            System.out.println("1. Highest Score in a Subject & Student (Q1)");
            System.out.println("2. Teacher assigned to a Subject (Q2)");
            System.out.println("3. Subject Statistics (Avg, Max, Min) (Q3)");
            System.out.println("4. Department-wise Student Count (Q4)");
            System.out.println("5. Most Intensive Classroom Schedule Dept (Q5)");
            System.out.println("6. Student Report Card Lookup (Q6)");
            System.out.println("7. Teachers in a Dept & Their Subjects (Q7)");
            System.out.println("8. Most Packed Day of the Week (Q8)");
            System.out.println("9. Rank Students Leaderboard (Q9)");
            System.out.println("10. Teachers Teaching > 3 Subjects (Q10)");
            System.out.println("0. Exit Application");
            System.out.print("Select an option (0-10): ");

            int choice = -1;
            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            System.out.println("\n--- Execution Output ---");
            switch (choice) {
                case 1:
                    System.out.print("Enter Subject ID (e.g., SUB101): ");
                    String subID1 = scanner.nextLine().trim();
                    queryService.query1(subID1);
                    break;
                case 2:
                    System.out.print("Enter Subject ID (e.g., SUB101): ");
                    String subID2 = scanner.nextLine().trim();
                    queryService.query2(subID2);
                    break;
                case 3:
                    System.out.print("Enter Subject ID (e.g., SUB101): ");
                    String subID3 = scanner.nextLine().trim();
                    queryService.query3(subID3);
                    break;
                case 4:
                    queryService.query4();
                    break;
                case 5:
                    queryService.query5();
                    break;
                case 6:
                    System.out.print("Enter Student ID (e.g., S101): ");
                    String studID = scanner.nextLine().trim();
                    queryService.query6(studID);
                    break;
                case 7:
                    System.out.print("Enter Department ID (e.g., DEPT_CS): ");
                    String deptID = scanner.nextLine().trim();
                    queryService.query7(deptID);
                    break;
                case 8:
                    queryService.query8();
                    break;
                case 9:
                    queryService.query9();
                    break;
                case 10:
                    queryService.query10();
                    break;
                case 0:
                    System.out.println("Exiting application...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Option out of range. Try again.");
            }
        }
    }
}