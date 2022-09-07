from django.db import models

# Create your models here.


class Hod(models.Model):
    hod_id = models.IntegerField(primary_key=True,verbose_name='hod')
    hod_name = models.CharField(max_length=30)
    Professor_id = models.IntegerField()
    def __str__(self):
        return str(self.hod_id)+self.hod_name


class Branch(models.Model):
    branch_id=models.IntegerField(primary_key=True)
    branch_name=models.CharField(max_length=30)
    hod_id=models.ForeignKey(Hod, on_delete=models.CASCADE)
    def __str__(self):
        return str(self.branch_id)+self.branch_name


class Professor(models.Model):
    professor_id = models.IntegerField(primary_key=True)
    professor_name = models.CharField(max_length=30)
    branch_id = models.IntegerField()
    salary = models.IntegerField()
    professor_password = models.CharField(max_length=30)
    def __str__(self):
        return str(self.professor_id)+self.professor_name



class Fee_report(models.Model):
    fee_id = models.IntegerField(primary_key=True)
    total_fee = models.IntegerField()
    fee_due = models.IntegerField()



class Course(models.Model):
    course_id=models.IntegerField(primary_key=True)
    # course_id = models.AutoField()
    course_name = models.CharField(max_length=30)
    employee_id = models.ForeignKey(Professor,on_delete=models.CASCADE)
    def __str__(self):
        return str(self.course_id)+self.course_name


class Exam_branch(models.Model):
    hall_ticket_number = models.IntegerField(primary_key=True)
    due_subjects = models.IntegerField()
    total_subjects = models.IntegerField()



class Student(models.Model):
    student_id = models.IntegerField(primary_key=True)
    student_name = models.CharField(max_length=30)
    Student_mobile = models.CharField(max_length=30)
    Student_email = models.CharField(max_length=30)
    branch_id = models.ForeignKey(Branch,on_delete=models.CASCADE)
    hall_ticket_number = models.ForeignKey(Exam_branch, on_delete=models.CASCADE)
    fee_id = models.ForeignKey(Fee_report, on_delete=models.CASCADE)
    student_password = models.CharField(max_length=30)
    def __str__(self):
        return str(self.student_id)+self.student_name


class Student_Attendance(models.Model):
    student_id = models.ForeignKey(Student,on_delete=models.CASCADE)
    total_days = models.PositiveIntegerField()
    attendance = models.PositiveIntegerField()
    def __str__(self):
        return str(self.student_id)+str(self.attendance)

























