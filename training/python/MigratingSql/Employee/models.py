from django.db import models
from django.core.validators import MinValueValidator,MaxValueValidator

# Create your models here.
class Departments(models.Model):
	dept_id=models.PositiveIntegerField(validators=[MinValueValidator(1)],primary_key=True)
	manager_id=models.PositiveIntegerField(validators=[MinValueValidator(1)],unique=True,null=True)
	dept_name=models.CharField(max_length=20,blank=False)
	
class Managers(models.Model):
	emp_id=models.PositiveIntegerField(validators=[MinValueValidator(1000),MaxValueValidator(9999)],blank=False)
	manager_id=models.PositiveIntegerField(validators=[MinValueValidator(1)],primary_key=True)
	dept_id=models.ForeignKey(Departments,on_delete= models.CASCADE)
	manager_since=models.DateField(blank=False)

class Employees(models.Model):
	emp_id=models.PositiveIntegerField(validators=[MinValueValidator(1000),MaxValueValidator(9999)],primary_key=True)
	emp_name=models.CharField(max_length=20)
	age=models.PositiveIntegerField(blank=False,validators=[MinValueValidator(18)])
	date_joined=models.DateField(blank=False)
	manager_id=models.ForeignKey(Managers,on_delete=models.CASCADE)
	dept_id=models.ForeignKey(Departments,on_delete=models.CASCADE)

class PayScale(models.Model):
	dept_id=models.ForeignKey(Departments,on_delete=models.CASCADE)
	designation=models.CharField(max_length=20,blank=False)
	salary=models.PositiveIntegerField()
	pk=models.CompositePrimaryKey('dept_id','designation')










