from rest_framework import serializers

from .models import Item, GraniteStore, ContainsItem, Cart
from customer.models import Customer
class customerSerializer(serializers.ModelSerializer):

    class Meta:
        model = Customer
        fields = "__all__"

class storeSerializer(serializers.ModelSerializer):

    class Meta:
        model = GraniteStore
        fields = '__all__'

class itemSerializer(serializers.ModelSerializer):

    class Meta:
        model = Item
        fields = '__all__'

class containsItemSerializer(serializers.ModelSerializer):
    store_id = storeSerializer()
    item_id = itemSerializer()

    class Meta:
        model = ContainsItem
        fields = '__all__'

class cartSerializer(serializers.ModelSerializer):
    customer_id = customerSerializer()
    item_id = containsItemSerializer()

    class Meta:
        model = Cart
        fields = "__all__"
