from django.db import models
from django.core.validators import MinValueValidator,MaxValueValidator
from django.db.models import Q, F, CheckConstraint
from datetime import datetime 
# Create your models here.


class Departments(models.Model):
	class DepartmentChoices(models.TextChoices):
		RD="R&D","Research and Development"
		FSD="FSD","Full Stack Developer"
		BD="BD","Backend Development"
		PD="PD","Production"
		TS="TS","Transactions"
		ND="ND","Not Determined"


	dept_name=models.CharField(max_length=3,blank=False,choices=DepartmentChoices.choices,default=DepartmentChoices.ND,unique=True)

class Designations(models.Model):
	# designation_choices={"ASE":"Associate Software Engineer","JSE":"Junior Software Engineer","SSE":"Senior Software Engineer"}
	class DesignationChoices(models.TextChoices):
		ASE="ASE","associate software engineer"
		JSE="JSE","junior software engineer"
		SSE="SSE","senior software engineer"
		NOD="NOD","Not Assigned"
	designation=models.CharField(max_length=3,unique=True,default=DesignationChoices.NOD,choices=DesignationChoices.choices)


class Employees(models.Model):
	emp_id=models.PositiveIntegerField(validators=[MinValueValidator(1000),MaxValueValidator(9999)],primary_key=True)
	emp_name=models.CharField(max_length=20,blank=False)
	dob=models.DateField(blank=False,null=True)
	designation=models.ForeignKey(Designations, on_delete=models.CASCADE,related_name='employees_des')
	date_joined=models.DateField(blank=False,default=datetime.today)
	dept=models.ForeignKey(Departments,on_delete=models.CASCADE,related_name='employees_dept')
	class Meta:
		constraints = [
			CheckConstraint(
				check=Q(emp_id__gte=1000) & Q(emp_id__lte=9999),
				name='emp_id'
			)
		]


class DepartmentHeads(models.Model):
	emp=models.ForeignKey(Employees,on_delete=models.CASCADE,blank=False,related_name='departmentheads')
	dept_head_since=models.DateField(blank=False)


class PayScale(models.Model):
	emp=models.ForeignKey(Employees,on_delete=models.CASCADE,blank=False, related_name="payscale")
	salary=models.DecimalField(max_digits=10,decimal_places=2,default=0.00,blank=True)






#controller.py,manage.py