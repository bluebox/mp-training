
from email.policy import default
from django.db import models

# Create your models here.
from django.db import models
from django.contrib.auth.models import User
from django.contrib.auth.models import AbstractUser
# Create your models here.
class User(AbstractUser):
    is_staff = models.BooleanField(default=False)

    class Meta:
        db_table = 'User'

    def __str__(self):
        return self.username
   
class Client(models.Model):
    # client_id = models.CharField(primary_key = True,max_length = 10)
    user_id = models.OneToOneField(User, on_delete=models.CASCADE)
    Client_contact_number = models.CharField(max_length=10)

    class Meta:
        db_table = 'Client'

    def __str__(self):
        return str(self.user_id)

class Branch(models.Model):
    branch_id = models.IntegerField(primary_key = True)
    branch_name = models.CharField(max_length=50)
    location = models.CharField(max_length=500)

    class Meta:
        db_table = 'Branch'

    def __str__(self):
        return self.branch_name

class Employee(models.Model):
    emp_id = models.CharField(primary_key = True,max_length = 10)
    user_id = models.OneToOneField(User, on_delete=models.CASCADE)
    branch_id = models.ForeignKey(Branch,  on_delete=models.CASCADE)
    role = models.CharField(max_length=20)
    emp_contact_number = models.CharField(max_length=10)

    class Meta:
        db_table = 'Employee'

    def __str__(self):
        return str(self.emp_id)

class services_provided(models.Model):
    service_id = models.IntegerField(primary_key = True)
    service_name = models.CharField(max_length=50)
    Amount_to_be_paid = models.IntegerField()

    class Meta:
        db_table = 'services_provided'

    def __str__(self):
        return self.service_name

class Appointment(models.Model):
    Appointment_id = models.IntegerField(primary_key = True)
    client_id = models.ForeignKey(Client,  on_delete=models.CASCADE)
    Time_of_appointment = models.TimeField()
    appointment_date = models.DateField()
    Appointment_Status = models.CharField(max_length=20,default="booked")
    emp_id = models.ForeignKey(Employee, on_delete=models.CASCADE)
    services = models.ManyToManyField(services_provided)

    class Meta:
        db_table = 'Appointment'


class Transaction(models.Model):
    trasaction_types = (('Credit Card','Credit Card'),('Debit Card','Debit Card'),('Net Banking','Net Banking'),('UPI','UPI'),('Pay at Salon','Pay at Salon'))
    trans_id = models.IntegerField(primary_key = True)
    Appointment_id = models.ForeignKey(Appointment, on_delete=models.CASCADE)
    trans_date = models.DateTimeField(auto_now_add=True, blank=True)
    trans_type = models.CharField(max_length=20,choices = trasaction_types)
    Total_amount = models.IntegerField()
    trans_status = models.CharField(max_length=20)

    class Meta:
        db_table = 'Transaction'

    def __str__(self):
        return str(self.trans_id)

class Reviews(models.Model):
    review_id = models.IntegerField(primary_key = True)
    Appointment_id = models.ForeignKey(Appointment,  on_delete=models.CASCADE)
    comments_and_reviews = models.CharField(max_length=500)
    rating = models.IntegerField()

    class Meta:
        db_table = 'Reviews'

    def __str__(self):
        return str(self.review_id)