'Models'
import datetime
from django.db import models
from django.contrib.auth.models import (
     AbstractUser
)


class Users(AbstractUser):
    'User database'
    User_id = models.AutoField(primary_key=True)
    User_name = models.CharField(max_length=45,default="")
    User_phone = models.CharField(max_length=45,default="",unique=True)
    User_email = models.CharField(max_length=45,default="",unique=True)
    password = models.CharField(max_length=45,default="mphs")
    User_DOB = models.DateField(default=datetime.date.today())
    User_Address = models.CharField(max_length=450)
    username = None
    is_active = models.CharField(default=False,max_length=25)
    Role_choices=[('Admin','Admin'),('User','User'),('TheatreOwner','TheatreOwner')]
    Role = models.CharField(max_length=20,choices=Role_choices,default='User')
    USERNAME_FIELD = 'User_email'
    REQUIRED_FIELDS = ['name']


class Movie(models.Model):
    'Movie Database'
    Movie_id = models.IntegerField(primary_key=True)
    Movie_poster=models.CharField(max_length=1000,default='https://cdn.bollywoodmdb.com/fit-in/'
                                                          'movies/largethumb/2021/jug-jugg-jeeyo/'
                                                          'jug-jugg-jeeyo-poster-5.jpg')
    Movie_name = models.CharField(max_length=45)
    Movie_lang = models.CharField(max_length=45)
    Movie_details = models.CharField(max_length=450)
    Movie_Release_date = models.DateField(default=datetime.date.today())
    Movie_category = models.CharField(max_length=450)
    Movie_cast = models.CharField(max_length=450)
    Movie_reviews = models.CharField(max_length=450)


class Theatre(models.Model):
    'theare Database'
    Theatre_id = models.IntegerField(primary_key=True)
    Theatre_owner=models.CharField(max_length=45,default='Owner')
    Theatre_name = models.CharField(max_length=45)
    Theatre_location = models.CharField(max_length=45)
    Theatre_screens = models.IntegerField(default=1)


class Hall(models.Model):
    'Hall database'
    Hall_id = models.AutoField(primary_key=True)
    Theatre_id = models.ForeignKey(Theatre, on_delete=models.CASCADE)
    Hall_no = models.IntegerField(default=1)
    Date = models.DateField(default=datetime.date.today())
    rows=models.IntegerField(default=10)
    cols=models.IntegerField(default=10)
    baseprice=models.IntegerField(default=100)


class PROMOCODE(models.Model):
    'promocode database'
    PROMOCODE = models.CharField(max_length=45)
    Discount = models.IntegerField()

class Booking(models.Model):
    'booking database'
    Booking_id = models.AutoField(primary_key=True)
    User_id = models.ForeignKey(Users, on_delete=models.CASCADE)
    Movie_id = models.ForeignKey(Movie, on_delete=models.CASCADE)
    Theatre_id = models.ForeignKey(Theatre, on_delete=models.CASCADE)
    Hall_id = models.ForeignKey(Hall, on_delete=models.CASCADE)
    # Selected_seats_id = models.ForeignKey(Selected_seats, on_delete=models.CASCADE)
    Date = models.DateField(default=datetime.date.today())
    Selected_seats=models.CharField(max_length=500,default="")
    Booking_choices = [('Booked', 'Booked'), ('Cancelled', 'Cancelled')]
    Booking_status=models.CharField(max_length=25,default='Booked',choices=Booking_choices)
    PROMOCODE = models.ForeignKey(PROMOCODE, on_delete=models.CASCADE,default=2)
    T_price = models.IntegerField(default=100)
    Payment_choices = [('Paid', 'Paid'), ('Not Paid', 'Not Paid')]
    Payment_Status = models.CharField(max_length=45, default='Not Paid',choices=Payment_choices)
    Payment_mode = models.CharField(max_length=45, default='Online')


class Payment(models.Model):
    'payment database'
    Booking_id = models.ForeignKey(Booking, on_delete=models.CASCADE)
    PROMOCODE = models.ForeignKey(PROMOCODE, on_delete=models.CASCADE)
    T_price = models.IntegerField()
    Payment_Status = models.CharField(max_length=45,default="Paid")
    Payment_mode = models.CharField(max_length=45,default='Online')


class DatewiseHall(models.Model):
    'hall data with respect to different dates'
    Date=models.DateField(default=datetime.date.today())
    Hall_id=models.ForeignKey(Hall,on_delete=models.CASCADE)
    T_No_Of_Seats = models.IntegerField(default=100)
    Movie_id = models.ForeignKey(Movie, on_delete=models.CASCADE,default=2)
    startTime = models.TimeField(auto_now=False,default="17:30:00")
    endTime = models.TimeField(auto_now=False,default="20:30:00")

    class Meta:
        unique_together = ('Date', 'Hall_id','startTime','endTime')

# --------------------------------------------------------------------------------
# --------------------------------------------------------------------------------



class Seating(models.Model):
    Seating_id = models.IntegerField(primary_key=True)
    Theatre_id = models.ForeignKey(Theatre, on_delete=models.CASCADE)
    Hall_id = models.ForeignKey(Hall, on_delete=models.CASCADE)
    Type_of_seat = models.CharField(max_length=25)
    No_Of_Seats = models.IntegerField()


# class Selected_seats(models.Model):
#     Selected_seats_id = models.IntegerField(primary_key=True)
#     Seating_id = models.ForeignKey(Seating,on_delete=models.CASCADE)
#     User_id = models.ForeignKey(User, on_delete=models.CASCADE)

# class Snacks(models.Model):
#     Snacks_id = models.IntegerField()
#     Snacks_name = models.CharField(max_length=45)
#     Snacks_price = models.IntegerField()
#
#     class Meta:
#         managed = True


class Cart(models.Model):
    Cart_id = models.IntegerField()
    User_id=models.ForeignKey(Users,on_delete=models.CASCADE,default=1)
    # Snacks = models.ManyToManyField(Snacks)
    quantity = models.IntegerField()
    total_snacks_price = models.IntegerField()

    class Meta:
        managed = True
# if managed is false in either of the tables then the intermidiate table will not be created if
# two models are in many-to-many relation


class Bill(models.Model):
    Bill_id = models.IntegerField()
    Cart_id = models.ForeignKey(Cart, on_delete=models.CASCADE)
    Payment_id = models.ForeignKey(Payment, on_delete=models.CASCADE)
    total_price = models.IntegerField()
    Payment_Status = models.CharField(max_length=45)
    Payment_mode = models.CharField(max_length=45)
