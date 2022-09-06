from django.db import models
import uuid

class Employee(models.Model):

    emp_name = models.CharField(max_length=120)
    emp_role = models.TextField(blank=True, null=True)
    emp_salary = models.DecimalField(decimal_places=2, max_digits=1000)
    emp_mail = models.TextField(blank=True, null=True)
    id = models.UUIDField(primary_key=True, default=uuid.uuid4, editable=False)
