

class students:
    list1 = []
    def __init__(self,nameofstudent,heightofstudent):
        self.nameofstudent = nameofstudent
        self.heightofstudent = heightofstudent

    def add_to_students_record(self):
        students.list1.append([self.nameofstudent,self.heightofstudent])

    @staticmethod
    def display():
        if students.list1:
            print("Students_name".center(15),"students_height")
            for i in students.list1:
                print(i[0].center(15),i[1])
        else:
            print("No student data found ")
student1 = students("saksham",5.11)
student2 = students("Rima" ,4.1)
student3 = students("ram",5.12)
student4 = students("himanshu",6.11)
student1.add_to_students_record()
student2.add_to_students_record()
student3.add_to_students_record()
student4.add_to_students_record()


students.display()
