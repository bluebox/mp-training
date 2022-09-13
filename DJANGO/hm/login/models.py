from django.db import models

# Create your models here.
class emp_login(models.Model):
    eid =models.IntegerField(unique=True)
    loginid=models.CharField(max_length=50,default="")
    password = models.CharField(max_length=50,default="")



class apply_candiate_login(models.Model):
    cid =models.IntegerField(unique=True)
    loginid=models.CharField(max_length=50,default="")
    password = models.CharField(max_length=50,default="")