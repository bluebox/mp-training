

from django.db import models
from django.core.exceptions import ValidationError
from django.utils.translation import gettext_lazy as _


def mobilenumberValidator(value):
    if len(value) != 10:
        raise ValidationError(
            _('%(value)s is not an a valid mobile number'),
            params={'value': value},
        )


class Facility(models.Model):
    facility_id = models.IntegerField(primary_key=True)
    facility_location = models.CharField(max_length=30)
    facility_dept = models.CharField(max_length=30)

    def __str__(self):
        return self.facility_location + ' ' + self.facility_dept


class Employee(models.Model):
    emp_id = models.AutoField(primary_key=True)
    emp_name = models.CharField(max_length=30)
    emp_mobile = models.CharField(max_length=30, null=True, validators=[mobilenumberValidator])
    emp_email = models.CharField(max_length=30, null=True)
    emp_role = models.CharField(max_length=30, null=True)
    emp_pic = models.ImageField(null=True,blank=True)
    emp_password = models.CharField(max_length=30, null=True)
    facility_id = models.ForeignKey(Facility, on_delete=models.CASCADE)

    def __str__(self):
        return self.emp_name + " : " + str(self.emp_id) + self.emp_role


class Role(models.Model):
    emp_id = models.ForeignKey(Employee, on_delete=models.CASCADE)
    emp_role = models.CharField(max_length=30)


class UserToken(models.Model):
    user_id = models.IntegerField()
    token = models.CharField(max_length=255)
    expired_at = models.DateTimeField()
    created_at = models.DateTimeField(auto_now_add=True)


class Device(models.Model):
    device_id = models.IntegerField(primary_key=True)
    device_name = models.CharField(max_length=30)

    def __str__(self):
        return self.device_name


class Employee_Devices(models.Model):
    emp_id = models.ForeignKey(Employee, on_delete=models.CASCADE)
    device_id = models.ForeignKey(Device, on_delete=models.CASCADE)
    device_name = models.CharField(max_length=30, null=True)
    emp_name = models.CharField(max_length=30, null=True)
    quantity = models.IntegerField(null=True)

    def __str__(self):
        return self.emp_name + " : " + self.device_name


class Complaint(models.Model):
    facility_id = models.ForeignKey(Facility, on_delete=models.CASCADE, default=None)
    comp_id = models.AutoField(primary_key=True)
    emp_id = models.ForeignKey(Employee, on_delete=models.CASCADE)
    device_id = models.ForeignKey(Device, on_delete=models.CASCADE)
    comp_desc = models.CharField(max_length=200, null=True)
    is_assigned = models.BooleanField(default=False)
    is_completed= models.BooleanField(default=False)

    def __str__(self):
        return str(self.emp_id) + ' ' + str(self.device_id) + " " + str(self.comp_desc)


class Issued_to(models.Model):
    comp_id = models.ForeignKey(Complaint, on_delete=models.CASCADE)
    emp_id = models.ForeignKey(Employee, on_delete=models.CASCADE)
    comp_status = models.CharField(max_length=30)


    def __str__(self):
        return str(self.emp_id) + ' ' + str(self.comp_id)


