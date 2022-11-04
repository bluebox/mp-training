from rest_framework import serializers

from .models import Item, GraniteStore, ContainsItem, Cart


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

    class Meta:
        model = Cart
        fields = "__all__"