from django.db import models

class Student(models.Model):
    name=models.CharField(max_length=100)
    age=models.IntegerField(null=True,blank=True)
    def __str__(self):
        return self.name
    class Meta:
        db_table='students'


class Example(models.Model):
    emp_id=models.CharField(max_length=20)
    emp_name=models.CharField(max_length=30)
    designation=models.CharField(max_length=30)

    def __str__(self):
        return self.emp_name

    class Meta:
        db_table='Example'

class Employee(models.Model):
    name=models.CharField(max_length=20)
    age=models.IntegerField()
    salary=models.CharField(max_length=20)

    def __str__(self):
        return self.name
    class Meta:
        db_table="Employee"