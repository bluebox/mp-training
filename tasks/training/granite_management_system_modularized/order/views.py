from django.shortcuts import render
from rest_framework.permissions import IsAuthenticated
from rest_framework_simplejwt.authentication import JWTAuthentication

from rest_framework.response import Response
from rest_framework.views import APIView

from .models import OrderedItems, Order, DeliveryT
from .serializers import orderSerializer, orderedItemsSerializer, deliverySerializer
from customer.models import Customer

from item.models import ContainsItem

from granite_mart.views import verifyToken

from employee.models import Employee
from employee.models import Role
from vehicle.models import Vehicle


# Create your views here.


class OrderedItemsAPI(APIView):

    def get(self, request, pk=None, format=None):
        token = request.COOKIES.get('token')
        refresh = request.COOKIES.get('refresh')
        valid = verifyToken(token, refresh, request)
        if valid == '200':
            print('hello')
            if pk is not None:
                print(pk)
                orderobj = Order.objects.get(order_id=pk)
                order = OrderedItems.objects.filter(order_id=orderobj)
                print(order)
                serialize = orderedItemsSerializer(order, many=True)
                return Response(serialize.data)
            if request.COOKIES.get('isAdmin') == 'True':
                order = OrderedItems.objects.all()
                serialize = orderedItemsSerializer(order, many=True)
                return Response(serialize.data)

            uorders = Order.objects.filter(customer_id=Customer.objects.get(username=request.COOKIES.get('username')))
            print(uorders)
            order = OrderedItems.objects.filter(order_id__in=uorders)
            serialize = orderedItemsSerializer(order, many=True)
            return Response(serialize.data)
            # return Response(['not reached'])

    def post(self, request, format=None):
        token = request.COOKIES.get('token')
        refresh = request.COOKIES.get('refresh')
        valid = verifyToken(token, refresh, request)
        if valid == '200':
        # print(request.data,'data received')
            try:
                customer = Customer.objects.get(username=request.COOKIES.get('username'))
                order_time = request.data['order_time']
                deliveryAddress = request.data['deliveryAddress']
                item_ids = request.data['contains_ids']
                orderid = Order.objects.all().last().order_id + 1
                order = Order(order_id=orderid, customer_id=customer, order_time=order_time,
                              delivery_address=deliveryAddress)
                order.save()
                driver = Employee.objects.filter(role_id=Role.objects.get(roll_name='driver'))
                vehicle = Vehicle.objects.filter(finished=True)
                delivery = DeliveryT(order_id=orderid, driver_id=driver[0], vehicle_id=vehicle[0])
                delivery.save()
                print(orderid, customer, order_time, deliveryAddress, item_ids)

                for id in item_ids:
                    item = ContainsItem.objects.get(contains_id=id)
                    orderitem = OrderedItems(order_id=order, contains_id=item)
                    orderitem.save()
                return Response('order placed successfully')

            except:
                return Response('error occured')
            return Response()


class DeliveryAPI(APIView):

    def get(self, request, formate=None, order_id=None):
        details = DeliveryT.objects.get(order_id=Order.objects.get(order_id=order_id))
        serializer = deliverySerializer(details)
        return Response(serializer.data)

