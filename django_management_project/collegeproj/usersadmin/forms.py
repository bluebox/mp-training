from dataclasses import fields
from statistics import mode
from django.forms import ModelForm
from .models import adminusers,students,courses,subject,faculties,assign_subject,enter_marks


class adminusersform(ModelForm):
    class Meta:
        model=adminusers
        fields='__all__'
class subjectform(ModelForm):
    class Meta:
        model=subject
        fields='__all__'
class studentsform(ModelForm):
    class Meta:
        model=students
        fields='__all__'
class courseform(ModelForm):
    class Meta:
        model=courses
        fields='__all__'
class facultiesform(ModelForm):
    class Meta:
        model=faculties
        fields='__all__'
class assign_subjectform(ModelForm):
    class Meta:
        model=assign_subject
        fields='__all__'
