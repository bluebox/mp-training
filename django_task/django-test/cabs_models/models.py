from datetime import datetime
from django.db import models
from .validators import password_length


class admin_users(models.Model):
    id=models.CharField(max_length=10,primary_key=True)
    name=models.CharField(max_length=255)
    username=models.CharField(max_length=255)
    password=models.CharField(max_length=255,validators=[password_length] )
    dob=models.DateField()


class rider_details(models.Model):
    id=models.CharField(max_length=10,primary_key=True)
    name=models.CharField(max_length=255)
    gender=models.CharField(max_length=255)
    country=models.CharField(max_length=255)
    phone_num=models.IntegerField(unique=True)
    username=models.CharField(max_length=255,unique=True)
    password=models.CharField(max_length=255,validators=[password_length])
    dob=models.DateField()
    joined_date=models.DateField()

class cab_details:
    id=models.CharField(max_length=255)
    reg_no=models.CharField(max_length=255)
    brand=models.CharField(max_length=255)
    total=models.CharField(max_length=255)
    cab_type=models.CharField(max_length=255)
    base_rate=models.CharField(max_length=255)

class driver_details:
    id=models.CharField(max_length=10,primary_key=True)
    name=models.CharField(max_length=255)
    joined_date=models.DateField()
    current_cab_id=models.OneToOneField(cab_details,on_delete=models.CASCADE)

class driver_documents:
    driver_id=models.OneToOneField(driver_details,on_delete=models.CASCADE)
    adhar_num=models.IntegerField()
    pan_num=models.CharField(max_length=255)
    verified_status=models.CharField(max_length=255)

class location:
    id=models.CharField(max_length=10,primary_key=True)
    address=models.TextField()
    islandmark=models.BooleanField()
    landmark=models.CharField(max_length=255)
    latitude=models.DecimalField(max_digits=8, decimal_places=6)
    longitude=models.DecimalField(max_digits=8, decimal_places=6)
    zipcode=models.CharField(max_length=255)

class rider_bookmarks:
    id=models.CharField(max_length=255)
    rider_id=models.CharField(max_length=255)
    bookmark_tag_name=models.CharField(max_length=255)
    bookmark_loc_id=models.OneToOneField(location, on_delete=models.CASCADE)

class user_review_system:
    id=models.CharField(max_length=10,primary_key=True)
    driver_id=models.OneToOneField(driver_details,on_delete=models.CASCADE)
    user_id=models.OneToOneField(rider_details,on_delete=models.CASCADE)
    description=models.TextField()
    rating_stars=models.IntegerField(max_value=5,min_Value=1)
    created_at=models.DateField()
    modify_at=models.DateField(null=True)

class driver_stats:
    id=models.CharField(max_length=10,primary_key=True)
    driver_id=models.OneToOneField(driver_details,on_delete=models.CASCADE)
    avg_rating=models.FloatField()
    total_trips=models.IntegerField()


class trip_initiated:
    id=models.CharField(max_length=255)
    rider_id=models.ForeignKey(rider_details,on_delete=models.CASCADE)
    driver_id=models.ForeignKey(driver_details,on_delete=models.CASCADE)
    start_loc_id=models.OneToOneField(location,on_delete=models.CASCADE)
    end_loc_id=models.OneToOneField(location,on_delete=models.CASCADE)
    trip_otp=models.IntegerField(max_length=6)
    issue_time=models.DateTimeField()
    trip_start_time=datetime()
    trip_end_time=datetime()
    trip_status=models.CharField(max_length=255)

class payment:
    id=models.CharField(max_length=10,primary_key=True)
    trip_id=models.OneToOneField(trip_initiated,on_delete=models.CASCADE)
    total_fare=models.IntegerField(max_length=10)
    payment_mode=models.CharField(max_length=255)
    transaction_id=models.CharField(max_length=255,unique=True)



