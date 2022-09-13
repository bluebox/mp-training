from django.db import models
from Admin.models import class_details
from Faculty.models import faculty_details

# Create your models here.
class student_details(models.Model):
    first_name = models.CharField(max_length=20)
    last_name = models.CharField(max_length=20)
    stud_username = models.CharField(max_length=35, primary_key=True)
    password = models.CharField(max_length=15)
    gender = models.CharField(max_length=6)
    date_of_birth = models.DateField(default=None)
    father_name = models.CharField(max_length=35)
    roll_no = models.IntegerField()
    user_type = models.CharField(max_length=15, default="")
    status = models.BooleanField(default=False)
    class_name = models.ForeignKey(class_details, max_length=35, on_delete=models.CASCADE)

class feedback_by_student(models.Model):
    to_faculty = models.ForeignKey(faculty_details, max_length=35, on_delete=models.CASCADE)
    stud_username = models.ForeignKey(student_details, max_length=35, on_delete=models.CASCADE)
    month = models.CharField(max_length=12)
    year = models.IntegerField()
    Q1 = models.IntegerField()
    Q2 = models.IntegerField()
    Q3 = models.IntegerField()
    Q4 = models.IntegerField()
    Q5 = models.IntegerField()
    Q6 = models.IntegerField()
    Q7 = models.IntegerField()
    Q8 = models.IntegerField()
    remarks = models.TextField(max_length=200)
    GPA = models.DecimalField(decimal_places=2, max_digits=3)

class feedback_by_faculty(models.Model):
    to_student = models.ForeignKey(student_details, max_length=35, on_delete=models.CASCADE)
    facul_username = models.ForeignKey(faculty_details, max_length=35, on_delete=models.CASCADE)
    month = models.CharField(max_length=12)
    year = models.IntegerField(max_length=4)
    Q1 = models.IntegerField()
    Q2 = models.IntegerField()
    Q3 = models.IntegerField()
    Q4 = models.IntegerField()
    Q5 = models.IntegerField()
    remarks = models.TextField(max_length=200)
    GPA = models.DecimalField(decimal_places=2, max_digits=3)