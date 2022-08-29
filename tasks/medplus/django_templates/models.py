from django.db import models
# Create your models here.
class variables(models.Model):
    first_name = models.TextField(max_length=100)
    last_name = models.TextField(max_length=100)
    phone_number = models.IntegerField()
    def __str__(self):
        return self.first_name+self.last_name
    