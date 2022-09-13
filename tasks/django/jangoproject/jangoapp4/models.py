from django.db import models

# Create your models here.

from django.db import models
from django.utils import timezone
from django.core.exceptions import ValidationError
from django.utils.translation import gettext_lazy as _
from django.core.validators import MinValueValidator, MaxValueValidator,FileExtensionValidator

def validate_marks(value):
    if value > 100:
        raise ValidationError(
            _('%(value)s is not an valid number'),
            params={'value': value},
        )

class Location(models.Model):
    name = models.CharField(max_length=20)
    address = models.CharField(max_length=500)

    class Meta:
        db_table = 'loaction'

    def __str__(self):
        return self.name

class college(models.Model):
    clg_code = models.CharField(max_length=5,primary_key=True)
    clgname = models.CharField(max_length=50)
    places = models.OneToOneField(Location,on_delete=models.PROTECT)

    class Meta:
        db_table = 'college'

    def __str__(self):
        return self.clgname

class department(models.Model):
    departments = (
    ('ece','electronics and communication'),
    ('eee','electrical and electronics'),
    ('civ','civil'),
    ('mech','mechanical')
    )
    # dept_id = models.IntegerField(default=1,primary_key=True)
    deptid = models.IntegerField(default=1,primary_key=True)
    dept_name = models.CharField(max_length=4,default='ece',choices=departments)

    class Meta:
        db_table = 'Department'
        managed = False

    def __str__(self):
        return self.dept_name

class Professor(models.Model):
    pid = models.IntegerField(primary_key=True)
    p_name = models.CharField(max_length=50,blank=False)
    departments = models.ManyToManyField(department)

    class Meta:
        db_table = 'Professor'
        managed = True

    def __str__(self):
        return self.p_name

class Student(models.Model):
    sid = models.IntegerField(primary_key=True,db_column='student id')
    sname = models.CharField(max_length=20)
    Marks = models.IntegerField(validators=[validate_marks])
    semester = models.IntegerField(validators=[MaxValueValidator(8),MinValueValidator(1)])
    file = models.FileField(validators=[FileExtensionValidator( ['pdf','png'] )])
    departments = models.ForeignKey(department,on_delete=models.PROTECT,default=1)

    class Meta:
        db_table = 'student'

    def __str__(self):
        return self.sname
# Create your models here.
class PracticeModel(models.Model):
    SHIRT_SIZES = (
    ('S', 'Small'),
    ('M', 'Medium'),
    ('L', 'Large'),
    )
    
    name = models.CharField(max_length=60,blank=True)
    shirt_size = models.CharField(max_length=1, choices=SHIRT_SIZES,null=True)
    
    languages = (
    ('tel','telugu'),
    ('hin','hindi'),
    ('eng','english')
    )
    
    languages_known = models.CharField(max_length=5,choices=languages,default='tel')
    
    Bool = models.BooleanField(default=False,verbose_name='this is a boolean field')
    character = models.CharField(verbose_name='name',max_length=100,help_text='enter full name')
    Date = models.DateField(default=timezone.now)
    deci = models.DecimalField(verbose_name='decimalfield',max_digits=5,decimal_places=2,default=1.0,db_column='decimal value')
