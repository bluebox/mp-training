from django.db import models

# Create your models here.


class department_details(models.Model):
    department_code = models.CharField(max_length=10, primary_key=True)
    department_name = models.CharField(max_length=35)


class subject_details(models.Model):
    subject_code = models.CharField(max_length=10, primary_key=True)
    subject_name = models.CharField(max_length=35)
    category = models.CharField(max_length=35)


class class_details(models.Model):
    class_code = models.CharField(max_length=10, primary_key=True)
    class_name = models.CharField(max_length=35)

    # def __str__(self):
    #     return self.class_code, self.class_name


class faculty_details(models.Model):
    first_name = models.CharField(max_length=20)
    last_name = models.CharField(max_length=20)
    facul_username = models.CharField(max_length=35, primary_key=True)
    password = models.CharField(max_length=12)
    gender = models.CharField(max_length=6)
    date_of_birth = models.CharField(max_length=60)
    main_subject = models.ForeignKey(subject_details, max_length=6, on_delete=models.CASCADE)
    department = models.ForeignKey(department_details, max_length=10, on_delete=models.CASCADE)
    joining_date = models.CharField(max_length=60)
    qualification = models.CharField(max_length=35)
    experience = models.IntegerField()
    user_type = models.CharField(max_length=15)
    status = models.BooleanField(default=False)


class batch_details(models.Model):
    batch_id = models.IntegerField(primary_key=True)
    faculty_name = models.CharField(max_length=40)
    time = models.TimeField()
    facul_username = models.ForeignKey(faculty_details, max_length=35, on_delete=models.CASCADE)
    class_code = models.ForeignKey(class_details, max_length=6, on_delete=models.CASCADE)
    subject_code = models.ForeignKey(subject_details, max_length=6, on_delete=models.CASCADE)


class student_details(models.Model):
    first_name = models.CharField(max_length=20)
    last_name = models.CharField(max_length=20)
    stud_username = models.CharField(max_length=35, primary_key=True)
    password = models.CharField(max_length=15)
    gender = models.CharField(max_length=6)
    date_of_birth = models.CharField(max_length=60)
    father_name = models.CharField(max_length=35, default=None)
    roll_no = models.IntegerField(default=None)
    user_type = models.CharField(max_length=15, default="")
    status = models.BooleanField(default=False)
    class_code = models.ForeignKey(class_details, max_length=6, on_delete=models.CASCADE)


class feedback_by_student(models.Model):
    to_faculty = models.ForeignKey(faculty_details, max_length=35, on_delete=models.CASCADE)
    stud_username = models.ForeignKey(student_details, max_length=35, on_delete=models.CASCADE)
    month = models.CharField(max_length=12)
    year = models.IntegerField()
    Q1 = models.IntegerField()
    Q2 = models.IntegerField()
    Q3 = models.IntegerField()
    Q4 = models.IntegerField()
    Q5 = models.IntegerField()
    Q6 = models.IntegerField()
    Q7 = models.IntegerField()
    Q8 = models.IntegerField()
    remarks = models.TextField(max_length=200)
    GPA = models.DecimalField(decimal_places=2, max_digits=3)


class feedback_by_faculty(models.Model):
    to_student = models.ForeignKey(student_details, max_length=35, on_delete=models.CASCADE)
    facul_username = models.ForeignKey(faculty_details, max_length=35, on_delete=models.CASCADE)
    month = models.CharField(max_length=12)
    year = models.IntegerField()
    Q1 = models.IntegerField()
    Q2 = models.IntegerField()
    Q3 = models.IntegerField()
    Q4 = models.IntegerField()
    Q5 = models.IntegerField()
    remarks = models.TextField(max_length=200)
    GPA = models.DecimalField(decimal_places=2, max_digits=3)


# for testing purpose
class TestModel(models.Model):
    username = models.CharField(max_length=20, primary_key=True)
    password = models.CharField(max_length=12)
    status = models.BooleanField(default=False)



