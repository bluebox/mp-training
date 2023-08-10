from django.db import models


# Create your models here.
class Patients(models.Model):
    p_name = models.CharField(max_length=70),
    p_age = models.IntegerField(),
    p_id = models.CharField(max_length=20, primary_key=True),
    p_gender = models.CharField(max_length=12),
    p_insurance_id = models.CharField(max_length=20, unique=True)

    def __str__(self):
        return self.p_name


class Doctor(models.Model):
    d_name = models.CharField(max_length=70),
    d_age = models.IntegerField(),
    d_id = models.CharField(max_length=20, primary_key=True),
    d_gender = models.CharField(max_length=12),
    d_salary_id = models.ForeignKey('Salary', on_delete=models.CASCADE),
    d_experience = models.IntegerField(),
    d_qualification = models.CharField(max_length=70)

    def __str__(self):
        return self.d_name


class Staff(models.Model):
    s_name = models.CharField(max_length=70),
    s_id = models.CharField(max_length=20, primary_key=True),
    d_id = models.ForeignKey('Doctor', on_delete=models.CASCADE, null=False),
    s_age = models.IntegerField(),
    s_gender = models.CharField(max_length=1),
    s_salary_id = models.ForeignKey('Salary', on_delete=models.CASCADE),
    s_experience = models.IntegerField(),
    s_designation = models.CharField(max_length=70)

    def __str__(self):
        return self.s_name


class Appointment(models.Model):
    a_id = models.CharField(max_length=20, primary_key=True),
    d_id = models.ForeignKey('Doctor', on_delete=models.CASCADE),
    p_id = models.ForeignKey('Patient', on_delete=models.CASCADE),
    b_id = models.CharField(max_length=20, unique=True),
    a_status = models.CharField(max_length=20),
    a_date = models.DateTimeField(unique=True)

    def __str__(self):
        return self.a_id


class Salary(models.Model):
    salary_id = models.CharField(max_length=20, primary_key=True),
    salary_status = models.CharField(max_length=6),
    amount = models.IntegerField

    def __str__(self):
        return self.salary_id


class Bills(models.Model):
    b_id = models.CharField(max_length=20, primary_key=True),
    p_id = models.ForeignKey('Patient', on_delete=models.CASCADE),
    b_status = models.CharField(max_length=6),
    amount = models.IntegerField(),
    b_date = models.DateTimeField(auto_now=True)

    def __str__(self):
        return self.b_id


class Diagnosis(models.Model):
    dia_id = models.CharField(max_length=20, primary_key=True),
    p_id = models.ForeignKey('Patient', on_delete=models.CASCADE),
    d_id = models.ForeignKey('Doctor', on_delete=models.CASCADE),
    dia_details = models.ImageField(),
    dia_date = models.DateTimeField(auto_now=True)

    def __str__(self):
        return self.dia_id


class Test(models.Model):
    test_id = models.CharField(max_length=20),
    test_name = models.CharField(max_length=50),
    p_id = models.ForeignKey('Patient', on_delete=models.CASCADE),
    d_id = models.ForeignKey('Doctor', on_delete=models.CASCADE),
    b_id = models.CharField(max_length=20),
    test_status = models.CharField(max_length=20),
    test_date = models.DateTimeField(),
    result = models.ImageField()

    def __str__(self):
        return self.test_name


class Slot(models.Model):
    time_slot = models.IntegerField(),
    start_time = models.TimeField(),
    start_time = models.TimeField(),
    status = models.CharField(max_length=20)
    def __str__(self):
        return self.time_slot
    # p_rec_id = models.CharField(max_length=20, primary_key=True),
    # p_id = models.ForeignKey('Patient', on_delete=models.CASCADE),
    # p_records = models.CharField(max_length=20)

# Create your models here.
