from itertools import count
import json
from rest_framework import serializers
from django.http.response import JsonResponse
from django.shortcuts import render, redirect
from httplib2 import Authentication
from rest_framework.exceptions import AuthenticationFailed
from rest_framework.views import APIView
from rest_framework import status
from .models import *
from django.http import HttpResponse
from rest_framework.parsers import JSONParser
from rest_framework.response import Response
from django.contrib.auth import authenticate
from django.contrib import messages
from rest_framework.decorators import api_view
from django.views.decorators.csrf import csrf_exempt
# from ecom_app.models import Customer
from ecom_app.serializers import CustomerSerializer, CustomerSerializerlogin, ProductListSerializer, ProductSerializer
from rest_framework.decorators import api_view, permission_classes
from rest_framework import permissions
from rest_framework.viewsets import ModelViewSet
from .renders import UserRenderer

# # Create your views here.
# from ..templates import *

def login(request):
    return render(request, 'login.html')


def index(request):
    return render(request, 'signup.html')


def login1(request):
    if request.method == "POST":
        x = request.POST['name']
        y = request.POST['password']
        if User.objects.filter(f_name=x).exists():
            if User.objects.get(f_name=x).password == y:
                return render(request, 'home.html')
            else:
                return HttpResponse("no output")
        else:
            return HttpResponse("Username doesn't match")
    else:
        return render(request, 'login.html')


# def login(request):
#     if request.method=='POST':
#         obj=Customer()
# def xyz(request, exception):
#     return render(request, 'login.html', status=404)


def signup(request):
    if request.method == 'POST':
        uname = request.POST['uname']
        f_name = request.POST['first_name']
        l_name = request.POST['last_name']
        mail = request.POST['email']
        password = request.POST['password']
        conf_pass = request.POST['confirm_password']
        a = request.POST['person']
        # img = request.POST['img']
        if password == conf_pass:
            if a == "buyer":
                if User.objects.filter(uname=uname).exists():
                    if User.objects.filter(mail=mail).exists():
                        messages.info(request, 'username or mail already exists')
                        return redirect('signup')
                else:
                    if User.objects.filter(mail=mail).exists():
                        messages.info(request, 'username or mail already exists')
                        return redirect('signup')
                    else:
                        x = User.objects.create(uname=uname, f_name=f_name, l_name=l_name, mail=mail,
                                                    password=password)
                        x.save()
                        return render(request, 'home.html')
            if a == "seller":
                return HttpResponse("Welcome  " + uname)
        else:
            messages.info(request, 'Password doesnot matches')
            return redirect('signup')
    else:
        return render(request, 'signup.html')


@api_view(['GET', 'POST'])
# @csrf_exempt
# @permission_classes((permissions.AllowAny,))
def customer_list(request, pk=None):
    id = pk
    """
    List all code snippets, or create a new snippet.
    """
    if request.method == 'GET':
        print(pk)
        cust = User.objects.filter(cust_id=2)
        print("customer id is: ", cust)
        serializer = CustomerSerializer(cust, many=True)
        return Response(serializer.data)

    elif request.method == 'POST':
        print(request)
        # data = JSONParser().parse(request)

        serializer = CustomerSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=201)
        return Response(serializer.errors, status=400)


class Customer1(ModelViewSet):
    x = User.objects.all()
    queryset = x
    serializer_class = CustomerSerializer


# class employeeViewModelset1(ModelViewSet):
#     queryset = employee.objects.all()
#     serializer_class = Myserializer1
    # permission_classes = [permissions.IsAuthenticatedOrReadOnly]

    # def highlight(self, request, *args, **kwargs):
    #     snippet = self.get_object()
    #     return Response(snippet.highlighted)
    #
    # def perform_create(self, serializer):
    #     serializer.save(owner=self.request.user)


class Product_list(APIView):
    def get(self, request):
        queryset = Products_Details.objects.all()
        serializer = ProductListSerializer(queryset, many=True)
        return Response(serializer.data)


# user signup
class Buyer_List(APIView):
 
    def get(self, request):
        queryset = User.objects.all()
        serializer = CustomerSerializer(queryset, many=True)
        print(serializer.data)
        return Response(serializer.data)

    def post(self, request):
        serializer = CustomerSerializer(data=request.data)
        if serializer.is_valid(raise_exception=True):
            serializer.save()
            return Response(serializer.data,status=status.HTTP_201_CREATED)
        
        return Response({'msg': "error occured"}, status=status.HTTP_400_BAD_REQUEST)


# user login
class UserLogin(APIView):
    renderer_classes=[UserRenderer]
    def post(self, request):
        username = request.data['username']
        email = request.data['email']
        password = request.data['password']
        user = authenticate(username=username,password=password)
        print(user)

        if user is not None:
            print(user, user.id)
            user_details=User.objects.get(username=username)

            serializer= CustomerSerializerlogin(user_details)


            return Response({'user_id':user_details.id},status=status.HTTP_200_OK)

        return Response({'msg': "error occured"}, status=status.HTTP_400_BAD_REQUEST)

class Productype(APIView):
    def get(self, request):
        queryset = Product_Type.objects.all()
        serializer = ProductSerializer(queryset, many=True)
        print(serializer.data)
        return Response(serializer.data)

    def post(self, request):
        serializer = ProductSerializer(data=request.data)
        serializer.is_valid(raise_exception=True)
        serializer.save()
        print(serializer.data)
        return Response(serializer.data)
