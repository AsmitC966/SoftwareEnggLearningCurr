import pandas as pd

def q1_highest_score_in_subject(marks_df, students_df, subject_id):
    """Q1: Highest score in a subject + student name"""
    subject_marks = marks_df[marks_df['subject_id'] == subject_id] # marks of the target subject
    if subject_marks.empty:
        return None
    
    idx = subject_marks['marks_obtained'].idxmax()
    top_record = subject_marks.loc[idx]
    
    student_row = students_df[students_df['student_id'] == top_record['student_id']]
    name = student_row['name'].iloc[0] if not student_row.empty else 'Unknown'
    
    return {'student': name, 'marks': float(top_record['marks_obtained'])}

def q2_teacher_taught_subject(assignments_df, teachers_df, subject_id):
    """Q2: Which teacher(s) taught a subject"""
    t_ids = assignments_df[assignments_df['subject_id'] == subject_id]['teacher_id'].unique()
    if len(t_ids) == 0:
        return []
    return teachers_df[teachers_df['teacher_id'].isin(t_ids)]['name'].tolist()

def q3_avg_max_min_score(marks_df, subject_id):
    """Q3: Avg/Max/Min score for a subject"""
    scores = marks_df[marks_df['subject_id'] == subject_id]['marks_obtained']
    if scores.empty:
        return None
    return {
        'avg': float(scores.mean()),
        'max': float(scores.max()),
        'min': float(scores.min())
    }

def q4_dept_wise_student_count(students_df):
    """Q4: Department-wise student count"""
    return students_df['dept_id'].value_counts().to_dict()


def q5_most_intensive_classroom_schedule(sessions_df, subjects_df, departments_df):
    """Q5: Find the department name with the highest number of class sessions"""
    # 1. Join sessions to subjects to get the dept_id for each session
    merged = sessions_df.merge(subjects_df[['subject_id', 'dept_id']], on='subject_id', how='left')
    # 2. Join that result to departments to get the human-readable dept_name
    merged = merged.merge(departments_df[['dept_id', 'dept_name']], on='dept_id', how='left')
    # 3. Count how many times each department name appears
    dept_counts = merged['dept_name'].value_counts()
    if dept_counts.empty:
        return None
    # 4. Return the top department name and its total session count
    return {'department': dept_counts.index[0], 'total_sessions': int(dept_counts.iloc[0])}


def q6_student_subjects_marks(students_df, marks_df, subjects_df):
    """Q6: Each student's subjects + marks"""
    merged = (marks_df
              .merge(subjects_df[['subject_id', 'subject_name']], on='subject_id', how='left')
              .merge(students_df[['student_id', 'name']], on='student_id', how='left'))
    
    result = {}
    for name, group in merged.groupby('name'):
        result[name] = [
            {'subject': row['subject_name'], 'marks': float(row['marks_obtained'])}
            for _, row in group.iterrows()
        ]
    return result

def q7_teachers_in_dept_assigned_subjects(teachers_df, assignments_df, subjects_df, dept_id):
    """Q7: Teachers in a department + their assigned subjects"""
    dept_teachers = teachers_df[teachers_df['dept_id'] == dept_id]
    assigned = assignments_df.merge(subjects_df[['subject_id', 'subject_name']], 
                                    on='subject_id', how='left')
    
    result = {}
    for _, teacher in dept_teachers.iterrows():
        tid = teacher['teacher_id']
        subs = assigned[assigned['teacher_id'] == tid]['subject_name'].dropna().tolist()
        result[teacher['name']] = subs
    return result

def q8_most_packed_weekday(sessions_df):
    """Q8: Busiest weekday by class count"""
    day_counts = sessions_df['day'].value_counts()
    if day_counts.empty:
        return None
    return {'day': day_counts.index[0], 'count': int(day_counts.iloc[0])}

def q9_rank_students_by_percentage(marks_df, students_df):
    """Q9: Rank students by overall percentage"""
    grouped = marks_df.groupby('student_id').agg(
        obtained=('marks_obtained', 'sum'),
        max_total=('max_marks', 'sum')
    ).reset_index()
    
    grouped['percentage'] = ((grouped['obtained'] / grouped['max_total']) * 100).round(2)
    ranked = (grouped
              .merge(students_df[['student_id', 'name']], on='student_id', how='left')
              .sort_values('percentage', ascending=False))
    
    return ranked[['name', 'percentage']].to_dict(orient='records')

def q10_teachers_teaching_more_than_3_subjects(assignments_df, teachers_df):
    """Q10: Teachers teaching >3 distinct subjects"""
    counts = (assignments_df
              .groupby('teacher_id')['subject_id']
              .nunique()
              .reset_index())
    counts.columns = ['teacher_id', 'subject_count']
    
    over_three = counts[counts['subject_count'] > 3]
    if over_three.empty:
        return []
        
    result = over_three.merge(teachers_df[['teacher_id', 'name']], 
                              on='teacher_id', how='left')
    return result[['name', 'subject_count']].to_dict(orient='records')