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
    course_name = models.CharField(max_length=100)
    credits = models.DecimalField(max_digits=3, decimal_places=1)
    dept = models.ForeignKey('Department', models.DO_NOTHING, blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'course'


class Department(models.Model):
    dept_id = models.AutoField(primary_key=True)
    dept_name = models.CharField(max_length=100)

    class Meta:
        managed = False
        db_table = 'department'


class Enrollment(models.Model):
    student = models.OneToOneField('Student', models.DO_NOTHING, primary_key=True)  # The composite primary key (student_id, course_id) found, that is not supported. The first column is selected.
    course = models.ForeignKey(Course, models.DO_NOTHING)
    internal_marks = models.DecimalField(max_digits=4, decimal_places=1, blank=True, null=True)
    external_marks = models.DecimalField(max_digits=4, decimal_places=1, blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'enrollment'
        unique_together = (('student', 'course'),)


class Student(models.Model):
    student_id = models.AutoField(primary_key=True)
    first_name = models.CharField(max_length=100)
    last_name = models.CharField(max_length=100)
    email = models.CharField(unique=True, max_length=100)
    enrollment_year = models.TextField()  # This field type is a guess.
    dept = models.ForeignKey(Department, models.DO_NOTHING, blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'student'


class StudentProfile(models.Model):
    student = models.OneToOneField(Student, models.DO_NOTHING, primary_key=True)
    dob = models.DateField(blank=True, null=True)
    address = models.CharField(max_length=255, blank=True, null=True)
    phone = models.CharField(max_length=15, blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'student_profile'


class Teacher(models.Model):
    teacher_id = models.AutoField(primary_key=True)
    first_name = models.CharField(max_length=100)
    last_name = models.CharField(max_length=100)
    email = models.CharField(unique=True, max_length=100)
    dept = models.ForeignKey(Department, models.DO_NOTHING, blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'teacher'


class TeacherCourse(models.Model):
    teacher = models.OneToOneField(Teacher, models.DO_NOTHING, primary_key=True)  # The composite primary key (teacher_id, course_id) found, that is not supported. The first column is selected.
    course = models.ForeignKey(Course, models.DO_NOTHING)

    class Meta:
        managed = False
        db_table = 'teacher_course'
        unique_together = (('teacher', 'course'),)
