from .models import Student


class StudentController:
    student_meta = [i.name for i in  Student._meta.get_fields()]
    @staticmethod
    def register_student_data(data):
        print(StudentController.student_meta)

