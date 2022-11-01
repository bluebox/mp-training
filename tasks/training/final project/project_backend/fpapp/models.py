#pylint:disable=E1101
#pylint:disable=E0102

"""models file"""
from django.db import models
from django.contrib.auth.models import User
from django.contrib.auth.models import AbstractUser

class User(AbstractUser):
    """this is Usertable"""
    mobile_no = models.CharField(max_length=10, null=False, blank=False,unique=True)
    address= models.CharField(max_length=200, null=False, blank=False)
    user_type=models.CharField(max_length=50, default="Admin")

    def __str__(self):
        """this is docstring"""
        return str(self.username)


class Student(models.Model):
    """this is Student table"""
    user = models.OneToOneField(User, on_delete=models.CASCADE, primary_key=True)
    reqister_number = models.CharField(max_length=20, null=False, blank=False, unique=True)
    college_name = models.CharField(max_length=50, null=False, blank=False)

    def __str__(self):
        """this is docstring"""
        return str(self.user.first_name)  + ' ' + str(self.user.last_name)


class Teacher(models.Model):
    """this is Teacher table"""
    user = models.OneToOneField(User, on_delete=models.CASCADE, primary_key=True)
    qualification = models.CharField(max_length=20, null=False, blank=False)
    position = models.CharField(max_length=20,null=False, blank=False)
    def __str__(self):
        """this is docstring"""
        return str(self.user.first_name) + ' ' + str(self.user.last_name)


class Course(models.Model):
    """this is Course Table"""
    course_name = models.CharField(max_length=400, null=False, blank=False)
    total_marks = models.PositiveIntegerField()
    teacher_id =models.ForeignKey("Teacher", on_delete=models.SET_NULL, null=True, blank=True)
    def __str__(self):
        """this is docstring"""
        return str(self.course_name)


class Question(models.Model):
    """this is question table"""
    question_name = models.TextField(max_length=10000, null=False, blank=False)
    option1=models.CharField(max_length=200)
    option2=models.CharField(max_length=200)
    option3=models.CharField(max_length=200)
    option4=models.CharField(max_length=200)
    options=(('option1','option1'),('option2','option2'),
            ('option3','option3'),('option4','option4'))
    answer=models.CharField(max_length=200,choices=options)
    course= models.ForeignKey("Course", on_delete=models.CASCADE, null=True, blank=True)

    def __str__(self):
        """this is docstring"""
        return str(self.question_name)


class Score(models.Model):
    """this is scorecard table"""
    score=models.PositiveIntegerField()
    exam_name=models.CharField(max_length=200, null=False, blank=False)
    date=models.DateTimeField(auto_now=True)
