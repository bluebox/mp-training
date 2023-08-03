from django.db import models

# Create your models here.

class City(models.Model):
    name = models.CharField(max_length=200, null=True, blank=True)
    state = models.CharField(max_length=200, null=True, blank=True)
    zipCode = models.CharField(max_length=200, null=True, blank=True)
    city_id = models.AutoField(primary_key=True, editable=False)
    def __str__(self):
        return self.name

class Cinema(models.Model):
    cinemaId = models.AutoField(primary_key=True, editable=False)
    name = models.CharField(max_length=200, null=True, blank=True)
    totalCinemaHalls = models.IntegerField(null=True, blank=True, default=1)
    city = models.ForeignKey(City, on_delete=models.CASCADE, null=True)
    def __str__(self):
        return self.name

class Cinema_Hall(models.Model):
    cinema_hall_id = models.AutoField(primary_key=True, editable=False)
    name = models.CharField(max_length=200, null=True, blank=True)
    totalSeats = models.IntegerField(null=True, blank=True, default=0)
    cinema = models.ForeignKey( Cinema, on_delete=models.CASCADE, null=True)
    def __str__(self):
        return self.name

class Cinema_Seat(models.Model):
    cinema_seat_id = models.AutoField(primary_key=True, editable=False)
    seat_number = models.IntegerField(null=True, blank=True, default=0)
    type = models.CharField(max_length=200, null=True, blank=True)
    cinemahall = models.ForeignKey( Cinema_Hall , on_delete=models.CASCADE, null=True)
    def __str__(self):
        return str(self.cinemahall)
