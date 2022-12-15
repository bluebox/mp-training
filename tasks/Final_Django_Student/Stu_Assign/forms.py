from django import forms
from .models import Student_Info
import re


class StudentForm(forms.ModelForm):
    name = forms.TextInput()
    address = forms.TextInput()
    d_o_b = forms.DateInput()
    branch = forms.TextInput()
    image = forms.FileInput()

    def clean_name(self):
        value = self.cleaned_data['name']
        if re.findall('[0-9]', value):
            raise forms.ValidationError('Name must contain characters only!')
        else:
            return value

    def clean_address(self):
        value = self.cleaned_data['address']
        if len(value) < 3:
            raise forms.ValidationError("Give Proper address")
        else:
            return value

    def clean_branch(self):
        value = self.cleaned_data['branch']
        if value.isupper():
            return value
        else:
            raise forms.ValidationError("Give ShortForm Capital letter string")

    class Meta:
        model = Student_Info
        fields = ['name', 'address', 'd_o_b', 'branch', 'image']
        labels = {
            'name': 'Student Name',
            'address': 'City',
            'd_o_b': 'Birth-Date',
            'branch': 'Stream',
        }
        widgets = {
            'name': forms.TextInput(attrs={'class': 'form-control'}),
            'address': forms.TextInput(attrs={'class': 'form-control'}),
            'd_o_b': forms.DateInput(attrs={'class': 'form-control'}),
            'branch': forms.TextInput(attrs={'class': 'form-control'}),
        }


