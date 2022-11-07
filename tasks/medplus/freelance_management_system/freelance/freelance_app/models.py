import datetime

import django
from django.db import models


class freelancer_details(models.Model):
    id = models.AutoField(primary_key=True)
    first_name = models.CharField(max_length=100)
    last_name = models.CharField(max_length=100)
    email_id = models.EmailField(unique=True)
    phone_number = models.CharField(max_length=12)
    password = models.CharField(max_length=25)
    country = models.CharField(max_length=20)

    def __str__(self) -> str:
        return "{} {} {} ".format(self.id, self.first_name, self.last_name)


class client_details(models.Model):
    client_id = models.AutoField(primary_key=True)
    client_name = models.CharField(max_length=100)
    client_country = models.CharField(max_length=20)
    phone_number = models.CharField(max_length=12)
    email_id = models.EmailField(unique=True)
    password = models.CharField(max_length=25)

    def __str__(self) -> str:
        return "{} {} ".format(self.client_id, self.client_name)


class client_jobs(models.Model):
    job_id = models.AutoField(primary_key=True)
    client_id = models.ForeignKey(client_details, on_delete=models.CASCADE)
    project_title = models.CharField(max_length=100)
    description = models.TextField(max_length=500, default="")
    total_pay = models.IntegerField()
    experience_level = models.CharField(max_length=100)
    skills_requried = models.CharField(max_length=100)
    is_deleted = models.BooleanField(default=False)

    def __str__(self) -> str:
        return "{} {} {} ".format(self.job_id, self.client_id, self.project_title)


class freelancer_proposals(models.Model):
    proprosal_id = models.AutoField(primary_key=True)
    freelancer_id = models.ForeignKey('freelancer_details', on_delete=models.CASCADE)
    job_id = models.ForeignKey(client_jobs, on_delete=models.CASCADE)
    skills = models.TextField(max_length=100)
    cover_letter = models.TextField(max_length=100)
    requried_pay = models.IntegerField()
    proprosal_status = models.CharField(max_length=20, default='pending for approval')

    def __str__(self) -> str:
        return "{} {} {} ".format(self.proprosal_id, self.freelancer_id, self.job_id)


class client_contract_details(models.Model):
    contract_id = models.AutoField(primary_key=True)
    emp_proposal_id = models.OneToOneField(freelancer_proposals, on_delete=models.CASCADE)
    client_id = models.ForeignKey(client_details, on_delete=models.CASCADE)
    project_deadline = models.DateField(default=django.utils.timezone.now)
    project_status = models.CharField(max_length=50, default='pending')
    contract_amount = models.IntegerField(default=10)

    def __str__(self) -> str:
        return "{} {} {} ".format(self.contract_id, self.emp_proposal_id, self.client_id)


class freelancer_payment_details(models.Model):
    payment_id = models.AutoField(primary_key=True)
    freelancer_id = models.ForeignKey(freelancer_details, on_delete=models.CASCADE)
    contract_id = models.OneToOneField(client_contract_details, on_delete=models.CASCADE)
    Tax = models.FloatField()
    earned = models.FloatField()

    def __str__(self) -> str:
        return "{} {} {} ".format(self.payment_id, self.freelancer_id, self.contract_id)


class client_fee_record(models.Model):
    contract_id = models.OneToOneField(client_contract_details, on_delete=models.CASCADE, default=1)
    handling_fee = models.FloatField()
    tax = models.FloatField()
    total_payment = models.FloatField()

    def __str__(self) -> str:
        return "{}".format(self.contract_id)


class client_feedback_form(models.Model):
    contract_id = models.OneToOneField(client_contract_details, on_delete=models.CASCADE)
    rating = models.IntegerField()
    feedback = models.TextField(max_length=100, null=True)

    def __str__(self) -> str:
        return "{} ".format(self.contract_id)


class freelancer_feedback_form(models.Model):
    contract_id = models.OneToOneField(client_contract_details, on_delete=models.CASCADE)
    rating = models.IntegerField()
    feedback = models.TextField(max_length=100, null=True)

    def __str__(self) -> str:
        return "{}".format(self.contract_id)


class UserToken(models.Model):
    email_id = models.EmailField()
    token = models.CharField(max_length=255)
    created_at = models.DateTimeField(auto_now_add=True)
    expired_at = models.DateTimeField()
