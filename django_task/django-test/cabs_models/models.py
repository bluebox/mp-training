from datetime import date,datetime
from lib2to3.pgen2 import driver
from tkinter.tix import Tree
from django.db import models

GENDER_CHOICES = [
    ('M', 'Male'),
    ('F', 'Female'),
    ('O', 'Other')
]
DOCUMENT_STATUS_CHOICES = [
    ('pending', 'pending'),
    ('verified', 'verified')
]
PAYMENT_MODE_CHOICES = [
    ('cash', 'cash'),
    ('online', 'online')
]


class admin_users(models.Model):
    admin_id=models.CharField(max_length=10,primary_key=True)
    name=models.CharField(max_length=30)
    username=models.CharField(max_length=20,unique=True)
    password=models.CharField(max_length=20)
    dob=models.DateField()

class rider_details(models.Model):
    rider_id=models.CharField(max_length=10,primary_key=True)
    name=models.CharField(max_length=20)
    gender=models.CharField(choices=GENDER_CHOICES,max_length=8)
    country=models.CharField(max_length=10)
    phone_num=models.IntegerField(unique=True)
    username=models.CharField(unique=True,max_length=20)
    password=models.CharField(max_length=20)
    dob=models.DateField()
    joined_date=models.DateField(default=date.today)

class cab_details(models.Model):
    cab_id=models.CharField(max_length=10,primary_key=True)
    reg_no=models.CharField(max_length=12,unique=True)
    brand=models.CharField(max_length=20)
    seat_cap=models.IntegerField()

class driver_details(models.Model):
    driver_id=models.CharField(max_length=10,primary_key=True)
    name=models.CharField(max_length=20)
    joined_date=models.DateField(default=date.today)
    current_cab=models.OneToOneField(cab_details,on_delete=models.CASCADE)


class driver_documents(models.Model):
    driver_details=models.OneToOneField(driver_details,on_delete=models.CASCADE)
    adhar_num=models.CharField(max_length=15,unique=True)
    pan_num=models.CharField(max_length=20,unique=True,null=True)
    verified_status=models.CharField(max_length=20,choices=DOCUMENT_STATUS_CHOICES)

class location(models.Model):
    location_id=models.CharField(primary_key=True,max_length=10)
    address=models.CharField(max_length=100)
    islandmark=models.BooleanField()
    landmark=models.CharField(max_length=20)
    latitude=models.DecimalField(max_digits=6,decimal_places=4)
    longitude=models.DecimalField(max_digits=6,decimal_places=4)
    zipcode=models.IntegerField()

class rider_bookmarks(models.Model):
    bookmark_id=models.CharField(primary_key=True,max_length=10)
    rider_details=models.ForeignKey(rider_details,on_delete=models.CASCADE)
    bookmark_tag_name=models.CharField(max_length=30)
    bookmark_loc_id=models.OneToOneField(location,on_delete=models.CASCADE)

class user_review(models.Model):
    review_id=models.CharField(primary_key=True,max_length=20)
    driver_details=models.ForeignKey(driver_details,on_delete=models.CASCADE)
    user_details=models.ForeignKey(rider_details,on_delete=models.CASCADE)    
    description=models.CharField(max_length=100)
    rating_stars=models.IntegerField()
    created_at=models.DateTimeField(default=datetime.now)
    modify_at=models.DateTimeField(null=True)

class driver_status(models.Model):
    driver_details=models.OneToOneField(driver_details,on_delete=models.CASCADE)
    avg_rating=models.DecimalField(max_digits=3,decimal_places=2)
    total_trips=models.IntegerField()

class trip_init(models.Model):
    trip_id=models.CharField(max_length=10,primary_key=Tree)
    rider_details=models.ForeignKey(rider_details,on_delete=models.CASCADE)
    driver_details=models.ForeignKey(driver_details,on_delete=models.CASCADE)
    start_loc=models.ForeignKey(location,on_delete=models.CASCADE,related_name='start_location')
    end_loc=models.ForeignKey(location,on_delete=models.CASCADE,related_name='end_location')
    trip_otp=models.IntegerField()
    issue_time=models.DateTimeField(default=datetime.now)
    trip_start_time=models.DateTimeField()
    trip_end_time=models.DateTimeField()
    trip_status=models.CharField(max_length=10)

class payment(models.Model):
    pay_id=models.IntegerField(primary_key=True)
    trip_details=models.OneToOneField(trip_init,on_delete=models.CASCADE)
    total_fare=models.IntegerField()
    payment_mode=models.CharField(choices=PAYMENT_MODE_CHOICES,max_length=20)
    transaction_details=models.IntegerField(null=True)





