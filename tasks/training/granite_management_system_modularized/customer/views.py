import requests
from django.contrib.auth.hashers import make_password
from django.contrib.auth.models import User
from rest_framework import status
from rest_framework.authentication import BasicAuthentication
from rest_framework.permissions import IsAuthenticated, AllowAny
from rest_framework.response import Response
from rest_framework.views import APIView
from rest_framework_simplejwt.authentication import JWTAuthentication

from .models import Customer
from .serializers import customerSerializer
from granite_mart.views import verifyToken


# Create your views here.

class CustomerAPI(APIView):

    def get(self, request, username=None, format=None):
        token = request.COOKIES.get('token')
        refresh = request.COOKIES.get('refresh')
        valid = verifyToken(token, refresh, request)
        if  valid == '200':
            user = User.objects.get(username=username)
            if not user.is_superuser:
                customer = Customer.objects.get(username=username)
                serialize = customerSerializer(customer)
                return Response(serialize.data)
            if user.is_superuser:
                cust = Customer.objects.all()
                serialize = customerSerializer(cust, many=True)
                return Response(serialize.data, status=status.HTTP_200_OK)
        else:
            raise Exception('user is not authorized')


    def post(self, request, format=None):

        # cust_id = User.objects.create_user(username=request.data['username'], email=request.data['email'],
        #                                    password=make_password(request.data['password']))
        cust_id = User.objects.create_user(username=request.data['username'], email=request.data['email'],
                                           password=(request.data['password']))
        cust_id.save()
        request.data['customer_id'] = cust_id.id  # user id
        uname = cust_id.username
        # user
        serializer = customerSerializer(data=request.data)
        if serializer.is_valid(raise_exception=True):
            customer_id = request.data['customer_id']
            password = request.data['password']

            fullname = request.data['customer_name']
            phone = request.data['phone']
            email = request.data['email']
            address = request.data['address']
            cust = Customer(customer_id=cust_id, password=password,
                            customer_name=fullname, phone=phone, username=uname, email=email, address=address)
            cust.save()
            print(cust)
            serialize = customerSerializer(cust)
            return Response(serialize.data)
        return Response()

    def put(self, request, username=None, format=None):
        token = request.COOKIES.get('token')
        refresh = request.COOKIES.get('refresh')
        valid = verifyToken(token, refresh, request)
        if valid == '200':
            password = request.data['password']
            fullname = request.data['customer_name']
            phone = request.data['phone']
            email = request.data['email']
            address = request.data['address']
            userid = User.objects.get(username=username)
            user = User(id=userid.id, username=username, password=make_password(password))
            user.save()
            cust = Customer(id=Customer.objects.get(customer_id=user).id, customer_id=user, password=password,
                            customer_name=fullname, phone=phone, username=username, email=email, address=address)
            cust.save()
            serialize = customerSerializer(cust)
            return Response(serialize.data)

    def delete(self,request,formate=None, username=None):
        token = request.COOKIES.get('token')
        refresh = request.COOKIES.get('refresh')
        valid = verifyToken(token, refresh, request)

        if valid == '200':
            user = User.objects.get(username=username)
            user.delete()
            return Response('deleted')