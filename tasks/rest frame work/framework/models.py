from django.db import models

# Create your models here.

class department(models.Model):
    deptid = models.AutoField(primary_key = True)
    deptname = models.CharField(max_length=25)

class employee(models.Model):
    empid = models.AutoField(primary_key = True)
    empname = models.CharField(max_length=25)
    empage = models.IntegerField()
    department = models.CharField(max_length=25)    