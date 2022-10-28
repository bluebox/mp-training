from dataclasses import field
from pyexpat import model
from rest_framework import serializers
from .models import Appointment, Liked, NewArrivals, Properties,TypeTable
from users.models import User
class TypeSerializer(serializers.ModelSerializer):
    class Meta:
        model = TypeTable
        fields ='__all__'


class PropertySerializer(serializers.ModelSerializer):
    type=TypeSerializer(many=True)
    class Meta:
        model = Properties
        fields ='__all__'

class UserSerializer(serializers.ModelSerializer):
    class Meta:
        model = User
        fields ='__all__'
         
class LikedSerializer(serializers.ModelSerializer):
    property=PropertySerializer()
    customer=UserSerializer()
    class Meta:
        model = Liked
        fields = '__all__'
class newarivalserial(serializers.ModelSerializer):
    property=PropertySerializer()
    class Meta:
        model = NewArrivals
        fields = '__all__'
class AppointmentSerializer(serializers.ModelSerializer):
    malik_id=UserSerializer()
    customer=UserSerializer()
    class Meta:
        model=Appointment
        fields='__all__'
