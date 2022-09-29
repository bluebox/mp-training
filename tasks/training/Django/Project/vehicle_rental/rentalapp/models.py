from django.db import models
from django.core.exceptions import ValidationError
# Create your models here.
def mail_validator(value):
    if value.endswith('.com') :
        return value
    else :
        raise ValidationError("email should ends with .com")

class Customer(models.Model) :
    customer_id = models.AutoField(primary_key=True)
    name = models.CharField(max_length=50)
    dl_no = models.CharField(max_length=50, unique=True)
    contact_no = models.CharField(max_length=50, unique=True)
    email = models.CharField(max_length=50, validators=[mail_validator], unique=True)
    address = models.CharField(max_length=200)
    password= models.CharField(max_length=50, default=0)

    # USERNAME_FIELD = 'email'
    # REQUIRED_FIELDS = []

    def __str__(self):
        return self.name

class Owner(models.Model):
    owner_id = models.AutoField(primary_key=True)
    name= models.CharField(max_length=50)
    contact_no = models.CharField(max_length=50, unique=True)
    email = models.CharField(max_length=50, validators=[mail_validator], unique=True)
    address = models.CharField(max_length=200)
    password= models.CharField(max_length=50, default=0)

    USERNAME_FIELD = 'email'
    REQUIRED_FIELDS = []


    def __str__(self):
        return self.name

class Vehicle(models.Model):
    vehicle_no = models.CharField(max_length=10, primary_key=True, )
    type = models.CharField(max_length=50)
    brand = models.CharField(max_length=50)
    model = models.CharField(max_length=50)
    image = models.TextField()
    owner_id = models.ForeignKey(Owner, on_delete=models.CASCADE)
    price_km = models.FloatField()
    price_hour = models.FloatField()
    price_day = models.FloatField()

    def __str__(self):
        return (self.vehicle_no)

class Vehicle_status(models.Model):
    sl_no = models.AutoField(primary_key=True)
    vehicle_no = models.OneToOneField(Vehicle, on_delete=models.CASCADE)
    is_available = models.BooleanField()


class Rent_Trip(models.Model):
    rent_id = models.AutoField(primary_key=True)
    vehicle_no = models.ForeignKey(Vehicle, on_delete=models.CASCADE)
    customer_id = models.ForeignKey(Customer, on_delete=models.CASCADE)
    owner_d = models.ForeignKey(Owner, on_delete=models.CASCADE)
    pickup_time_date = models.DateTimeField()
    return_date_time = models.DateTimeField()
    odo_start_reading = models.IntegerField()
    odo_end_reading = models.IntegerField()
    customer_review = models.CharField(max_length=500)
    owner_review = models.CharField(max_length=500)

    def __str__(self):
        return self.rent_id

class Bill(models.Model):
    bill_no = models.AutoField(primary_key=True)
    rental_id = models.OneToOneField(Rent_Trip, on_delete=models.CASCADE)
    rental_days = models.IntegerField()
    rental_hours = models.IntegerField()
    km_ran = models.IntegerField()
    amount = models.IntegerField()

    def __str__(self):
        return self.bill_no


