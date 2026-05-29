class Teacher:
    def __init__(self, teacher_id, name, email, dept_id, specialization, hire_date, salary):
        self.teacher_id = teacher_id
        self.name = name
        self.email = email
        self.dept_id = int(dept_id)
        self.specialization = specialization
        self.hire_date = hire_date
        self.salary = float(salary)

    def __repr__(self):
        return f"Teacher({self.teacher_id}, {self.name}, Dept:{self.dept_id})"
