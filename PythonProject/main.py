# main.py
from university import University

def print_menu():
    print("\n=============================")
    print("  UNIVERSITY RECORD SYSTEM")
    print("=============================")
    print("1. Pass/Fail Report")
    print("2. Grade Report")
    print("3. Student GPA")
    print("4. Department Average GPA")
    print("5. Top 3 Toppers")
    print("6. Generate Full Report")
    print("7. Search Student")
    print("8. At-Risk Students")
    print("9. Course Difficulty Ranking")
    print("10. Exit")
    print("Enter choice: ", end="")

def main():
    uni = University()
    # Loads CSVs and triggers all edge case warnings immediately
    uni.load_all("students.csv", "courses.csv", "marks.csv")

    choice = 0
    while choice != 10:
        print_menu()
        try:
            user_input = input().strip()
            if not user_input:
                continue
            choice = int(user_input)
        except ValueError:
            print("Invalid input. Please enter a number.")
            continue

        if choice == 1:
            uni.pass_fail()
        elif choice == 2:
            uni.grade_report()
        elif choice == 3:
            sid = input("Enter student ID: ").strip()
            print(f"GPA for {sid}: {uni.get_gpa(sid):.2f}")
        elif choice == 4:
            dept = input("Enter department (CSE/AIML/DS): ").strip()
            print(f"Avg GPA for {dept}: {uni.dept_avg(dept):.2f}")
        elif choice == 5:
            uni.topper_finder()
        elif choice == 6:
            uni.generate_report()
        elif choice == 7:
            query = input("Enter search query: ").strip()
            uni.search_student(query)
        elif choice == 8:
            uni.at_risk_students()
        elif choice == 9:
            uni.course_difficulty()
        elif choice == 10:
            print("Exiting program.")
        else:
            print("Invalid choice. Please enter 1-10.")

if __name__ == "__main__":
    main()