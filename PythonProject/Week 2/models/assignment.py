class TeacherAssignment:
    def __init__(self, assignment_id, teacher_id, subject_id, semester, assignment_date):
        self.assignment_id = assignment_id
        self.teacher_id = teacher_id
        self.subject_id = subject_id
        self.semester = int(semester)
        self.assignment_date = assignment_date

    def __repr__(self):
        return f"Assignment({self.assignment_id}, {self.teacher_id} -> {self.subject_id})"
