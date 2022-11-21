from django.db import models

# Create your models here.


class Vehicle(models.Model):  # 4. model for vehicles information

    vehicle_no = models.CharField(primary_key=True, max_length=20)
    model = models.CharField(max_length=20)
    owner_name = models.CharField(max_length=50)
    permit_range = models.CharField(max_length=50)
    fuel_efficiency = models.FloatField()
    load_capacity = models.FloatField()

    def __str__(self):
        return self.vehicle_no