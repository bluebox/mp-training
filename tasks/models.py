from django.db import models
# from django.db.models.functions import Coalesce
from django.core.exceptions import ValidationError

# Create your models here.
class DahlBookManager(models.Manager):
    def get_queryset(self):
        return super().get_queryset().filter(dept_name='police')
def validate_even(value):
    if value % 2 != 0:
        raise ValidationError(
            ('%(value)s is not an even number'),
            params={'value': value},
        )


class login(models.Model):

    shashi = models.Manager()
    user_name = models.CharField(max_length=25,unique=True,null=False)
    email = models.EmailField(max_length=25)
    password = models.CharField(max_length=25,null=False)

class salary(models.Model):
    salary = models.IntegerField(validators=[validate_even])


class role(models.Model):
    role_name = models.CharField(max_length=25)
    salary_id = models.OneToOneField(salary,on_delete=models.CASCADE)


class insurance(models.Model):
    insurance_name = models.CharField(max_length=25)


class Attendence(models.Model):
    in_time = models.TimeField()
    out_time = models.TimeField()
    DATE = models.DateField()

class department(models.Model):
    dept_name = models.CharField(max_length=25)
    role_id = models.ForeignKey(role,on_delete=models.CASCADE)
    attendence_id = models.OneToOneField(Attendence,on_delete=models.CASCADE)
    objects = models.Manager()
    req_objects = DahlBookManager()


class leave(models.Model):
    leave_type = models.CharField(max_length=25)
    attendence_id = models.OneToOneField(Attendence,on_delete=models.CASCADE)


class personal_details(models.Model):
    full_name = models.CharField(max_length=25,null=False)
    dob = models.DateField()
    login_id = models.OneToOneField(login,on_delete=models.CASCADE)
    contact_no = models.IntegerField()
    dept_id = models.ForeignKey(department,on_delete=models.CASCADE)
    insurance = models.ForeignKey(insurance,on_delete=models.CASCADE)

class Team_lead(models.Model):
    lead_name = models.CharField(max_length=25)
    leave_request = models.IntegerField()
    profile_id = models.OneToOneField(personal_details,on_delete=models.CASCADE)