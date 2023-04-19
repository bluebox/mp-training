from .models import Order, OrderItem, ShippingAddress
from rest_framework.serializers import ModelSerializer
from rest_framework import serializers


class OrderItemSerializer(ModelSerializer):
    price = serializers.SerializerMethodField(read_only=True)
    name = serializers.SerializerMethodField(read_only=True)
    image = serializers.SerializerMethodField(read_only=True)

    class Meta:
        model = OrderItem
        fields = '__all__'

    def get_price(self, obj):
        return obj.product.price
    
    def get_name(self, obj):
        return obj.product.name
    
    def get_image(self, obj):
        return obj.product.image.url


class ShippingAddressSerializer(ModelSerializer):

    class Meta:
        model = ShippingAddress
        fields = '__all__'
        extra_kwargs = {'user': {'read_only': True}}


class OrderSerializer(ModelSerializer):
    orderItems = OrderItemSerializer(read_only=True, many=True)
    class Meta:
        model = Order
        fields = '__all__'
        depth = 1

