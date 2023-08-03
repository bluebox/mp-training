from django.db import models
from base.models import Movie

# Create your models here.

class Show(models.Model):
    show_id = models.AutoField(primary_key=True, editable=False)
    dateOfShow = models.DateField()
    startTime = models.TimeField()
    endTime = models.TimeField()
    # cinemaHall_id 
    movie = models.ForeignKey(Movie, on_delete=models.SET_NULL, null=True)

