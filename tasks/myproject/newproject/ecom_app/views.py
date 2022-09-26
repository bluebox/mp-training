from itertools import count

from django.http.response import JsonResponse
from django.shortcuts import render, redirect
from .models import *
from django.http import HttpResponse
from rest_framework.parsers import JSONParser
from rest_framework.response import Response
from django.contrib.auth import authenticate
from django.contrib import messages
from rest_framework.decorators import api_view
from django.views.decorators.csrf import csrf_exempt
from ecom_app.models import Customer
from ecom_app.serializers import CustomerSerializer, Myserializer1
from rest_framework.decorators import api_view, permission_classes
from rest_framework import permissions
from rest_framework.viewsets import ModelViewSet


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
        if Customer.objects.filter(f_name=x).exists():
            if Customer.objects.get(f_name=x).password == y:
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
                if Customer.objects.filter(uname=uname).exists():
                    if Customer.objects.filter(mail=mail).exists():
                        messages.info(request, 'username or mail already exists')
                        return redirect('signup')
                else:
                    if Customer.objects.filter(mail=mail).exists():
                        messages.info(request, 'username or mail already exists')
                        return redirect('signup')
                    else:
                        x = Customer.objects.create(uname=uname, f_name=f_name, l_name=l_name, mail=mail,
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
        cust = Customer.objects.filter(cust_id=2)
        print("customer id is: ", cust)
        serializer = CustomerSerializer(cust,many=True)
        return  Response(serializer.data)

    elif request.method == 'POST':
        print(request)
        # data = JSONParser().parse(request)

        serializer = CustomerSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=201)
        return Response(serializer.errors, status=400)


class Customer1(ModelViewSet):
    x = Customer.objects.all()
    queryset = x
    serializer_class = CustomerSerializer


class employeeViewModelset1(ModelViewSet):
    queryset = employee.objects.all()
    serializer_class = Myserializer1
    # permission_classes = [permissions.IsAuthenticatedOrReadOnly]

    # def highlight(self, request, *args, **kwargs):
    #     snippet = self.get_object()
    #     return Response(snippet.highlighted)
    #
    # def perform_create(self, serializer):
    #     serializer.save(owner=self.request.user)