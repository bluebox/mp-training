from django.db import models
class User(models.Model):
    Name = models.CharField(max_length=150)
    Age = models.IntegerField()
    Email = models.CharField(max_length=150)
    PhoneNumber = models.CharField(max_length=13)
    Branch = models.CharField(max_length=3)
    Languages = models.CharField(max_length=10)
    State = models.CharField(max_length=50)
    City = models.CharField(max_length=50)

    def __str__(self):
        return self.Name
