from doctest import master
from django.db import models
from django_countries.fields import CountryField   
# Create your models here.j


class freelancer_details(models.Model):
    id = models.IntegerField(primary_key=True)
    first_name = models.CharField()
    last_name = models.CharField()
    email_id = models.EmailField()
    phone_number = models.CharField(max_length=12)
    password = models.CharField(max_length=25)
    country = CountryField(multiple=True)
