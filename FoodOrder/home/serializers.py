from rest_framework import serializers
from .models import Customer, Food, Restaurant, Employee

class CustomerSerializer(serializers.ModelSerializer):
    class Meta:
        model = Customer
        fields = '__all__'


class FoodSerializer(serializers.ModelSerializer):
    class Meta:
        model = Food
        fields = '__all__'


class RestaurantSerializer(serializers.ModelSerializer):
    class Meta:
        model = Restaurant
        fields = ('restaurant_id','restaurant_name','restaurant_address1','restaurant_address2','restaurant_city','restaurant_state','restaurant_code','restaurant_username','restaurant_password','restaurant_phn','restaurant_email','open_timing','close_timing','restaurant_photo')



class EmployeeSerializer(serializers.ModelSerializer):
    class Meta:
        model= Employee
        fields=('emp_id','emp_name','emp_username','emp_password','emp_phn','emp_email')