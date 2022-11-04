from django.contrib.auth.models import User
from django.db import models

# Create your models here.

class Customer(models.Model):  # 5. model to for data of the customers

    customer_id = models.ForeignKey(User, on_delete=models.CASCADE)
    username = models.CharField(max_length=100)
    password = models.CharField(max_length=100)
    customer_name = models.CharField(max_length=50)
    phone = models.CharField(max_length=13)
    email = models.EmailField(max_length=60)
    address = models.CharField(max_length=250)

    class Meta:
        managed=True

    def __str__(self):
        return self.customer_name

