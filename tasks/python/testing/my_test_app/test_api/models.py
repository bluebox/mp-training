from django.contrib.auth.models import AbstractUser
from django.db import models

class Languages(models.Model):
    language_choices = [("TELUGU","TELUGU"),("HINDI","HINDI"),("ENGLISH","ENGLISH")]

    lang = models.CharField(choices=language_choices,max_length=20)
    class Meta:
        db_table ="languages"
class ModelUser(models.Model):
    Branch_Choices = [('CSE','CSE'),('ECE','ECE'),'']
    Name = models.CharField(max_length=150)
    Age = models.IntegerField()
    Email = models.CharField(max_length=150)
    PhoneNumber = models.CharField(max_length=13)
    Branch = models.CharField(max_length=3)
    Languages = models.ManyToManyField(Languages)
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


