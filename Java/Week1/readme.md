# 🎓 University Academic Record Manager
### A Progressive Practice Project — Java & Python

> A single end-to-end project that covers variables, conditions, loops, functions, arrays, strings, file I/O, OOP, and error handling — all in one realistic system.

---

## 📁 Setup — Create These Files First

Before writing any code, create the following CSV files in your project directory.

### `students.csv`
```
student_id,name,age,department
101,Aarav,20,CSE
102,Diya,19,AIML
103,Rohan,21,CSE
104,Meera,20,DS
105,Kabir,22,AIML
106,Ishaan,19,CSE
107,Anaya,20,DS
108,Vihaan,21,AIML
```

### `courses.csv`
```
course_id,course_name,credits
C101,Data Structures,4
C102,Operating Systems,3
C103,DBMS,3
C104,Machine Learning,4
C105,Computer Networks,3
```

### `marks.csv`
```
student_id,course_id,marks
101,C101,78
101,C102,88
101,C103,91
102,C101,65
102,C104,72
103,C101,55
103,C102,60
104,C103,84
105,C104,95
106,C105,49
107,C101,73
108,C104,89
```

---

## 🟢 Part 1 — Variables, Conditions, Loops

### Problem 1: Pass/Fail Analyzer
Read `marks.csv` and print whether each student passed or failed each course.

**Rule:** marks >= 50 → PASS, else → FAIL

**Expected output format:**
```
student_id  course_id  result
101         C101       PASS
106         C105       FAIL
```

**Edge cases to handle:**
- Missing marks field
- Negative marks
- Marks greater than 100

---

### Problem 2: Grade Calculator
Convert marks to letter grades using the table below.

| Marks   | Grade |
|---------|-------|
| 90 – 100 | A    |
| 75 – 89  | B    |
| 60 – 74  | C    |
| 50 – 59  | D    |
| Below 50 | F    |

**Expected output format:**
```
101  C101  B
101  C102  A
101  C103  A
```

**Must use:** conditionals, loops, and a helper function for grade conversion.

---

## 🟡 Part 2 — Functions & Arrays

### Problem 3: Student GPA Calculator
Calculate GPA for each student using weighted grade points.

**Grade point mapping:**
```
A = 10,  B = 8,  C = 6,  D = 5,  F = 0
```

**Formula:**
```
GPA = sum(grade_points × course_credits) / sum(course_credits)
```

**Task:** Write a function `calculateGPA(student_id)` that returns the student's GPA.

**Expected output:**
```
Student: Aarav
GPA: 8.25
```

---

### Problem 4: Department Average GPA
Group students by department and print the average GPA for each.

**Expected output:**
```
CSE  Avg GPA: X.XX
AIML Avg GPA: X.XX
DS   Avg GPA: X.XX
```

---

## 🟠 Part 3 — File I/O

### Problem 5: Topper Finder
Find the top 3 students sorted by GPA (descending). Read from all three CSV files.

**Expected output:**
```
Rank  Name    GPA
1     Kabir   9.50
2     Aarav   8.25
3     Vihaan  8.00
```

---

### Problem 6: Report Generator
Write a program that generates a `report.txt` file with the following content:

```
UNIVERSITY REPORT
=================

Total Students : 8
Total Courses  : 5

Topper: Kabir (GPA: 9.50)

Failing Students:
  106  Ishaan

Department Statistics:
  CSE  Avg GPA: X.XX
  AIML Avg GPA: X.XX
  DS   Avg GPA: X.XX
```

---

## 🔵 Part 4 — OOP Design

Refactor your code into classes.

### Class: `Student`
```
Fields  : student_id, name, age, department, marks (list)
Methods : calculateGPA(), addMark(course_id, marks), getReport()
```

### Class: `Course`
```
Fields  : course_id, name, credits
```

### Class: `University`
```
Fields  : students (list), courses (list)
Methods : loadStudents(), loadCourses(), loadMarks(),
          findTopper(), generateReport()
```

**Goal:** All previous problems should now work by calling methods on these objects instead of using raw functions.

---

## 🔴 Part 5 — Error Handling

Add proper error handling throughout your system. The program must **never crash** — it should print a warning and continue.

**File errors to handle:**
- File not found
- Wrong/corrupted format
- Empty file

**Data errors to handle:**
- `student_id` not found in students list
- Invalid `course_id`
- Duplicate records (see edge cases below)

**Example behaviour:**
```
Warning: student_id 999 not found. Skipping record.
Warning: marks.csv is empty. No data loaded.
```

Java → use `try/catch`
Python → use `try/except`

---

## 🟣 Part 6 — String Processing

### Problem 7: Student Search System
Allow the user to search for a student by name. Display their full profile.

**Must support:**
- Case-insensitive search
- Partial matches

**Example:**
```
Enter search query: aar

Result:
  ID         : 101
  Name       : Aarav
  Department : CSE
  GPA        : 8.25
  Courses    : C101, C102, C103
```

---

## ⚫ Part 7 — Tricky Logic

### Problem 8: At-Risk Students
Find students who meet **either** condition:
- GPA < 6.0
- 2 or more failed subjects

**Expected output:**
```
At-Risk Students:
  Ishaan  (GPA: X.X, Failures: 1)
  Rohan   (GPA: X.X, Failures: 0)
```

---

### Problem 9: Course Difficulty Ranking
Find the course with the most failures.

**Expected output:**
```
Hardest Course:
  C101  Data Structures  (Failures: 3)
```

---

## ⚡ Part 8 — Edge Case Engineering

Your system must gracefully handle all of these without crashing.

**Case 1 — Invalid marks value:**
```
marks.csv contains: 101,C101,abc
```
Handle: print a warning, skip the record.

**Case 2 — Missing field:**
```
students.csv contains: 109,Unknown,,CSE
```
Handle: assign a default age (e.g. 0 or "N/A"), continue loading.

**Case 3 — Duplicate record:**
```
marks.csv contains:
101,C101,78
101,C101,82
```
**Decision required:** Choose one of the following and document it in a comment:
- Keep the first entry
- Keep the latest entry  
- Use the average

There is no "correct" answer — consistency and documentation matter.

---

## 🏆 Part 9 — Menu System (Capstone)

Tie everything together with an interactive CLI menu.

```
=============================
  UNIVERSITY RECORD SYSTEM
=============================
1. View student report
2. Find topper
3. Department statistics
4. Search student
5. Exit

Enter choice:
```

**Requirements:**
- Loop until user selects Exit
- Validate all inputs (e.g. handle letters when a number is expected)
- Call the appropriate method/function for each option

---

## ✅ Coverage Map

| Topic          | Where it appears                        |
|----------------|-----------------------------------------|
| Variables      | Parts 1, 2                              |
| Conditions     | Problems 1, 2, 8                        |
| Loops          | All parts                               |
| Functions      | Problems 3, 4, 7                        |
| Arrays / Lists | Problems 3, 4, 5                        |
| Strings        | Problem 7                               |
| File I/O       | Problems 5, 6                           |
| OOP            | Part 4                                  |
| Error Handling | Part 5, Part 8                          |

---

## 💡 Tips

- Build incrementally — finish Part 1 before moving to Part 2.
- Get the Python version working first, then port to Java.
- For Java, use `BufferedReader` or `Scanner` for file I/O.
- For Python, use the built-in `csv` module.
- Commit after each Part so you can track your progress.