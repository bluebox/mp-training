import json

from django.contrib.auth.decorators import login_required
from django.shortcuts import render, redirect
from django.http import HttpResponse, JsonResponse
from django.views.decorators.csrf import csrf_exempt
from rest_framework.decorators import api_view
from rest_framework.parsers import JSONParser
from rest_framework.response import Response
from .models import *
from django.contrib.auth.forms import UserCreationForm
from django.contrib.auth import authenticate, login, logout
from django.contrib.auth.hashers import make_password
import datetime

from .serializers import storeSerializer, custSerializer, empSerializer, vehicleSerializer, orderSerializer, \
    itemSerializer, roleSerializer


# Create your views here.

def home(request):
    return render(request, 'home.html')

def custregistrationPage(request):

    form = UserCreationForm()
    context = {'form':form}

    return render(request, 'registerUser.html', context)


@api_view(['POST'])
def registerCustomer(request):
    if request.method == 'POST':
        serializer = custSerializer(data=request.data)
        serializer.is_valid()
        print(serializer.is_valid())
        username = request.data['customer_id']
        password = request.data['password']
        print(password)
        if password:
            passwordHashed = make_password(password)
            user = User(username=username, password=passwordHashed)
            user.save()
            fullname = request.data['customer_name']
            phone = request.data['phone']
            email = request.data['email']
            address = request.data['address']
            cust = Customer(customer_id=user, password=password,
                        customer_name=fullname, phone=phone, username=username, email=email, address=address)
            cust.save()
            print(cust)
            serialize = custSerializer(cust)
            return Response(serialize.data)
    return Response('failed')


def custloginPage(request):
    context = {}
    return render(request, 'userlogin.html', context)

@api_view(['POST'])
def customerLogin(request):
    if request.method == 'POST':
        uname = request.data['customer_id']
        upass = request.data['password']
        pk=''
        u = authenticate(username=uname, password=upass)
        if u:
            login(request, u)
            request.session['userid'] = uname
            print(request.session['userid'])
            pk=User.objects.get(username=uname).id
            return Response(uname)
        else:
            return Response('failed')

@api_view(['GET'])
def logoutc(request):
    print(request.session['userid'])
    logout(request)
    return Response('logged out successfully')


@api_view(['GET'])
def custdashboard(request):
    userid = request.session['userid']
    user = User.objects.get(username=userid)
    cust_data = Customer.objects.filter(customer_id=user)[:1]
    orders = Order.objects.filter(customer_id=cust_data)
    order_data = []
    for order in orders:
        details = {}
        orderedItems = OrderedItems.objects.filter(order_id=order)
        for orditem in orderedItems:
            store = GraniteStore.objects.get(store_id=orditem.contains_id.store_id.store_id)
            items = Item.objects.get(item_id=orditem.contains_id.item_id.item_id)
            store_data = {'store_id': store.store_id, 'store_name': store.store_name}
            item_data = {'item_id': items.item_id, 'item_name': items.item_name, 'price': orditem.contains_id.price}
            details = {'order_id': order.order_id, 'store': store_data, 'item': item_data}
        order_data.append(details)
    serialize = custSerializer(cust_data,many=True)
    context = {'custdata': serialize.data[0], 'orders': reversed(order_data)}
    return Response(context)

@login_required(login_url='custlogin')
def buyproducts(request):
    userid = request.session['userid']
    stores = GraniteStore.objects.all()
    context = {'stores': stores}
    return render(request, 'stores.html', context)


@login_required(login_url='custlogin')
def productsPage(request, storeid):
    userid = request.session['userid']
    citem = ContainsItem.objects.filter(store_id=storeid)
    items = []
    request.session['name'] = 'Ajay'
    name = request.session['name']
    for item in citem:
        i = Item.objects.get(item_id=item.item_id.item_id)
        data = {'item_id': i.item_id, 'item_name': i.item_name, 'price': item.price}
        items.append(data)
    context = {'userid': userid, 'storeid': ContainsItem.objects.get(store_id=storeid), 'items': items, 'name': name}
    return render(request, 'products.html', context)


@api_view(['GET'])
def viewCustomers(request):
    cust = Customer.objects.all()
    serialize = custSerializer(cust,many=True)
    return Response(serialize.data)


@api_view(['GET'])
def viewCustomer(request, customer_id):
    print(customer_id)
    cust = Customer.objects.get(username=customer_id)
    serialize = custSerializer(cust)
    return Response(serialize.data)

@api_view(['POST'])
def updateCustomer(request, customer_id):
    print(customer_id)
    password=request.data['password']
    fullname = request.data['customer_name']
    phone = request.data['phone']
    email = request.data['email']
    address = request.data['address']
    userid=User.objects.get(username=customer_id)
    user=User(id=userid.id, username=customer_id, password=make_password(password))
    user.save()
    cust = Customer(id=Customer.objects.get(customer_id=user).id, customer_id=user, password=password,
                    customer_name=fullname, phone=phone, username=customer_id, email=email, address=address)
    cust.save()
    serialize = custSerializer(cust)
    return Response(serialize.data)


@api_view(['POST'])
def deleteCustomer(request, customer_id):
    print(customer_id)
    cust = User.objects.get(username=customer_id)
    cust.delete()
    return Response('deleted')


@api_view(['GET'])
def viewEmployees(request):
    emp = Employee.objects.all()
    serialize = empSerializer(emp, many=True)
    return Response(serialize.data)


@api_view(['GET'])
def viewEmployee(request,employee_id):
    emp = Employee.objects.get(employee_id=employee_id)
    serialize = empSerializer(emp)
    return Response(serialize.data)

@api_view(['GET'])
def getRoles(request):
    roles = Role.objects.all()
    serialize = roleSerializer(roles, many=True)
    return Response(serialize.data)

@api_view(['POST'])
def registerEmployee(request):
    if request.method == 'POST':
        serializer = empSerializer(data=request.data)
        print(serializer.is_valid())
        print(serializer.errors)
        employee_id = request.data['employee_id']
        employee_name = request.data['employee_name']
        doj = request.data['doj']
        role = request.data['role']
        salary = request.data['salary']
        phone = request.data['phone']
        email = request.data['email']
        address = request.data['address']
        emp = Employee(employee_id=employee_id, employee_name=employee_name, doj=doj,
                           role_id=Role.objects.get(role_id=role), salary=salary, phone=phone, email=email, address=address)
        emp.save()
        serialize = empSerializer(emp)
        return Response(serialize.data)
    return Response('failed')


@api_view(['POST'])
def updateEmployee(request, employee_id):
    if request.method == 'POST':
        serializer = empSerializer(data=request.data)
        print(serializer.is_valid())
        print(serializer.errors)
        employee_name = request.data['employee_name']
        doj = request.data['doj']
        role = request.data['role']
        salary = request.data['salary']
        phone = request.data['phone']
        email = request.data['email']
        address = request.data['address']
        emp = Employee(employee_id=employee_id, employee_name=employee_name, doj=doj,
                           role_id=Role.objects.get(role_name=role), salary=salary, phone=phone, email=email, address=address)
        emp.save()
        serialize = empSerializer(emp)
        return Response(serialize.data)
    return Response('failed')


@api_view(['POST'])
def deleteEmployee(request, employee_id):
    emp = Employee.objects.get(employee_id=employee_id)
    emp.delete()
    return Response('deleted')


@login_required()
@api_view(['GET','POST'])
def viewStores(request):
    store = GraniteStore.objects.all()
    serialize = storeSerializer(store, many=True)
    return Response(serialize.data)

@api_view(['GET'])
def viewStore(request,store_id):
    store = GraniteStore.objects.get(store_id=store_id)
    serialize = storeSerializer(store)
    return Response(serialize.data)


@api_view(['GET'])
def viewVehicles(request):
    vehicle = Vehicle.objects.all()
    serialize = vehicleSerializer(vehicle, many=True)
    return Response(serialize.data)


@api_view(['GET'])
def viewVehicle(request,vehicle_no):
    vehicle = Vehicle.objects.get(vehicle_no=vehicle_no)
    serialize = vehicleSerializer(vehicle)
    return Response(serialize.data)


@api_view(['GET'])
def viewOrders(request):

    orders = OrderedItems.objects.all()
    data = []

    for order in orders:
        serializeOrder = orderSerializer(order)
        customers = order.order_id.customer_id
        serializeCustomer = custSerializer(customers)
        item = Item.objects.get(item_id=order.contains_id.item_id.item_id)
        serializeItem = itemSerializer(item)
        data.append({order.order_id.order_id: [serializeCustomer.data, serializeItem.data]})

    return Response(data)


@api_view(['GET'])
def viewOrder(request, order_id):

    order = OrderedItems.objects.get(order_id=order_id)
    data = []
    customers = order.order_id.customer_id
    serializeCustomer = custSerializer(customers)
    item = Item.objects.get(item_id=order.contains_id.item_id.item_id)
    serializeItem = itemSerializer(item)
    data.append({order.order_id.order_id: [serializeCustomer.data, serializeItem.data]})

    return Response(data)


@api_view(['GET'])
def viewItems(request):

    items = ContainsItem.objects.all()
    data=[]
    for item in items:
        store = GraniteStore.objects.get(store_id=item.store_id.store_id)
        serializeStore = storeSerializer(store)
        item_data = Item.objects.get(item_id=item.item_id.item_id)
        serializeItem = itemSerializer(item_data)
        data.append({item.contains_id: [serializeStore.data, serializeItem.data]})
    return Response(data)


@api_view(['GET'])
def viewItem(request, contains_id):

    item = ContainsItem.objects.get(contains_id=contains_id)
    data = []
    store = GraniteStore.objects.get(store_id=item.store_id.store_id)
    serializeStore = storeSerializer(store)
    item_data = Item.objects.get(item_id=item.item_id.item_id)
    serializeItem = itemSerializer(item_data)
    data.append({item.contains_id: [serializeStore.data, serializeItem.data]})
    return Response(data)

