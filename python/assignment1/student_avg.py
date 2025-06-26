"""This module calculates the average score of students."""


def get_student_info():
    """ this return a list"""
    student1 = (1, "Anand", (90, 90, 90))
    student2 = (2, "Abhi", (90, 100, 90))
    student3 = (3, "Naresh", (80, 90, 90))
    student4 = (4, "Sai", (90, 70, 90))
    student5 = (5, "Anand", (90, 60, 90))
    student6 = (6, "Abhi", (90, 50, 90))
    student7 = (7, "Naresh", (30, 90, 90))
    student8 = (8, "Sai", (90, 90, 40))
    student9 = (9, "Anand", (90, 70, 40))
    student10 = (10, "Abhi", (70, 90, 90))

    student_val = [
        student1, student2, student3, student4, student5,
        student6, student7, student8, student9, student10,
    ]
    return student_val


student_data = get_student_info()

for student_id, name, scores in student_data:
    average = round(sum(scores) / len(scores), 2)
    print(student_id, name, average)
