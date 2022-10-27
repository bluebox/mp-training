from email.policy import default
from enum import unique
from random import choices


from django.db import models
import uuid



# Create your models here.


class User(models.Model):
    GENDER_CHOICES = (
        ('M', 'Male'),
        ('F', 'Female'),
        ('T','Transgender')
    )
    questions = (
        ('What is your pet name?', 'What is your pet name?'),
        ('What is the name of your first school?', 'What is the name of your first school?'),
        ('Who is your Childhood hero?', 'Who is your Childhood hero?'),
        ('What is your favourite past-time?','What is your favourite past-time?'),
        ('What is your all time favourite sports team?', 'What is your all time favourite sports team?'),
        ('What is your fathers middle name?', 'What is your fathers middle name?'),
        ('Where did you first meet your spouse?', 'Where did you first meet your spouse?'),

    )
    ROLE_CHOICES = (
        ('Admin', 'Admin'),
        ('User','User')
    )
    user_id = models.CharField(max_length=50, primary_key=True)
    first_name = models.CharField(max_length=50)
    last_name = models.CharField(max_length=50)
    adhar_no = models.CharField(max_length=50, default="")
    gender = models.CharField(max_length=1, choices=GENDER_CHOICES)
    age = models.IntegerField(default=0)
    mobile_no = models.CharField(max_length=13, unique=True)
    email = models.EmailField(max_length=50)
    city = models.CharField(max_length=50)
    state = models.CharField(max_length=50)
    pincode = models.CharField(max_length=12)
    password = models.CharField(max_length=50)
    security_ques = models.CharField(max_length=50, default="What is your pet name?",choices= questions)
    security_ans = models.CharField(max_length=50, default="john")
    role = models.CharField(max_length=10, choices=ROLE_CHOICES, default="User")
    def __str__(self):
        return self.first_name

class Coach(models.Model):
    coach_id = models.IntegerField(primary_key=True)
    coach_type = models.CharField(max_length=10, default="sleeper")
    avail_no_of_seats = models.IntegerField()

    def __str__(self):
        return self.coach_type
class Station(models.Model):
    station_no = models.CharField(max_length=20, primary_key=True)
    station_name = models.CharField(max_length=50)
    state = models.CharField(max_length=50, default="")
    address = models.CharField(max_length=50, default="")
   
    

    def __str__(self):
        return self.station_no

class Pnr(models.Model):
    id = models.UUIDField(primary_key=True, default=uuid.uuid4, editable=False)

class Train(models.Model):
    train_no = models.IntegerField(primary_key=True)
    train_name = models.CharField(max_length=50)
    source = models.ForeignKey(Station, on_delete=models.SET(None), related_name="source_tickets",default="")
    source_time = models.TimeField("source_time",auto_now=False, auto_now_add=False,default='20:00')
    dest_time = models.TimeField("dest_time",auto_now=False, auto_now_add=False,default='20:00')
    dest = models.ForeignKey(Station, on_delete=models.SET(None), related_name="dest_tickets", default="")
    distance = models.IntegerField("Distance", null=True)
    duration_h = models.IntegerField("Duration Hours", null=True)
    duration_m = models.IntegerField("Duration Minutes", null=True)
    def __int__(self):
        return self.train_no

class Seat_Chart(models.Model):
    train = models.ForeignKey(Train, on_delete=models.CASCADE, related_name="train_chart")
    first_ac = models.IntegerField("1st AC")
    second_ac = models.IntegerField("2nd AC")
    third_ac = models.IntegerField("3rd AC")
    sleeper = models.IntegerField("Sleeper")
    date = models.DateField("Date")

    class Meta:
        unique_together = ('train', 'date',)

    def get1A(self):
        return self.first_ac - self.chart_tickets.all().filter(type="1A").count()

    def get2A(self):
        return self.second_ac - self.chart_tickets.all().filter(type="2A").count()

    def get3A(self):
        return self.third_ac - self.chart_tickets.all().filter(type="3A").count()

    def getSL(self):
        return self.sleeper - self.chart_tickets.all().filter(type="SL").count()

    def __str__(self):
        return str(self.train) +" on "+str(self.date)

class Schedule(models.Model):
    arrival = models.CharField("Arrival", max_length=8, null=True)
    day = models.IntegerField("Day", null=True)
    train = models.ForeignKey(Train, on_delete=models.CASCADE, related_name="train_schedule")
    station = models.ForeignKey(Station, on_delete=models.CASCADE, related_name="station_schedule")
    id = models.IntegerField("id",primary_key=True)
    departure = models.CharField("Departure", max_length=8, null=True)

    def __str__(self):
        return str(self.train) +" at "+str(self.station)

class Booking(models.Model):
    # train_no = models.OneToOneField(Train, unique=True,on_delete=models.CASCADE)
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
        ('T','Transgender')
    )


    passenger_id = models.AutoField(primary_key=True)
    age = models.IntegerField()
    gender = models.CharField(max_length=1, choices=GENDER_CHOICES)
    name = models.CharField(max_length=50)
    

    def __str__(self):
        return self.name


# 7 Books Table

class Ticket(models.Model):

    Status_CHOICES = (
    ('Booked', 'Booked'),
    ('Cancelled', 'Cancelled')
)
    
    passenger = models.ManyToManyField(Passenger,max_length=20)  
    train = models.ForeignKey(Train, on_delete=models.CASCADE, related_name="train_tickets",default="")
    type = models.CharField("Type",max_length=2,default="")
    chart = models.ForeignKey(Seat_Chart, on_delete=models.CASCADE, related_name="chart_tickets",default="")
    user = models.ForeignKey(User, on_delete=models.CASCADE, related_name="tickets",default="")
    date = models.DateField("Date", null=True)
    fare = models.CharField("Fare",max_length=10,default="")
    seats = models.CharField("seat", max_length=100,default="")
    status = models.CharField("status", max_length=10,default="BOOKED")
    def __str__(self):
        return str(self.passenger) +" on "+str(self.date)+" in "+str(self.train)



class Book(models.Model):
    id = models.IntegerField(primary_key=True)
    user_id = models.ForeignKey(User, on_delete=models.CASCADE)

    def __str__(self):
        return self.id

class Cancel(models.Model):
    id = models.IntegerField(primary_key=True)
    ticket_id=models.ForeignKey(Ticket,on_delete=models.CASCADE,null=True,unique=True )
    user_id = models.ForeignKey(User, on_delete=models.CASCADE)
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


class StartDateOfJourney(models.Model):
    start_date = models.DateField(auto_now=False, auto_now_add=False)

class EndDateOfJourney(models.Model):
    end_date = models.DateField(auto_now=False, auto_now_add=False)