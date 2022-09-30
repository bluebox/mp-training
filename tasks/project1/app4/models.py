from django.db import models
from django.contrib import admin
from django.contrib.auth.models import User


class Airport(models.Model):
    airport_id = models.PositiveIntegerField(primary_key=True)
    airport_name = models.CharField(max_length=50, blank=True, null=True)
    airport_owner = models.CharField(max_length=50, blank=True, null=True)
    city = models.CharField(max_length=50, blank=True, null=True)


class Terminal(models.Model):
    terminal_id = models.PositiveIntegerField(primary_key=True)
    terminal_description = models.TextField(max_length=200, blank=True, null=True)
    airport_id = models.ForeignKey(Airport, on_delete=models.CASCADE)


class Airlines(models.Model):
    airlines_name = models.CharField(max_length=50, primary_key=True)
    airlines_id = models.PositiveIntegerField(blank=True, null=True)
    origin_country = models.CharField(max_length=50, blank=True, null=True)
    airport_id = models.ForeignKey(Airport, on_delete=models.CASCADE)
    terminal_id = models.ForeignKey(Terminal, on_delete=models.CASCADE)
    airlines_img=models.ImageField(default=None, max_length=100)


class Flight(models.Model):
    flight_id = models.PositiveIntegerField(primary_key=True)
    flight_name = models.CharField(max_length=50, blank=True, null=True)
    destination = models.CharField(max_length=50, blank=True, null=True)
    model_number = models.CharField(max_length=50, blank=True, null=True)
    capacity = models.PositiveIntegerField(blank=True, null=True)
    weight = models.PositiveIntegerField(blank=True, null=True)
    airport_id = models.ForeignKey(Airport, on_delete=models.CASCADE)
    airlines_name = models.ForeignKey(Airlines, on_delete=models.CASCADE)


class Passenger(models.Model):
    passenger_id = models.PositiveIntegerField(primary_key=True)
    passenger_name = models.CharField(max_length=50, blank=True, null=True)
    mobile_number = models.CharField(max_length=50, blank=True, null=True)
    email = models.EmailField(max_length=50)
    passenger_city = models.CharField(max_length=50, blank=True, null=True)
    password=models.CharField(max_length=50, blank=True, null=True)


class Ticket(models.Model):
    ticket_id = models.PositiveIntegerField(primary_key=True,)
    booking_from = models.CharField(max_length=50, blank=True, null=True)
    booking_to = models.CharField(max_length=50, blank=True, null=True)
    travel_date = models.DateField()
    flight_id = models.ForeignKey(Flight, on_delete=models.CASCADE)
    passenger_id = models.ForeignKey(Passenger, on_delete=models.CASCADE)


class Schedule(models.Model):
    departure_time = models.DateTimeField(primary_key=True)
    arrival_time = models.DateTimeField()
    flight_id = models.OneToOneField(Flight, on_delete=models.CASCADE)
    airport_id = models.ForeignKey(Airport, on_delete=models.CASCADE)
    terminal_id = models.ForeignKey(Terminal, on_delete=models.CASCADE)


class Staff(models.Model):
    staff_id = models.PositiveIntegerField(primary_key=True)
    staff_designation = models.CharField(max_length=50, blank=True, null=True)
    staff_name = models.CharField(max_length=50, blank=True, null=True)
    staff_phone = models.PositiveIntegerField()
    airport_id = models.ForeignKey(Airport, on_delete=models.CASCADE)
    airlines_name = models.ForeignKey(Airlines, on_delete=models.CASCADE)


class StaffShifts(models.Model):
    shift_from = models.DateTimeField('default=timezone.now')
    shift_to = models.DateTimeField('default=timezone.now')
    staff_id = models.OneToOneField(Staff, on_delete=models.CASCADE)
    flight_id = models.ForeignKey(Flight, on_delete=models.CASCADE)


class Luggage(models.Model):
    luggage_type = models.CharField(max_length=50, blank=True, null=True)
    luggage_weight = models.PositiveIntegerField()
    ticket_id = models.OneToOneField(Ticket, on_delete=models.CASCADE)


# class Profile(models.Model):
#     user = models.OneToOneField(User, on_delete=models.CASCADE)
#     image = models.ImageField(default='default.png', upload_to = 'profile_pics')
#
#     def __str__(self):
#         return f'{self.user.username} Profile'
