from django.contrib.auth import authenticate
from django.contrib.auth.models import update_last_login
from rest_framework import serializers
from rest_framework.settings import api_settings
from rest_framework_jwt.settings import api_settings
import FoodOrder
from .models import Customer, Food, Restaurant, Employee, Menu, MenuList, OrderFood, User

JWT_PAYLOAD_HANDLER = api_settings.JWT_PAYLOAD_HANDLER
JWT_ENCODE_HANDLER = api_settings.JWT_ENCODE_HANDLER


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
        fields = (
        'user', 'restaurant_id', 'restaurant_name', 'restaurant_address1', 'restaurant_address2', 'restaurant_city',
        'restaurant_state', 'restaurant_code', 'restaurant_username', 'restaurant_phn', 'open_timing', 'close_timing',
        'restaurant_photo')


class EmployeeSerializer(serializers.ModelSerializer):
    class Meta:
        model = Employee
        fields = ('user', 'emp_id', 'emp_name', 'emp_username', 'emp_phn')


class MenuSerializer(serializers.ModelSerializer):
    class Meta:
        model = Menu
        fields = '__all__'


class MenuListSerializer(serializers.ModelSerializer):
    class Meta:
        model = MenuList
        fields = '__all__'


class CartSerializer(serializers.ModelSerializer):
    class Meta:
        model = OrderFood
        fields = '__all__'


class RestaurantRegistrationSerializer(serializers.ModelSerializer):
    profile = RestaurantSerializer(required=False)

    class Meta:
        model = User
        fields = ('email', 'password', 'profile')
        extra_kwargs = {'password': {'write_only': True}}

    def create(self, validated_data):
        profile_data = validated_data.pop('profile')
        user = User.objects.create_restaurantuser(**validated_data)
        Restaurant.objects.create(

            restaurant_id=profile_data['restaurant_id'],
            user=user,
            restaurant_name=profile_data['restaurant_name'],
            restaurant_address1=profile_data['restaurant_address1'],
            restaurant_address2=profile_data['restaurant_address2'],
            restaurant_city=profile_data['restaurant_city'],
            restaurant_state=profile_data['restaurant_state'],
            restaurant_code=profile_data['restaurant_code'],
            restaurant_username=profile_data['restaurant_username'],
            restaurant_phn=profile_data['restaurant_phn'],
            open_timing=profile_data['open_timing'],
            close_timing=profile_data['close_timing'],
            restaurant_photo=profile_data['restaurant_photo'],
        )
        return user


class CustomerRegistrationSerializer(serializers.ModelSerializer):
    profile = CustomerSerializer(required=False)

    class Meta:
        model = User
        fields = ('email', 'password', 'profile')
        extra_kwargs = {'password': {'write_only': True}}

    def create(self, validated_data):
        profile_data = validated_data.pop('profile')
        user = User.objects.create_customeruser(**validated_data)
        Customer.objects.create(

            customer_id=profile_data['customer_id'],
            user=user,
            customer_name=profile_data['customer_name'],
            customer_address1=profile_data['customer_address1'],
            customer_address2=profile_data['customer_address2'],
            customer_city=profile_data['customer_city'],
            customer_state=profile_data['customer_state'],
            customer_code=profile_data['customer_code'],
            customer_phn=profile_data['customer_phn'],
            customer_username=profile_data['customer_username'],

        )
        return user


class EmpRegistrationSerializer(serializers.ModelSerializer):
    profile = EmployeeSerializer(required=False)

    class Meta:
        model = User
        fields = ('email', 'password', 'profile')
        extra_kwargs = {'password': {'write_only': True}}

    def create(self, validated_data):
        profile_data = validated_data.pop('profile')
        user = User.objects.create_empuser(**validated_data)
        Employee.objects.create(

            emp_id=profile_data['emp_id'],
            user=user,
            emp_name=profile_data['emp_name'],
            emp_username=profile_data['emp_username'],
            emp_phn=profile_data['emp_phn'],

        )
        return user




class UserLoginSerializer(serializers.Serializer):
    email = serializers.CharField(max_length=255)
    password = serializers.CharField(max_length=128, write_only=True)
    token = serializers.CharField(max_length=255, read_only=True)

    def validate(self, data):
        email = data.get("email", None)
        password = data.get("password", None)
        user = User.objects.filter(email=email).first()
        passwordUser = user.password
        print(passwordUser)

        # print(user)
        print("password is this:")

        if user is None:
            raise serializers.ValidationError(
                'A user with this email and password is not found.'
            )
        if not user.check_password(password):
            raise serializers.ValidationError(
                'wrong password'
            )

        try:
            payload = JWT_PAYLOAD_HANDLER(user)
            jwt_token = JWT_ENCODE_HANDLER(payload)
            update_last_login(None, user)
        except User.DoesNotExist:
            raise serializers.ValidationError(
                'User with given email and password does not exists'
            )
        return {
            'email': user.email,
            'token': jwt_token
        }
