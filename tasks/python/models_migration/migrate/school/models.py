from django.db import models

class Student(models.Model):
    Name = models.CharField(max_length=100,null=False)
    Age = models.IntegerField(null=False)
    Class = models.ForeignKey('Classes', on_delete=models.CASCADE, null=False)
    Section = models.CharField(max_length=3,null=False)
    joining_date = models.DateField(auto_now_add=True,null=False)
    attendance = models.IntegerField(null=False)
    class Meta:
        db_table = 'student'


class Classes(models.Model):
    Class_id = models.IntegerField(null=False,primary_key=True)
    Section = models.CharField(max_length=3,null=False)
    teacher = models.ForeignKey('Teacher', on_delete=models.CASCADE, null=False)
    class Meta:
        db_table = 'classes'
        unique_together = ('Section', 'Class_id')

class Teacher(models.Model):
    Name = models.CharField(max_length=100,null=False)
    level = models.CharField(max_length=2,null=False,choices=(('p','primary'),('h','high school'),('l','low school')))
    joining_date = models.DateField(auto_now_add=True,null=False)
    experience = models.IntegerField(null=False)
    class Meta:
        db_table = 'teacher'

class Subject(models.Model):
    Name = models.CharField(max_length=100,null=False)
    Class = models.ForeignKey('Classes', on_delete=models.CASCADE, null=False)
    Teacher = models.ForeignKey('Teacher', on_delete=models.CASCADE, null=False)
    class Meta:
        db_table = 'subject'

class Results(models.Model):
    Class = models.ForeignKey('Classes', on_delete=models.CASCADE, null=False)
    student = models.ForeignKey('Student', on_delete=models.CASCADE, null=False)
    subject = models.ForeignKey('Subject', on_delete=models.CASCADE, null=False)
    grade = models.IntegerField(null=False)
    percentage = models.FloatField(null=False)
    class Meta:
        db_table = 'results'

class StudentProfile(models.Model):
    Student = models.OneToOneField('Student', on_delete=models.CASCADE, null=False)
    FatherName = models.CharField(max_length=100,null=False)
    MotherName = models.CharField(max_length=100,null=False)
    FatherAge = models.IntegerField(null=False)
    MotherAge = models.IntegerField(null=False)
    address = models.CharField(max_length=100,null=False)
    class Meta:
        db_table = 'student_profile'