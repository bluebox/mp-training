# from django.contrib.auth.models import User
from django.db import models
#
#
# # Create your models here.
#
#
# class GraniteStore(models.Model):  # 1. model for granite stores data
#
#     store_id = models.IntegerField(primary_key=True)
#     store_name = models.CharField(max_length=50)
#     established_year = models.IntegerField()
#     store_description = models.CharField(max_length=250)
#     contact = models.IntegerField()
#     website = models.CharField(max_length=150)
#     address = models.CharField(max_length=250)
#
#     def __str__(self):
#         return self.store_name
#
#
class Role(models.Model):  # 2. model for roles of the employees

    role_id = models.IntegerField(primary_key=True)
    role_name = models.CharField(max_length=50)

    def __str__(self):
        return self.role_name
#
#
# class Employee(models.Model):  # 3. model for employees data
#
#     employee_id = models.IntegerField(primary_key=True)
#     employee_name = models.CharField(max_length=50)
#     role_id = models.ForeignKey(Role, on_delete=models.CASCADE)
#     doj = models.DateField()
#     phone = models.CharField(max_length=13)
#     email = models.EmailField(max_length=60)
#     address = models.CharField(max_length=250)
#     salary = models.FloatField()
#
#     def __str__(self):
#         return self.employee_name
#
#
# class Vehicle(models.Model):  # 4. model for vehicles information
#
#     vehicle_no = models.CharField(primary_key=True, max_length=20)
#     model = models.CharField(max_length=20)
#     owner_name = models.CharField(max_length=50)
#     permit_range = models.CharField(max_length=50)
#     fuel_efficiency = models.FloatField()
#     load_capacity = models.FloatField()
#
#     def __str__(self):
#         return self.vehicle_no
#
#
# class Customer(models.Model):  # 5. model to for data of the customers
#
#     customer_id = models.ForeignKey(User, on_delete=models.CASCADE)
#     username = models.CharField(max_length=100)
#     password = models.CharField(max_length=100)
#     customer_name = models.CharField(max_length=50)
#     phone = models.CharField(max_length=13)
#     email = models.EmailField(max_length=60)
#     address = models.CharField(max_length=250)
#
#     def __str__(self):
#         return self.customer_name
#
#
# class Item(models.Model):  # 6. model to for items data
#
#     item_id = models.IntegerField(primary_key=True)
#     item_name = models.CharField(max_length=50)
#     item_pattern = models.CharField(max_length=30)
#     item_description = models.CharField(max_length=150)
#
#     def __str__(self):
#         return self.item_name
#
#
# class ContainsItem(models.Model):  # 7. model for stores containing items
#
#     contains_id = models.IntegerField(primary_key=True)
#     store_id = models.ForeignKey(GraniteStore, on_delete=models.CASCADE)
#     item_id = models.ForeignKey(Item, on_delete=models.CASCADE)
#     price = models.FloatField()
#
#
# class Order(models.Model):  # 8. model for Order information
#
#     order_id = models.IntegerField(primary_key=True)
#     customer_id = models.ForeignKey(Customer, on_delete=models.CASCADE)
#     order_time = models.DateTimeField()
#
#
# class OrderedItems(models.Model):  # 9. model for items per Order
#
#     order_id = models.ForeignKey(Order, on_delete=models.CASCADE)
#     contains_id = models.ForeignKey(ContainsItem, on_delete=models.CASCADE)
#
#
# class Payment(models.Model):  # 10. Model for Payment Information
#
#     payment_id = models.IntegerField(primary_key=True)
#     order_id = models.OneToOneField(Order, on_delete=models.CASCADE)
#     amount = models.FloatField()
#     payment_method = models.CharField(max_length=80)
#     payment_datetime = models.DateTimeField()
#
#     def __str__(self):
#         return self.payment_id
#
#
# class Driver(models.Model):  # 11. Model for drivers information
#
#     driver_id = models.IntegerField(primary_key=True)
#     employee_id = models.OneToOneField(Employee, on_delete=models.CASCADE)
#     license_no = models.CharField(max_length=25)
#
#     def __str__(self):
#         return self.license_no
#
#
# class CoDriver(models.Model):  # 12. Model for co-drivers information
#
#     co_driver_id = models.IntegerField(primary_key=True)
#     employee_id = models.OneToOneField(Employee, on_delete=models.CASCADE)
#
#     def __str__(self):
#         return self.co_driver_id
#
#
# class Shipment(models.Model):  # 13. Model for shipment information
#
#     shipment_id = models.IntegerField(primary_key=True)
#     order_id = models.OneToOneField(Order, on_delete=models.CASCADE)
#     vehicle_id = models.ForeignKey(Vehicle, on_delete=models.CASCADE)
#     driver_id = models.ForeignKey(Driver, on_delete=models.CASCADE)
#     co_driver_id = models.ForeignKey(CoDriver, on_delete=models.CASCADE)
#     delivery_address = models.CharField(max_length=250)
#
#     def __str__(self):
#         return self.shipment_id
#
#
