from rest_framework import serializers
from django.contrib.auth.models import User
from user.serializers import UserSerializer
from .models import *
from rest_framework_simplejwt.tokens import RefreshToken
from review.serializers import ReviewSerializer

class BookingAddressSerializer(serializers.ModelSerializer):
    class Meta:
        model = BookingAddress
        fields = '__all__'

class BookingItemSerializer(serializers.ModelSerializer):
    class Meta:
        model = BookingItem
        fields = '__all__'

class BookingSerializer(serializers.ModelSerializer):
    bookingItems = serializers.SerializerMethodField(read_only=True)
    bookingAddress = serializers.SerializerMethodField(read_only=True)
    user = serializers.SerializerMethodField(read_only=True)

    class Meta:
        model = Booking
        fields = '__all__'

    def get_bookingItems(self, obj):
        items = obj.bookingItem.all()
        serializer = BookingItemSerializer(items, many=True)
        return serializer.data
    
    def get_bookingAddress(self, obj):
        try:
            address = BookingAddressSerializer(obj.bookingAddress, many=False)
        except:
            address = False
        return address

    def get_user(self, obj):
        user = obj.user
        serializer = UserSerializer(user, many=False)
        return serializer.data
