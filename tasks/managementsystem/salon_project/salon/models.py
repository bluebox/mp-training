from django.db import models

# Create your models here.
class Client(models.Model):
    Client_id = models.IntegerField(primary_key = True)
    Client_first_name = models.CharField(max_length=50)
    Client_last_name = models.CharField(max_length=50)
    Client_Email_id = models.EmailField()
    Client_contact_number = models.CharField(max_length=10)
    Password = models.CharField(max_length=20)

class Branch(models.Model):
    branch_id = models.IntegerField(primary_key = True)
    branch_name = models.CharField(max_length=50)
    location = models.CharField(max_length=500)

class Employee(models.Model):
    emp_id = models.IntegerField(primary_key = True)
    emp_name = models.CharField(max_length=50)
    branch_id = models.ForeignKey(Branch,  on_delete=models.CASCADE)
    role = models.CharField(max_length=20)
    emp_email_id = models.EmailField(max_length=10)
    emp_contact_number = models.CharField(max_length=10)
    password = models.CharField(max_length=20)

class services_provided(models.Model):
    service_id = models.IntegerField(primary_key = True)
    service_name = models.CharField(max_length=50)
    Amount_to_be_paid = models.IntegerField()

class Appointment(models.Model):
    Appointment_id = models.IntegerField(primary_key = True)
    Client_id = models.ForeignKey(Client,  on_delete=models.CASCADE)
    Time_of_appointment = models.TimeField()
    appointment_date = models.DateField()
    Appointment_Status = models.CharField(max_length=20)
    emp_id = models.ForeignKey(Employee, on_delete=models.CASCADE)


class Appointment_service_Info(models.Model):
    id = models.IntegerField(primary_key = True)
    Appointment_id = models.IntegerField()
    service_id = models.IntegerField()

class Transaction(models.Model):
    trans_id = models.IntegerField(primary_key = True)
    Appointment_id = models.ForeignKey(Appointment, on_delete=models.CASCADE)
    trans_date = models.DateTimeField(auto_now_add=True, blank=True)
    trans_type = models.CharField(max_length=20)
    Total_amount = models.IntegerField()
    trans_status = models.CharField(max_length=20)

class Reviews(models.Model):
    review_id = models.IntegerField(primary_key = True)
    Appointment_id = models.ForeignKey(Appointment,  on_delete=models.CASCADE)
    comments_and_reviews = models.CharField(max_length=500)
    rating = models.IntegerField()