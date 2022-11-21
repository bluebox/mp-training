from rest_framework import serializers

from .models import OrderedItems, Order, itemsDelivery
from item.serializers import containsItemSerializer
from customer.serializers import customerSerializer
from vehicle.serializers import vehicleSerializer

from employee.serializers import employeeSerializer


class orderSerializer(serializers.ModelSerializer):
    customer_id = customerSerializer()
    class Meta:
        model = Order
        fields = '__all__'

class orderedItemsSerializer(serializers.ModelSerializer):
    order_id = orderSerializer()
    contains_id = containsItemSerializer()
    class Meta:
        model = OrderedItems
        fields = '__all__'


class deliverySerializer(serializers.ModelSerializer):
    order_id = orderSerializer()
    vehicle_id = vehicleSerializer()
    driver_id = employeeSerializer()
    class Meta:
        model = itemsDelivery
        fields = '__all__'

