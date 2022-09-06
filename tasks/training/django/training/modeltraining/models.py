from atexit import register
from django.db import models
from django.contrib.auth.models import AbstractUser
from django.contrib.auth.models import User
import uuid
# Create your models here.
class Address(models.Model):
    addr_id = models.CharField(max_length=10 , primary_key=True)
    pincode = models.IntegerField(null=False,blank=False)
    city = models.CharField(max_length=100 ,null=True,blank=True)
    state = models.CharField(max_length=100 ,null=True,blank=True)
    Country = models.CharField(max_length=100 )
    
    def __str__(self):
        return self.city
    

class Profile(models.Model):
    user = models.ForeignKey(User, on_delete=models.CASCADE , null=True , blank=True)
    name  = models.CharField(max_length=200 , null=True,blank=True)
    email = models.EmailField(max_length=500, blank=True,null=True)
    username = models.CharField(max_length=200 , null=True,blank=True)
    created_on  = models.DateTimeField(auto_now_add=True)
    id = models.UUIDField(default=uuid.uuid4 , unique=True,primary_key=True , editable=False)
    address = models.ForeignKey(Address,null=True,blank=True, on_delete=models.SET_NULL)
    role = models.ManyToManyField("Role")
    salary = models.IntegerField()

    
    def __str__(self):
        return str(self.username)


class Role(models.Model):
    id = models.UUIDField(default=uuid.uuid4 , unique=True,primary_key=True , editable=False)
    role = models.CharField(max_length=200 ,unique=True)
    position = models.CharField(max_length=200 ,unique=True)

    def __str__(self):
        return str(self.position)


class Room(models.Model):
    ROOM_TYPE = (
        ('Multibed','Multibed Ward'),
        ('Twin sharing','Twin sharing Room'),
        ('Single','Single Room'),
        ('Single Deluxe','Single Deluxe Room'),
        ('General','General ward'),
    )
    STATUS = (
        ('occupied' , 'occupied' ),
        ('occupied' , 'occupied' )
    )

    room_no = models.IntegerField(primary_key=True )
    room_type = models.CharField(max_length=200 , choices=ROOM_TYPE)
    status = models.CharField(max_length=200 , choices=STATUS)


class Patient(models.Model):
    Gender = (
        ('Male' , 'Male' ),
        ('Female' , 'Female' )
    )
    patient_id = models.CharField(max_length=10,primary_key=True)
    patient_name  = models.CharField(max_length=200 )
    age = models.IntegerField(blank=True , null=True)
    room_type = models.CharField(max_length=200 , choices=Gender)
    register_on  = models.DateTimeField(auto_now_add=True)
    addr_id = models.ForeignKey(Address , on_delete=models.SET_NULL,null=True)

class InPatient(models.Model):
    disease = models.CharField(max_length=200 )
    patient = models.ForeignKey(Patient,on_delete=models.CASCADE)
    room  = models.ForeignKey(Room,on_delete=models.SET_NULL,null=True)
    doctor = models.ForeignKey(Profile,on_delete=models.SET_NULL,null=True)
    # nurse = models.ForeignKey(Profile,on_delete=models.SET_NULL,null=True)
    date_of_admission = models.DateTimeField(auto_now_add=True)
    date_of_discharge =models.DateTimeField()


class OutPatient(models.Model):
    disease = models.CharField(max_length=200 )
    patient = models.ForeignKey(Patient,on_delete=models.CASCADE)
    date_of_treatment = models.DateTimeField(auto_now_add=True)
    doctor = models.ForeignKey(Profile,on_delete=models.SET_NULL,null=True)
    # nurse = models.ForeignKey(Profile,on_delete=models.SET_NULL,null=True)


class Bill(models.Model):
    bill_no = models.AutoField(primary_key=True)
    patient = models.ForeignKey(Patient,on_delete=models.CASCADE)
    doctor_charge = models.IntegerField(blank=True , null=True)
    room_charge = models.IntegerField(blank=True , null=True)
    total_charge = models.IntegerField()
    num_of_days = models.IntegerField(blank=True , null=True)
    generated_on = models.DateTimeField(auto_now_add=True)












# class UpdatedUser(AbstractUser):
#     gender = models.CharField(max_length=20 , null=True , blank=True)
#     mobile_number = models.IntegerField( null=True , blank=True)
#     addr = models.ForeignKey(Address , on_delete=models.SET_NULL , null=True)

