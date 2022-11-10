from rest_framework import serializers
from .models import *
from django.contrib.auth.models import User

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
        fields = '__all__'
        # exclude = ('image',)
#
# class VehicleStatusSerializer(serializers.ModelSerializer):
#     class Meta:
#         model = Vehicle_status
#         fields = '__all__'

class Rent_TripDetailSerializer(serializers.ModelSerializer):
    class Meta:
        model = RentTrip
        fields = '__all__'

class Rent_TripSerializer(serializers.ModelSerializer):
    customer_id = CustomerSerializer()
    class Meta:
        model = RentTrip
        fields = '__all__'


class BillSerializer(serializers.ModelSerializer):
    class Meta:
        model = Bill
        fields = '__all__'

class User_Serializer(serializers.ModelSerializer):
    class Meta:
        model = User
        fields = ('username','password','is_active','is_staff','is_superuser')
