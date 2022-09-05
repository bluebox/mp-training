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
    




















# class UpdatedUser(AbstractUser):
#     gender = models.CharField(max_length=20 , null=True , blank=True)
#     mobile_number = models.IntegerField( null=True , blank=True)
#     addr = models.ForeignKey(Address , on_delete=models.SET_NULL , null=True)

