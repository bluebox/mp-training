from django.db import models


# Create your models here.

class Customer(models.Model):
    cust_id = models.AutoField(primary_key=True)
    uname = models.CharField(max_length=20)
    f_name = models.CharField(max_length=25)
    l_name = models.CharField(max_length=25)
    contact = models.IntegerField(default=0)
    address = models.CharField(max_length=100)
    pin = models.IntegerField(default=0)
    mail = models.CharField(max_length=30)
    password = models.CharField(max_length=10)


class Order_list(models.Model):
    ord1_id = models.IntegerField(blank=True, primary_key=True)
    customer_id = models.ForeignKey(Customer, on_delete=models.CASCADE)


class Product_list(models.Model):
    pro_id = models.AutoField(primary_key=True)
    name = models.CharField(max_length=25)
    price = models.IntegerField()
    stock = models.IntegerField()
    product_type = models.CharField(max_length=25)
    image = models.ImageField(default=None)


class cust_cart_list(models.Model):
    order_id = models.ForeignKey(Order_list, on_delete=models.CASCADE)
    product_id = models.ForeignKey(Product_list, on_delete=models.CASCADE)


class return_list(models.Model):
    order_id = models.ForeignKey(Order_list, on_delete=models.CASCADE)
    product_id = models.ForeignKey(Product_list, on_delete=models.CASCADE)
    cust_id = models.ForeignKey(Customer, on_delete=models.CASCADE)


class Employees(models.Model):
    emp_id = models.AutoField(primary_key=True)
    name = models.CharField(max_length=25)
    contact = models.IntegerField()
    # head_id = models.ForeignKey(Employees , on_delete=models.CASCADE)
    dept = models.CharField(max_length=20)


class Total_delivered_items(models.Model):
    delivery_id = models.AutoField(primary_key=True)
    emp_id = models.ForeignKey(Employees, on_delete=models.CASCADE)
    order_id = models.ForeignKey(Order_list, on_delete=models.CASCADE)


class pickup(models.Model):
    id = models.IntegerField(primary_key=True)
    ofc_name = models.CharField(max_length=30)
    address = models.CharField(max_length=100)
    pincode = models.IntegerField()


class delivery_person(models.Model):
    emp_id = models.ForeignKey(Employees, on_delete=models.CASCADE)
    order_id = models.ForeignKey(Order_list, on_delete=models.CASCADE)


class Cust_delivered_items(models.Model):
    cust_id = models.ForeignKey(Customer, on_delete=models.CASCADE)
    order_id = models.ForeignKey(Order_list, on_delete=models.CASCADE)


class Emp_salary(models.Model):
    delivery_id = models.ForeignKey(Total_delivered_items, on_delete=models.CASCADE)
