from rest_framework import serializers
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
        fields='__all__'