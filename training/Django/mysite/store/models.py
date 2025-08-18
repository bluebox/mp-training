from MySQLdb.constants.FLAG import NOT_NULL
from django.db import models
from django.contrib.auth.models import  AbstractUser


# Create your models here




class CustomUser(AbstractUser):
    name=models.CharField(max_length=50,default="")
    age=models.IntegerField()
    address=models.CharField(max_length=150)
    role=models.CharField(max_length=15)
    gender = models.CharField(max_length=50)


class Books(models.Model):
    title=models.CharField(max_length=50,unique=True)
    content=models.CharField(max_length=5000,null=True)
    price=models.IntegerField()
    author=models.ForeignKey(CustomUser,on_delete=models.CASCADE,default="")


class Orders(models.Model):
    customer = models.ForeignKey(CustomUser, on_delete=models.CASCADE)
    book = models.ForeignKey(Books, on_delete=models.CASCADE)
    order_date = models.DateTimeField(auto_now_add=True)
    quantity = models.FloatField()





