from django.core.exceptions import ValidationError
from django.shortcuts import render

# Create your views here.
def validate_length(data):
    if len(data)!= 10:
        raise ValidationError("Length must be 10");



