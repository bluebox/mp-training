"""Models"""
#pylint:disable=E1101
#pylint:disable=R0903
#pylint:disable=W0611
#pylint:disable=E0102
from email.policy import default
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

def from_100():
    """increment from default value"""
    largest = Branch.objects.all().order_by('branch_id').last()
    # smallest = Branch.objects.all().order_by('location').last()
    # print(largest)
    # print(largest.branch_id)
    # print(smallest)
    if not largest:
        return 100
    return largest.branch_id + 1



class Branch(models.Model):
    """Branch model to store Branch details"""
    branch_id = models.IntegerField(primary_key = True,default=from_100)
    branch_name = models.CharField(max_length=50)
    location = models.CharField(max_length=500)

    class Meta:
        """To display the table name as Client in database"""
        db_table = 'Branch'

    def __str__(self):
        return str(self.branch_name)

def from_2500():
    """increment from default value"""
    largest = Employee.objects.all().order_by('emp_id').last()
    if not largest:
        return 2500
    return largest.emp_id + 1

class Employee(models.Model):
    """Employee model to store Employee details"""
    emp_id = models.IntegerField(primary_key = True,default=from_2500)
    user_id = models.OneToOneField(User, on_delete=models.CASCADE)
    branch_id = models.ForeignKey(Branch,  on_delete=models.CASCADE)
    role = models.CharField(max_length=20)
    emp_contact_number = models.CharField(max_length=10)

    class Meta:
        """To display the table name as Employee in database"""
        db_table = 'Employee'

    def __str__(self):
        return str(self.emp_id)

def from_200():
    """increment from default value"""
    largest = ServicesProvided.objects.all().order_by('service_id').last()
    if not largest:
        return 200
    return largest.service_id + 1

class ServicesProvided(models.Model):
    """ServicesProvided model to store ServicesProvided details"""
    service_id = models.IntegerField(primary_key = True,default=from_200)
    service_name = models.CharField(max_length=50)
    Amount_to_be_paid = models.IntegerField()

    class Meta:
        """To display the table name as services_provided in database"""
        db_table = 'services_provided'

    def __str__(self):
        return str(self.service_name)


def from_900():
    """increment from default value"""
    largest = Appointment.objects.all().order_by('Appointment_id').last()
    if not largest:
        return 900
    return largest.Appointment_id + 1

class Appointment(models.Model):
    """Appointment model to store Appointment details"""
    appointment_time_choices = (('9.00am-10.00am','9.00am-10.00am'),
    ('10.00am-11.00am','10.00am-11.00am'),('11.00am-12.00pm','11.00am-12.00pm'),
    ('12.00pm-1.0pm','12.00pm-1.0pm'),('1.00pm-2.00pm','1.00pm-2.00pm'),
    ('2.00pm-3.00pm','2.00pm-3.00pm'),('3.00pm-4.00pm','3.00pm-4.00pm'),
    ('4.00pm-5.00pm','4.00pm-5.00pm'))
    Appointment_id = models.IntegerField(primary_key = True, default=from_900)
    client_id = models.ForeignKey(Client,  on_delete=models.CASCADE)
    Time_of_appointment = models.CharField(max_length=20,choices=appointment_time_choices)
    appointment_date = models.DateTimeField(auto_now_add = False)
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

def from_700():
    """increment from default value"""
    largest = Reviews.objects.all().order_by('review_id').last()
    if not largest:
        return 700
    return largest.review_id + 1

class Reviews(models.Model):
    """Reviews model to store Reviews details"""
    review_id = models.IntegerField(primary_key=True, default=from_700)
    Appointment_id = models.ForeignKey(Appointment, on_delete=models.CASCADE)
    comments_and_reviews = models.CharField(max_length=500)
    rating = models.IntegerField()

    class Meta:
        """To display the table name as Reviews in database"""
        db_table = 'Reviews'

    def __str__(self):
        return str(self.review_id)
