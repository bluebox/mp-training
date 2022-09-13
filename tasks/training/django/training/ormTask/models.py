from django.db import models
from django.core.exceptions import ValidationError
from django.utils.translation import gettext_lazy as _
# Create your models here.

def validate_even(value):
    if value<1 or value>8:
        raise ValidationError(
            _('%(value)s is not valid semester'),
            params={'value': value},
        )


class Person(models.Model):
    name = models.CharField(max_length=100 , null=True,blank=True)
    age = models.IntegerField(null=True,blank=True)

    class Meta:
        abstract = True

class Department(models.Model):
    dept_id =  models.CharField(primary_key=True,max_length=10)
    dept_name = models.CharField(max_length=50,default='computer Science')
    
    class Meta:
        db_table ="branch"


class Student(Person):
    semester = models.IntegerField(validators=[validate_even],default=1)
    joined_on = models.DateTimeField(auto_now_add=True,null=True,blank=True,db_column="admission_date")
    subjects_enrolled = models.ManyToManyField("Subject",blank=True)
    department =  models.ForeignKey(Department , on_delete=models.SET_NULL,null=True , related_name='students')

    class Meta:
        db_table ="class"
        ordering = ['semester']
        verbose_name ="class"
        verbose_name_plural = 'class-es'

    def __str__(self):
        return str(self.semester)


class Lecturer(models.Model):
    lecturer_name = models.CharField(primary_key=True,max_length=100)
    subject = models.ManyToManyField("Subject",blank=True)

    class Meta:
        db_table = "Teaching Staff"
        managed = False


class Subject(models.Model):
    subj_code = models.CharField(primary_key=True,max_length=10)
    subj_name = models.CharField(max_length=50)
    description = models.TextField(blank=True,null=True)

    class Meta:
        db_table = "subject"
