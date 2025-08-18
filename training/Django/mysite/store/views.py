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
from django.conf import settings

from . models import *

from . serialize import  *
simple_jwt_settings=settings.SIMPLE_JWT
class CustomUserView(APIView):
    permission_classes = [AllowAny]
    def get(self,request,name=None):
        if name:
            query_set = (CustomUser.objects.get(username=name))
            serialized_query_set = CustomerUserSerializer(query_set)
            return Response(serialized_query_set.data)
        if request.GET.get('id'):
            query_set = (CustomUser.objects.get(id=request.GET.get('id')))
            serialized_query_set = CustomerUserSerializer(query_set)
            return Response(serialized_query_set.data)
        query_set = (CustomUser.objects.all())
        serialized_query_set = CustomerUserSerializer(query_set,many=True)
        return Response(serialized_query_set.data)

    def post(self,request):
        print("came into post")
        print("data--->",request.data)
        serialized=CustomerUserSerializer(data=request.data)
        print("serialized--->",serialized)
        print(serialized.is_valid())
        if serialized.is_valid():
            serialized.save()
            return Response("Success")
        return Response("fail")
        # else:
        #     print("Validation errors:", serialized.errors)  # Add this
        #     return Response(serialized.errors)
    def patch(self,request,name=None):
        if not name:
            return Response("ERROR!! need an id.")
        customUser = CustomUser.objects.get(id=name)
        serializer_data = CustomerUserSerializer(instance=customUser, data=request.data, partial=True)
        if serializer_data.is_valid():
            serializer_data.save()
            return Response("SUCCUSS")
        return Response("GOT ERROR")
    def delete(self,request,name=None):
        if name:
            user=CustomUser.objects.get(id=name).delete()
            return Response(user)

class orders(APIView):
    permission_classes = [IsAuthenticatedOrReadOnly]

    def get(self,request,id=None):
        if request.GET.get('id'):
            query_set=Orders.objects.filter(customer=request.GET.get('id'))
            serialized_query_set = OrdersSerializer(query_set,many=True)
            return Response(serialized_query_set.data)
        if request.GET.get('username'):
            # query_set=(Orders.objects.select_related('customer').filter(customer__username=request.GET.get('username')))
            query_set=Orders.objects.filter(customer=id)
            serialized_query_set = OrdersSerializer(query_set,many=True)
            return Response(serialized_query_set.data)

        if not id:
            query_set = Orders.objects.select_related('customer','book').values('id','order_date','quantity','customer__username','book__title').order_by('-order_date')
            serialized_query_set = OrdersSerializer(query_set, many=True)
            # return Response(serialized_query_set.data)
            return Response(query_set)
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
    # permission_classes=[AllowAny]
    def get(self,request,id=None):

        if request.GET.get('id'):
            print("enter to ")
            # query_set=Books.objects.prefetch_related('bookauthors_set').filter(bookauthors__author__name=request.GET.get('username'))
            query_set=Books.objects.filter(author_id=request.GET.get('id'))
            serialized_query_set = BooksSerializer(query_set,many=True)
            return Response(serialized_query_set.data)

        if not id:
            query_set = Books.objects.select_related('author').values("id","title","price","content",'author__username')
            serialized_query_set = BooksSerializer(query_set, many=True)
            # return Response(serialized_query_set.data)
            return Response(query_set)
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

class LogInView(APIView):
    permission_classes = [AllowAny]

    def post(self, request):
        redis_client = redis.StrictRedis(host='localhost', port=6379, db=0, decode_responses=True)
        username = request.data['username']
        password = request.data['password']
        user = authenticate(username=username, password=password)
        if user:
            refresh = RefreshToken.for_user(user)
            access_token = str(refresh.access_token)
            refresh_token = str(refresh)

            redis_client.set(f"access:{user.id}", access_token, ex=simple_jwt_settings['ACCESS_TOKEN_LIFETIME'])
            redis_client.set(f"refresh:{user.id}", refresh_token, ex=simple_jwt_settings['REFRESH_TOKEN_LIFETIME'])

            return Response([user.id,user.role])
        return Response("failed")
class GetAccessToken(APIView):
    permission_classes = [AllowAny]

    def get(self,request,id):
        redis_client = redis.StrictRedis(host='localhost', port=6379, db=0, decode_responses=True)
        if (redis_client.get(f"access:{id}")):
            access_token=redis_client.get(f"access:{id}")
            return Response({'access':access_token})
        refresh_token = redis_client.get(f"refresh:{id}")
        if (refresh_token):
            refresh = RefreshToken(refresh_token)
            new_access_token = str(refresh.access_token)
            redis_client.set(f"access:{id}", new_access_token, ex=300)
            return Response({'access':new_access_token})
        return Response({'access':"Session expired !!! please login again"})


@api_view(['GET'])
@permission_classes([AllowAny])
def LogOut(request,id):
    redis_client=redis.StrictRedis(host='localhost',port=6379,db=0,decode_responses=True)
    redis_client.delete(f"access:{id}")
    redis_client.delete(f"refresh:{id}")
    return Response("Log Out")



@api_view(['GET'])
@permission_classes([AllowAny])
def getCustomers(request):
    query_set=CustomUser.objects.filter(role='customer')
    serialized_query_set=CustomerUserSerializer(query_set,many=True)
    return Response(serialized_query_set.data)

@api_view(['GET'])
@permission_classes([AllowAny])
def getAuthors(request):
    query_set=CustomUser.objects.filter(role='author')
    serialized_query_set=CustomerUserSerializer(query_set,many=True)
    return Response(serialized_query_set.data)

@api_view(['GET'])
@permission_classes([AllowAny])
def getUsername(request,id):
    query_set=CustomUser.objects.get(id=id)
    return Response(query_set.username)