from django.db import models


# Create your models here.
class Order_list(models.Model):
    order_id = models.IntegerField(max_length=5, primary_key=True)


class Customer(models.Model):
    cust_id = models.IntegerField(max_length=5)
    f_name = models.CharField(max_length=25)
    l_name = models.CharField(max_length=25)
    contact = models.IntegerField(max_length=12)
    address = models.CharField(max_length=100)
    pin = models.IntegerField(max_length=5)
    order_id = models.ForeignKey(Order_list, on_delete=models.CASCADE)
    mail = models.CharField(max_length=30)
    password = models.CharField(max_length=10)


class Product_list(models.Model):
    pro_id = models.IntegerField(max_length=5, primary_key=True)
    name = models.CharField(max_length=25)
    price = models.IntegerField(max_length=7)
    stock = models.IntegerField(max_length=1000)
    product_type = models.CharField(max_length=25)


class cust_cart_list(models.Model):
    order_id = models.ForeignKey(Order_list, on_delete=models.CASCADE)
    product_id = models.ForeignKey(Product_list, on_delete=models.CASCADE)
