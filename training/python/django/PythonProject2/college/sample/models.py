from django.db import models

class Department(models.Model):
    dept_id = models.AutoField(primary_key=True)
    dept_name = models.CharField(max_length=100)

    class Meta:
        db_table = 'department'


class Student(models.Model):
    student_id = models.AutoField(primary_key=True)
    first_name = models.CharField(max_length=100)
    last_name = models.CharField(max_length=100)
    email = models.CharField(unique=True, max_length=100)
    enrollment_year = models.TextField()
    dept = models.ForeignKey(Department, models.DO_NOTHING)

    class Meta:
        db_table = 'student'


class StudentProfile(models.Model):
    student = models.OneToOneField(Student, models.DO_NOTHING, primary_key=True)
    dob = models.DateField(blank=True, null=True)
    address = models.CharField(max_length=255, blank=True, null=True)
    phone = models.CharField(max_length=15)

    class Meta:
        db_table = 'student_profile'


class Teacher(models.Model):
    teacher_id = models.AutoField(primary_key=True)
    first_name = models.CharField(max_length=100)
    last_name = models.CharField(max_length=100)
    email = models.CharField(unique=True, max_length=100)
    dept = models.ForeignKey(Department, models.DO_NOTHING)

    class Meta:
        db_table = 'teacher'


class Course(models.Model):
    course_id = models.AutoField(primary_key=True)
    course_name = models.CharField(max_length=100)
    credits = models.DecimalField(max_digits=3, decimal_places=1)
    dept = models.ForeignKey('Department', models.DO_NOTHING)

    class Meta:
        db_table = 'course'


class Enrollment(models.Model):
    enrollment_id = models.AutoField(primary_key= True)
    student = models.ForeignKey('Student', models.DO_NOTHING)
    course = models.ForeignKey(Course, models.DO_NOTHING)
    internal_marks = models.DecimalField(max_digits=4, decimal_places=1, blank=True, null=True)
    external_marks = models.DecimalField(max_digits=4, decimal_places=1, blank=True, null=True)

    class Meta:
        db_table = 'enrollment'
        unique_together = (('student', 'course'),)


class TeacherCourse(models.Model):
    teacher_course_id = models.AutoField(primary_key = True)
    teacher = models.ForeignKey(Teacher, models.DO_NOTHING)
    course = models.ForeignKey(Course, models.DO_NOTHING)

    class Meta:
        db_table = 'teacher_course'
        unique_together = (('teacher', 'course'),)
