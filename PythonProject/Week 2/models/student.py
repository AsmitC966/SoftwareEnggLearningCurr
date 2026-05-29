class Student:
    def __init__(self, student_id, name, email, dept_id, stream_id, enrollment_year, current_semester):
        self.student_id = student_id
        self.name = name
        self.email = email
        self.dept_id = int(dept_id)
        self.stream_id = stream_id
        self.enrollment_year = int(enrollment_year)
        self.current_semester = int(current_semester)

    def __repr__(self):
        return f"Student({self.student_id}, {self.name}, Dept:{self.dept_id})"
