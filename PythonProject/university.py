# university.py
from loading import read_csv

class Course:
    def __init__(self, course_id, name, credits):
        self.course_id = course_id
        self.name = name
        self.credits = credits

class Student:
    def __init__(self, student_id, name, age, department):
        self.student_id = student_id
        self.name = name
        # Edge Case 2: Missing age field
        self.age = age.strip() if age.strip() else "N/A"
        self.department = department.strip()
        self.marks = {}  # course_id -> marks

    def add_mark(self, course_id, marks):
        self.marks[course_id] = marks

    def get_letter_grade(self, marks):
        if marks >= 90: return 'A'
        elif marks >= 75: return 'B'
        elif marks >= 60: return 'C'
        elif marks >= 50: return 'D'
        else: return 'F'

    def get_grade_points(self, letter_grade):
        return {'A': 10, 'B': 8, 'C': 6, 'D': 5, 'F': 0}.get(letter_grade, 0)

    def calculate_gpa(self, courses):
        total_points = 0.0
        total_credits = 0
        for cid, m in self.marks.items():
            if cid in courses:
                cred = courses[cid].credits
                gp = self.get_grade_points(self.get_letter_grade(m))
                total_points += gp * cred
                total_credits += cred
        return total_points / total_credits if total_credits > 0 else 0.0

    def count_failures(self):
        return sum(1 for m in self.marks.values() if m < 50)


class University:
    def __init__(self):
        self.students = {}
        self.courses = {}
        self.valid_marks = []  # Stores tuples: (student_id, course_id, marks)

    def load_all(self, s_path, c_path, m_path):
        print("Loading data...")
        s_data = read_csv(s_path)
        c_data = read_csv(c_path)
        m_data = read_csv(m_path)

        # Load Courses with validation
        for row in c_data:
            if len(row) < 3: continue
            try:
                cred = int(row[2].strip())
                if cred <= 0: raise ValueError
                self.courses[row[0].strip()] = Course(row[0].strip(), row[1].strip(), cred)
            except (ValueError, IndexError):
                print(f"Warning: Invalid course data skipped: {row}")

        # Load Students
        for row in s_data:
            if len(row) < 4: continue
            sid = row[0].strip()
            # Handles missing age gracefully
            self.students[sid] = Student(sid, row[1].strip(), row[2].strip(), row[3].strip())

        # Load Marks with strict validation
        seen_pairs = set()
        for row in m_data:
            if len(row) < 3: continue
            sid, cid = row[0].strip(), row[1].strip()
            context = f"student={sid}, course={cid}"

            # Validate student and course IDs exist
            if sid not in self.students:
                print(f"Warning: student_id {sid} not found. Skipping record.")
                continue
            if cid not in self.courses:
                print(f"Warning: course_id {cid} not found. Skipping record.")
                continue

            # Edge Case 3: Duplicate handling strategy documented here:
            # "Keep the first valid entry encountered in the file."
            pair = (sid, cid)
            if pair in seen_pairs:
                print(f"Warning: Duplicate record {context}. Keeping first entry.")
                continue

            # Edge Case 1: Invalid/Out-of-range marks
            try:
                m = int(row[2].strip())
                if m < 0 or m > 100:
                    print(f"Warning: Out-of-range marks ({m}) for {context}. Skipping.")
                    continue
            except ValueError:
                print(f"Warning: Non-numeric marks ('{row[2].strip()}') for {context}. Skipping.")
                continue

            # All checks passed
            seen_pairs.add(pair)
            self.students[sid].add_mark(cid, m)
            self.valid_marks.append((sid, cid, m))

    def pass_fail(self):
        print(f"{'Student_ID':<12}{'Course_ID':<12}{'Marks':<8}{'Result'}")
        print("-" * 40)
        for sid, cid, m in self.valid_marks:
            res = "PASS" if m >= 50 else "FAIL"
            print(f"{sid:<12}{cid:<12}{m:<8}{res}")

    def grade_report(self):
        print(f"{'Student_ID':<12}{'Course_ID':<12}{'Marks':<8}{'Grade'}")
        print("-" * 40)
        for sid, cid, m in self.valid_marks:
            grade = self.students[sid].get_letter_grade(m)
            print(f"{sid:<12}{cid:<12}{m:<8}{grade}")

    def get_gpa(self, student_id):
        if student_id not in self.students:
            print(f"Warning: Student {student_id} not found.")
            return 0.0
        return self.students[student_id].calculate_gpa(self.courses)

    def dept_avg(self, dept):
        dept_students = [s for s in self.students.values() if s.department == dept]
        if not dept_students:
            print(f"Warning: Department {dept} not found or has no valid data.")
            return 0.0
        total_gpa = sum(s.calculate_gpa(self.courses) for s in dept_students)
        return total_gpa / len(dept_students)

    def topper_finder(self):
        gpa_list = [(s.name, s.calculate_gpa(self.courses)) for s in self.students.values()]
        gpa_list.sort(key=lambda x: x[1], reverse=True)
        print(f"{'Rank':<6}{'Name':<12}{'GPA'}")
        print("-" * 25)
        for i, (name, gpa) in enumerate(gpa_list[:3], 1):
            print(f"{i:<6}{name:<12}{gpa:.2f}")

    def generate_report(self):
        total_students = len(self.students)
        total_courses = len(self.courses)

        toppers = [(s.name, s.calculate_gpa(self.courses)) for s in self.students.values()]
        toppers.sort(key=lambda x: x[1], reverse=True)
        topper_name, topper_gpa = toppers[0] if toppers else ("N/A", 0.0)

        failing = [s for s in self.students.values() if s.count_failures() > 0]

        with open("report.txt", "w") as f:
            f.write("UNIVERSITY REPORT\n=================\n\n")
            f.write(f"Total Students : {total_students}\nTotal Courses  : {total_courses}\n\n")
            f.write(f"Topper: {topper_name} (GPA: {topper_gpa:.2f})\n\n")
            f.write("Failing Students:\n")
            for i, s in enumerate(failing, 1):
                f.write(f"  {s.student_id}  {s.name}\n")
            f.write("\nDepartment Statistics:\n")
            for d in sorted(set(s.department for s in self.students.values())):
                f.write(f"  {d}  Avg GPA: {self.dept_avg(d):.2f}\n")
        print("Report generated successfully in report.txt")

    def search_student(self, query):
        query = query.lower()
        results = [s for s in self.students.values() if query in s.name.lower()]
        if not results:
            print("No students found matching the query.")
            return
        for s in results:
            gpa = s.calculate_gpa(self.courses)
            courses_taken = ", ".join(sorted(s.marks.keys()))
            print(f"\n  ID         : {s.student_id}")
            print(f"  Name       : {s.name}")
            print(f"  Department : {s.department}")
            print(f"  GPA        : {gpa:.2f}")
            print(f"  Courses    : {courses_taken}")

    def at_risk_students(self):
        at_risk = []
        for s in self.students.values():
            gpa = s.calculate_gpa(self.courses)
            fails = s.count_failures()
            if gpa < 6.0 or fails >= 2:
                at_risk.append((s.name, gpa, fails))
        at_risk.sort(key=lambda x: x[1])
        print("At-Risk Students:")
        for name, gpa, fails in at_risk:
            print(f"  {name:<10} (GPA: {gpa:.1f}, Failures: {fails})")

    def course_difficulty(self):
        fail_counts = {}
        for sid, cid, m in self.valid_marks:
            if m < 50:
                fail_counts[cid] = fail_counts.get(cid, 0) + 1
        if not fail_counts:
            print("No failing records found.")
            return
        hardest_cid = max(fail_counts, key=fail_counts.get)
        course = self.courses[hardest_cid]
        print("Hardest Course:")
        print(f"  {hardest_cid}  {course.name}  (Failures: {fail_counts[hardest_cid]})")