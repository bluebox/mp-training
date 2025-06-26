def get_student_info():
    studentsdata = [
        (1, "Surya Chandra Teja", (20, 50, 60)),
        (2, "Sai Chandra Teja", (17, 11, 45)),
        (3, "Rahul", (57, 88, 15)),
        (4, "Sumanth", (41, 76, 65))
    ]

    return studentsdata

returned_students_data = get_student_info()
for i in returned_students_data:
    student_id, student_name, student_scores = i
    avg_score = (student_scores[0]+student_scores[1]+student_scores[2])/3
    floor_avg_value =  (student_scores[0]+student_scores[1]+student_scores[2])//3
    print(student_name + ": ", avg_score)
