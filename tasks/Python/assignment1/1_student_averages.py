def get_student_info():
    students_data = [
        (1, "Abhi", (88, 89, 90)),
        (2, "Balu", (68, 97, 80)),
        (3, "Sai", (82, 79, 70)),
        (4, "Ram", (98, 89, 80)),
        (5, "Uday", (83, 76, 99)),
    ]

    return students_data

data = get_student_info()
for student in data:
    student_id, student_name, (score1, score2, score3) = student
    average_score = (score1 + score2 + score3) / 3
    print(f"Student Name: {student_name} Average score: {average_score}")