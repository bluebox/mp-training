from django.db import models

# Create your models here.

class UserDetails(models.Model):
    first_name = models.CharField(max_length=50)
    last_name = models.CharField(max_length=50)
    email = models.CharField(max_length=50)
    password = models.IntegerField()

    def __str__(self):
        return self.first_name
