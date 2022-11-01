#pylint:disable=R0903
'''serializers for appointment related'''
from rest_framework import serializers
from . import models


class LabSerializer(serializers.ModelSerializer):
    """lab serializer"""
    class Meta:
        """additional details"""
        model = models.Lab
        fields = "__all__"

    def __str__(self):
        return self.__class__.__name__


class BranchSerializer(serializers.ModelSerializer):
    """branch serializer"""
    class Meta:
        """additional details"""
        model = models.Branch
        fields = "__all__"

    def __str__(self):
        return self.__class__.__name__


class TestSerializer(serializers.ModelSerializer):
    """test serializer"""
    class Meta:
        """additional details"""
        model = models.Test
        fields = ['test_id', 'test_name', 'test_description', 'lab']

    def __str__(self):
        return self.__class__.__name__


class ReviewSerializer(serializers.ModelSerializer):
    """review serializer"""
    class Meta:
        """additional details"""
        model = models.Review
        fields = "__all__"

    def __str__(self):
        return self.__class__.__name__


class AppointmentSerializer(serializers.ModelSerializer):
    """appointment serializer"""
    class Meta:
        """additional details"""
        model = models.Appointment
        fields = ["appointment_id", 'user', 'branch', 'slot',
                  'doctor_id', 'nurse_id', 'lab_technician',
                  'sample_collector', 'status', 'date', 'tests']

    def __str__(self):
        return self.__class__.__name__


class GetAppointmentSerializer(serializers.ModelSerializer):
    """to get appointment serializer"""
    customer_id = serializers.CharField(source='user')
    username = serializers.CharField(source='user.user_id.username')
    doctor = serializers.CharField(source='doctor_id.user_id.username', default=None)
    nurse = serializers.CharField(source='nurse_id.user_id.username', default=None)
    lab_technician = serializers.CharField(source='lab_technician.user_id.username', default=None)
    sample_collector = serializers.CharField(source='sample_collector.user_id.username',
                                             default=None)

    class Meta:
        """additional details"""
        model = models.Appointment
        fields = ['appointment_id', 'customer_id', 'username', 'slot', 'date', 'doctor', 'nurse',
                  'lab_technician', 'sample_collector', 'status', 'tests']

    def __str__(self):
        return self.__class__.__name__


class BillSerializer(serializers.ModelSerializer):
    """bill serializer"""
    class Meta:
        """additional details"""
        model = models.Bill
        fields = "__all__"

    def __str__(self):
        return self.__class__.__name__


class ReportSerializer(serializers.ModelSerializer):
    """report serializer"""
    class Meta:
        """additional details"""
        model = models.Report
        fields = "__all__"

    def __str__(self):
        return self.__class__.__name__
