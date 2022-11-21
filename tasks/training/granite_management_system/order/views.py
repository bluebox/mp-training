from rest_framework.response import Response
from rest_framework.views import APIView

from .businessLogic.manager import getOrder, addOrder
from .models import Order, itemsDelivery
from .serializers import deliverySerializer

from granite_mart.views import verifyToken

class OrderedItemsAPI(APIView):

    def get(self, request, pk=None, format=None):
        token = request.COOKIES.get('token')
        refresh = request.COOKIES.get('refresh')
        valid = verifyToken(token, refresh, request)

        if valid == '200':
            response = getOrder(request, pk)
            return Response(response)
        else:
            return Response("Authentication Failed")

    def post(self, request, format=None):
        token = request.COOKIES.get('token')
        refresh = request.COOKIES.get('refresh')
        valid = verifyToken(token, refresh, request)

        if valid == '200':
            response = addOrder(request)
            return Response(response)
        return Response("Authentication Failed")

class DeliveryAPI(APIView):

    def get(self, request, formate=None, order_id=None):
        token = request.COOKIES.get('token')
        refresh = request.COOKIES.get('refresh')
        valid = verifyToken(token, refresh, request)

        if valid == '200':
            details = itemsDelivery.objects.get(order_id=Order.objects.get(order_id=order_id))
            serializer = deliverySerializer(details)
            return Response(serializer.data)


