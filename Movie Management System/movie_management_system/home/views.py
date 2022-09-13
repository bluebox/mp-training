from django.shortcuts import render
from django.http import HttpResponse
from django.core.exceptions import ValidationError
from django.utils.translation import gettext_lazy as _
# Create your views here.
def index(request):
    return HttpResponse("Movie Management System")

def validate_Phone(value):
    if value.length < 10:
        raise ValidationError(
            _('%(value)s is not a right phone number'),
            params={'value': value},
        )
