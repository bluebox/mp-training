from django.db import models

# Create your models here.
class User(models.Model):
    first_name = models.CharField(max_length=30,null=False)
    last_name = models.CharField(max_length=30)
    email = models.EmailField(null=False)
    phone_number = models.IntegerField()

class UserPatterns(models.Model):
    user = models.ForeignKey(User,on_delete=models.CASCADE,primary_key=True)

