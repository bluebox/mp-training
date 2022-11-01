# from django.contrib.auth.models import AbstractUser
import datetime
from email.policy import default
from enum import unique
from imp import NullImporter
from pydoc import Doc
from django.db import models
# from .models import Doctor
# Create your models here.
# Create your models here.
class Patients(models.Model):
    GENDER_CHOICES = (
        ('M', 'Male'),
        ('F', 'Female'),
    )
    # ADMIN_CHOICES=(('V','VERIFIED'),
    # ('N','NOT VERIFIED'),
    # )
    # user = models.OneToOneField(User, on_delete=models.CASCADE, null=True)
    name = models.CharField(max_length=70)
    age = models.IntegerField()
    gender = models.CharField(max_length=1, choices=GENDER_CHOICES)
    insurance_id = models.CharField(max_length=20, unique=True)
    email=models.CharField(max_length=70)
    # is_available = models.BooleanField(default=False, null=True)
    phone_no = models.CharField(max_length=70)
    address=models.CharField(max_length=220)
    # verified=models.CharField(max_length=1, choices=ADMIN_CHOICES,default='V')

    def __str__(self):
        return self.name


class Doctor(models.Model):
    GENDER_CHOICES = (
        ('M', 'Male'),
        ('F', 'Female'),
    ) 
    
    # ADMIN_CHOICES=(('V','VERIFIED'),
    # ('N','NOT VERIFIED'),
    # )
    # user = models.OneToOneField(User, on_delete=models.CASCADE)
    address=models.CharField(max_length=220,default='hydrabad')
    name = models.CharField(max_length=70)
    age = models.IntegerField()
    gender = models.CharField(max_length=1, choices=GENDER_CHOICES)
    email=models.CharField(max_length=70, unique=True)
    # salary_id = models.ForeignKey('Salary', on_delete=models.CASCADE),
    experience = models.IntegerField()
    qualification = models.CharField(max_length=70)
    # is_available = models.BooleanField(default=False)
    phone_no = models.CharField(max_length=70)
    # slot=models.ManyToManyField(Slot)
    # verified=models.CharField(max_length=1, choices=ADMIN_CHOICES,default='N')
    def __str__(self):
        return self.name


class Staff(models.Model):
    GENDER_CHOICES = (
        ('M', 'Male'),
        ('F', 'Female'),
    )
    # ADMIN_CHOICES=(
    #     ('V','VERIFIED'),
    # ('N','NOT VERIFIED'),
    # )
    # user = models.OneToOneField(User, on_delete=models.CASCADE)
    name = models.CharField(max_length=70)
    # doctor = models.ForeignKey(Doctor, on_delete=models.CASCADE, null=False)
    age = models.IntegerField()
    gender = models.CharField(max_length=1, choices=GENDER_CHOICES)
    experience = models.IntegerField()
    designation = models.CharField(max_length=70)
    # is_available = models.BooleanField(default=False)
    email=models.CharField(max_length=70, unique=True)
    phone_number = models.CharField(max_length=70,null=True)
    # verified=models.CharField(max_length=1, choices=ADMIN_CHOICES,default='N')
    # email=models.CharField(max_length=70)
    def __str__(self):
        return self.name
class AllUser(models.Model):
    USER_TYPE_CHOICES=(
        ('D','DOCTOR'),
        ('P','PATIENT'),
        ('S','STAFF'),
        ('A','Admin'),
    )
    ADMIN_CHOICES=(
        ('V','VERIFIED'),
    ('N','NOT VERIFIED'),
    )
    # USERNAME_FIELD = 'email'
    # Username = None
    # REQUIRED_FIELDS = []
    email=models.CharField(max_length=70,unique=True)
    name = models.CharField(max_length=70)
    password=models.CharField(max_length=70)
    verified=models.CharField(max_length=1, choices=ADMIN_CHOICES,default='N')
    type_of_user= models.CharField(max_length=1, choices=USER_TYPE_CHOICES)
    def __str__(self):
        return self.name
class DatewiseSlot(models.Model):
    CHOICES = (
        ('A', 'Available'),
        ('B', 'Booked'),
    )
    date=models.DateField(default=datetime.date.today())
    startTime = models.TimeField(auto_now=False,default="17:30:00")
    endTime = models.TimeField(auto_now=False,default="20:30:00")
    status=models.CharField(max_length=1, choices=CHOICES, default='A')
    doctor = models.ForeignKey(Doctor, on_delete=models.CASCADE,null=True, blank=True)
    class Meta:
        unique_together = ('date', 'doctor','startTime','endTime')
    def __str__(self):
        return str(self.startTime)
class Appointment(models.Model):
    slot = models.ForeignKey(DatewiseSlot, on_delete=models.CASCADE)
    patient = models.ForeignKey(Patients, on_delete=models.CASCADE, default='1')
    def __str__(self):
        return self.slot
