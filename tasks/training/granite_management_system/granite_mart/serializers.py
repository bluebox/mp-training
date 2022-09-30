from rest_framework import serializers
from .models import GraniteStore, Customer, Employee, Vehicle, Order, ContainsItem, OrderedItems, Item, Role


class storeSerializer(serializers.ModelSerializer):

    class Meta:
        model = GraniteStore
        fields = '__all__'


class custSerializer(serializers.ModelSerializer):

    class Meta:
        model = Customer
        exclude=('customer_id',)


class empSerializer(serializers.ModelSerializer):

    class Meta:
        model = Employee
        fields = '__all__'

class vehicleSerializer(serializers.ModelSerializer):

    class Meta:
        model = Vehicle
        fields = '__all__'


class orderSerializer(serializers.ModelSerializer):

    class Meta:
        model = OrderedItems
        fields = '__all__'

class itemSerializer(serializers.ModelSerializer):

    class Meta:
        model = Item
        fields = '__all__'

class roleSerializer(serializers.ModelSerializer):
    class Meta:
        model = Role
        fields = '__all__'
