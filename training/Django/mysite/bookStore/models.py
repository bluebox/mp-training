from MySQLdb.constants.FLAG import NOT_NULL
from django.db import models

# Create your models here.
from django.db import models


class  Customers(models.Model):
    name=models.CharField(max_length=50,db_index=True)
    age = models.IntegerField()
    username = models.CharField(max_length=50,unique=True)
    gender=models.CharField(max_length=50)
    email=models.CharField(max_length=50,unique=True)
    address = models.CharField(max_length=50)
    password = models.CharField(max_length=50)
    confirmPassword = models.CharField(max_length=50)


class Books(models.Model):
    title=models.CharField(max_length=50,unique=True)
    content=models.CharField(max_length=5000,null=True)
    price=models.IntegerField()


class Orders(models.Model):

    customer = models.ForeignKey(Customers, on_delete=models.CASCADE)
    book = models.ForeignKey(Books, on_delete=models.CASCADE)
    order_date = models.DateTimeField(auto_now_add=True)
    quantity = models.FloatField()


class Authors(models.Model):
    name=models.CharField(max_length=50)
    username = models.CharField(max_length=50, unique=True)
    age = models.IntegerField()
    gender = models.CharField(max_length=50)
    email = models.EmailField(max_length=50)
    address = models.CharField(max_length=50,default=0)
    books = models.CharField(max_length=150)
    password = models.CharField(max_length=50)
    confirmPassword = models.CharField(max_length=50)

class BookAuthors(models.Model):
    book=models.ForeignKey(Books,on_delete=models.CASCADE)
    author=models.ForeignKey(Authors,on_delete=models.CASCADE)
    class Meta:
        models.Index(fields=['book',"author"])




