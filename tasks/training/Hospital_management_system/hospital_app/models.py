from django.db import models

# Create your models here.
class Patient(models.Model):
    patient_id=models.IntegerField(primary_key=True)
    name=models.CharField(max_length=100)
    age=models.IntegerField()
    GENDERS = (
        ('M', 'Male'),
        ('F', 'Female'),
        ('T', 'Transgender'),
    )
    gender=models.CharField(max_length=1, choices=GENDERS)
    contact_num=models.CharField(max_length=20)
    address=models.CharField(max_length=150)
    in_date_time=models.DateTimeField()
    out_date_time=models.DateTimeField(null=True)

class Staff(models.Model):
    emp_id = models.IntegerField(primary_key=True)
    name = models.CharField(max_length=50)
    GENDERS = (
        ('M', 'Male'),
        ('F', 'Female'),
        ('T', 'Transgender'),
    )
    gender=models.CharField(max_length=1, choices=GENDERS)
class Role(models.Model):
    role_id = models.IntegerField(primary_key=True)
    role_name = models.CharField(max_length=50)

class Department(models.Model):
    dept_id=models.IntegerField(primary_key=True)
    dept_name=models.CharField(max_length=50)

class Staff_Role_Dept(models.Model):
    sl_no = models.IntegerField(primary_key=True)
    emp_id = models.ForeignKey(Staff, on_delete=models.CASCADE)
    dept_id = models.ForeignKey(Department, on_delete=models.CASCADE)
    role_id = models.ForeignKey(Role, on_delete=models.CASCADE)
    start_date = models.DateField()
    end_date = models.DateField()


class Ward(models.Model):
    ward_id = models.IntegerField()
    ward_name = models.CharField(max_length=50)

class Beds(models.Model):
    bed_no = models.IntegerField(primary_key=True)
    bed_type = models.CharField(max_length=50)
    charge = models.IntegerField()
    ward_id=models.ForeignKey(Ward, on_delete=models.CASCADE)
    is_occupied = models.BooleanField()

class Labs(models.Model):
    lab_id = models.IntegerField()
    lab_type = models.CharField(max_length=50)

class Diagnosis(models.Model):
    diagnosis_id = models.IntegerField(primary_key=True)
    lab_id = models.ForeignKey(Labs, on_delete=models.CASCADE)
    patient_id = models.ForeignKey(Patient, on_delete=models.CASCADE)
    doctor_id = models.ForeignKey(Staff, on_delete=models.CASCADE)
    amount = models.IntegerField()

class Treatment_details(models.Model):
    sl_no = models.IntegerField(primary_key=True)
    patient_id = models.ForeignKey(Patient, on_delete=models.CASCADE)
    disease = models.CharField(max_length=50)
    treated_doc = models.ForeignKey(Staff, on_delete=models.CASCADE)
    bed_no = models.ForeignKey(Beds, on_delete=models.CASCADE)

class Bill(models.Model):
    bill_no = models.IntegerField(primary_key=True)
    patient_id = models.ForeignKey(Patient, on_delete=models.CASCADE)
    bed_charge = models.ForeignKey(Beds, on_delete=models.CASCADE)
    no_of_days = models.IntegerField()
    lab_charge = models.ForeignKey(Diagnosis, on_delete=models.CASCADE)
    consultation_charge= models.IntegerField()
    total_amount = models.IntegerField()
