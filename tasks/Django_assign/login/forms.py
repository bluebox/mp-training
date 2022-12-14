from django import forms
from .models import *


class Login(forms.Form):
    Username = forms.CharField(min_length=5)



