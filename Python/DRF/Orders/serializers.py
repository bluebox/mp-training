from django.contrib.auth.models import User
from rest_framework import serializers

from .models import Product, Customer, Order, OrderItem


class ProductSerializer(serializers.ModelSerializer):
    class Meta:
        model=Product
        fields='__all__'


class CustomerSerializer(serializers.ModelSerializer):
    class Meta:
        model=Customer
        fields='__all__'

class OrderSerializer(serializers.ModelSerializer):
    customer=CustomerSerializer()
    class Meta:
        model=Order
        fields='__all__'


class OrderItemSerializer(serializers.ModelSerializer):
    class Meta:
        model=OrderItem
        fields="__all__"


class UserSerializer(serializers.ModelSerializer):
    password = serializers.CharField(write_only=True)

    class Meta:
        model = User
        fields = ['id', 'username', 'email', 'password']

    def create(self, validated_data):
        user = User.objects.create_user(
            username=validated_data['username'],
            email=validated_data['email'],
            password=validated_data['password']
        )
        #user=User.objects.create_user(**validate)
        return user
