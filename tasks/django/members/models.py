# This is an auto-generated Django model module.
# You'll have to do the following manually to clean this up:
#   * Rearrange models' order
#   * Make sure each model has one field with primary_key=True
#   * Make sure each ForeignKey and OneToOneField has `on_delete` set to the desired behavior
#   * Remove `managed = False` lines if you wish to allow Django to create, modify, and delete the table
# Feel free to rename the models, but don't rename db_table values or field names.
from django.db import models
from django.utils.safestring import mark_safe

class Branch(models.Model):
    branch_id = models.IntegerField(primary_key=True)
    branch_name = models.CharField(max_length=255, blank=True, null=True)

    def __str__(self):
        return self.branch_name 

    class Meta:
        managed = False
        db_table = 'Branch'


class Marks(models.Model):
    marks_id = models.IntegerField(primary_key=True)
    student = models.ForeignKey('StudentInfo', on_delete = models.CASCADE, blank=True, null=False)
    subject = models.ForeignKey('Subjects', on_delete = models.CASCADE, blank=True, null=False)
    marks = models.IntegerField(blank=True, null=True)

    def __str__(self):
        return f'{self.student} {self.subject}' 

    class Meta:
        managed = False
        db_table = 'Marks'

GENDER = [
    ('Female', 'female'),
    ('Male', 'male'),    
]
        
class StudentInfo(models.Model):
    student_id = models.IntegerField(primary_key=True)
    student_name = models.CharField(max_length=255, blank=True, null=True)
    roll_no = models.CharField(max_length=255, blank=True, null=True)
    branch = models.ForeignKey(Branch, on_delete = models.CASCADE, blank=True, null=False)
    dob = models.DateField(db_column='DOB', blank=True, null=True)  # Field name made lowercase.
    address = models.CharField(max_length=255, blank=True, null=True)
    gender = models.CharField(max_length=6, blank=True, null=True, choices=GENDER)
    phone_no = models.CharField(max_length=10, blank=True, null=True)
    image = models.ImageField(null=True, blank=True, upload_to='')
    image_strl = models.CharField(max_length=255, null=True, blank=True)

    def __str__(self):
        return self.student_name

    class Meta:
        managed = False
        db_table = 'Student_Info'


class Subjects(models.Model):
    subject_id = models.IntegerField(primary_key=True)
    subject_name = models.CharField(max_length=255, blank=True, null=True)
    semester = models.IntegerField(blank=True, null=True)

    def __str__(self):
        return self.subject_name

    class Meta:
        managed = False
        db_table = 'Subjects'
