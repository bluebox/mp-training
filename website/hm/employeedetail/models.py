from django.db import models

# Create your models here.
class emp_detail(models.Model):

    eid = models.IntegerField(primary_key = True,default=0)
    name = models.CharField(max_length=50,default="-")
    designation_id = models.ForeignKey("designation", on_delete=models.CASCADE)
    contact = models.IntegerField(default=0, unique=True)
    age = models.CharField(max_length=50,default="-")
    mail = models.EmailField(max_length=50,unique = True)
    gender = models.CharField(max_length=50,default="-")
    special_role=models.CharField(max_length=50,default='none')


class   emp_salary(models.Model):
    eid = models.ForeignKey("emp_detail", on_delete=models.CASCADE)
    hra = models.CharField(max_length=10,default="-")
    basic= models.CharField(max_length=10,default="-")
    pfc = models.CharField(max_length=10,default="-")
    pfe = models.CharField(max_length=10,default="-")
    insurance1 = models.CharField(max_length=10,default="-")
    insurance2 = models.CharField(max_length=10,default="-")
    total = models.CharField(max_length=10,default="-")


class emp_document(models.Model):
    eid = models.ForeignKey("emp_detail", on_delete=models.CASCADE)
    aadharcaed = models.ImageField(upload_to='images/')
    pancard = models.ImageField(upload_to='images/')
    passport = models.ImageField(upload_to='images/')
    bank_detail = models.ImageField(upload_to='images/')


class attandance(models.Model):
    eid = models.ForeignKey("emp_detail", on_delete=models.CASCADE)
    date = models.DateField(default="-")
    timein = models.TimeField(default="00:00:00")
    timeout = models.TimeField(default="00:00:00")
    remark = models.CharField(max_length=19,default="Absent")

class pay_slip_info(models.Model):
    eid = models.ForeignKey("emp_detail", on_delete=models.CASCADE)
    number_of_wd = models.IntegerField(default="-")
    total_present = models.IntegerField(default="-")
    total_absent= models.IntegerField(default="-")
    total_payment = models.IntegerField(default="-")



class leave(models.Model):
    eid = models.ForeignKey("emp_detail", on_delete=models.CASCADE)
    total_oh =models.IntegerField()
    left_oh =models.IntegerField()
    total_cl =models.IntegerField()
    left_cl =models.IntegerField()
    total_sl =models.IntegerField()
    left_sl =models.IntegerField()

class   designation(models.Model):
    dept_id=models.ForeignKey("department", on_delete=models.CASCADE)
    designation_id = models.CharField(max_length=10,primary_key=True,default="-")
    designation_name = models.CharField(max_length=10,default="-")

class emp_roll(models.Model):
    eid = models.ForeignKey("emp_detail", on_delete=models.CASCADE)
    #hr_eid = models.ForeignKey("emp_detail", on_delete=models.CASCADE)
    designation = models.ForeignKey("designation", on_delete=models.CASCADE)
    #team_lead_eid = models.ForeignKey("emp_detail", on_delete=models.CASCADE)
    project = models.CharField(max_length=50, default="-")

class department(models.Model):
    dept_id = models.IntegerField(primary_key=True)
    department_name = models.CharField(max_length=10,default='-')

