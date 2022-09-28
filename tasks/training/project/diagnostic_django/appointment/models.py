from django.db import models
from users.models import Customer

# Create your models here.


class Branch(models.Model):
    branch_id = models.CharField(max_length=10, primary_key=True)
    branch_name = models.CharField(max_length=100)
    location = models.CharField(max_length=100)


class Lab(models.Model):
    STATUS = (
        ('occupied' , 'occupied'),
        ('available', 'available'),
    )
    branch = models.ForeignKey(Branch , on_delete = models.CASCADE)
    lab_id = models.CharField(max_length=10, primary_key=True)
    lab_number = models.IntegerField()
    lab_type = models.CharField(max_length=100)
    lab_name = models.CharField(max_length=100)
    lab_status = models.CharField(choices = STATUS , default = 'available',max_length=100 )

    def __str__(self):
        return self.lab_id


class Test(models.Model):
    test_id = models.CharField(max_length=10, primary_key=True)
    test_type = models.CharField(max_length=100)
    test_name = models.CharField(max_length=100)
    test_description = models.TextField(max_length=5000)
    lab = models.ForeignKey(Lab, on_delete=models.SET_NULL, null=True)

    def __str__(self):
        return self.test_name


class Review(models.Model):
    user_id = models.ForeignKey(Customer, on_delete=models.CASCADE)
    rating = models.DecimalField(decimal_places = 1 , max_digits = 1)
    comment = models.TextField(max_length=200)

    def __str__(self):
        return self.user_id


class Appointment(models.Model):
    slots = (
        ('10 AM', 'Morning'),
        ('1PM', 'Afternoon'),
        ('4 PM', 'Evening')
    )
    STATUS = (
        ('booked' , 'booked'),
        ('completed', 'completed'),
        ('approved', 'approved'),
        ('rejected', 'rejected'),
        ('pending', 'pending'),

    )
    appointment_id = models.IntegerField(models.AutoField, primary_key=True)
    user = models.ForeignKey(Customer, on_delete=models.CASCADE)
    branch = models.ForeignKey(Branch , on_delete = models.CASCADE)
    date = models.DateTimeField(auto_now_add=True)
    slot = models.CharField(choices=slots, max_length=100)
    test = models.ManyToManyField(Test, blank=True)
    doctor_id = models.CharField(max_length = 10, null=True,blank=True)
    nurse_id = models.CharField(max_length= 10, null=True,blank=True)
    lab_technician = models.CharField(max_length = 10, null=True,blank=True)
    sample_collector = models.CharField(max_length=10, null=True,blank=True)
    status = models.CharField(choices = STATUS , default = 'pending',max_length=100 )

    def __str__(self):
        return self.appointment_id


class Bill(models.Model):
    appointment = models.ForeignKey(Appointment, on_delete=models.CASCADE)
    consultation_fee = models.IntegerField(null=True, blank=True)
    test_fee = models.IntegerField(null=True, blank=True)
    tax = models.IntegerField(null=True, blank=True)
    total = models.IntegerField(default=0)

    def __str__(self):
        return self.id


class Report(models.Model):
    appointment = models.ForeignKey(Appointment, on_delete=models.CASCADE)
    description = models.TextField(max_length=200, null=True, blank=True)
    date = models.DateTimeField(auto_now_add=True)
    report_type = models.CharField(max_length=200, null=True, blank=True)



