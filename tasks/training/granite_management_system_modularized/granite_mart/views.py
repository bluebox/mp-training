# from rest_framework_simplejwt.views import (
#     TokenObtainPairView,
#     TokenRefreshView,
# )
import json

import requests
from django.contrib.auth.models import User
from django.views.decorators.csrf import csrf_exempt
from rest_framework.authentication import BasicAuthentication
from rest_framework.decorators import api_view
from rest_framework.parsers import JSONParser
from rest_framework.permissions import AllowAny
from rest_framework.response import Response
from rest_framework.views import APIView

from .models import *
from django.contrib.auth.forms import UserCreationForm
from django.contrib.auth import authenticate, login, logout
from django.contrib.auth.hashers import make_password
import datetime
#
from .serializers import roleSerializer
# storeSerializer, custSerializer, empSerializer, vehicleSerializer, orderSerializer, \
# #     itemSerializer,
#
# # Create your views here.
#
# def home(request):
#     return render(request, 'home.html')
#
# def custregistrationPage(request):
#
#     form = UserCreationForm()
#     context = {'form':form}
#
#     return render(request, 'registerUser.html', context)
#
#
# @api_view(['POST'])
# def registerCustomer(request):
#     if request.method == 'POST':
#         cust_id=User.objects.create_user(username=request.data['customer_id'], email=request.data['email'],password=make_password(request.data['password']))
#         cust_id.save()
#         request.data['customer_id']=cust_id.id  #user id
#         uname=cust_id.username
#         # user
#         serializer = custSerializer(data=request.data)
#         if serializer.is_valid(raise_exception=True):
#             customer_id = request.data['customer_id']
#             password = request.data['password']
#
#             fullname = request.data['customer_name']
#             phone = request.data['phone']
#             email = request.data['email']
#             address = request.data['address']
#             cust = Customer(customer_id=customer_id, password=password,
#                         customer_name=fullname, phone=phone, username=uname, email=email, address=address)
#             cust.save()
#             print(cust)
#             serialize = custSerializer(cust)
#             return Response(serialize.data)
#     return Response('failed')
#
#
# def custloginPage(request):
#     context = {}
#     return render(request, 'userlogin.html', context)
#


#
@api_view(['GET'])
def getRoles(request):
    roles = Role.objects.all()
    serialize = roleSerializer(roles, many=True)
    return Response(serialize.data)
#
# @api_view(['POST'])
# def registerEmployee(request):
#     if request.method == 'POST':
#         serializer = empSerializer(data=request.data)
#         print(serializer.is_valid())
#         print(serializer.errors)
#         employee_id = request.data['employee_id']
#         employee_name = request.data['employee_name']
#         doj = request.data['doj']
#         role = request.data['role']
#         salary = request.data['salary']
#         phone = request.data['phone']
#         email = request.data['email']
#         address = request.data['address']
#         emp = Employee(employee_id=employee_id, employee_name=employee_name, doj=doj,
#                            role_id=Role.objects.get(role_id=role), salary=salary, phone=phone, email=email, address=address)
#         emp.save()
#         serialize = empSerializer(emp)
#         return Response(serialize.data)
#     return Response('failed')
#
#
# @api_view(['POST'])
# def updateEmployee(request, employee_id):
#     if request.method == 'POST':
#         serializer = empSerializer(data=request.data)
#         print(serializer.is_valid())
#         print(serializer.errors)
#         employee_name = request.data['employee_name']
#         doj = request.data['doj']
#         role = request.data['role']
#         salary = request.data['salary']
#         phone = request.data['phone']
#         email = request.data['email']
#         address = request.data['address']
#         emp = Employee(employee_id=employee_id, employee_name=employee_name, doj=doj,
#                            role_id=Role.objects.get(role_name=role), salary=salary, phone=phone, email=email, address=address)
#         emp.save()
#         serialize = empSerializer(emp)
#         return Response(serialize.data)
#     return Response('failed')
#
#
# @api_view(['POST'])
# def deleteEmployee(request, employee_id):
#     emp = Employee.objects.get(employee_id=employee_id)
#     emp.delete()
#     return Response('deleted')
#
#
# @api_view(['GET'])
# def viewStores(request):
#     store = GraniteStore.objects.all()
#     serialize = storeSerializer(store, many=True)
#     return Response(serialize.data)
#
# @api_view(['GET'])
# def viewStore(request,store_id):
#     store = GraniteStore.objects.get(store_id=store_id)
#     serialize = storeSerializer(store)
#     return Response(serialize.data)
#
# @api_view(['POST'])
# def registerStore(request):
#     serializer = storeSerializer(data=request.data)
#     if serializer.is_valid():
#         store_id = request.data['store_id']
#         store_name = request.data['store_name']
#         established_year = request.data['established_year']
#         store_description = request.data['store_description']
#         contact = request.data['contact']
#         website = request.data['website']
#         address = request.data['address']
#         store = GraniteStore(store_id=store_id, store_name=store_name, established_year=established_year, store_description=store_description,
#                              contact=contact, website=website, address=address)
#         store.save()
#         return Response(storeSerializer(store).data)
#     return Response('failed')
#
#
# @api_view(['POST'])
# def updateStore(request):
#     serializer = storeSerializer(data=request.data)
#     if serializer.is_valid():
#         store_id = request.data['store_id']
#         store_name = request.data['store_name']
#         established_year = request.data['established_year']
#         store_description = request.data['store_description']
#         contact = request.data['contact']
#         website = request.data['website']
#         address = request.data['address']
#         store = GraniteStore(id=GraniteStore.objects.get(store_id=store_id).id, store_name=store_name, established_year=established_year, store_description=store_description,
#                              contact=contact, website=website, address=address)
#         store.save()
#         return Response(storeSerializer(store).data)
#     return Response('failed')
#
#
# @api_view(['POST'])
# def deleteStore(request, store_id):
#     store = GraniteStore.objects.get(employee_id=store_id)
#     store.delete()
#     return Response('deleted')
#
#
# @api_view(['GET'])
# def viewVehicles(request):
#     vehicle = Vehicle.objects.all()
#     serialize = vehicleSerializer(vehicle, many=True)
#     return Response(serialize.data)
#
#
# @api_view(['GET'])
# def viewVehicle(request,vehicle_no):
#     vehicle = Vehicle.objects.get(vehicle_no=vehicle_no)
#     serialize = vehicleSerializer(vehicle)
#     return Response(serialize.data)
#
#
# @api_view(['POST'])
# def registerVehicle(request):
#     serializer=vehicleSerializer(data=request.data)
#     if serializer.is_valid():
#         vehicle_no = request.data['vehicle_no']
#         model = request.data['model']
#         owner_name = request.data['owner_name']
#         permit_range = request.data['permit_range']
#         fuel_efficiency = request.data['fuel_efficiency']
#         load_capacity = request.data['load_capacity']
#         vehicle = Vehicle(vehicle_no=vehicle_no, model=model, owner_name=owner_name, permit_range=permit_range,
#                           fuel_efficiency=fuel_efficiency, load_capacity=load_capacity)
#         vehicle.save()
#         return Response(vehicleSerializer(vehicle).data)
#     return Response('failed')
#
#
# @api_view(['POST'])
# def updateVehicle(request):
#     serializer=vehicleSerializer(data=request.data)
#     if serializer.is_valid():
#         vehicle_no = request.data['vehicle_no']
#         model = request.data['model']
#         owner_name = request.data['owner_name']
#         permit_range = request.data['permit_range']
#         fuel_efficiency = request.data['fuel_efficiency']
#         load_capacity = request.data['load_capacity']
#         vehicle = Vehicle(id=Vehicle.objects.get(vehicle_no=vehicle_no).id, model=model, owner_name=owner_name, permit_range=permit_range,
#                           fuel_efficiency=fuel_efficiency, load_capacity=load_capacity)
#         vehicle.save()
#         return Response(vehicleSerializer(vehicle).data)
#     return Response('failed')
#
#
#
# @api_view(['POST'])
# def deleteVehicle(request,vehicle_no):
#     vehicle = Vehicle.objects.get(vehicle_no=vehicle_no)
#     vehicle.delete()
#     return Response(vehicleSerializer(vehicle).data)
#
#
#
# @api_view(['GET'])
# def viewOrders(request):
#
#     orders = OrderedItems.objects.all()
#     data = []
#
#     for order in orders:
#         serializeOrder = orderSerializer(order)
#         customers = order.order_id.customer_id
#         serializeCustomer = custSerializer(customers)
#         item = Item.objects.get(item_id=order.contains_id.item_id.item_id)
#         serializeItem = itemSerializer(item)
#         data.append({order.order_id.order_id: [serializeCustomer.data, serializeItem.data]})
#
#     return Response(data)
#
#
# @api_view(['GET'])
# def viewOrder(request, order_id):
#
#     order = OrderedItems.objects.get(order_id=order_id)
#     data = []
#     customers = order.order_id.customer_id
#     serializeCustomer = custSerializer(customers)
#     item = Item.objects.get(item_id=order.contains_id.item_id.item_id)
#     serializeItem = itemSerializer(item)
#     data.append({order.order_id.order_id: [serializeCustomer.data, serializeItem.data]})
#
#     return Response(data)
#
#
# @api_view(['POST'])
# def addOrder(request):
#     return Response("data")
#
#
# @api_view(['GET'])
# def viewItems(request):
#
#     items = ContainsItem.objects.all()
#     data=[]
#     for item in items:
#         store = GraniteStore.objects.get(store_id=item.store_id.store_id)
#         serializeStore = storeSerializer(store)
#         item_data = Item.objects.get(item_id=item.item_id.item_id)
#         serializeItem = itemSerializer(item_data)
#         data.append({'contains_id':item.contains_id,'price': item.price,
#                      'contains_details': [serializeStore.data, serializeItem.data]})
#     return Response(data)
#
#
# @api_view(['GET'])
# def viewItem(request, contains_id):
#
#     item = ContainsItem.objects.get(contains_id=contains_id)
#     data = []
#     store = GraniteStore.objects.get(store_id=item.store_id.store_id)
#     serializeStore = storeSerializer(store)
#     item_data = Item.objects.get(item_id=item.item_id.item_id)
#     serializeItem = itemSerializer(item_data)
#     data.append({'contains_id':item.contains_id,'price': item.price,
#                      'contains_details': [serializeStore.data, serializeItem.data]})
#     return Response(data)
#

def verifyToken(token, refresh, request):
    verify = requests.post('http://127.0.0.1:8000/granite_mart/api/token/verify/', {'token': token})
    if verify.status_code != 200:
        refreshed = requests.post('http://127.0.0.1:8000/granite_mart/api/token/refresh/', {'refresh': refresh})
        if refreshed.status_code == 200:
            response = Response()
            data = json.loads(refreshed.text)
            response.set_cookie('token', data['access'], httponly=True)
            verify.status_code = 200

    return str(verify.status_code)

class customerLogin(APIView):
    def post(self, request, format=None):
        if request.method == 'POST':
            uname = request.data['username']
            upass = request.data['password']
            user1 = User.objects.get(username=uname)
            print(user1,user1.password)

            user = authenticate(username=uname, password=upass)
            print(uname, upass)
            print(user)
            if user1:
                isAdmin = User.objects.get(username=uname).is_superuser
                token_pair = requests.post("http://127.0.0.1:8000/granite_mart/api/token/", {'username': uname, 'password': upass})

                data = json.loads(token_pair.text)
                print(data)
                response = Response()

                response.set_cookie(key='token', value=data['access'], httponly=True, samesite='None', secure=True)
                response.set_cookie(key='refresh', value=data['refresh'], httponly=True, samesite='None', secure=True)
                response.set_cookie(key='username', value= uname, samesite='None',)
                response.set_cookie(key='isAdmin', value= isAdmin, samesite='None',)
                response.set_cookie(key='login', value= 'true', samesite='None',)
                print(uname, isAdmin)
                response.data = {

                    'token': token_pair,
                    "msg": "success"
                }
                return response
        return Response('failed')


@api_view(['GET'])
def logout(request):
    response = Response()
    response.set_cookie('token', '')
    response.set_cookie('refresh', '')
    response.set_cookie('login', 'false')
    return response
