class Mark:
    def __init__(self, record_id, student_id, subject_id, semester, marks_obtained, max_marks, exam_date):
        self.record_id = record_id
        self.student_id = student_id
        self.subject_id = subject_id
        self.semester = int(semester)
        self.marks_obtained = float(marks_obtained)
        self.max_marks = int(max_marks)
        self.exam_date = exam_date

    @property
    def percentage(self):
        return (self.marks_obtained / self.max_marks * 100) if self.max_marks > 0 else 0

    def __repr__(self):
        return f"Mark({self.record_id}, {self.student_id}, {self.subject_id}: {self.marks_obtained}/{self.max_marks})"
