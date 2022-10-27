from django.db import models

# Create your models here.
class Contact(models.Model):
    user_Name=models.CharField(max_length=50)
    email=models.CharField(max_length=50)
    phone_No=models.CharField(max_length=50)
    password=models.CharField(max_length=50)