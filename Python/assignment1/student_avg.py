def get_student_info():
    student1 = (1, "Anand", (90, 90, 90))
    student2 = (2, "abhi", (90, 100, 90))
    student3 = (3, "naresh", (80, 90, 90))
    student4 = (4, "sai", (90, 70, 90))
    student5 = (5, "Anand", (90, 60, 90))
    student6 = (6, "abhi", (90, 50, 90))
    student7 = (7, "naresh", (30, 90, 90))
    student8 = (8, "sai", (90, 90, 40))
    student9 = (9, "Anand", (90, 70, 40))
    student10 = (10, "abhi", (70, 90, 90))
    ans=[student1,student2,student3,student4,student5,student6,student7,student8,student9,student10]
    return ans

ans=get_student_info()
for i in ans:
    id,name,score=i
    print(id,name,(sum(score)/len(score)))
