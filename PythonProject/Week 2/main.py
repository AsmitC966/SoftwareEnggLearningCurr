import pandas as pd
from loader import load_all_data
from queries import (
    q1_highest_score_in_subject,
    q2_teacher_taught_subject,
    q3_avg_max_min_score,
    q4_dept_wise_student_count,
    q5_most_intensive_classroom_schedule,
    q6_student_subjects_marks,
    q7_teachers_in_dept_assigned_subjects,
    q8_most_packed_weekday,
    q9_rank_students_by_percentage,
    q10_teachers_teaching_more_than_3_subjects
)

print("Loading University Data System (Pandas)...")
data = load_all_data()

students    = data['students']
marks       = data['marks']
subjects    = data['subjects']
teachers    = data['teachers']
sessions    = data['sessions']
assignments = data['assignments']
departments = data['departments']

print("\n" + "=" * 60)
print("QUERY RESULTS (Python Pandas - DataFrame Ops)")
print("=" * 60)

print("\n[Q1] Highest score in SUB101:")
print(f"  → {q1_highest_score_in_subject(marks, students, 'SUB101')}")

print("\n[Q2] Teachers who taught SUB101:")
print(f"  → {q2_teacher_taught_subject(assignments, teachers, 'SUB101')}")

print("\n[Q3] Stats for SUB101:")
print(f"  → {q3_avg_max_min_score(marks, 'SUB101')}")

print("\n[Q4] Department-wise student count:")
for dept_id, count in sorted(q4_dept_wise_student_count(students).items(), key=lambda x: x[0]):
    print(f"  → Dept {dept_id}: {count} students")

print("\n[Q5] Most intensive classroom:")
print(f"  → {q5_most_intensive_classroom_schedule(sessions, departments)}")

print("\n[Q6] Student subjects + marks (first 3 students):")
for name, subs in list(q6_student_subjects_marks(students, marks, subjects).items())[:3]:
    print(f"  → {name}: {subs}")

print("\n[Q7] Teachers in Dept 101 + assigned subjects:")
for name, subs in q7_teachers_in_dept_assigned_subjects(teachers, assignments, subjects, 101).items():
    print(f"  → {name}: {subs}")

print("\n[Q8] Most packed weekday:")
print(f"  → {q8_most_packed_weekday(sessions)}")

print("\n[Q9] Top 5 students by percentage:")
for i, r in enumerate(q9_rank_students_by_percentage(marks, students)[:5], 1):
    print(f"  {i}. {r['name']}: {r['percentage']}%")

print("\n[Q10] Teachers teaching >3 subjects:")
result = q10_teachers_teaching_more_than_3_subjects(assignments, teachers)
print(f"  → {result if result else 'None found'}")

print("\n" + "=" * 60)
print("All queries executed using Pandas.")