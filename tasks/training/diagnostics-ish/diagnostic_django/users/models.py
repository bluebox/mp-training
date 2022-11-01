#pylint:disable=E0102
#pylint:disable=E1101
"""models for user related tables"""
from django.contrib.auth.models import AbstractUser
from django.contrib.auth.models import User
from django.db import models


class User(AbstractUser):
    """abstract user with additional fields"""
    mobile_number = models.CharField(max_length=10, default=0)
    age = models.IntegerField(null=True, blank=True)
    address = models.TextField(max_length=200)
    pincode = models.CharField(max_length=8, null=True, blank=True)
    user_type = models.CharField(max_length=10, default='admin')
    USERNAME_FIELD = 'username'
    REQUIRED_FIELDS = []


class Customer(models.Model):
    """customer model with 1:1 relatio with user"""
    customer_id = models.CharField(max_length=10, primary_key=True)
    user_id = models.OneToOneField(User, on_delete=models.CASCADE)

    def __str__(self):
        """return id"""
        return self.customer_id


class Staff(models.Model):
    """staff model"""
    DESIGNATION = (
        ('Doctor', 'Doctor'),
        ('Nurse', 'Nurse'),
        ('Lab Technician', 'Lab Technician'),
        ('Sample Collector', 'Sample Collector'),
        ('Receptionist', 'Receptionist'),
    )
    staff_id = models.CharField(max_length=10, primary_key=True)
    user_id = models.OneToOneField(User, on_delete=models.CASCADE)
    designation = models.CharField(max_length=100, choices=DESIGNATION)
    qualification = models.CharField(max_length=100)
    salary = models.IntegerField()
    years_of_experience = models.IntegerField()
    branch = models.ForeignKey('appointment.Branch', on_delete=models.CASCADE)

    def __str__(self):
        """retur username when object accessed"""
        return self.user_id.username
