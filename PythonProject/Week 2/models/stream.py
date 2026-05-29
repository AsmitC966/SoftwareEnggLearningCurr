class Stream:
    def __init__(self, stream_id, stream_name, dept_id, duration_years, fees_per_semester):
        self.stream_id = stream_id
        self.stream_name = stream_name
        self.dept_id = int(dept_id)
        self.duration_years = int(duration_years)
        self.fees_per_semester = float(fees_per_semester)

    def __repr__(self):
        return f"Stream({self.stream_id}, {self.stream_name})"
