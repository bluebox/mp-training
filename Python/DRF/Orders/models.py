from django.db import models

from Orders.CustomManager import CustomCustomerManager


class Product(models.Model):
    name = models.CharField(max_length=255)
    description = models.TextField(blank=True)
    price = models.DecimalField(max_digits=10, decimal_places=2)

    class Meta:
        db_table = "Product"

    def __str__(self):
        return self.name


class Customer(models.Model):
    first_name = models.CharField(max_length=100)
    last_name = models.CharField(max_length=100)
    email = models.EmailField(unique=True)
    phone_number = models.CharField(max_length=20, blank=True, null=True)
    address = models.CharField(max_length=255, blank=True, null=True)
    city = models.CharField(max_length=100, blank=True, null=True)
    state = models.CharField(max_length=100, blank=True, null=True)
    zip_code = models.CharField(max_length=10, blank=True, null=True)
    registration_date = models.DateTimeField(auto_now_add=True)
    objects = CustomCustomerManager()
    class Meta:
        db_table = 'Customer'

    def __str__(self):
        return f'{self.first_name} - {self.last_name}'


class Order(models.Model):
    customer = models.ForeignKey(Customer, on_delete=models.CASCADE, related_name='orders')
    order_date = models.DateTimeField(auto_now_add=True)
    total_amount = models.DecimalField(max_digits=10, decimal_places=2)

    class Meta:
        db_table = "Orders"

    def __str__(self):
        return self.customer.first_name


class OrderItem(models.Model):
    order = models.ForeignKey(Order, on_delete=models.CASCADE, related_name='items')
    product = models.ForeignKey(Product, on_delete=models.RESTRICT, related_name='order_items')
    quantity = models.IntegerField()
    unit_price = models.DecimalField(max_digits=10, decimal_places=2)

    class Meta:
        db_table = "OrderItem"

    def __str__(self):
        return f'{self.product.name} x {self.quantity}'