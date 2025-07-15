from django.db import models
from django.core.validators import MinValueValidator,MaxValueValidator
from django.db.models import Q,F
from datetime import datetime 
# Create your models here.
class Departments(models.Model):
	dept_id=models.PositiveIntegerField(validators=[MinValueValidator(1)],primary_key=True)
	dept_name=models.CharField(max_length=20,blank=False)

class Employees(models.Model):
	emp_id=models.PositiveIntegerField(validators=[MinValueValidator(1000),MaxValueValidator(9999)],primary_key=True)
	emp_name=models.CharField(max_length=20,blank=False)
	dob=models.DateField(blank=False)
	date_joined=models.DateField(blank=False,default=datetime.today)
	dept_id=models.ForeignKey(Departments,on_delete=models.CASCADE)
	# class Meta:
	# 	constraints=[models.CheckConstraint(condition=models.Q(models.ExpressionWrapper(models.functions.ExtractYear(F('date_joined')-F('dob')),output_field=models.IntegerField())>=18),name="eligibility_constraint"),]
	
class DepartmentHeads(models.Model):
	emp_id=models.ForeignKey(Employees,on_delete=models.CASCADE,blank=False)
	dept_head_id=models.PositiveIntegerField(validators=[MinValueValidator(1)],primary_key=True)
	dept_id=models.ForeignKey(Departments,on_delete= models.CASCADE,unique=True,blank=False)
	dept_head_since=models.DateField(blank=False)

class Designations(models.Model):
	designation=models.CharField(max_length=20,primary_key=True)


class PayScale(models.Model):
	dept_id=models.ForeignKey(Departments,on_delete=models.CASCADE,blank=False)
	designation=models.ForeignKey(Designations,on_delete=models.CASCADE,blank=False)
	salary=models.DecimalField(max_digits=10,decimal_places=2,default=0.00,blank=False)
	pk=models.CompositePrimaryKey('dept_id','designation')










