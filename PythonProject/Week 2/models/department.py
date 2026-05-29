class Department:
    def __init__(self, dept_id, dept_name, dept_head_id, building, budget):
        self.dept_id = int(dept_id)
        self.dept_name = dept_name
        self.dept_head_id = dept_head_id
        self.building = building
        self.budget = float(budget)

    def __repr__(self):
        return f"Department({self.dept_id}, {self.dept_name})"
