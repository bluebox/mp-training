"""Models"""
from random import choices
from unittest.util import _MAX_LENGTH
from django.db import models
from django.contrib.auth.models import User
from django.contrib.auth.models import AbstractUser


# Create your models here.
class User(AbstractUser):
    """user model stores user details"""
    is_staff = models.BooleanField(default=False)
    class Meta:
        """To display the table name as user in database"""
        db_table = 'User'
    def __str__(self):
        return str(self.username)

class Client(models.Model):
    """client model to store client details"""
    user_id = models.OneToOneField(User, on_delete=models.CASCADE)
    Client_contact_number = models.CharField(max_length=10)

    class Meta:
        """To display the table name as Client in database"""
        db_table = 'Client'

    def __str__(self):
        return str(self.user_id)

class Branch(models.Model):
    """Branch model to store Branch details"""
    branch_id = models.IntegerField(primary_key = True)
    branch_name = models.CharField(max_length=50)
    location = models.CharField(max_length=500)

    class Meta:
        """To display the table name as Client in database"""
        db_table = 'Branch'

    def __str__(self):
        return str(self.branch_name)

class Employee(models.Model):
    """Employee model to store Employee details"""
    emp_id = models.CharField(primary_key = True,max_length = 10)
    user_id = models.OneToOneField(User, on_delete=models.CASCADE)
    branch_id = models.ForeignKey(Branch,  on_delete=models.CASCADE)
    role = models.CharField(max_length=20)
    emp_contact_number = models.CharField(max_length=10)

    class Meta:
        """To display the table name as Employee in database"""
        db_table = 'Employee'

    def __str__(self):
        return str(self.emp_id)

class ServicesProvided(models.Model):
    """ServicesProvided model to store ServicesProvided details"""
    service_id = models.IntegerField(primary_key = True)
    service_name = models.CharField(max_length=50)
    Amount_to_be_paid = models.IntegerField()

    class Meta:
        """To display the table name as services_provided in database"""
        db_table = 'services_provided'

    def __str__(self):
        return str(self.service_name)

class Appointment(models.Model):
    """Appointment model to store Appointment details"""
    appointment_time_choices = (('9.00pm-10.00pm','9.00pm-10.00pm'),('10.00pm-11.00pm','10.00pm-11.00pm'),('11.00pm-12.00pm','11.00pm-12.00pm'),('12.00pm-1.0pam','12.00pm-1.0pam'),('1.00pm-2.00pm','1.00pm-2.00pm'),('2.00pm-3.00pm','2.00pm-3.00pm'),('3.00pm-4.00pm','3.00pm-4.00pm'),('4.00pm-5.00pm','4.00pm-5.00pm'))
    Appointment_id = models.IntegerField(primary_key = True)
    client_id = models.ForeignKey(Client,  on_delete=models.CASCADE)
    Time_of_appointment = models.CharField(max_length=20,choices=appointment_time_choices)
    appointment_date = models.DateField()
    Appointment_Status = models.CharField(max_length=20,default="booked")
    emp_id = models.ForeignKey(Employee, on_delete=models.CASCADE)
    services = models.ManyToManyField(ServicesProvided)

    class Meta:
        """To display the table name as Appointment in database"""
        db_table = 'Appointment'

    def __str__(self):
        return str(self.Appointment_id)


class Transaction(models.Model):
    """Transaction model to store Transaction details"""
    trasaction_types = (('Credit Card','Credit Card'),('Debit Card','Debit Card'),
    ('Net Banking','Net Banking'),('UPI','UPI'),('Pay at Salon','Pay at Salon'))
    trans_id = models.IntegerField(primary_key = True)
    Appointment_id = models.ForeignKey(Appointment, on_delete=models.CASCADE)
    trans_date = models.DateTimeField(auto_now_add=True, blank=True)
    trans_type = models.CharField(max_length=20,choices = trasaction_types)
    Total_amount = models.IntegerField()
    trans_status = models.CharField(max_length=20)

    class Meta:
        """To display the table name as Transaction in database"""
        db_table = 'Transaction'

    def __str__(self):
        return str(self.trans_id)

class Reviews(models.Model):
    """Reviews model to store Reviews details"""
    review_id = models.IntegerField(primary_key = True)
    Appointment_id = models.ForeignKey(Appointment,  on_delete=models.CASCADE)
    comments_and_reviews = models.CharField(max_length=500)
    rating = models.IntegerField()

    class Meta:
        """To display the table name as Reviews in database"""
        db_table = 'Reviews'

    def __str__(self):
        return str(self.review_id)
