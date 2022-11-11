from django.db import models


from django.utils import timezone


class Customer(models.Model):
    customer_id = models.IntegerField(primary_key=True,default=0)
    Name = models.CharField(max_length=50,default="")
    Phone = models.CharField(max_length=11,default="")
    Address = models.CharField(max_length=255,default="")
    email = models.CharField(max_length=50,default="")


class DeliveryPartners(models.Model):
    DeliveryPartner_id = models.CharField(max_length=10,primary_key=True,default=0)
    DeliveryPartner_Name = models.CharField(max_length=50,default="")
    DeliveryPartner_Phone = models.CharField(max_length=11,default="")
    DeliveryPartner_Address = models.CharField(max_length=255,default="")
    DeliveryPartner_status = models.CharField(max_length=50,default="")


class Menu(models.Model):
    Item_id = models.CharField(max_length=10,primary_key=True)
    Item_Name = models.CharField(max_length=50)
    Item_Price = models.IntegerField
    Details = models.CharField(max_length=255)
    Category = models.CharField(max_length=50)
    Item_status = models.CharField(max_length=50)


class Cart(models.Model):
    cart_id = models.CharField(max_length=10,primary_key=True)
    item_id = models.ForeignKey(Menu, on_delete=models.CASCADE)
    quantity = models.IntegerField


class Order(models.Model):
    order_id = models.CharField(max_length=10,primary_key=True)
    customer_id = models.ForeignKey(Customer, on_delete=models.CASCADE)
    DeliveryPartner_id = models.ForeignKey(DeliveryPartners, on_delete=models.CASCADE)
    Cart_id = models.ForeignKey(Cart, on_delete=models.CASCADE)
    Total_Price = models.IntegerField
    Order_status = models.CharField(max_length=50)


class Payment(models.Model):
    Payment_id = models.CharField(max_length=10,primary_key=True)
    customer_id = models.ForeignKey(Customer, on_delete=models.CASCADE)
    order_id = models.ForeignKey(Order, on_delete=models.CASCADE)
    Billing_address= models.CharField(max_length=250)
    mode =(
    ("CASH", "CASH"),
    ("CARD", "CARD"))
    Payment_method = models.CharField(max_length=20,choices=mode)


class Invoice(models.Model):
    order_id = models.CharField(max_length=10,primary_key=True)
    customer_id = models.ForeignKey(Customer, on_delete=models.CASCADE)
    Payment_id = models.ForeignKey(Payment, on_delete=models.CASCADE)
    Date = models.DateField(default=timezone.now)
