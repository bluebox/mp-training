from django.core.exceptions import ValidationError
from django.utils.translation import gettext_lazy as _
import re
# Create your models here.


def validatePassword(value):

    if len(value) < 8 :
        regcap = re.compile(r'[A-Z]+')
        reglow = re.compile(r'[a-z]+')
        regdig = re.compile(r'[0-9]+')
        if not regcap.search(value) and not reglow.search(value) and not regdig.search(value):
            raise ValidationError(_(' value error of  number'), params={'value': value})
