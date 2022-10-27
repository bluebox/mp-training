from django.shortcuts import render
from django.core.exceptions import ValidationError
from django.utils.translation import gettext_lazy as _
# Create your views here.


def validate_password(value):
    if len(value) < 6:
        raise ValidationError(
            _('%(value)s should be atleast of length 6'),
            params={'value': value},
        )


def validate_phone(value):
    if len(value) < 10:
        raise ValidationError(
            _('%(value)s is not valid'),
            params={'value': value},
        )


def validate_userId(value):
    if len(value) < 4:
        raise ValidationError(
            _('%(value)s should be atleast of length 4'),
            params={'value': value},
        )
