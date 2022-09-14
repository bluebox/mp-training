from rest_framework import serializers
from . models import department,employee

class DepartmentSerializer(serializers.ModelSerializer):
    class Meta:
        model = department
        fields = '__all__'

class EmployeeSerializer(serializers.ModelSerializer):
    class Meta:
        model = employee
        fields = '__all__'        
