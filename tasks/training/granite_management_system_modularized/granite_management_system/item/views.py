from rest_framework.response import Response
from rest_framework.views import APIView

from .businessLogic.manager import getItems, addItems, updateItem, getStore, addStore, updateStore, getCart, addToCart, \
    updateCart, deleteCart
from .models import ContainsItem, GraniteStore, Item, Cart
from .serializers import storeSerializer, itemSerializer, containsItemSerializer, cartSerializer
from granite_mart.views import verifyToken

from customer.models import Customer


class ItemAPI(APIView):

    def get(self, request, pk=None, format=None):
        print(" username : ",request.user)

        token = request.COOKIES.get('token')
        refresh = request.COOKIES.get('refresh')
        valid = verifyToken(token, refresh, request)

        if valid == '200':
            response = getItems(request, pk)
            return Response(response)
        else:
            return Response('authentication failed')

    def post(self, request, format=None):
        token = request.COOKIES.get('token')
        refresh = request.COOKIES.get('refresh')
        valid = verifyToken(token, refresh, request)

        if valid == '200':
            response = addItems(request)
            return Response(response)
        else:
            return Response('authentication failed')

    def put(self, request, pk, format=None):
        token = request.COOKIES.get('token')
        refresh = request.COOKIES.get('refresh')
        valid = verifyToken(token, refresh, request)

        if valid == '200':
            response = updateItem(request, pk)
            return Response(response)
        else:
            return Response('authentication failed')

    def delete(self, request, pk, format=None):
        token = request.COOKIES.get('token')
        refresh = request.COOKIES.get('refresh')
        valid = verifyToken(token, refresh, request)

        if valid == '200':

            item = ContainsItem.objects.get(contains_id=pk)
            item.delete()
            return Response([item])


class GraniteStoreAPI(APIView):

    def get(self, request, pk=None, format=None):
        token = request.COOKIES.get('token')
        refresh = request.COOKIES.get('refresh')
        valid = verifyToken(token, refresh, request)

        if valid == '200':
            response = getStore(request, pk)
            return Response(response)
        else:
            return Response("authentication failed")

    def post(self, request, format=None):
        token = request.COOKIES.get('token')
        refresh = request.COOKIES.get('refresh')
        valid = verifyToken(token, refresh, request)

        if valid == '200':
            response = addStore(request)
            return Response(response)
        return Response('Authentication failed')

    def put(self, request, pk, format=None):
        token = request.COOKIES.get('token')
        refresh = request.COOKIES.get('refresh')
        valid = verifyToken(token, refresh, request)

        if valid == '200':
            response = updateStore(request, pk)
            return Response(response)
        return Response('failed')

    def delete(self, request, pk, format=None):
        token = request.COOKIES.get('token')
        refresh = request.COOKIES.get('refresh')
        valid = verifyToken(token, refresh, request)

        if valid == '200':

            store = GraniteStore.objects.get(employee_id=pk)
            store.delete()
            return Response('deleted')


class CartAPI(APIView):

    def get(self, request, customer_id, format = None):sdef
        token = request.COOKIES.get('token')
        refresh = request.COOKIES.get('refresh')
        valid = verifyToken(token, refresh, request)

        if valid == '200':
            items = Cart.objects.filter(customer_id=Customer.objects.get(username=customer_id))
            serialize = cartSerializer(items, many=True)
            return Response(serialize.data)
        else:
            return Response('Authentication failed')

    def post(self, request, format=None):
        token = request.COOKIES.get('token')
        refresh = request.COOKIES.get('refresh')
        valid = verifyToken(token, refresh, request)

        if valid == '200':
            response = addToCart(request)
            return Response(response)
        else:
            return Response('Authentication failed')

    def put(self, request, format=None):
        token = request.COOKIES.get('token')
        refresh = request.COOKIES.get('refresh')
        valid = verifyToken(token, refresh, request)

        if valid == '200':
            response = updateCart(request)
            return Response(response)
        return Response("Authentication failed")

    def delete(self, request, formate=None,customer_id=None):

        token = request.COOKIES.get('token')
        refresh = request.COOKIES.get('refresh')
        valid = verifyToken(token, refresh, request)

        if valid == '200':
            response = deleteCart(request, customer_id)
            return Response(response)
        return Response('authentication failed')

class rawItemAPI(APIView):
    def get(self, request, format=None):
        token = request.COOKIES.get('token')
        refresh = request.COOKIES.get('refresh')
        valid = verifyToken(token, refresh, request)

        if valid == '200':
            items = Item.objects.all()
            serialize = itemSerializer(items, many=True)
            return Response(serialize.data)
        return Response("Authentication failed")

    def post(self, request, format=None):
        token = request.COOKIES.get('token')
        refresh = request.COOKIES.get('refresh')
        valid = verifyToken(token, refresh, request)

        if valid == '200':
            serialize = containsItemSerializer(data=request.data)
            if serialize.is_valid():
                serialize.save()
                return Response('success')
            print(serialize.errors)
            return Response('Error Occured')
        return Response("Authentication failed")

