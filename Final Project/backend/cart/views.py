from rest_framework.response import Response
from rest_framework import status, permissions
from rest_framework.decorators import api_view, permission_classes
from .models import Cart, CartItem
from base.models import Product
from .serializers import CartSerializer, CartItemSerializer
# Create your views here.

@api_view(['GET',])
@permission_classes([permissions.IsAuthenticated])
def getCartItems(request):
    user = request.user
    if not Cart.objects.filter(user=user).exists():
        Cart.objects.create(user=user)
    cart = Cart.objects.get(user=user)
    serializer = CartSerializer(cart)
    return Response(serializer.data)

@api_view(['POST',])
@permission_classes([permissions.IsAuthenticated])
def addToCart(request):
    try:
        cart, created = Cart.objects.get_or_create(user=request.user)
        product = Product.objects.get(id=request.data['product'])
        if CartItem.objects.filter(cart=cart, product=product).exists():
            cartItem = CartItem.objects.get(cart=cart, product=product)
            cartItem.qty = request.data['qty']
            cartItem.save()
        else:
            cartItem = CartItem.objects.create(cart=cart, product=product, qty=request.data['qty'])
        serializer = CartItemSerializer(cartItem, many=False)
        return Response(serializer.data)
    except Product.DoesNotExist:
        return Response({'detail': 'Product not found'}, status=status.HTTP_400_BAD_REQUEST)
    
@api_view(['PUT',])
@permission_classes([permissions.IsAuthenticated])
def updateCart(request, product):
    try:
        cartItem = CartItem.objects.get(cart__user = request.user.id, product_id = product)
        cartItem.qty = request.data['qty']
        cartItem.save()
        serializer = CartItemSerializer(cartItem, many=False)
        return Response(serializer.data)
    except Exception as e:
        return Response({'detail': 'cartItem not found'}, status=status.HTTP_400_BAD_REQUEST)

@api_view(['DELETE'])
@permission_classes([permissions.IsAuthenticated])
def removeItem(request, product):
    try:
        cartItem = CartItem.objects.get(cart__user=request.user.id, product_id=product)
        cartItem.delete()
        return Response(status=status.HTTP_204_NO_CONTENT)
    except:
        return Response({'detail': 'cartItem not found'}, status=status.HTTP_400_BAD_REQUEST)
