from django.db import models

class User(models.Model):
    username=models.CharField(max_length=200)
    emailid=models.CharField(max_length=50)
    password=models.CharField(max_length=20)
    phone=models.CharField(max_length=13)
    gender=models.CharField(max_length=6)

    def __str__(self):
        return self.username
