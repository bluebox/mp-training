from django.db import models

# Create your models here.

class TeamMember(models.Model):
    first_name = models.CharField(max_length=100, null=True , blank=True)
    last_name = models.CharField(max_length=100, null=True , blank=True)
    employee_code = models.CharField(max_length=6, null=False , blank=False,unique=True)

    def __str__(self):
        return self.first_name