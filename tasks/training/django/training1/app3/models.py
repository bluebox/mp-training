from django.db import models
import uuid


class Employee(models.Model):
    emp_name = models.CharField(max_length=120)
    emp_role = models.TextField(blank=True, null=True)
    emp_email= models.EmailField(null=False, blank=False)
    emp_salary = models.IntegerField(null=True)
    id = models.UUIDField(primary_key=True, default=uuid.uuid4, editable=False)













