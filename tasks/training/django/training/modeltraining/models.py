from django.db import models
from django.contrib.auth.models import AbstractUser
from django.contrib.auth.models import User
import uuid

class User(AbstractUser):
    # username = models.CharField(max_length=255,unique=True)
    # first_name = models.CharField(max_length=255)
    # last_name = models.CharField(max_length=255)
    # email = models.EmailField(max_length=255, unique=True)
    # password = models.CharField(max_length=255)
    # is_staff = models.BooleanField(default=False)
    mobile_number = models.CharField(max_length=12,default=0)
    age = models.IntegerField(null=True,blank=True)
    addres = models.TextField(max_length=200)

    USERNAME_FIELD = 'username'
    REQUIRED_FIELDS = []


class Roles(models.Model):
    role_id  = models.CharField(max_length=10 , primary_key=True)
    role_name = models.CharField(max_length=100)
    role_description = models.TextField(max_length=5000)

class User_role(models.Model):
    user_id = models.ForeignKey(User,on_delete=models.CASCADE)
    role_id  = models.ForeignKey(Roles,on_delete=models.SET_NULL , null=True)
    designation = models.CharField(max_length=100)
    qualification = models.CharField(max_length=100)
    salary = models.IntegerField()
    years_of_experience = models.IntegerField()


class Lab(models.Model):
    lab_id  = models.CharField(max_length=10 , primary_key=True)
    lab_type = models.CharField(max_length=100)
    lab_name = models.CharField(max_length=100)
    lab_status= models.CharField(max_length=5000)
    lab_number= models.IntegerField()

class Test(models.Model):
    test_id  = models.CharField(max_length=10 , primary_key=True)
    test_type = models.CharField(max_length=100)
    test_name = models.CharField(max_length=100)
    test_description = models.TextField(max_length=5000)
    lab = models.ForeignKey(Lab,on_delete=models.SET_NULL, null=True)

class Review(models.Model):
    user_id = models.ForeignKey(User,on_delete=models.CASCADE)
    comment = models.TextField(max_length=200)


class Appointment(models.Model):
    slots = (
        ('10 AM' ,'Morning'),
        ('1PM' , 'Afternoon'),
        ( '4 PM' , 'Evening')
       
    )
    appointment_id = models.IntegerField(models.AutoField , primary_key=True)
    user = models.ForeignKey(User,on_delete=models.CASCADE)
    date = models.DateTimeField(auto_now_add=True)
    slot = models.CharField(choices=slots , max_length=100)
    staff = models.ManyToManyField(User_role,blank=True)
    test = models.ManyToManyField(Test,blank=True)

class Bill(models.Model):
    appointment = models.ForeignKey(Appointment,on_delete=models.CASCADE)
    consultation_fee = models.IntegerField(null=True,blank=True)
    test_fee = models.IntegerField(null=True,blank=True)    
    tax = models.IntegerField(null=True,blank=True)    
    total = models.IntegerField(default=0)    


class Report(models.Model):
    appointment = models.ForeignKey(Appointment,on_delete=models.CASCADE)
    description = models.TextField(max_length=200,null=True,blank=True)
    date = models.DateTimeField(auto_now_add=True)
    report_type = models.CharField(max_length=200,null=True,blank=True)
    
