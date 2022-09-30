from django.db import models
from . import views

# Create your models here.


class User(models.Model):
    GENDER_CHOICES = (
        ('M', 'Male'),
        ('F', 'Female'),
    )

    user_id = models.CharField(max_length=50, primary_key=True, validators=[views.validate_userId])
    first_name = models.CharField(max_length=50)
    last_name = models.CharField(max_length=50)
    adhar_no = models.CharField(max_length=50, default="")
    gender = models.CharField(max_length=1, choices=GENDER_CHOICES)
    age = models.IntegerField(default=0)
    mobile_no = models.CharField(max_length=13, validators=[views.validate_phone])
    email = models.EmailField(max_length=50)
    city = models.CharField(max_length=50)
    state = models.CharField(max_length=50)
    pincode = models.CharField(max_length=12)
    password = models.CharField(max_length=50, validators=[views.validate_password])
    security_ques = models.CharField(max_length=50, default="")
    security_ans = models.CharField(max_length=50, default="")

    def __str__(self):
        return self.first_name

class Coach(models.Model):
    coach_id = models.IntegerField(primary_key=True)
    coach_type = models.CharField(max_length=10, default="sleeper")
    avail_no_of_seats = models.IntegerField()

    def __str__(self):
        return self.coach_id

class Train(models.Model):
    train_no = models.CharField( max_length=50, primary_key= True)
    train_name = models.CharField(max_length=50)
    arrival_time = models.TimeField(auto_now=False, auto_now_add=False)
    departure_time = models.TimeField(auto_now=False, auto_now_add=False)
    availability_of_seats = models.IntegerField()
    train_date = models.DateField(auto_now=False, auto_now_add=False)
    coach_id = models.ForeignKey(Coach, on_delete=models.CASCADE)

    def __str__(self):
        return self.train_no

class Ticket(models.Model):
    STATUS_CHOICES = (
        ('WL', 'Waiting list'),
        ('CNF', 'Confirm'),
        ('RAC', 'RAC'),
    )
    id = models.AutoField(primary_key=True)
    user_id = models.ForeignKey(User, on_delete=models.CASCADE)
    status = models.CharField(max_length=3, choices=STATUS_CHOICES)
    no_of_passengers = models.IntegerField()
    train_no = models.ForeignKey(Train, on_delete=models.CASCADE)
    coach_type = models.ForeignKey(Coach,max_length=10,on_delete=models.CASCADE)
    berth_no = models.IntegerField()

    def __str__(self):
        return self.id

class Station(models.Model):
    station_no = models.CharField(max_length=20, primary_key=True)
    station_name = models.CharField(max_length=50)
    hault = models.IntegerField()
    arrival_time = models.TimeField(auto_now=False, auto_now_add=False)
    train_no = models.ForeignKey(Train, on_delete=models.CASCADE)

    def __str__(self):
        return self.station_no
class Booking(models.Model):
    train_no = models.OneToOneField(Train, unique=True,on_delete=models.CASCADE)
    b_seat1 = models.IntegerField()
    b_seat2 = models.IntegerField()
    w_seat1 = models.IntegerField()
    w_seat2 = models.IntegerField()
    a_seat1 = models.IntegerField()
    a_seat2 = models.IntegerField()
    fair2 = models.IntegerField()
    fair2 = models.IntegerField()

    def __str__(self):
        return self.a_seat1

class Passenger(models.Model):
    GENDER_CHOICES = (
        ('M', 'Male'),
        ('F', 'Female'),
    )

    STATUS_CHOICES = (
        ('WL', 'Waiting list'),
        ('CNF', 'Confirm'),
        ('RAC', 'RAC'),
    )

    passenger_id = models.IntegerField(primary_key=True)
    pnr_no = models.IntegerField()
    age = models.IntegerField()
    gender = models.CharField(max_length=1, choices=GENDER_CHOICES)
    user_id = models.ForeignKey(User, on_delete=models.CASCADE)
    reservation_status = models.CharField(max_length=3, choices=STATUS_CHOICES)
    seat_no = models.CharField(max_length=10)
    name = models.CharField(max_length=50)
    ticket_id = models.ForeignKey(Ticket, on_delete=models.CASCADE)

    def __str__(self):
        return self.passenger_id


# 7 Books Table


class Book(models.Model):
    id = models.IntegerField(primary_key=True)
    user_id = models.ForeignKey(User, on_delete=models.CASCADE)

    def __str__(self):
        return self.id

class Cancel(models.Model):
    id = models.IntegerField(primary_key=True)
    user_id = models.ForeignKey(User, on_delete=models.CASCADE)
    passenger_id = models.ForeignKey(Passenger, on_delete=models.CASCADE)

    def __str__(self):
        return self.id
class TrainStatus(models.Model):
    train_no = models.ForeignKey(Train, on_delete=models.CASCADE)
    station_no = models.ForeignKey(Station, on_delete=models.CASCADE)
    expected_arrival = models.TimeField(auto_now=False, auto_now_add=False)
    reached_time = models.TimeField(auto_now=False, auto_now_add=False)
    expected_departure = models.TimeField(auto_now=False, auto_now_add=False)
    departure_time = models.TimeField(auto_now=False, auto_now_add=False)
    journey_date = models.DateField(auto_now=False, auto_now_add=False)

    def __str__(self):
        return self.train_no