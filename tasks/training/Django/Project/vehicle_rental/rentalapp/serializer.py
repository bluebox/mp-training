from rest_framework import serializers
from .models import *

class CustomerSerializer(serializers.ModelSerializer):

    class Meta:
        model = Customer
        fields = '__all__'


class OwnerSerializer(serializers.ModelSerializer):

    class Meta:
        model = Owner
        fields = '__all__'


class VehicleSerializer(serializers.ModelSerializer):
    class Meta:
        model = Vehicle
        # fields = '__all__'
        exclude = ('image',)

class VehicleStatusSerializer(serializers.ModelSerializer):
    class Meta:
        model = Vehicle_status
        fields = '__all__'


class Rent_TripSerializer(serializers.ModelSerializer):
    class Meta:
        model = Rent_Trip
        fields = '__all__'


class BillSerializer(serializers.ModelSerializer):
    class Meta:
        model = Bill
        fields = '__all__'

