from django.db import models

# Create your models here.
class Employee(models.Model):
    emp_id = models.IntegerField(primary_key=True)
    emp_name = models.CharField(max_length=30)
    emp_mobile = models.CharField(max_length=30)
    emp_mail = models.CharField(max_length=30)
    emp_role = models.CharField(max_length=30)
    emp_pic = models.ImageField(null=True)
    dept_id = models.IntegerField()
    emp_password = models.CharField(max_length=30,null=True)

    # def __str__(self):
    #     return self.emp_pic
    def __str__(self):
        return self.emp_name+" : "+str(self.emp_id)+self.emp_role




# class Department(models.Model):
#     dept_id=models.IntegerField(primary_key=True)
#     dept_location=models.CharField(max_length=30)
#     dept_name=models.CharField(max_length=30)
#
#
#
# class Manager(models.Model):
#     manager_id = models.IntegerField(primary_key=True)
#     manager_name = models.CharField(max_length=30)
#     emp_id = models.ForeignKey(Employee, on_delete=models.CASCADE)
#     Dept_id = models.ForeignKey(Department, on_delete=models.CASCADE)
#     def __str__(self):
#         return self.manager_name  +" : "+ str(self.emp_id)


class Device(models.Model):
    device_id=models.IntegerField(primary_key=True)
    device_name=models.CharField(max_length=30)
    def __str__(self):
        return self.device_name


class Employee_Devices(models.Model):
    emp_id = models.ForeignKey(Employee, on_delete=models.CASCADE)
    device_id = models.ForeignKey(Device, on_delete=models.CASCADE)
    device_name = models.CharField(max_length=30, null=True)
    emp_name = models.CharField(max_length=30, null=True)
    def __str__(self):
        return self.emp_name +" : "+self.device_name


class Complaint(models.Model):
    comp_id = models.IntegerField(primary_key=True)
    emp_id = models.ForeignKey(Employee, on_delete=models.CASCADE)
    device_id = models.ForeignKey(Device, on_delete=models.CASCADE)
    comp_desc = models.CharField(max_length=200)
    def __str__(self):
        pass

class Issued_to(models.Model):
    comp_id = models.ForeignKey(Complaint, on_delete=models.CASCADE)
    emp_id = models.ForeignKey(Employee, on_delete=models.CASCADE)
    comp_status = models.CharField(max_length=30)


class Facility(models.Model):
    facility_id = models.IntegerField(primary_key=True)
    facility_location = models.CharField(max_length=30)
    facility_dept = models.CharField(max_length=30)


class Role(models.Model):
    emp_id = models.ForeignKey(Employee, on_delete=models.CASCADE)
    emp_role = models.CharField(max_length=30)
