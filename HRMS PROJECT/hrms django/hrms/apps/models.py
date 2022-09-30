from django.db import models

class log(models.Model):
    user_name = models.CharField(max_length=25,unique=False,null=False)
    email = models.EmailField(max_length=25)
    password = models.CharField(max_length=25,null=False)

class salary(models.Model):
    salary = models.IntegerField()

class department(models.Model):
    dept_name = models.CharField(max_length=25)


class role(models.Model):
    role_name = models.CharField(max_length=25)
    det_id = models.ForeignKey(department,on_delete=models.CASCADE)
    salary_id = models.ForeignKey(salary,on_delete=models.CASCADE,related_name = "roles")


class Team_lead(models.Model):
    lead_name = models.CharField(max_length=25)
    leave_request = models.IntegerField()



class personal_details(models.Model):
    username = models.CharField(max_length=30,null=True)
    full_name = models.CharField(max_length=25,null=False)
    dob = models.DateField()
    login_id = models.OneToOneField(log,on_delete=models.CASCADE)
    contact_no = models.IntegerField()
    dep_id = models.ForeignKey(department,on_delete=models.CASCADE,related_name="department")
    lead_id = models.ForeignKey(Team_lead,on_delete=models.CASCADE,related_name="teamlead")




class insurance(models.Model):
    insurance_name = models.CharField(max_length=25)
    persona_id = models.ForeignKey(personal_details,on_delete=models.CASCADE,related_name="prof")


class Attendence(models.Model):
    in_time = models.TimeField()
    out_time = models.TimeField()
    DATE = models.DateField()
    profil_id = models.ForeignKey(personal_details,on_delete=models.CASCADE,related_name="pro")



class leave(models.Model):
    leave_type = models.CharField(max_length=25)
    profil_id = models.ForeignKey(personal_details,on_delete=models.CASCADE,related_name="profile")

