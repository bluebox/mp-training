from django.core.validators import MinValueValidator, MaxValueValidator
from django.db import models

# Create your models here.

class EmployeeModel(models.Model):
    employee_name = models.CharField(max_length=100)
    employee_email = models.EmailField()
    birth_date = models.DateField()
    mobile_number = models.PositiveBigIntegerField(validators=[MinValueValidator(6000000000), MaxValueValidator(10000000000)])
    date_joined = models.DateTimeField(auto_now_add=True)

    def __str__(self):
        return self.employee_name
