from django.db import models

from .validators import validatePassword


class ValidatorsTask(models.Model):
    uid = models.IntegerField(primary_key=True)
    uname = models.CharField(max_length=50,)
    upass = models.CharField(max_length=50, validators=[validatePassword])  # works with ModelForm


class Course(models.Model):
    courseid = models.IntegerField(primary_key=True)
    coursename = models.CharField(max_length=100)
    courseduration = models.FloatField()

    class Meta:
        managed = True

class Student(models.Model):

    rollno = models.CharField(max_length=20, primary_key=True)
    sname = models.CharField(max_length=50)
    email = models.EmailField(max_length=60)
    phone = models.CharField(max_length=13)
    address = models.TextField(max_length=250)
    course = models.ManyToManyField(Course)

    class Meta:
        managed = False




    def __str__(self):
        return self.uname
