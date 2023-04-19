from rest_framework import serializers
from .models import Cart, CartItem
from rest_framework.validators import UniqueTogetherValidator

class CartItemSerializer(serializers.ModelSerializer):

    class Meta:
        model = CartItem
        fields = "__all__"
        depth = 0
        validators = [
            UniqueTogetherValidator(
                queryset=CartItem.objects.all(),
                fields=['product', 'cart']
            )
        ]
    def to_representation(self, instance):
        self.Meta.fields = ('product', 'qty')
        self.Meta.depth = 1
        return super().to_representation(instance)


    
class CartSerializer(serializers.ModelSerializer):
    cartItems = CartItemSerializer(read_only=True, many=True)

    class Meta:
        model = Cart
        fields = '__all__'
