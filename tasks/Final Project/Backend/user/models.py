from django.db import models
from django.contrib.auth.models import User
from base.models import Movie
# Create your models here.

class TheatreOwner(models.Model):
    user = models.OneToOneField(User, on_delete=models.CASCADE)
    movie = models.ForeignKey(Movie, on_delete=models.CASCADE)
    theatre_owner_id = models.IntegerField(null=True, blank=True)
    movie_name = models.CharField(max_length=200, null=True, blank=True)
    name = models.CharField(max_length=200, null=True, blank=True)
    contact_number = models.IntegerField(null=True, blank=True)
    address = models.CharField(max_length=200, null=True, blank=True)
    email = models.EmailField(max_length=200, null= True, blank=True)

    def __str__(self):
        return self.name
