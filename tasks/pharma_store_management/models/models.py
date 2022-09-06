# This is an auto-generated Django model module.
# You'll have to do the following manually to clean this up:
#   * Rearrange models' order
#   * Make sure each model has one field with primary_key=True
#   * Make sure each ForeignKey and OneToOneField has `on_delete` set to the desired behavior
#   * Remove `managed = False` lines if you wish to allow Django to create, modify, and delete the table
# Feel free to rename the models, but don't rename db_table values or field names.
from django.db import models


class CustomersDetails(models.Model):
    customer_id = models.AutoField(primary_key=True)
    name = models.CharField(max_length=255)
    phone_number = models.CharField(db_column='Phone_number', max_length=255)  # Field name made lowercase.
    address = models.CharField(max_length=255, blank=True, null=True)


class EmployeeRole(models.Model):
    emp_role_id = models.AutoField(primary_key=True)
    emp_role = models.CharField(max_length=255)


class StoresDetails(models.Model):
    store_id = models.AutoField(primary_key=True)
    store_location = models.CharField(max_length=255)
    store_name = models.CharField(max_length=255)


class EmployeeDetails(models.Model):
    emp_id = models.AutoField(primary_key=True)
    emp_name = models.CharField(max_length=255)
    store_id = models.ForeignKey('StoresDetails', models.DO_NOTHING)
    emp_username = models.CharField(max_length=255)
    emp_password = models.CharField(max_length=255)
    emp_role_id = models.ForeignKey('EmployeeRole', models.DO_NOTHING)


class PharmaCompany(models.Model):
    company_id = models.AutoField(primary_key=True)
    company_name = models.CharField(max_length=255)
    company_place = models.CharField(max_length=255)


class ProductDetails(models.Model):
    product_id = models.AutoField(primary_key=True)
    product_name = models.CharField(max_length=255)
    manufacturer_id = models.ForeignKey(PharmaCompany, models.DO_NOTHING)
    selling_price = models.IntegerField()
    cost_price = models.IntegerField()


class OrdersData(models.Model):
    order_id = models.AutoField(primary_key=True)
    customer_id = models.ForeignKey(CustomersDetails, models.DO_NOTHING)
    store_id = models.ForeignKey(StoresDetails, models.DO_NOTHING)
    total_cost = models.IntegerField()
    date = models.DateField()


class OrderDetails(models.Model):
    order_id = models.ForeignKey(OrdersData, models.DO_NOTHING)
    product_id = models.ForeignKey(ProductDetails, models.DO_NOTHING)
    quantity = models.IntegerField()


class StoreInventory(models.Model):
    store_id = models.ForeignKey(StoresDetails, models.DO_NOTHING)
    product_id = models.ForeignKey(ProductDetails, models.DO_NOTHING)
    quantity = models.IntegerField()



