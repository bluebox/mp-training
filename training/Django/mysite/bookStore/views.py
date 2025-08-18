from django.contrib.auth import authenticate
from django.contrib.auth.models import User
import redis
from django.template.context_processors import request
from django.views.decorators.csrf import csrf_exempt
from rest_framework.pagination import PageNumberPagination
from rest_framework.permissions import IsAuthenticated, BasePermission, SAFE_METHODS, IsAdminUser, \
    IsAuthenticatedOrReadOnly, AllowAny
from rest_framework.response import Response
from rest_framework.decorators import api_view, permission_classes
from rest_framework import  generics
from django.shortcuts import render
from rest_framework.views import APIView
import rest_framework
from rest_framework_simplejwt.tokens import RefreshToken

from . models import *

from . serializer import  *



class customers(APIView):
    def get_permissions(self):
        if self.request.method == 'POST':
            return [AllowAny()]
        return [IsAuthenticatedOrReadOnly()]
    def get(self,request,id=None,):
        paginator = PageNumberPagination()
        if request.GET.get('username'):
            query_set = Customers.objects.get(username=request.GET.get('username'))
            serialized_query_set = CustomerSerializer(query_set)
            return Response(serialized_query_set.data)
        if id:
            query_set = Customers.objects.get(id=id)
            serialized_query_set = CustomerSerializer(query_set)
            return Response(serialized_query_set.data)

        elif request.GET.get('name'):
            query_set = Customers.objects.filter(name=request.GET.get('name'))
            serialized_query_set = CustomerSerializer(query_set, many=True)
            return Response(serialized_query_set.data)
        else:
            query_set = Customers.objects.all()
            a=paginator.paginate_queryset(query_set,request)
            serialized_query_set = CustomerSerializer(a, many=True)
            # return Response(serialized_query_set.data)

            return paginator.get_paginated_response(serialized_query_set.data)

            # query_set = Customers.objects.all()
            # serialized_query_set = CustomerSerializer(query_set, many=True)
            # return Response(serialized_query_set.data)


    def post(self,request):

        serialized_data=CustomerSerializer(data=request.data)
        if serialized_data.is_valid():

            serialized_data.save()
            return Response("SUCCuss")
        return  Response("got error!!")
    def put(self,request,id=None):
        if not id:
            return Response("ERROR!! need an id.")
        customer=Customers.objects.get(id=id)
        serializer_data=CustomerSerializer(instance=customer,data=request.data)
        if serializer_data.is_valid():
            serializer_data.save()
            return Response("SUCCUSS")
        return Response("GOT ERROR")
    def patch(self,request,id=None):
        if not id:
            return Response("ERROR!! need an id.")
        customer=Customers.objects.get(id=id)
        serializer_data=CustomerSerializer(instance=customer,data=request.data,partial=True)
        if serializer_data.is_valid():
            serializer_data.save()
            return Response("SUCCUSS")
        return Response("GOT ERROR")

    def delete(self,request,id=None):
        if id:
            customer=Customers.objects.get(id=id).delete()
            # serialized_data=CustomerSerializer(customer)
            return Response(customer)

        elif 'email' in request.data:
            customer = Customers.objects.get(email=request.data['email']).delete()
            # serialized_data = CustomerSerializer(customer)
            return Response(customer)
        elif 'name' in request.data:
            customer = Customers.objects.filter(name=request.data['name']).delete()
            # serialized_data = CustomerSerializer(customer)
            return Response(customer)
        return Response("no matching id provided")


class orders(APIView):
    permission_classes = [IsAuthenticatedOrReadOnly]

    def get(self,request,id=None):

        if request.GET.get('username'):
            query_set=(Orders.objects.select_related('customer').filter(customer__username=request.GET.get('username')))
            serialized_query_set = OrdersSerializer(query_set,many=True)
            return Response(serialized_query_set.data)

        if not id:
            query_set = Orders.objects.all().order_by('-order_date')
            serialized_query_set = OrdersSerializer(query_set, many=True)
            return Response(serialized_query_set.data)
        else:
            query_set = Orders.objects.get(id=id)
            serialized_query_set = OrdersSerializer(query_set)
            return Response(serialized_query_set.data)
    def post(self,request):
        serialized_data=OrdersSerializer(data=request.data)
        a="out"
        if serialized_data.is_valid():
            a+="before save"
            serialized_data.save()
            a+="after save"
            return Response("SUCCuss")
        return  Response(a)
    def put(self,request,id=None):
        if not id:
            return Response("ERROR!! need an id.")
        order=Orders.objects.get(id=id)
        serializer_data=OrdersSerializer(instance=order,data=request.data)
        if serializer_data.is_valid():
            serializer_data.save()
            return Response("SUCCUSS")
        return Response("GOT ERROR")
    def patch(self,request,id=None):
        if not id:
            return Response("ERROR!! need an id.")
        order=Orders.objects.get(id=id)
        serializer_data=OrdersSerializer(instance=order,data=request.data,partial=True)
        if serializer_data.is_valid():
            serializer_data.save()
            return Response("SUCCUSS")
        return Response("GOT ERROR")

    # @csrf_exempt
    def delete(self,request,id=None):
        if id:
            order=Orders.objects.get(id=id).delete()
            return Response(order)

        elif 'customer_id' in request.data:
            order = Orders.objects.get(email=request.data['customer_id']).delete()
            return Response(order)
        return Response("no matching id provided")


class books(APIView):
    permission_classes = [IsAuthenticatedOrReadOnly]
    def get(self,request,id=None):
        if request.GET.get('username'):

            query_set=Books.objects.prefetch_related('bookauthors_set').filter(bookauthors__author__name=request.GET.get('username'))

            serialized_query_set = BooksSerializer(query_set,many=True)
            return Response(serialized_query_set.data)

        if not id:
            query_set = Books.objects.all()
            serialized_query_set = BooksSerializer(query_set, many=True)
            return Response(serialized_query_set.data)
        else:
            query_set = Books.objects.get(id=id)
            serialized_query_set = BooksSerializer(query_set)
            return Response(serialized_query_set.data)

    def post(self,request):
        serialized_data=BooksSerializer(data=request.data)
        if serialized_data.is_valid():
            serialized_data.save()
            return Response("SUCCuss")
        return  Response("got error!!!")
    def put(self,request,id=None):
        if not id:
            return Response("ERROR!! need an id.")
        book=Books.objects.get(id=id)
        serializer_data=BooksSerializer(instance=book,data=request.data)
        if serializer_data.is_valid():
            serializer_data.save()
            return Response("SUCCUSS")
        return Response("GOT ERROR")
    def patch(self,request,id=None):
        if not id:
            return Response("ERROR!! need an id.")
        book=Books.objects.get(id=id)
        serializer_data=BooksSerializer(instance=book,data=request.data,partial=True)
        if serializer_data.is_valid():
            serializer_data.save()
            return Response("SUCCUSS")
        return Response("GOT ERROR")

    def delete(self,request,id=None):
        if id:
            book=Books.objects.get(id=id).delete()
            return Response(book)
        elif 'title' in request.data:
            book = Books.objects.get(title=request.data['title']).delete()
            return Response(book)

        elif 'price' in request.data:
            book = Books.objects.filter(price=request.data['price']).delete()
            return Response(book)
        return Response("no matching id provided")


# @api_view(['GET','POST','PUT','PATCH','DELETE'])
# # @permission_classes([IsAuthenticated])
# def order_items(request,id=None):
#     if request.method == 'GET':
#         if not id:
#             query_set = OrderItems.objects.all()
#             serialized_query_set = Order_itemsSerializer(query_set, many=True)
#             return Response(serialized_query_set.data)
#         else:
#             query_set = OrderItems.objects.get(id=id)
#             serialized_query_set = Order_itemsSerializer(query_set)
#             return Response(serialized_query_set.data)
#
#     elif request.method == 'POST':
#         serialized_data = Order_itemsSerializer(data=request.data)
#         if serialized_data.is_valid():
#             serialized_data.save()
#             return Response("SUCCuss")
#         return Response("got error")
#
#     elif request.method == 'PUT':
#         if not id:
#             return Response("ERROR!! need an id.")
#         order_item=OrderItems.objects.get(id=id)
#         serializer_data=Order_itemsSerializer(instance=order_item,data=request.data)
#         if serializer_data.is_valid():
#             serializer_data.save()
#             return Response("SUCCUSS")
#         return Response("GOT ERROR")
#     elif request.method == 'PATCH':
#         if not id:
#             return Response("ERROR!! need an id.")
#         order_item=OrderItems.objects.get(id=id)
#         serializer_data=Order_itemsSerializer(instance=order_item,data=request.data,partial=True)
#         if serializer_data.is_valid():
#             serializer_data.save()
#             return Response("SUCCUSS")
#         return Response("GOT ERROR")
#     elif request.method == 'DELETE':
#         if id:
#             order_item=OrderItems.objects.get(id=id).delete()
#             return Response(order_item)
#         elif 'order_id' in request.data:
#             order_item = OrderItems.objects.get(order_id=request.data['order_id']).delete()
#             return Response(order_item)
#
#         elif 'book_id' in request.data:
#             order_item = OrderItems.objects.filter(book_id=request.data['book_id']).delete()
#             return Response(order_item)
#         elif 'quantity' in request.data:
#             order_item = OrderItems.objects.filter(quantity=request.data['quantity']).delete()
#             return Response(order_item)
#         return Response("no matching id provided")

@api_view(['GET','POST','PUT','PATCH','DELETE'])
@permission_classes ([IsAuthenticatedOrReadOnly])
def authors(request,id=None):
    if request.method == 'GET':
        if request.GET.get('username'):
            query_set = Authors.objects.get(username=request.GET.get('username'))
            serialized_query_set = AuthorsSerializer(query_set)
            return Response(serialized_query_set.data)
        if not id:
            query_set = Authors.objects.all()
            serialized_query_set = AuthorsSerializer(query_set, many=True)
            return Response(serialized_query_set.data)
        else:
            query_set = Authors.objects.get(id=id)
            serialized_query_set = AuthorsSerializer(query_set)
            return Response(serialized_query_set.data)

    elif request.method == 'POST':
        serialized_data = AuthorsSerializer(data=request.data)
        if serialized_data.is_valid():
            serialized_data.save()
            return Response("SUCCuss")
        return Response("got error")

    elif request.method == 'PUT':
        if not id:
            return Response("ERROR!! need an id.")
        author=Authors.objects.get(id=id)
        serializer_data=AuthorsSerializer(instance=author,data=request.data)
        if serializer_data.is_valid():
            serializer_data.save()
            return Response("SUCCUSS")
        return Response("GOT ERROR")
    elif request.method == 'PATCH':
        if not id:
            return Response("ERROR!! need an id.")
        author=Authors.objects.get(id=id)
        serializer_data=AuthorsSerializer(instance=author,data=request.data,partial=True)
        if serializer_data.is_valid():
            serializer_data.save()
            return Response("SUCCUSS")
        return Response("GOT ERROR")
    elif request.method == 'DELETE':
        if id:
            author=Authors.objects.get(id=id).delete()
            return Response(author)
        elif 'name' in request.data:
            order_item = Authors.objects.get(name=request.data['name']).delete()
            return Response(order_item)
        return Response("no matching id provided")
    return None


class  book_authors(generics.ListCreateAPIView):
    queryset = BookAuthors.objects.all()
    serializer_class = Book_authorsSerializer
    pagination_class = rest_framework.pagination.PageNumberPagination


def index(request):
    return render(request, 'Web_World/index.html')



class getUserRole(APIView):
    permission_classes=[AllowAny]
    def get(self,request,user_name):
        if Customers.objects.filter(username=user_name).exists():
            return Response('customer')
        if Authors.objects.filter(username=user_name).exists():
            return Response('author')
        return Response('admin')


class LogInView(APIView):
    permission_classes = [AllowAny]
    def post(self,request):
        redis_client = redis.StrictRedis(host='localhost', port=6379, db=0, decode_responses=True)
        username = request.data['username']
        password = request.data['password']
        user = authenticate(username=username, password=password)
        if user:
            refresh = RefreshToken.for_user(user)
            access_token = str(refresh.access_token)
            refresh_token = str(refresh)

            redis_client.set(f"access:{user.id}", access_token, ex=300)
            redis_client.set(f"refresh:{user.id}", refresh_token, ex=86400)

            return Response("login suceess")
        return Response("failed")

class GetAccessToken(APIView):
    permission_classes = [AllowAny]

    def get(self,request,id):
        redis_client = redis.StrictRedis(host='localhost', port=6379, db=0, decode_responses=True)
        if (redis_client.get(f"access:{id}")):
            access_token=redis_client.get("access:{id}")
            return Response(access_token)

        if (redis_client.get(f"refresh:{id}")):
            refresh_token = redis_client.get("access:{id}")
            refresh = RefreshToken(refresh_token)
            new_access_token = str(refresh.access_token)
            redis_client.set(f"access:{id}", new_access_token, ex=86400)
            return Response(new_access_token)
        return Response("Session expired !!! please login again")


class CustomUser(APIView):
    permission_classes = [AllowAny]
    def get(self,request,name):
        quesry_set= Response(User.objects.get(username=name))
        serialized_quesry_set=UserSerializer(quesry_set)
        return Response(serialized_quesry_set.data)
    def patch(self, request, id=None):
        if not id:
            return Response("ERROR!! need an id.")
        customUser = User.objects.get(id=id)
        serializer_data = UserSerializer(instance=customUser, data=request.data, partial=True)
        if serializer_data.is_valid():
            serializer_data.save()
            return Response("SUCCUSS")
        return Response("GOT ERROR")



