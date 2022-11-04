from django.shortcuts import render
from rest_framework.permissions import IsAuthenticated
from rest_framework.response import Response
from rest_framework.views import APIView
from rest_framework_simplejwt.authentication import JWTAuthentication

from .models import ContainsItem, GraniteStore, Item, Cart
from .serializers import storeSerializer, itemSerializer, containsItemSerializer \
    # , containsItemSerializer, cartSerializer
from granite_mart.views import verifyToken

from customer.models import Customer


# Create your views here.


class ItemAPI(APIView):

    def get(self, request, pk=None, format=None):

        token = request.COOKIES.get('token')
        refresh = request.COOKIES.get('refresh')
        valid = verifyToken(token, refresh, request)
        if valid=='200':
            if pk is not None:
                item = ContainsItem.objects.get(contains_id=pk)
                data = []
                store = GraniteStore.objects.get(store_id=item.store_id.store_id)
                serializeStore = storeSerializer(store)
                item_data = Item.objects.get(item_id=item.item_id.item_id)
                serializeItem = itemSerializer(item_data)
                data.append({'contains_id': item.contains_id, 'price': item.price,
                             'contains_details': [serializeStore.data, serializeItem.data]})
                return Response(data)

            items = ContainsItem.objects.all()
            data = []
            for item in items:
                store = GraniteStore.objects.get(store_id=item.store_id.store_id)
                serializeStore = storeSerializer(store)
                item_data = Item.objects.get(item_id=item.item_id.item_id)
                serializeItem = itemSerializer(item_data)
                data.append({'contains_id': item.contains_id, 'price': item.price,
                             'contains_details': [serializeStore.data, serializeItem.data]})
            return Response(data)

    def post(self, request, format=None):
        token = request.COOKIES.get('token')
        refresh = request.COOKIES.get('refresh')
        valid = verifyToken(token, refresh, request)
        if valid == '200':

            contains_id = request.data["contains_id"]
            store_id = request.data["store_id"]
            item_id = request.data["item_id"]
            price = request.data["price"]
            item = ContainsItem(contains_id=contains_id, store_id=store_id, item_id=item_id, price=price)
            item.save()
            data = []
            store = GraniteStore.objects.get(store_id=item.store_id.store_id)
            serializeStore = storeSerializer(store)
            item_data = Item.objects.get(item_id=item.item_id.item_id)
            serializeItem = itemSerializer(item_data)
            data.append({'contains_id': item.contains_id, 'price': item.price,
                         'contains_details': [serializeStore.data, serializeItem.data]})
            return Response(data)

    def put(self, request, pk, format=None):
        token = request.COOKIES.get('token')
        refresh = request.COOKIES.get('refresh')
        valid = verifyToken(token, refresh, request)
        if valid == '200':

            contains_id = request.data["contains_id"]
            store_id = request.data["store_id"]
            item_id = request.data["item_id"]
            price = request.data["price"]
            item = ContainsItem(id=ContainsItem.objects.get(contains_id=pk).id, contains_id=contains_id, store_id=store_id, item_id=item_id, price=price)
            item.save()
            data = []
            store = GraniteStore.objects.get(store_id=item.store_id.store_id)
            serializeStore = storeSerializer(store)
            item_data = Item.objects.get(item_id=item.item_id.item_id)
            serializeItem = itemSerializer(item_data)
            data.append({'contains_id': item.contains_id, 'price': item.price,
                         'contains_details': [serializeStore.data, serializeItem.data]})
            return Response(data)

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
            if pk is not None:
                store = GraniteStore.objects.get(store_id=pk)
                serialize = storeSerializer(store)
                return Response(serialize.data)

            store = GraniteStore.objects.all()
            serialize = storeSerializer(store, many=True)
            return Response(serialize.data)

    def post(self, request, format=None):
        token = request.COOKIES.get('token')
        refresh = request.COOKIES.get('refresh')
        valid = verifyToken(token, refresh, request)
        if valid == '200':

            serializer = storeSerializer(data=request.data)
            if serializer.is_valid():
                store_id = request.data['store_id']
                store_name = request.data['store_name']
                established_year = request.data['established_year']
                store_description = request.data['store_description']
                contact = request.data['contact']
                website = request.data['website']
                address = request.data['address']
                store = GraniteStore(store_id=store_id, store_name=store_name, established_year=established_year,
                                     store_description=store_description,
                                     contact=contact, website=website, address=address)
                store.save()
                return Response(storeSerializer(store).data)
        return Response('failed')

    def put(self, request, pk, format=None):
        token = request.COOKIES.get('token')
        refresh = request.COOKIES.get('refresh')
        valid = verifyToken(token, refresh, request)
        if valid == '200':

            serializer = storeSerializer(data=request.data)
            if serializer.is_valid():
                store_id = request.data['store_id']
                store_name = request.data['store_name']
                established_year = request.data['established_year']
                store_description = request.data['store_description']
                contact = request.data['contact']
                website = request.data['website']
                address = request.data['address']
                store = GraniteStore(id=GraniteStore.objects.get(store_id=store_id).id, store_name=store_name,
                                     established_year=established_year, store_description=store_description,
                                     contact=contact, website=website, address=address)
                store.save()
                return Response(storeSerializer(store).data)
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


    def get(self, request, customer_id, format = None):
        token = request.COOKIES.get('token')
        refresh = request.COOKIES.get('refresh')
        valid = verifyToken(token, refresh, request)
        if valid == '200':
            items = Cart.objects.filter(customer_id=Customer.objects.get(username=customer_id))

            list=[]
            for i in items:
                print(i.item_id.item_id.item_name)
                print(i.item_id.store_id.store_name)
                print(i.item_id.price)
                print(i.item_id.contains_id)
                print(i.quantity)
                list.append({'contains_id': i.item_id.contains_id, 'item_name': i.item_id.item_id.item_name,
                            'store_name': i.item_id.store_id.store_name, 'price': i.item_id.price, 'quantity': i.quantity})

            return Response({'data': list})

    def post(self, request, format=None):
        token = request.COOKIES.get('token')
        refresh = request.COOKIES.get('refresh')
        valid = verifyToken(token, refresh, request)
        if valid == '200':
            customer_id = request.COOKIES.get('username')
            cust_id = Customer.objects.get(username=customer_id)
            item_id = ContainsItem.objects.get(contains_id=request.data)
            try:
                item = Cart.objects.get(customer_id=cust_id, item_id=item_id)
                if item is not None:
                    c = Cart(id=item.id, customer_id=cust_id,item_id=item_id, quantity=(item.quantity+1))
                    c.save()
                    print(c.quantity)
            except:
                c = Cart(customer_id=cust_id, item_id=item_id)
                c.save()
                print('error occured')
            return Response('added successfully')

    def put(self, request, format=None):
        token = request.COOKIES.get('token')
        refresh = request.COOKIES.get('refresh')
        valid = verifyToken(token, refresh, request)
        if valid == '200':
            customer_id = request.COOKIES.get('username')
            cust_id = Customer.objects.get(username=customer_id)
            item_id = ContainsItem.objects.get(contains_id=request.data['contains_id'])
            value = request.data['value']
            try:
                item = Cart.objects.get(customer_id=cust_id, item_id=item_id)
                if item is not None:
                    if item.quantity + value >0:
                        c = Cart(id=item.id, customer_id=cust_id, item_id=item_id, quantity=(item.quantity + value))
                        c.save()
                    else:
                        c = Cart(id=item.id)
                        c.delete()
                    print(c.quantity)
            except:
                print('error occured')

            return Response()

    def delete(self, request, formate=None,customer_id=None):

        token = request.COOKIES.get('token')
        refresh = request.COOKIES.get('refresh')
        valid = verifyToken(token, refresh, request)
        if valid == '200':

            print('value',customer_id)

            if customer_id == '-1':

                cartItem = Cart.objects.all()
                cartItem.delete()
                return Response('deleted all items')


            else:
                contains_id = customer_id
                print(contains_id)
                customer_id = request.COOKIES.get('username')
                cust_id = Customer.objects.get(username=customer_id)
                item_id = ContainsItem.objects.get(contains_id=int(contains_id))
                item = Cart.objects.get(customer_id=cust_id, item_id=item_id)
                item.delete()
                return Response('successfull')
            return Response('failed')


class rawItemAPI(APIView):
    def get(self, request, format=None):
        token = request.COOKIES.get('token')
        refresh = request.COOKIES.get('refresh')
        valid = verifyToken(token, refresh, request)
        if valid == '200':
            items = Item.objects.all()
            serialize = itemSerializer(items, many=True)
            return Response(serialize.data)

    def post(self, request, format=None):
        token = request.COOKIES.get('token')
        refresh = request.COOKIES.get('refresh')
        valid = verifyToken(token, refresh, request)
        if valid == '200':
            serialize = containsItemSerializer(data= request.data)
            if serialize.is_valid():
                serialize.save()
                return Response('success')
            print(serialize.errors)
            return Response('Error Occured')
