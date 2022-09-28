from rest_framework import serializers
from  .models import Customer, Staff, User


class CustomerSerializer(serializers.ModelSerializer):
    class Meta:
        model = Customer
        fields = '__all__'

class UserSerializer(serializers.ModelSerializer):
    class Meta:
        model = User
        fields = ['username','first_name','last_name','email','mobile_number','age','address','pincode','password']

class EmployeeSerializer(serializers.ModelSerializer):
    class Meta:
        model = Staff
        fields = '__all__'
    


