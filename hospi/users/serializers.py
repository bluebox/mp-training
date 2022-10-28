from users.models import Appointment, Doctor,Staff,Patients,AllUser,DatewiseSlot
from rest_framework import serializers
class PatientSerializer(serializers.ModelSerializer):
    class Meta:
        model = Patients
        fields ='__all__'

class StaffSerializer(serializers.ModelSerializer):
    class Meta:
        model = Staff
        fields = '__all__'

class DoctorSerializer(serializers.ModelSerializer):
    class Meta:
        model = Doctor
        fields = '__all__'

class AllUserSerializer(serializers.ModelSerializer):
    class Meta:
        model = AllUser
        fields = '__all__'
        extra_kwargs = {'password': {'write_only': True}}
class DatewiseSlotSerializer(serializers.ModelSerializer):
    class Meta:
        model = DatewiseSlot
        fields = '__all__'
class AppointmentSerializer(serializers.ModelSerializer):
    class Meta:
        model = Appointment
        fields = '__all__'