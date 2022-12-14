from django.db import models


# Create your models here.
class Branch(models.Model):
    name = models.CharField(max_length=30)


class Semester(models.Model):
    sem_name = models.CharField(max_length=10)


class StudentDetails(models.Model):
    name = models.CharField(max_length=255)
    phone = models.CharField(max_length=20)
    address = models.TextField()
    gender = models.CharField(max_length=10, default='Male')
    branch_id = models.ForeignKey(
        Branch,
        models.CASCADE,
        verbose_name="Branch Id",
        null=True,
        default=None,
    )
    sem_id = models.ForeignKey(
        Semester,
        models.CASCADE,
        verbose_name='Semester',
        null=True,
        default=None,
    )

    def __str__(self):
        return self.name


class Subjects(models.Model):
    name = models.CharField(max_length=61)
    branch_id = models.ForeignKey(
        Branch,
        models.CASCADE,
        verbose_name="Branch Id",
        null=True,
        default=None,
    )
    sem_id = models.ForeignKey(
        Semester,
        models.CASCADE,
        verbose_name='Semester',
        null=True,
        default=None,
    )

    def __str__(self):
        return self.name


class Marks(models.Model):
    marks_obtained = models.IntegerField()
    sub_id = models.ForeignKey(
        Subjects,
        models.CASCADE,
        verbose_name="Subject Id",
        null=True,
        default=None
    )
    std_id = models.ForeignKey(
        StudentDetails,
        models.CASCADE,
        verbose_name="Student Id",
        null=True,
        default=None
    )
    sem_id = models.ForeignKey(
        Semester,
        models.CASCADE,
        verbose_name='Semester',
        null=True,
        default=None,
    )

