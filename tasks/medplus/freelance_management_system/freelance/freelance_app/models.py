
from django.db import models
from django_countries.fields import CountryField
from phone_field import PhoneField
from  django.core.validators import EmailValidator
from phonenumber_field.modelfields import PhoneNumberField

class freelancer_details(models.Model):
    id = models.IntegerField(primary_key=True)
    first_name = models.CharField(max_length=100)
    last_name = models.CharField(max_length=100)
    email_id = models.EmailField(validators=[EmailValidator("not a vaild email ")])
    phone_number = PhoneNumberField()
    password = models.CharField(max_length=25)
    country =models.CharField(max_length=20)
    def __str__(self) -> str:
        return "{} {} {} ".format(self.id,self.first_name,self.last_name)

class client_details(models.Model):
    client_id = models.IntegerField(primary_key=True)
    client_name = models.CharField(max_length=100)
    client_country =models.CharField(max_length=20)
    phone_number = PhoneField(blank=True)
    email_id = models.EmailField(validators=[EmailValidator('not a vaild email ')])
    password = models.CharField(max_length=25)
    def __str__(self) -> str:
        return "{} {} ".format(self.client_id,self.client_name)


class client_jobs(models.Model):
    options = (
        ('E','Expert'),
        ('I','Intermediate'),
        ("F","Fresher")
    )
    job_id = models.IntegerField(primary_key=True)
    client_id = models.ForeignKey(client_details,on_delete=models.CASCADE)
    project_title = models.CharField(max_length=100)
    description = models.TextField(max_length=3000,default='description')
    total_pay = models.IntegerField()
    experience_level = models.CharField(max_length=1,choices=options)
    skills_requried = models.CharField(max_length=100)

    def __str__(self) -> str:
        return "{} {} {} ".format(self.job_id,self.client_id,self.project_title)

class freelancer_proposals(models.Model):
    proprosal_id = models.IntegerField(primary_key=True)
    freelancer_id = models.ForeignKey(freelancer_details,on_delete=models.CASCADE)
    job_id = models.ForeignKey(client_jobs,on_delete=models.CASCADE)
    skills = models.TextField(max_length=100)
    cover_letter = models.TextField(max_length=100)
    requried_pay = models.IntegerField()
    def __str__(self) -> str:
        return "{} {} {} ".format(self.proprosal_id,self.freelancer_id,self.job_id)

class client_contract_details(models.Model):
    pro_status = (
        ("P","Pending"),
        ("C","Completed")
    )
    contract_id = models.IntegerField(primary_key=True)
    emp_proposal_id = models.OneToOneField(freelancer_proposals,on_delete=models.CASCADE)
    client_id = models.ForeignKey(client_details,on_delete=models.CASCADE)
    project_deadline = models.DateTimeField()
    project_status = models.CharField(max_length=1,choices=pro_status)
    def __str__(self) -> str:
        return "{} {} {} ".format(self.contract_id,self.emp_proposal_id,self.client_id)



class freelancer_payment_details(models.Model):
    payment_id = models.IntegerField(primary_key=True)
    freelancer_id = models.ForeignKey(freelancer_details,on_delete=models.CASCADE)
    contract_id = models.OneToOneField(client_contract_details,on_delete=models.CASCADE)
    Tax = models.IntegerField()
    earned = models.IntegerField()
    def __str__(self) -> str:
        return "{} {} {} ".format(self.payment_id,self.freelancer_id,self.contract_id)


class client_fee_record(models.Model):
    contract_id = models.OneToOneField(client_contract_details,on_delete=models.CASCADE,default=1)
    handling_fee = models.IntegerField()
    tax = models.IntegerField()
    total_payment = models.IntegerField()
    def __str__(self) -> str:
        return "{}".format(self.contract_id)

class client_feedback_form(models.Model):
    rating_choices = (
        ('E','Excellent'),
        ('G','Good'),
        ('B','BAD')
    )
    contract_id = models.OneToOneField(client_contract_details,on_delete=models.CASCADE)
    rating = models.CharField(max_length=1,choices=rating_choices)
    feedback = models.TextField(max_length=100)
    def __str__(self) -> str:
        return "{} ".format(self.contract_id)

class freelancer_feedback_form(models.Model):
    rating_choices = (
        ('E','Excellent'),
        ('G',"Good"),
        ('B','BAD')
    )
    contract_id = models.OneToOneField(client_contract_details,on_delete=models.CASCADE)
    rating = models.CharField(max_length=1,choices=rating_choices)
    feedback = models.TextField(max_length=100)


    def __str__(self) -> str:
        return "{}".format(self.contract_id)


