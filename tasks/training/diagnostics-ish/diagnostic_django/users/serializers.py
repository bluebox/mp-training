#pylint:disable=R0903
"""serializers"""
from rest_framework import serializers
from .models import Customer, Staff, User


class CustomerSerializer(serializers.ModelSerializer):
    """customer serializer"""
    class Meta:
        """additional"""
        model = Customer
        fields = '__all__'


class UserSerializer(serializers.ModelSerializer):
    """user serializer"""
    class Meta:
        """meta details"""
        model = User
        fields = ('username', 'first_name', 'last_name', 'email', 'mobile_number',
                  'age', 'address', 'pincode', 'password', 'user_type')

    def create(self, validated_data):
        """create method"""
        return User.objects.create_user(**validated_data)


class EmployeeSerializer(serializers.ModelSerializer):
    """staff model serializer"""
    class Meta:
        """meta details"""
        model = Staff
        fields = '__all__'
