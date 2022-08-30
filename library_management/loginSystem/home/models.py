from ast import Return
from datetime import datetime
from django.db import models
from django.contrib.auth.models import User


class ExtendedUser(models.Model):
    user = models.OneToOneField(User, on_delete=models.CASCADE)
    First_name = models.CharField(max_length=100)
    Last_name= models.CharField(max_length=100)
    Gender = models.CharField(max_length=100)
    email = models.CharField(max_length=100)
    Phone_number = models.CharField(max_length=100)

# Create your models here.
class book(models.Model):

    BookName=models.CharField(max_length=122)
    AuthorName=models.CharField(max_length=122)
    price=models.CharField(max_length=12)
    genre=models.CharField(max_length=12)
    status=models.CharField(max_length=12)
    Username=models.CharField(max_length=122,default='no issue')
    BorrowDate=models.DateTimeField(default=datetime.now )
    DueDate=models.DateTimeField(default=datetime.now )
    mode=models.CharField(max_length=20,default="Borrow")
    ReturnDate=models.DateField(default=datetime.now)
    fine=models.CharField(max_length=10,default="0")

    def __str__(self):
        return self.BookName
