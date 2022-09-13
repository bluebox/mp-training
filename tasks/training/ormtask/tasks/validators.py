from django.core.exceptions import ValidationError
from django.utils.translation import gettext_lazy as _
import re
# Create your models here.


def validatePassword(value):
    regcap = re.compile(r'[A-Z]+')
    reglow = re.compile(r'[a-z]+')
    regdig = re.compile(r'[0-9]+')
    if len(value) < 8 :
        raise ValidationError(_(' value error of  number'), params={'value': value})

    elif not (regcap.search(value) and reglow.search(value) and regdig.search(value)):
        raise ValidationError(_(' value error of  number'), params={'value': value})

