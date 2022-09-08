# from operator import mod
from django.db import models

class professor(models.Model):
    id = models.IntegerField(primary_key=True)
    name = models.CharField(max_length=50)
    salary = models.IntegerField()
    password = models.CharField(max_length=25,null=True)
    def __str__(self):
        return "{}".format(self.id)

class professor_attendance(models.Model):
    professor_id = models.ForeignKey(professor,on_delete=models.CASCADE)
    total_leaves = models.IntegerField()
    total_working_days = models.IntegerField()
    def __str__(self):
        return "{}".format(self.professor_id)

class department(models.Model):
    id = models.IntegerField(primary_key=True)
    name = models.CharField(max_length=40)
    def __str__(self):
        return "{} ".format(self.id,self.name)

class subjects(models.Model):
    id = models.IntegerField(primary_key=True)
    name = models.CharField(max_length=40)
    professor_id = models.ForeignKey(professor,on_delete=models.CASCADE)
    semester = models.IntegerField()
    year = models.IntegerField()
    credits = models.IntegerField()


    def __str__(self):
        return "{} {}  ".format(self.id,self.professor_id)


class department_subjects(models.Model):
    department_id = models.ForeignKey(department,on_delete=models.CASCADE)
    subject_id = models.ForeignKey(subjects,on_delete=models.CASCADE)
    def __str__(self):
        return "{} {} ".format(self.department_id, self.subject_id)

class students(models.Model):
    name = models.CharField(max_length=50)
    department_id = models.ForeignKey(department,on_delete=models.CASCADE)
    student_id = models.IntegerField(primary_key=True)
    password = models.CharField(max_length=25,null=True)
    mail_id = models.EmailField(default="cms@gmail.com")
    def __str__(self):
        return "{} ".format(self.student_id)

class student_fee_details(models.Model):
    student_id = models.ForeignKey(students,on_delete=models.CASCADE)
    fee_due = models.IntegerField()
    fee_paid = models.IntegerField()
    total_fee = models.IntegerField()
    def __str__(self):
        return "{}".format(self.student_id)


class students_schedule(models.Model):
    student_id  = models.ForeignKey(professor,on_delete=models.CASCADE)
    subject_id= models.ForeignKey(subjects,on_delete=models.CASCADE)
    from_time = models.TimeField()
    to_time = models.TimeField()
    def __str__(self):
        return "{} {}".format(self.student_id,self.subject_id)



class students_attendence(models.Model):
    student_id = models.ForeignKey(students,on_delete=models.CASCADE)
    total_absent = models.IntegerField()
    total_attendance = models.IntegerField()

    def __str__(self):
        return "{}".format(self.student_id)

class admin_details(models.Model):
    admin_id = models.IntegerField()
    password = models.CharField(max_length=10)
    def __str__(self):
        return '{} '.format(self.admin_id)