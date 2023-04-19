from django.shortcuts import render

# Create your views here.
from rest_framework.decorators import api_view, permission_classes
from rest_framework.permissions import IsAuthenticated, IsAdminUser
from rest_framework.response import Response
from datetime import datetime
from base.models import Product
from .models import OrderItem, Order, ShippingAddress
from cart.models import CartItem
from order.serializers import OrderSerializer, ShippingAddressSerializer
from order.permissions import IsOrderUser
from rest_framework import status

@api_view(['POST',])
@permission_classes([IsAuthenticated,])
def addOrderItems(request):
    user = request.user
    data = request.data

    orderItems = data['orderItems']
    
    if orderItems and len(orderItems) == 0:
        return Response({'detail': 'No Order Items'}, status=status.HTTP_400_BAD_REQUEST)

    else:
        order = Order.objects.create(
            shippingAddress_id=data['shippingAddress'],
            user=user,
            paymentMethod=data['paymentMethod'],
            taxPrice=data['taxPrice'],
            shippingPrice=data['shippingPrice'],
            totalPrice=data['totalPrice']
        )

        for i in orderItems:
            product = Product.objects.get(pk=i['product']['id'])
            item = OrderItem.objects.create(
                product=product,
                order=order,
                qty=i['qty']
            )
            product.countInStock -= item.qty
            product.save()
        CartItem.objects.filter(cart__user=user).delete()
        serializer = OrderSerializer(order)
        return Response(serializer.data)
    

@api_view(['GET'])
@permission_classes([IsAuthenticated])
def getMyOrders(request):
    user = request.user
    orders = user.order_set.all()
    serializer = OrderSerializer(orders, many=True)
    return Response(serializer.data)


@api_view(['GET'])
@permission_classes([IsAdminUser])
def getOrders(request):
    orders = Order.objects.all()
    serializer = OrderSerializer(orders, many=True)
    return Response(serializer.data)

@api_view(['GET'])
@permission_classes([IsAuthenticated])
def getOrderById(request, pk):
    user = request.user
    try:
        order = Order.objects.get(pk=pk)
        if user == order.user or user.is_staff:
            serializer = OrderSerializer(order)
            return Response(serializer.data)
        else:
            return Response({'detail': 'Not authorized to view this order'}, status=status.HTTP_401_UNAUTHORIZED)
    except:
        return Response({'detail': 'Order does not exist'}, status=status.HTTP_400_BAD_REQUEST)


@api_view(['PUT'])
@permission_classes([IsAuthenticated])
def updateOrderToPaid(request, pk):
    order = Order.objects.get(pk=pk)
    order.isPaid = True
    order.paidAt = datetime.now()
    order.save()
    return Response('Order was paid')


@api_view(['PUT'])
@permission_classes([IsAdminUser])
def updateOrderToDelivered(request, pk):
    order = Order.objects.get(pk=pk)
    order.isDelivered = True
    order.deliveredAt = datetime.now()
    order.save()
    return Response('Order was delivered')


@api_view(['POST'])
@permission_classes([IsAuthenticated])
def saveShippingAddress(request):
    if ShippingAddress.objects.filter(user=request.user).exists():
        shippingAddress = ShippingAddress.objects.get(user=request.user)
        serializer = ShippingAddressSerializer(shippingAddress, data=request.data)
    else:
        serializer = ShippingAddressSerializer(data=request.data)
    if serializer.is_valid():
        serializer.validated_data['user'] = request.user
        serializer.save()
        return Response(serializer.data)
    return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)


@api_view(['PUT'])
@permission_classes([IsAuthenticated])
def editShippingAddress(request):
    shippingAddress = ShippingAddress.objects.get(user=request.user)
    serializer = ShippingAddressSerializer(shippingAddress, data=request.data)
    if serializer.is_valid():
        serializer.validated_data['user'] = request.user
        serializer.save()
        return Response(serializer.data)
    return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)