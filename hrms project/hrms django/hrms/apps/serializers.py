
from rest_framework import serializers
from . models import *
from django.contrib.auth.models import User

class UserSerializer(serializers.ModelSerializer):
    class Meta:
        model = User
        fields = ('username','password',)
class logSerializer(serializers.ModelSerializer):
    class Meta:
        model = log
        fields = '__all__'

class SalarySerializer(serializers.ModelSerializer):
    class Meta:
        model = salary
        fields = '__all__'   

class DepartmentSerializer(serializers.ModelSerializer):
    class Meta:
        model = department
        fields = '__all__'

class RoleSerializer(serializers.ModelSerializer):
    class Meta:
        model = role
        fields = '__all__'  


class TeamleadSerializer(serializers.ModelSerializer):
    class Meta:
        model = Team_lead
        fields = '__all__'

class PersonaldetailsSerializer(serializers.ModelSerializer):
    class Meta:
        model = personal_details
        fields = '__all__'  


class InsuranceSerializer(serializers.ModelSerializer):
    class Meta:
        model = insurance
        fields = '__all__'

class AttendenceSerializer(serializers.ModelSerializer):
    class Meta:
        model = Attendence
        fields = '__all__'   

class LeaveSerializer(serializers.ModelSerializer):
    class Meta:
        model = leave
        fields = '__all__'   
