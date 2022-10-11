from email.policy import default
from enum import unique
from django.db import models


# Create your models here.
class Vehicle(models.Model):
    # vehicleid = models.BigAutoField(primary_key=True)
    vehicle_type = models.CharField(max_length=24)
    model = models.CharField(max_length=24)
    vehicle_number = models.CharField(max_length=24, unique=True)
    isAC = models.BooleanField(default=False)
    total_seats = models.IntegerField()

    def __str__(self):
        return self.vehicle_type + "" + self.vehicle_number

    class Meta:
        db_table = 'vehicles'


class Employee(models.Model):
    # empid = models.BigAutoField(primary_key=True)
    name = models.CharField(max_length=55)
    image = models.TextField(default="https://res.cloudinary.com/dklq1vuce/image/upload/v1665298218/employee_profile_pic_cl6agt.jpg")
    mobile = models.BigIntegerField()
    email = models.EmailField(null=True)
    address = models.TextField()
    salary = models.DecimalField(max_digits=15, decimal_places=2)
    # vehicleid = models.ForeignKey(Vehicle, on_delete=models.CASCADE, null=True, blank=True)

    def __str__(self):
        return self.name +" "+ str(self.mobile)

    class Meta:
        db_table = 'employees'


class Place(models.Model):
    # placeid = models.BigAutoField(primary_key=True)
    place_name = models.CharField(max_length=24)
    image = models.TextField()
    description = models.TextField()

    def __str__(self):
        return self.place_name

    class Meta:
        db_table = 'places'

class Coupon(models.Model):
    # couponid = models.BigAutoField(primary_key=True)
    couponcode = models.CharField(max_length=24)
    discount = models.DecimalField(max_digits=4, decimal_places=2)
    description = models.TextField(null=True)
    created_at = models.DateTimeField(auto_now_add=True)
    valid_till = models.DateTimeField()

    def __str__(self):
        return self.couponcode

    class Meta:
        db_table = 'coupons'
        ordering = ['created_at']


class Tour(models.Model):
    # tourid = models.BigAutoField(primary_key=True)
    tour_name = models.CharField(max_length=50)
    tour_from = models.CharField(max_length=50)
    tour_to = models.CharField(max_length=50)
    tour_type = models.CharField(max_length=50)
    start_date = models.DateTimeField(null=True)
    nights = models.IntegerField()
    days = models.IntegerField()
    price = models.DecimalField(max_digits=15, decimal_places=2)
    image = models.TextField()
    description = models.TextField()
    places = models.ManyToManyField(Place)
    vehicleid = models.ForeignKey(Vehicle, on_delete=models.CASCADE, null=True, blank=True)
    coupons = models.ManyToManyField(Coupon)
    guides = models.ManyToManyField(Employee)
    created_at = models.DateTimeField(auto_now_add=True)
    updated_at = models.DateTimeField(auto_now=True)

    def __str__(self):
        return self.tour_from +" "+ self.tour_to +" "+ self.tour_name

    class Meta:
        db_table = 'tours'

class Package(models.Model):
    # packageid = models.BigAutoField(primary_key=True)
    package_name = models.CharField(max_length=30)
    package_type = models.CharField(max_length=30, null=True, blank=True)
    image = models.TextField()
    description = models.TextField()
    tours = models.ManyToManyField(Tour)
    created_at = models.DateTimeField(auto_now_add=True)
    updated_at = models.DateTimeField(auto_now=True)

    def __str__(self):
        return self.package_name

    class Meta:
        db_table = 'packages'
        ordering = ['created_at']



class Enquiry(models.Model):
    name = models.CharField(max_length=50)
    mobile = models.BigIntegerField()
    email = models.EmailField()
    status = models.BooleanField(default=True)
    subject = models.TextField()
    message = models.TextField()
    created_at = models.DateTimeField(auto_now_add = True)
    updated_at = models.DateTimeField(auto_now = True)

    def __str__(self):
        return self.email +" "+self.subject

    class Meta:
        db_table = 'enquiry'
        ordering = ['created_at']
