from django.contrib.auth.models import AbstractUser
from django.core.validators import MinValueValidator, MaxValueValidator
from django.db import models


class BaseUser(AbstractUser):
    class Role(models.TextChoices):
        ADMIN = 'admin','Admin'
        TEACHER = 'teacher','Teacher'
        STUDENT = 'student','Student'
    role = models.CharField(max_length=10, choices=Role.choices, default=Role.ADMIN)
class Student(models.Model):
    # id = models.AutoField(unique=True)

    class StatusChoices(models.TextChoices):
        STUDYING = 'S'
        DROPPED = 'D'

    class StudentManager(models.Manager):
        def get_queryset(self):
            return super().get_queryset().filter(status = Student.StatusChoices.STUDYING)
        def dropped(self):
            return super().get_queryset().filter(status = Student.StatusChoices.DROPPED)

    objects = StudentManager()

    is_class_representative = models.BooleanField(default=False)

    Name = models.CharField(max_length=100, null=False, db_index=True)

    user = models.OneToOneField(BaseUser, primary_key=True, on_delete=models.CASCADE, limit_choices_to={'role':BaseUser.Role.STUDENT})

    Class = models.ForeignKey('Classes', on_delete=models.CASCADE, null=False)
    # Section = models.CharField(max_length=3,null=False)
    attendance = models.IntegerField(null=False)
    status = models.CharField(max_length=1, null=False, choices = StatusChoices.choices, default=StatusChoices.STUDYING)
    class Meta:
        db_table = 'student'


class Classes(models.Model):
    Class_id = models.IntegerField(null=False, unique=True)
    Section = models.CharField(max_length=3, null=False)
    teacher = models.ForeignKey('Teacher', on_delete=models.CASCADE, null=False)
    class Meta:
        db_table = 'classes'
        unique_together = ('Section', 'Class_id')






class Teacher(models.Model):
    user = models.OneToOneField(BaseUser, primary_key=True, on_delete=models.CASCADE, limit_choices_to={"role":BaseUser.Role.TEACHER})
    Name = models.CharField(max_length=100, null=False, db_index=True)
    level = models.CharField(max_length=2, null=False, choices=(('p', 'primary'), ('h', 'high school'), ('l', 'low school')))
    joining_date = models.DateField(auto_now_add=True, null=False)
    experience = models.IntegerField(null=False)
    class Meta:
        db_table = 'teacher'

class Subject(models.Model):
    Name = models.CharField(max_length=100, null=False)
    # Class = models.ForeignKey('Classes', on_delete=models.CASCADE, null=False)
    # Teacher = models.ForeignKey('Teacher', on_delete=models.CASCADE, null=False)
    Teacher = models.ManyToManyField('Teacher', through='subject_teacher', related_name='Subjects')
    class Meta:
        db_table = 'subject'


class subject_teacher(models.Model):
    subject = models.ForeignKey('Subject', on_delete=models.CASCADE, null=False)
    teacher = models.ForeignKey('Teacher', on_delete=models.CASCADE, null=False)
    rel_class = models.ForeignKey('Classes', on_delete=models.CASCADE, null=False, related_name='subject_teacher_set')
    class Meta:
        db_table = 'subject_teacher'



class Results(models.Model):
    Class = models.ForeignKey('Classes', on_delete=models.CASCADE, null=False)
    student = models.ForeignKey('Student', on_delete=models.CASCADE, null=False)
    subject = models.ForeignKey('Subject', on_delete=models.CASCADE, null=False)
    grade = models.IntegerField(null=False, validators=[MinValueValidator(0), MaxValueValidator(10)])
    percentage = models.FloatField(null=False)
    class Meta:
        db_table = 'results'
        unique_together = ('student', 'subject')

class StudentProfile(models.Model):
    Student = models.OneToOneField('Student', on_delete=models.CASCADE, null=False, db_index=True)
    joining_date = models.DateField(auto_now_add=True, null=False)
    FatherName = models.CharField(max_length=100, null=False)
    MotherName = models.CharField(max_length=100, null=False)
    FatherAge = models.IntegerField(null=False)
    MotherAge = models.IntegerField(null=False)
    Age = models.IntegerField(null=False)
    address = models.CharField(max_length=100, null=False)
    phoneNo = models.CharField(max_length=13, null=False)
    class Meta:
        db_table = 'student_profile'

# qs = Student.objects.select_related('Class','Class__teacher').all()
# Results.objects.values('Class').annotate(avg_percentage=Avg('percentage'))
# Results.objects.values('Class').annotate(avg_percentage = Avg('percentage')).filter(avg_percentage__gt=80)
# Student.objects.all().order_by('attendance')