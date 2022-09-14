from django.db import models

# Create your models here.

class faculty_details(models.Model):
    first_name = models.CharField(max_length=20)
    last_name = models.CharField(max_length=20)
    facul_username = models.CharField(max_length=35, primary_key=True)
    password = models.CharField(max_length=12)
    gender = models.CharField(max_length=6)
    date_of_birth = models.DateField()
    main_subject = models.CharField(max_length=35)
    department = models.CharField(max_length=35)
    joining_date = models.DateField()
    qualification = models.CharField(max_length=35)
    experience = models.IntegerField()
    user_type = models.CharField(max_length=15)

    # def __str__(self):
    #     return self.first_name, self.last_name, self.facul_username, self.password, self.gender, self.date_of_birth, self.main_subject, self.department, self.joining_date, self.qualification, self.experience, self.user_type
