from django.core.exceptions import ValidationError
from django.utils.translation import gettext_lazy as _

# Create your models here.


def validatePassword(value):

    if len(value) < 8 :
        raise ValidationError(_(' value error of  number'), params={'value': value})
