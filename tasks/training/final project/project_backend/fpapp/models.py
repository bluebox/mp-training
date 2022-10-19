
from asyncio.windows_events import NULL
from email.policy import default
from enum import unique

from django.db import models
import uuid
from django.contrib.auth.models import User
from datetime import datetime    
from django.contrib.auth.models import AbstractUser


class User(AbstractUser):
    mobile_no = models.CharField(max_length=10, null=False, default='')
    address= models.CharField(max_length=200, null=False, default='')
    user_type=models.CharField(max_length=50, default="Admin")

    def __str__(self):
        return self.username


class Student(models.Model):
    user = models.OneToOneField(User, on_delete=models.CASCADE, primary_key=True)
    reqister_number = models.CharField(max_length=20, null=False, blank=False, unique=True, default=400)
    college_name = models.CharField(max_length=50, null=False, blank=False, default="")

    def __str__(self):
        return self.user.first_name  + ' ' + self.user.last_name


class Teacher(models.Model):
    user = models.OneToOneField(User, on_delete=models.CASCADE, primary_key=True)
    qualification = models.CharField(max_length=20, null=False, blank=False, default='')
    position = models.CharField(max_length=20,null=False, blank=False, default='')
    
    def __str__(self):
        return self.user.first_name + ' ' + self.user.last_name


class Course(models.Model):
    course_name = models.CharField(max_length=400, null=False, blank=False)
    total_marks = models.PositiveIntegerField()
    teacher_id =models.ForeignKey("Teacher", on_delete=models.SET_NULL, null=True, blank=True)
    

    def __str__(self):
        return self.course_name


class Question(models.Model):
    question_name = models.TextField(max_length=10000, null=False, blank=False)
    option1=models.CharField(max_length=200)
    option2=models.CharField(max_length=200)
    option3=models.CharField(max_length=200)
    option4=models.CharField(max_length=200)
    options=(('option1','option1'),('option2','option2'),('option3','option3'),('option4','option4'))
    answer=models.CharField(max_length=200,choices=options)
    course= models.ForeignKey("Course", on_delete=models.CASCADE, null=True, blank=True)

    def __str__(self):
        return self.question_name





