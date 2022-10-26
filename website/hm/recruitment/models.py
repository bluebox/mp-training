from django.db import models
from django.core.exceptions import ValidationError
from django.utils.translation import gettext_lazy as _
from employeedetail.models import *

def validate_email(value):
    if value[-7:] == '@xyz.com' :
        raise ValidationError(
            _('%(value)s is not an valid email'),
            params={'value': value},
        )
def validate_number(value):
    value=str(value)
    if int(value[0]) <6 :
        raise ValidationError(
            _('%(value)s is not an valid number'),
            params={'value': value},
        )


class candidate_info(models.Model):

    cid = models.IntegerField(primary_key=True,default="-")
    name = models.CharField(max_length=50,default="-")
    contact = models.CharField(max_length=10,default='0',unique=True)
    age = models.CharField(max_length=50,default="-")
    mail = models.EmailField(max_length=50,unique = True)
    gender = models.CharField(max_length=50,default="-")
    #cv = models.ImageField(upload_to='images/',default="-")
    addharcard_no = models.IntegerField(unique=True,default=0)

class applying(models.Model):
    vid = models.ForeignKey('vaacancy', on_delete=models.CASCADE, )
    cid = models.ForeignKey("candidate_info", on_delete=models.CASCADE)







class assesment(models.Model):
    cid = models.ForeignKey("candidate_info", on_delete=models.CASCADE)
    vid=models.ForeignKey("vaacancy",on_delete=models.CASCADE)
    name = models.CharField(max_length=20,default="-")
    apptitute = models.CharField(max_length=10,default="pending")
    codeing =models.CharField(max_length=10,default="pending")
    interview1 =models.CharField(max_length=10,default="pending")
    interview2 = models.CharField(max_length=10,default="pending")
    result = models.CharField(max_length=50,default="AA")
    update = models.CharField(max_length=50,default="APPLICATION UNDER REVIEW")

class vaacancy(models.Model):
    vid = models.IntegerField(db_column="test", primary_key=True)
    designeation = models.ForeignKey(designation, on_delete=models.CASCADE)
    salary = models.CharField(max_length=50,default="-")
    skill  = models.CharField(max_length=50,default="-")
    experience = models.CharField(max_length=50,default="-")
    no_of_vacancy = models.CharField(max_length=50,default="-")










