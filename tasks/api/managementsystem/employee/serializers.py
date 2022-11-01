from pyexpat import model

from rest_framework import serializers

from .models import *


class EmployeeSerializer(serializers.ModelSerializer):
    class Meta:
        model = Employee
        fields='__all__'

class EditEmployeeSerializer(serializers.ModelSerializer):
    class Meta:
        model = Employee
        fields=['emp_name', 'emp_mobile', 'emp_email', 'emp_role', 'emp_password','facility_id']

class DeviceSerializer(serializers.ModelSerializer):
    class Meta:
        model = Device
        fields = '__all__'


class FacilitySerializer(serializers.ModelSerializer):
    class Meta:
        model = Facility
        fields = '__all__'


class ComplaintSerializer(serializers.ModelSerializer):
    emp_id=EmployeeSerializer()
    device_id=DeviceSerializer()
    class Meta:
        model = Complaint
        fields = '__all__'

#
# class PostComplaintSerializer(serializers.ModelSerializer):
#     class Meta:
#         model=Complaint
#         feilds = ['facility_id', 'emp_id', 'device_id', 'comp_desc']

class DeviceSerializer(serializers.ModelSerializer):
    class Meta:
        model = Device
        fields = '__all__'

class ComplaintSerializerWithOutEmployee(serializers.ModelSerializer):
    device_id = DeviceSerializer()
    class Meta:
        model = Complaint
        fields = '__all__'


class Issued_toSerializer(serializers.ModelSerializer):
    # emp_id = EmployeeSerializer()
    comp_id=ComplaintSerializer()
    class Meta:
        model = Issued_to
        fields = '__all__'


class EmployeeSerializerPic(serializers.ModelSerializer):
    class Meta:
        model = Employee
        exclude = ['emp_pic']


class EmployeeDeviceSerializer(serializers.ModelSerializer):
    class Meta:
        model = Employee_Devices
        fields = '__all__'


class PostComplaintSerializer(serializers.ModelSerializer):
    class Meta:
        model = Complaint
        fields = ['facility_id', 'emp_id', 'device_id', 'comp_desc']
        # exclude = ['comp_id', 'is_assigned']

class ComplaintWithDeviceSerializer(serializers.ModelSerializer):
    device_id = DeviceSerializer()
    class Meta:
        model= Complaint
        fields='__all__'


