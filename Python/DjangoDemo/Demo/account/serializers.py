from rest_framework import serializers
from .models import Student, Example, Employee
import re

class StudentSerializer(serializers.ModelSerializer):
    class Meta:
        model=Student
        fields='__all__'

class ExampleSerializer(serializers.ModelSerializer):
    class Meta:
        model=Example
        fields='__all__'

class EmployeeSerializer(serializers.ModelSerializer):
    def validate_salary(self, value):
        if not re.fullmatch(r'\d+', str(value)):
            raise serializers.ValidationError("Enter a valid numeric score.")
        return value
    class Meta:
        model=Employee
        fields='__all__'

