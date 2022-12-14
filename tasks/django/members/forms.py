from django import forms
from .models import StudentInfo


class StudentForm(forms.ModelForm):
    class Meta:
        model = StudentInfo
        # fields = '__ALL__' can also be used
        fields = ['student_id', 'student_name', 'roll_no',
                'branch', 'dob', 'address', 'gender', 'phone_no', 'image']
        labels = {
            'student_id': 'Student ID',
            'student_name': 'Student Name',
            'roll_no': 'Roll No.',
            'branch': 'Branch ID',
            'dob': 'DOB',
            'address': 'Address',
            'gender': 'Gender',
            'phone_no': 'Phone No.',
            'image': 'image',
        }

        widgets = {
            'student_id': forms.NumberInput(attrs={'class': 'form-control'}),
            'student_name': forms.TextInput(attrs={'class': 'form-control'}),
            'roll_no': forms.TextInput(attrs={'class': 'form-control'}),
            'branch': forms.NumberInput(attrs={'class': 'form-control'}),
            'dob': forms.DateInput(attrs={'class': 'form-control'}),
            'address': forms.TextInput(attrs={'class': 'form-control'}),
            'gender': forms.TextInput(attrs={'class': 'form-control'}),
            'phone_no': forms.TextInput(attrs={'class': 'form-control'}),
            'image': forms.FileInput(attrs={'class': 'form-control'}),
        }

