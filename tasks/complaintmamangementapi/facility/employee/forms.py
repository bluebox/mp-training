from django.forms import ModelForm
from .models import Employee, Complaint ,Issued_to


class Employeeform (ModelForm):
    class Meta (ModelForm):
        model=Employee
        fields='__all__'


class Complaintform (ModelForm):
    class Meta (ModelForm):
        model=Complaint
        fields= ['facility_id', 'emp_id', 'device_id', 'comp_desc']


class IssuedtoForm (ModelForm):
    class Meta (ModelForm):
        model=Issued_to
        fields='__all__'