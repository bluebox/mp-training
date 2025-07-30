from .models import *
from rest_framework import serializers


class CustomerSerializer(serializers.ModelSerializer):
    class Meta:
        model=Customers
        fields='__all__'


class OrdersSerializer(serializers.ModelSerializer):
    class Meta:
        model = Orders
        fields = '__all__'


class BooksSerializer(serializers.ModelSerializer):
    class Meta:
        model = Books
        fields = '__all__'




class AuthorsSerializer(serializers.ModelSerializer):
    class Meta:
        model = Authors
        fields = '__all__'


class Book_authorsSerializer(serializers.ModelSerializer):
    class Meta:
        model = BookAuthors
        fields = '__all__'



# class CustomerWithOrdersSerializer(serializers.ModelSerializer):
#     orders = OrdersSerializer(many=True, read_only=True)
#
#     class Meta:
#         model = Customers
#         fields = ['id', 'name', 'email', 'orders']