def get_student_info():
    l=[
    (101, "Alice", (85, 90, 88)),
    (102, "Bob", (78, 82, 80)),
    (103, "Charlie", (92, 87, 85)),
    (104, "Diana", (70, 75, 72)),
    (105, "Ethan", (88, 90, 92)),
    (106, "Fiona", (95, 93, 96)),
    (107, "George", (65, 70, 68)),
    (108, "Hannah", (80, 82, 79)),
    (109, "Ian", (85, 87, 84)),
    (110, "Julia", (90, 92, 94))]
    return l
student_info_list=get_student_info()
for i in student_info_list:
    student_Id,student_name,marks=i
    #marks=marks[0]  
    average_markes=sum(marks)/3
    
    print("Name:"+student_name,"Average Marks:",average_markes)
