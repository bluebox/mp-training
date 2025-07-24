from django.contrib.auth.models import AbstractUser
from django.db import models

class ModelUser(models.Model):
    Name = models.CharField(max_length=150)
    Age = models.IntegerField()
    Email = models.CharField(max_length=150)
    PhoneNumber = models.CharField(max_length=13)
    Branch = models.CharField(max_length=3)
    Languages = models.CharField(max_length=10)
    State = models.CharField(max_length=50)
    City = models.CharField(max_length=50)
    class Meta:
        db_table = "model_user"

    def __str__(self):
        return self.Name

# class MyUser(AbstractUser):
#     pass

class MyBaseUser(AbstractUser):
    class Meta:
        db_table = "my_users"
