from django.db import models

class Student(models.Model):
    name=models.CharField(max_length=100)
    age=models.IntegerField(null=True,blank=True)
    def __str__(self):
        return self.name
    class Meta:
        db_table='students'