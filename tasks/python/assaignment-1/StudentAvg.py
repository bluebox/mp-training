import math
import random


def get_student_info()->list:
    students = []
    names = ["Madhav","Ram","Rahim","Anurag","Harsha","kalyan","Sai","Sandeep","Samual","John","Lakshmi","Sita"]
    for i in range(1,21):
        stu = []
        # student_id = ""
        student_id = "A" + str(i)
        stu.append(student_id)
        name = random.choice(names)
        stu.append(name)
        scores = []
        for j in range(10):
            scores.append(random.randint(1,100))
        stu.append(tuple(scores))
        students.append(tuple(stu))
    return students

# print(get_student_info())

student_details = get_student_info()
for id,name,scores in student_details:
    print(f"{name} baring id no. {id} has got an average score of {sum(scores)/len(scores)}")

