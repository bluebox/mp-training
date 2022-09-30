from rest_framework import serializers

import facility
from .models  import *
class EmployeeSerializer(serializers.ModelSerializer):
    class Meta:
        model=Employee
        fields='__all__'

class DeviceSerializer(serializers.ModelSerializer):
    class Meta:
        model=Device
        fields='__all__'


class FacilitySerializer(serializers.ModelSerializer):
    class Meta:
        model=Facility
        fields='__all__'


class ComplaintSerializer(serializers.ModelSerializer):
    class Meta:
        model=Complaint
        # fields=["facility_id","emp_id","comp_desc",'device_id']
        fields='__all__'


class DeviceSerializer(serializers.ModelSerializer):
    class Meta:
        model = Device
        fields = '__all__'

class Issued_toSerializer(serializers.ModelSerializer):
    class Meta:
        model =Issued_to
        fields = '__all__'