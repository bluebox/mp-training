from django.db import models

# Create your models here.


class Role(models.Model):  # 2. model for roles of the employees

    role_id = models.IntegerField(primary_key=True)
    role_name = models.CharField(max_length=50)

    def __str__(self):
        return self.role_name


class Employee(models.Model):  # 3. model for employees data

    employee_id = models.IntegerField(primary_key=True)
    employee_name = models.CharField(max_length=50)
    role_id = models.ForeignKey(Role, on_delete=models.CASCADE)
    doj = models.DateField()
    phone = models.CharField(max_length=13)
    email = models.EmailField(max_length=60)
    address = models.CharField(max_length=250)
    salary = models.FloatField()

    class Meta:
        managed = True
        db_table="gmemployee"

    def __str__(self):
        return self.employee_name