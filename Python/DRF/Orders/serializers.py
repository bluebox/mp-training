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
    class Meta:
        model=Order
        fields='__all__'


class OrderItemSerializer(serializers.ModelSerializer):
    class Meta:
        model=OrderItem
        fields="__all__"