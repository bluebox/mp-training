from django.db import models
import datetime


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
    ('6','6th')
)
ROLE_CHOICES=(
    ('admin',"admin"),
    ('teacher',"teacher")
)

class adminusers(models.Model):
    first_name = models.CharField(max_length=40)
    last_name = models.CharField(max_length=50)
    admin_DOB = models.DateField(("Date"), default=datetime.date.today)
    username = models.CharField(max_length=50)
    password = models.CharField(max_length=50)
    role = models.CharField(max_length=10,default="admin")
    
    def __str__(self) -> str:
        return (self.first_name + self.last_name)


class courses(models.Model):
    course_code = models.CharField(max_length=40,unique=True)
    course_name = models.CharField(max_length=50)
    total_sem = models.IntegerField()
    
    def __str__(self) -> str:
        return (self.course_name )



class students(models.Model):
    admission_id= models.CharField(max_length=40,unique=True,default="m001")
    first_name = models.CharField(max_length=40)
    last_name = models.CharField(max_length=50)
    roll_number = models.IntegerField(default=1)
    student_dob = models.DateField(("Date"), default=datetime.date.today)
    username = models.CharField(max_length=50)
    password = models.CharField(max_length=50)
    course = models.ForeignKey(courses, on_delete=models.CASCADE)
    semester = models.CharField(max_length=10,choices=SEMESTER_CHOICES,default='1')
    email = models.CharField(max_length=50)
    phone = models.CharField(max_length=50)
    gender = models.CharField(max_length=50 , choices=GENDER_CHOICES ,default="male")
    city = models.CharField(max_length=50)
    state = models.CharField(max_length=50)
    father_name = models.CharField(max_length=50)
    father_occupation = models.CharField(max_length=50)
    mother_name = models.CharField(max_length=50)
    mother_occupation = models.CharField(max_length=50)

    def __str__(self) -> str:
        return (self.first_name + self.last_name)


class subject(models.Model):
    sub_code = models.CharField(max_length=10,unique=True)
    sub_name = models.CharField(max_length=30)
    course = models.ForeignKey(courses, on_delete=models.CASCADE)
    semester = models.CharField(max_length=10,choices=SEMESTER_CHOICES,default='1')
    theory_marks = models.IntegerField()
    def __str__(self) -> str:
        return (self.sub_name + self.sub_code)

class faculties(models.Model):
    fac_id = models.CharField(max_length=10)
    first_name = models.CharField(max_length=40)
    last_name = models.CharField(max_length=50)
    faculties_dob = models.DateField(("Date"), default=datetime.date.today)
    qualifications = models.CharField(max_length=100)
    gender = models.CharField(max_length=50 , choices=GENDER_CHOICES ,default="male")
    experience = models.IntegerField()
    username = models.CharField(max_length=50)
    password = models.CharField(max_length=50)

    def __str__(self) -> str:
        return (self.first_name + " " +self.fac_id)

class assign_subject(models.Model):
    fac_id = models.ForeignKey(faculties,on_delete=models.CASCADE)
    sub_code = models.ForeignKey(subject,on_delete=models.CASCADE)


class enter_marks(models.Model):
    std_id = models.ForeignKey(students,on_delete=models.CASCADE)
    sub_id = models.ForeignKey(subject,on_delete=models.CASCADE)
    marks= models.IntegerField()

    def __str__(self) -> str:
        return (str(self.std_id) + " " +str(self.sub_id))
