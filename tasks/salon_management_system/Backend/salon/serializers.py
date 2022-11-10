"""serializers"""
# pylint:disable=R0903
from rest_framework import serializers

from .models import Branch, Employee, User, ServicesProvided, Client, Appointment, Reviews


class Userserializer(serializers.ModelSerializer):
    """user serializer for user model"""

    class Meta:
        """metaclass to use required fields of the User model"""
        model = User
        fields = ['id', 'username', 'first_name', 'last_name', 'email', 'is_staff']


class ClientSerializer(serializers.ModelSerializer):
    """client serializer for client model"""

    class Meta:
        """class to use required fields of the client model"""
        model = Client
        fields = "__all__"


class EmployeeSerializer(serializers.ModelSerializer):
    """employee serializer for Employee model"""

    class Meta:
        """class to use required fields of the Employee model"""
        model = Employee
        fields = "__all__"


class BranchSerializer(serializers.ModelSerializer):
    """branch serializer for Branch Model"""

    class Meta:
        """class to use required of the Branch Model"""
        model = Branch
        fields = "__all__"


class ServicesSerializer(serializers.ModelSerializer):
    """Services serializer for services_provided model"""

    class Meta:
        """class to choose required fields of the services_provided model"""
        model = ServicesProvided
        fields = "__all__"


class AppointmentSerializer(serializers.ModelSerializer):
    """Appointment serializer for Appointment model"""

    class Meta:
        """class to choose required fields of the appointment model"""
        model = Appointment
        fields = "__all__"


class ReviewSerializer(serializers.ModelSerializer):
    """review serializer for review model"""

    class Meta:
        """class to choose required fields from Review model"""
        model = Reviews
        fields = "__all__"
