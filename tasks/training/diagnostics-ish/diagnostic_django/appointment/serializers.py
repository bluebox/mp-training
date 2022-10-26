from rest_framework import serializers
from . import models


class LabSerializer(serializers.ModelSerializer):
    class Meta:
        model = models.Lab
        fields = "__all__"


class BranchSerializer(serializers.ModelSerializer):
    class Meta:
        model = models.Branch
        fields = "__all__"


class TestSerializer(serializers.ModelSerializer):
    class Meta:
        model = models.Test
        fields = ['test_id', 'test_name', 'test_description' , 'lab']


class ReviewSerializer(serializers.ModelSerializer):
    class Meta:
        model = models.Review
        fields = "__all__"


class AppointmentSerializer(serializers.ModelSerializer):
    # customer_id = serializers.CharField(source='user')
    # username = serializers.CharField(source='user.user_id.username')
    # doctor = serializers.CharField(source='doctor_id.user_id.username', default=None)
    # nurse = serializers.CharField(source='nurse_id.user_id.username', default=None)
    # lab_technician = serializers.CharField(source='lab_technician.user_id.username', default=None)
    # sample_collector = serializers.CharField(source='sample_collector.user_id.username', default=None)

    class Meta:
        model = models.Appointment
        fields = ["appointment_id",'user','branch','slot','doctor_id','nurse_id','lab_technician','sample_collector','status','date','tests']
        # fields = [ 'appointment_id', 'customer_id', 'username', 'slot','date', 'doctor', 'nurse',
        #     'lab_technician', 'sample_collector', 'status','tests']



class GetAppointmentSerializer(serializers.ModelSerializer):
    customer_id = serializers.CharField(source='user')
    username = serializers.CharField(source='user.user_id.username')
    doctor = serializers.CharField(source='doctor_id.user_id.username', default=None)
    nurse = serializers.CharField(source='nurse_id.user_id.username', default=None)
    lab_technician = serializers.CharField(source='lab_technician.user_id.username', default=None)
    sample_collector = serializers.CharField(source='sample_collector.user_id.username', default=None)

    class Meta:
        model = models.Appointment
        fields = ['appointment_id', 'customer_id', 'username', 'slot', 'date', 'doctor', 'nurse',
                  'lab_technician', 'sample_collector', 'status', 'tests']



class BillSerializer(serializers.ModelSerializer):
    class Meta:
        model = models.Bill
        fields = "__all__"



class ReportSerializer(serializers.ModelSerializer):
    class Meta:
        model = models.Report
        fields = "__all__"
