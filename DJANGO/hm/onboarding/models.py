from django.db import models



class creating_employee(models.Model):
    cid = models.IntegerField(unique=True,default=0)
    eid = models.IntegerField(primary_key=True)
    dept_id = models.IntegerField(unique=True)
    bankdetail = models.ImageField(upload_to='images/')
    passport = models.ImageField(upload_to='images/')
    pancard = models.ImageField(upload_to='images/')
    aadharcard = models.ImageField(upload_to='images/')
    status  = models.CharField(max_length=50,default="-")
    name  = models.CharField(max_length=50,default="-")
    designeation  = models.CharField(max_length=50,default="-")
    contact  = models.CharField(max_length=50,default="-")
    age  = models.CharField(max_length=50,default="-")
    mail  = models.EmailField(max_length=50,default="-")
    gender  = models.CharField(max_length=50,default="-")

