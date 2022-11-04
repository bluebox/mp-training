from django.db import models

# Create your models here.

class Appointment(models.Model):
    patient_name = models.CharField(max_length=50)
    age = models.IntegerField(null=True)
    disease = models.CharField(max_length=100)

    def __str__(self):
        return self.patient_name