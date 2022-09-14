from django.db import models
from Faculty.models import faculty_details

# Create your models here.

class subject_details(models.Model):
    subject_code = models.CharField(max_length=6, primary_key=True)
    subject_name = models.CharField(max_length=35)
    sub_subject_name = models.CharField(max_length=35)

class class_details(models.Model):
    class_code = models.CharField(max_length=6, primary_key=True)
    class_name = models.CharField(max_length=35)

    # def __str__(self):
    #     return self.class_code, self.class_name

class batch_details(models.Model):
    batch_id = models.IntegerField(primary_key=True)
    faculty_name = models.CharField(max_length=40)
    time = models.TimeField()
    facul_username = models.ForeignKey(faculty_details, max_length=35, on_delete=models.CASCADE)
    class_code = models.ForeignKey(class_details, max_length=6, on_delete=models.CASCADE)
    subject_code = models.ForeignKey(subject_details, max_length=6, on_delete=models.CASCADE)
