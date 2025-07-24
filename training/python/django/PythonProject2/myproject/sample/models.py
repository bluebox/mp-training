# This is an auto-generated Django model module.
# You'll have to do the following manually to clean this up:
#   * Rearrange models' order
#   * Make sure each model has one field with primary_key=True
#   * Make sure each ForeignKey and OneToOneField has `on_delete` set to the desired behavior
#   * Remove `managed = False` lines if you wish to allow Django to create, modify, and delete the table
# Feel free to rename the models, but don't rename db_table values or field names.
from django.db import models


class Course(models.Model):
    course_id = models.AutoField(primary_key=True)
    course_name = models.CharField(max_length=255)
    dept_id = models.IntegerField()
    credits = models.DecimalField(max_digits=3, decimal_places=1)

    class Meta:
        managed = False
        db_table = 'course'


class CourseAssignment(models.Model):
    course_id = models.IntegerField(primary_key=True)  # The composite primary key (course_id, teacher_id) found, that is not supported. The first column is selected.
    teacher_id = models.IntegerField()

    class Meta:
        managed = False
        db_table = 'course_assignment'
        unique_together = (('course_id', 'teacher_id'),)


class CourseEnrollment(models.Model):
    student_id = models.IntegerField()
    course_id = models.IntegerField()
    teacher_id = models.IntegerField()
    internal_marks = models.DecimalField(max_digits=3, decimal_places=1)
    external_marks = models.DecimalField(max_digits=3, decimal_places=1)

    class Meta:
        managed = False
        db_table = 'course_enrollment'


class Department(models.Model):
    dept_id = models.AutoField(primary_key=True)
    dept_name = models.CharField(max_length=255)

    class Meta:
        managed = False
        db_table = 'department'


class Student(models.Model):
    student_id = models.AutoField(primary_key=True)
    first_name = models.CharField(max_length=255)
    last_name = models.CharField(max_length=255)
    email = models.CharField(max_length=255)
    enrollment_year = models.TextField()  # This field type is a guess.
    dept_id = models.IntegerField()

    class Meta:
        managed = False
        db_table = 'student'


class Teacher(models.Model):
    teacher_id = models.AutoField(primary_key=True)
    first_name = models.CharField(max_length=255)
    last_name = models.CharField(max_length=255)
    email = models.CharField(max_length=255)
    dept_id = models.IntegerField()

    class Meta:
        managed = False
        db_table = 'teacher'
