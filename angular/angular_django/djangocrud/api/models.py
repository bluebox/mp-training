from django.db import models

# Create your models here.
class movies(models.Model):
    movie_name = models.CharField(max_length=255)
    movie_description = models.TextField(max_length=255)
    date_joined=models.DateField()
