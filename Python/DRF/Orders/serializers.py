from django.contrib.auth.models import User
from rest_framework import serializers
from rest_framework.serializers import ModelSerializer

from .models import Product, Customer, Order, OrderItem, Product_review


class ProductSerializer(serializers.ModelSerializer):
    class Meta:
        model=Product
        fields='__all__'

    def validate_price(self,value):
        if value<=0:
            raise serializers.ValidationError('enter a valid price >0')
        return value

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

    def validate_email(self,value):
        if not value.endswith('@example.com'):
            raise serializers.ValidationError('email with domain @example.com is only allowed')
# class StudentSerializer(serializers.ModelSerializer):
#     class Meta:
#         model=Student
#         fields='__all__'
#
#     def save(self):
#         if self.validated_data['gender'] not in ['female','male','other']:
#             raise serializers.ValidationError('this gender is not in the option')
#         return super().save()
#       def validate_email(self,value):
#           if not value.endwith("@example.com")
#               raise serializers.ValidationError('email must end with @example.com')
#         return value


class ReviewSerializers(serializers.ModelSerializer):
    user = UserSerializer(read_only=True)
    user_id = serializers.PrimaryKeyRelatedField(
        queryset=User.objects.all(), write_only=True
    )
    # this is to post the data into the Product_reviews
    # because user=UserSerializer is not working to post the data
    class Meta:
        model = Product_review
        fields = ['id', 'user', 'user_id', 'rating']

    def create(self, validated_data):
        validated_data['user'] = validated_data.pop('user_id')
        return super().create(validated_data)