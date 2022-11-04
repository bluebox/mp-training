#pylint:disable=R0903
'''define models here'''
from django.db import models
from django.core.validators import RegexValidator
from users.models import Customer, Staff


class Branch(models.Model):
    '''branch models define branch of diagnostic center'''
    branch_id = models.CharField(max_length=10, primary_key=True, validators=[
        RegexValidator(
            regex=r'^MEDB[0-9]{2}',
            message=('Pattern is MEDB***(digits)'),
        ),
    ])
    branch_name = models.CharField(max_length=100)
    location = models.CharField(max_length=100)

    def __str__(self):
        return self.branch_name


class Lab(models.Model):
    '''model for lab detail in branch'''
    STATUS = (
        ('occupied', 'occupied'),
        ('available', 'available'),
    )
    branch = models.ForeignKey(Branch, on_delete=models.CASCADE)
    lab_id = models.CharField(max_length=10, primary_key=True, validators=[
        RegexValidator(
            regex=r'^MEDL[0-9]{2}',
            message=('Pattern is MEDBL***(digits)'),
        ),
    ])
    lab_name = models.CharField(max_length=100)
    # lab_status = models.CharField(choices=STATUS, default='available', max_length=100)

    def __str__(self):
        return self.lab_id


class Test(models.Model):
    '''test available for appointment'''
    test_id = models.CharField(max_length=10, primary_key=True, validators=[
        RegexValidator(
            regex=r'^MEDT[0-9]{2}',
            message=('Pattern is MEDT***(digits)'),
        ),
    ])
    test_name = models.CharField(max_length=100)
    test_description = models.TextField(max_length=5000, null=True)
    lab = models.ForeignKey(Lab, on_delete=models.SET_NULL, null=True)

    def __str__(self):
        return self.test_name


class Review(models.Model):
    '''review by customers '''
    user_id = models.ForeignKey(Customer, on_delete=models.CASCADE)
    rating = models.DecimalField(decimal_places=1, max_digits=1)
    comment = models.TextField(max_length=200)

    def __str__(self):
        return self.user_id


class Appointment(models.Model):
    '''appointment details booked for customer'''
    slots = (
        ('10 AM', '10 AM'),
        ('1 PM', '1 PM'),
        ('4 PM', '4 PM')
    )
    STATUS = (
        ('booked', 'booked'),
        ('completed', 'completed'),
        ('approved', 'approved'),
        ('rejected', 'rejected'),
        ('pending', 'pending'),
    )
    appointment_id = models.AutoField(primary_key=True)
    user = models.ForeignKey(Customer, on_delete=models.CASCADE, null=True, blank=True)
    branch = models.ForeignKey(Branch, on_delete=models.CASCADE, null=True, blank=True)
    date = models.DateTimeField(auto_now_add=False)
    slot = models.CharField(choices=slots, max_length=100)
    tests = models.ManyToManyField(Test, blank=True, null=True,
                                   related_name="tests")
    doctor_id = models.ForeignKey(Staff, on_delete=models.SET_NULL,
                                  null=True, blank=True, related_name='doctor')
    nurse_id = models.ForeignKey(Staff, on_delete=models.SET_NULL,
                                 null=True, blank=True, related_name='nurse')
    lab_technician = models.ForeignKey(Staff, on_delete=models.SET_NULL,
                                       null=True, blank=True,
                                       related_name='lab_technician')
    sample_collector = models.ForeignKey(Staff, on_delete=models.SET_NULL,
                                         null=True, blank=True,
                                         related_name='sample_collector')
    status = models.CharField(choices=STATUS, default='pending', max_length=100)

    def __str__(self):
        return str(self.appointment_id)

    class Meta:
        '''additional details'''
        ordering = ['-date']


class Bill(models.Model):
    '''bill generated for appointment'''
    appointment = models.ForeignKey(Appointment, on_delete=models.CASCADE)
    consultation_fee = models.IntegerField(null=True, blank=True)
    test_fee = models.IntegerField(null=True, blank=True)
    tax = models.IntegerField(null=True, blank=True)
    total = models.IntegerField(default=0)


class Report(models.Model):
    '''report for appointment'''
    appointment = models.ForeignKey(Appointment, on_delete=models.CASCADE)
    description = models.TextField(max_length=200, null=True, blank=True)
    date = models.DateTimeField(auto_now_add=True)
