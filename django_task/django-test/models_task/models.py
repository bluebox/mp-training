from logging import root
from django.db import models
import datetime

# -------------------

##NOTE:
# for admin pannel

# username: root
# password: root

# -----------------------


# Create your models here.


GENDER_CHOICES = (
    ('male','male'),
    ('female','female')
)
SEMESTER_CHOICES = (
    ('1','1st'),
    ('2','2nd'),
    ('3','3rd'),
    ('4','4th'),
    ('5','5th'),
    ('6','6th'),
    ('7','7th'),
    ('8','8th')
)
ROLE_CHOICES=(
    ('admin',"admin"),
    ('teacher',"teacher"),
    ('student',"student")
)
state_choices = (("Andhra Pradesh","Andhra Pradesh"),
    ("Arunachal Pradesh ","Arunachal Pradesh "),
    ("Assam","Assam"),
    ("Bihar","Bihar"),
    ("Chhattisgarh","Chhattisgarh"),
    ("Goa","Goa"),
    ("Gujarat","Gujarat"),
    ("Haryana","Haryana"),
    ("Himachal Pradesh","Himachal Pradesh"),
    ("Jammu and Kashmir ","Jammu and Kashmir "),
    ("Jharkhand","Jharkhand"),
    ("Karnataka","Karnataka"),
    ("Kerala","Kerala"),
    ("Madhya Pradesh","Madhya Pradesh"),
    ("Maharashtra","Maharashtra"),
    ("Manipur","Manipur"),
    ("Meghalaya","Meghalaya"),
    ("Mizoram","Mizoram"),
    ("Nagaland","Nagaland"),
    ("Odisha","Odisha"),
    ("Punjab","Punjab"),
    ("Rajasthan","Rajasthan"),
    ("Sikkim","Sikkim"),
    ("Tamil Nadu","Tamil Nadu"),
    ("Telangana","Telangana"),
    ("Tripura","Tripura"),
    ("Uttar Pradesh","Uttar Pradesh"),
    ("Uttarakhand","Uttarakhand"),
    ("West Bengal","West Bengal"),
    ("Andaman and Nicobar Islands","Andaman and Nicobar Islands"),
    ("Chandigarh","Chandigarh"),
    ("Dadra and Nagar Haveli","Dadra and Nagar Haveli"),
    ("Daman and Diu","Daman and Diu"),
    ("Lakshadweep","Lakshadweep"),
    ("National Capital Territory of Delhi","National Capital Territory of Delhi"),
    ("Puducherry","Puducherry")
    )


class admin_login(models.Model):
    first_name = models.CharField(max_length=50)
    last_name = models.CharField(max_length=50)
    username = models.CharField(max_length=50,unique=True,null=False)
    password = models.CharField(max_length=50)
    created_at=models.DateTimeField(default=datetime.datetime.today)
    modify_at=models.DateTimeField(null=True)

    def __str__(self) -> str:
        return (self.first_name + self.last_name)

# -------------------------------------------------

class courses(models.Model):
    course_code = models.CharField(max_length=40,primary_key=True)
    course_name = models.CharField(max_length=50)
    total_sem = models.IntegerField(max_length=2)
    created_at=models.DateTimeField()
    modify_at=models.DateTimeField(null=True)
    
    def __str__(self) -> str:
        return (self.course_name )

# -------------------------------------------------

class subject(models.Model):
    sub_code = models.CharField(max_length=10,primary_key=True)
    sub_name = models.CharField(max_length=30)
    course = models.ForeignKey(courses, on_delete=models.CASCADE)
    semester = models.CharField(max_length=2,choices=SEMESTER_CHOICES)
    theory_marks = models.IntegerField(max_length=3)
    practical_marks = models.IntegerField(max_length=3,null=True)

    def __str__(self) -> str:
        return (self.sub_name + self.sub_code)

# -------------------------------------------------

class students(models.Model):
    admission_id= models.CharField(max_length=40,primary_key=True)
    first_name = models.CharField(max_length=40)
    last_name = models.CharField(max_length=50)
    roll_number = models.IntegerField()
    student_dob = models.DateField(("Date"), default=datetime.date.today)
    course = models.ForeignKey(courses, on_delete=models.CASCADE)
    semester = models.CharField(max_length=10,choices=SEMESTER_CHOICES)
    email = models.CharField(max_length=50,unique=True)
    phone = models.CharField(max_length=50,unique=True)
    gender = models.CharField(max_length=50 ,choices=GENDER_CHOICES)
    city = models.CharField(max_length=50)
    state = models.CharField(choices=state_choices,max_length=255)
    father_name = models.CharField(max_length=50)
    father_occupation = models.CharField(max_length=50)
    mother_name = models.CharField(max_length=50)
    mother_occupation = models.CharField(max_length=50)
    created_at=models.DateTimeField()
    modify_at=models.DateTimeField(null=True)

    def __str__(self) -> str:
        return (self.first_name + self.last_name)

# -------------------------------------------------

class students_documents(models.Model):
    std_id=models.OneToOneField(students,on_delete = models.CASCADE, primary_key = True)
    adhar_num=models.IntegerField(unique=True,null=True)
    birth_certificate=models.CharField(max_length=255,null=True)
    created_at=models.DateTimeField()
    modify_at=models.DateTimeField(null=True)
    
    def __str__(self) -> str:
        return (self.std_id + self.adhar_num)

# -------------------------------------------------

class student_login(models.Model):
    std_id=models.OneToOneField(students,on_delete = models.CASCADE, primary_key = True)
    username = models.CharField(max_length=50,unique=True,null=False)
    password = models.CharField(max_length=50)
    created_at=models.DateTimeField(default=datetime.datetime.today)
    modify_at=models.DateTimeField(null=True)

    def __str__(self) -> str:
        return (self.first_name + self.last_name)

# -------------------------------------------------

class faculties(models.Model):
    fac_id = models.CharField(max_length=10,primary_key=True)
    first_name = models.CharField(max_length=40)
    last_name = models.CharField(max_length=50)
    faculties_dob = models.DateField(("Date"), default=datetime.date.today)
    qualifications = models.CharField(max_length=100)
    gender = models.CharField(max_length=50 , choices=GENDER_CHOICES )
    experience = models.IntegerField()
    created_at=models.DateTimeField(default=datetime.datetime.today)
    modify_at=models.DateTimeField(null=True)

    def __str__(self) -> str:
        return (self.first_name + " " +self.fac_id)

# -------------------------------------------------

class faculties_documents(models.Model):
    fac_id=models.OneToOneField(faculties,on_delete = models.CASCADE, primary_key = True)
    adhar_num=models.IntegerField(unique=True,null=True)
    pan_number=models.IntegerField(unique=True,null=True)
    birth_certificate=models.CharField(max_length=255,null=True)
    created_at=models.DateTimeField()
    modify_at=models.DateTimeField(null=True)
    
    def __str__(self) -> str:
        return (self.std_id + self.adhar_num)

# -------------------------------------------------

class faculties_login(models.Model):
    fac_id=models.OneToOneField(faculties,on_delete = models.CASCADE, primary_key = True)
    username = models.CharField(max_length=50,unique=True,null=False)
    password = models.CharField(max_length=50)
    created_at=models.DateTimeField(default=datetime.datetime.today)
    modify_at=models.DateTimeField(null=True)

    def __str__(self) -> str:
        return (self.username)

# -------------------------------------------------

class assign_subject(models.Model):
    fac_id = models.ForeignKey(faculties,on_delete=models.CASCADE)
    sub_code = models.ForeignKey(subject,on_delete=models.CASCADE)
    created_at=models.DateTimeField(default=datetime.datetime.today)
    modify_at=models.DateTimeField(null=True)
    def __str__(self) -> str:
        return (str(self.fac_id) + " " +str(self.sub_code))


class enter_marks(models.Model):
    std_id = models.ForeignKey(students,on_delete=models.CASCADE)
    sub_id = models.ForeignKey(subject,on_delete=models.CASCADE)
    theory_marks= models.IntegerField()
    practical_marks= models.IntegerField(null=True)
    created_at=models.DateTimeField(default=datetime.datetime.today)
    modify_at=models.DateTimeField(null=True)

    def __str__(self) -> str:
        return (str(self.std_id) + " " +str(self.sub_id))
