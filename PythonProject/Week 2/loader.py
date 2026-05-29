import pandas as pd

def load_all_data(data_dir='data'):
    """Load all 8 CSV files into pandas DataFrames"""
    files = [
        'departments', 'streams', 'students', 'subjects',
        'student_marks', 'teachers', 'class_sessions', 'teacher_assignments'
    ]
    return {name: pd.read_csv(f'{data_dir}/{name}.csv') for name in files}