class ClassSession:
    def __init__(self, session_id, subject_id, teacher_id, day, start_time, end_time, room, semester):
        self.session_id = session_id
        self.subject_id = subject_id
        self.teacher_id = teacher_id
        self.day = day
        self.start_time = start_time
        self.end_time = end_time
        self.room = room
        self.semester = int(semester)

    def __repr__(self):
        return f"Session({self.session_id}, {self.subject_id}, {self.day} {self.start_time})"
