def get_student_info(studentInfo):
    for studentId, studentName, marks in studentInfo:
        print("Student Id            : ", studentId)
        print("Student Name          : ",studentName)
        s1, s2, s3 = marks
        avg = sum(marks) / len(marks)
        print(f"Average marks obtained: {avg:.2f}")

# Info=[]
# print("Enter student Id: ")
# studentId = int(input())
# Info.append(studentId)
# print("Enter student name: ")
# name = input()
# Info.append(name)
# marks=[]
# print("Enter marks in maths; ")
# s1 = int(input())
# marks.append(s1)
# print("Enter marks in physics: ")
# s2 = int(input())
# marks.append(s2)
# print("Enter marks in chemistry: ")
# s3 = int(input())
# marks.append(s3)
#
# Info.append(tuple(marks))
# print(Info)
# studentInfo=tuple(Info)

studentInfo=(
    (101, 'ravi', (56,76,89)) ,
    (102, 'bhargav', (55,65,78)),
    (103, 'krishna', (50,86,99))
)

print(studentInfo)
get_student_info(studentInfo)
