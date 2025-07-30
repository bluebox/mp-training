from django import forms
from django.contrib.auth.models import User
from django.contrib.auth.forms import  UserCreationForm
class ContactForm(forms.Form):
    name = forms.CharField(label='Your Name', max_length=100)
    email = forms.EmailField(label='Your Email')
    message = forms.CharField(widget=forms.Textarea)


class RegistrationForm(UserCreationForm):
    email=forms.EmailField(required=True,widget=forms.EmailInput(attrs={'class':'form-control'}))
    class Meta:
        model=User
        fields=['username','email']
