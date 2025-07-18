# This is an auto-generated Django model module.
# You'll have to do the following manually to clean this up:
#   * Rearrange models' order
#   * Make sure each model has one field with primary_key=True
#   * Make sure each ForeignKey and OneToOneField has `on_delete` set to the desired behavior
#   * Remove `managed = False` lines if you wish to allow Django to create, modify, and delete the table
# Feel free to rename the models, but don't rename db_table values or field names.
from dataclasses import fields
from datetime import timezone

from django.db import models

class Courses(models.Model):
    course_id = models.AutoField(primary_key=True)
    course_code = models.CharField(unique=True, max_length=20)
    course_name = models.CharField(max_length=100)
    department = models.ForeignKey('Departments', on_delete=models.SET_NULL, blank=True, null=True)
    professor = models.ForeignKey('Professors', on_delete=models.SET_NULL, blank=True, null=True)
    year = models.IntegerField(blank=True, null=True)

    class Meta:
        managed = True
        db_table = 'courses'
        indexes=[
            models.Index(fields=['course_code'],name='idx_course_code'),
        ]

class Departments(models.Model):
    department_id = models.AutoField(primary_key=True)
    department_name = models.CharField(unique=True, max_length=50)

    class Meta:
        managed = True
        db_table = 'departments'
class Enroll_Status(models.TextChoices):
    ENROLLED = 'enrolled', 'Enrolled'
    DROPPED = 'dropped', 'Dropped'
    COMPLETED = 'completed', 'Completed'
    FAILED = 'failed', 'Failed'
class Enrollments(models.Model):
    enrollment_id = models.AutoField(primary_key=True)
    student = models.ForeignKey('Student', on_delete=models.CASCADE,null=True,blank=True)
    course = models.ForeignKey('Courses', on_delete=models.CASCADE,null=True,blank=True)
    enroll_date = models.DateField(blank=True, null=True)
    status = models.CharField(max_length=9,choices=Enroll_Status.choices,default=Enroll_Status.ENROLLED)

    class Meta:
        managed = True
        db_table = 'enrollments'
        unique_together = (('student', 'course'),)
        indexes=[
            models.Index(fields=['student','course'],name='idx_student_course'),
        ]


class Grades(models.Model):
    grade_id = models.AutoField(primary_key=True)
    enrollment = models.ForeignKey(Enrollments, on_delete=models.CASCADE,null=True,blank=True)
    grade = models.DecimalField(max_digits=5, decimal_places=2, blank=True, null=True)

    class Meta:
        managed = True
        db_table = 'grades'

class Professors(models.Model):
    professor_id = models.AutoField(primary_key=True)
    first_name = models.CharField(max_length=50)
    last_name = models.CharField(max_length=50)
    email = models.CharField(unique=True, max_length=100)
    hire_date = models.DateField(blank=True, null=True)
    last_date = models.DateField(blank=True, null=True)
    department = models.ForeignKey(Departments, on_delete=models.SET_NULL, blank=True, null=True)

    @property
    def is_active(self):
        return not self.last_date or self.last_date > timezone.now().date()
    class Meta:
        managed = True
        db_table = 'professors'

class Status(models.TextChoices):
    ACTIVE = 'active', 'Active'
    GRADUATED = 'graduated', 'Graduated'
    DROPPED = 'dropped','Dropped'

class Student(models.Model):
    student_id = models.AutoField(primary_key=True)
    first_name = models.CharField(max_length=50)
    last_name = models.CharField(max_length=50)
    email = models.CharField(unique=True, max_length=50)
    dob = models.DateField(blank=True, null=True)
    enrolled_date = models.DateField(blank=True, null=True)
    department = models.ForeignKey(Departments, on_delete=models.SET_NULL, blank=True, null=True)
    status = models.CharField(max_length=10,choices=Status.choices,default=Status.ACTIVE)
    graduation_date = models.DateField(blank=True, null=True)

    @property
    def full_name(self):
        return f"{self.first_name} {self.last_name}"

    class Meta:
        managed = True
        db_table = 'student'
        indexes=[
            models.Index(fields=['email'],name='idx_students_email'),
        ]
# Add timestamps (created_at, updated_at) using an abstract base class.
#
# Use UUIDs for primary keys (if you're planning microservices or exposing IDs in public APIs).
#
# Normalize department choices or constraints.
#
# Add role/permission tracking if multiple user types will access data.