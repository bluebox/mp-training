from django.db import models

# # Create your models here.
class Student(models.Model):
    first_name = models.CharField(max_length=30)
    last_name = models.CharField(max_length=30)
    cls=models.CharField(max_length=8)
    teacher=models.ForeignKey("Teacher",on_delete = models.CASCADE)

class Teacher(models.Model):
    name=models.CharField(max_length=20)


