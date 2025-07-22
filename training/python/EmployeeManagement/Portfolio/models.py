from django.core.validators import MinValueValidator, MaxValueValidator
from django.db import models

# Create your models here.

class DepartmentModel(models.Model):
    dept_name = models.CharField(max_length=100,blank=True,null=False,default='NOT DETERMINED',unique=True)

    def __str__(self):
        return self.dept_name

class EmployeeModel(models.Model):
    employee_name = models.CharField(max_length=100,blank=False,null=False)
    employee_email = models.EmailField(blank=False,unique=True)
    birth_date = models.DateField(blank=False)
    mobile_number = models.PositiveBigIntegerField(blank=False,validators=[MinValueValidator(6000000000), MaxValueValidator(9999999999)])
    date_joined = models.DateTimeField(auto_now_add=True,blank=True)
    # reference_files_url = models.URLField(blank=True,null=False)
    # country = models.CharField(choices = Countries.choices, max_length=3, default=Countries.INDIA)
    dept_id=models.ForeignKey(DepartmentModel,on_delete=models.CASCADE)
    class Meta:
        pass

    def __str__(self):
        return self.employee_name

class EmployeeFiles(models.Model):
    emp_id=models.ForeignKey(EmployeeModel,on_delete=models.CASCADE)
    link_to_files=models.URLField(blank=False,null=False)


# class Address(models.Model):
#     class Countries(models.TextChoices):
#         INDIA="IND","India"
#         CHINA="CHN","China"
#         MEXICO="MX","Mexico"
#         SRILANKA="SL","Sri Lanka"
#         NEWZEALAND="NZ","New Zealand"
#         USA="USA","America"
#     country=models.CharField(choices = Countries.choices,max_length=3,default=Countries.INDIA)
#     emp_id=models.ForeignKey(EmployeeModel,on_delete=models.CASCADE)








