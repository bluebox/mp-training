import datetime

from django.db import models
from home.views import validate_Phone
# Create your models here.
class User(models.Model):
    User_id= models.IntegerField(primary_key=True,default=1)
    User_name= models.CharField(max_length=45)
    User_phone = models.CharField(max_length=45, validators=[validate_Phone])
    User_email = models.CharField(max_length=45)
    User_DOB = models.DateField(default=datetime.date.today())
    User_Address = models.CharField(max_length=450)

class Movie(models.Model):
    Movie_id= models.IntegerField(primary_key=True)
    Movie_name= models.CharField(max_length=45)
    Movie_lang = models.CharField(max_length=45)
    Movie_details = models.CharField(max_length=45)
    Movie_Release_date = models.DateField(default=datetime.date.today())
    Movie_category = models.CharField(max_length=450)
    Movie_cast = models.CharField(max_length=450)
    Movie_reviews= models.CharField(max_length=450)

class Theatre(models.Model):
    Theatre_id = models.IntegerField(primary_key=True)
    Theatre_name = models.CharField(max_length=45)
    Theatre_location = models.CharField(max_length=45)
    Theatre_screens = models.IntegerField(default=1)

class Hall(models.Model):
    Hall_id = models.IntegerField(primary_key=True)
    Theatre_id = models.OneToOneField(Theatre,on_delete=models.CASCADE)
    Movie_id = models.ForeignKey(Movie,on_delete=models.CASCADE)
    Hall_no = models.IntegerField(default=1)
    T_No_Of_Seats = models.IntegerField(default=1)

class Seating(models.Model):
    Seating_id= models.IntegerField(primary_key=True)
    Theatre_id = models.ForeignKey(Theatre,on_delete=models.CASCADE)
    Hall_id = models.ForeignKey(Hall,on_delete=models.CASCADE)
    Type_of_seat = models.IntegerField()
    No_Of_Seats = models.IntegerField()

class Selected_seats(models.Model):
    Selected_seats_id = models.IntegerField(primary_key=True)
    Seating_id = models.ForeignKey(Seating,on_delete=models.CASCADE)
    User_id = models.ForeignKey(User, on_delete=models.CASCADE)

class Booking(models.Model):
    Booking_id= models.IntegerField(primary_key=True)
    User_id = models.ForeignKey(User, on_delete=models.CASCADE)
    Movie_id = models.ForeignKey(Movie,on_delete=models.CASCADE)
    Theatre_id = models.ForeignKey(Theatre,on_delete=models.CASCADE)
    Hall_id = models.ForeignKey(Hall, on_delete=models.CASCADE)
    Selected_seats_id = models.ForeignKey(Selected_seats, on_delete=models.CASCADE)
    Date= models.DateField(default=datetime.date.today())

class PROMOCODE(models.Model):
    PROMOCODE=models.CharField(max_length=45)
    Discount=models.IntegerField()

class Payment(models.Model):
    Payment_id=models.IntegerField()
    Booking_id=models.ForeignKey(Booking,on_delete=models.CASCADE)
    PROMOCODE= models.ForeignKey(PROMOCODE,on_delete=models.CASCADE)
    T_price=models.IntegerField()



class Snacks(models.Model):
    Snacks_id=models.IntegerField()
    Snacks_name=models.CharField(max_length=45)
    Snacks_price= models.IntegerField()

    class Meta:
        managed = True

class Cart(models.Model):
    Cart_id=models.IntegerField()
    # User_id=models.ForeignKey(User,on_delete=models.CASCADE)
    Snacks=models.ManyToManyField(Snacks)
    quantity= models.IntegerField()
    total_snacks_price= models.IntegerField()

    class Meta:
        managed = True
        # if managed is false in either of the tables then the intermidiate table will not be created if two models are in many to many relation


class Bill(models.Model):
    Bill_id=models.IntegerField()
    Cart_id = models.ForeignKey(Cart,on_delete=models.CASCADE)
    Payment_id = models.ForeignKey(Payment,on_delete=models.CASCADE)
    total_price=models.IntegerField()
    Payment_Status=models.CharField(max_length=45)
    Payment_mode= models.CharField(max_length=45)