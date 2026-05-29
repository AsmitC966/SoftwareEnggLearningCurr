class Subject:
    def __init__(self, subject_id, subject_name, dept_id, credits, semester_offered, max_marks):
        self.subject_id = subject_id
        self.subject_name = subject_name
        self.dept_id = int(dept_id)
        self.credits = int(credits)
        self.semester_offered = int(semester_offered)
        self.max_marks = int(max_marks)

    def __repr__(self):
        return f"Subject({self.subject_id}, {self.subject_name})"
