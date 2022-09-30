# import json
from django.shortcuts import render
from django.http import HttpResponse, JsonResponse
from appointment import serializers
from rest_framework.response import Response
from rest_framework import status
from rest_framework.views import APIView
from rest_framework.decorators import api_view
from users.serializers import UserSerializer,CustomerSerializer ,EmployeeSerializer
from .models import User , Customer 
from appointment.models import Branch
from appointment.serializers import BranchSerializer
from django.views.decorators.csrf import csrf_exempt
from django.contrib.auth import authenticate,login,logout
# Create your views here.

# @api_view(['POST','PUT'])
# def registerCustomer(request):
#     if request.method == 'POST':
#         print(request.data)
#         print(request)
#         # data = json.parse(request)

#         serializer = UserSerializer(data = request.data)
#         if serializer.is_valid():
#             user = serializer.save()
#             customer_obj = CustomerSerializer(data = {"customer_id":"MEDC"+str(user.id),"user_id":user.id})
#             if customer_obj.is_valid():
#                 customer_obj.save()

#             return Response(serializer.data)
#         # print(user_obj)
#         # print(serializer)
#     return Response({"msg":"not created"},status =400)


class RegisterCustomer(APIView):
    def post(self,request):
        print(request.data)
        username = request.data['username']
        try:
            user = User.objects.get(username = username)
            print(user)
            return Response({'message':"User  Exist"})
        except:
            serializer = UserSerializer(data=request.data)
            print(serializer)
            if serializer.is_valid():
                user = serializer.save()
                customer_obj = CustomerSerializer(data = {"customer_id":"MEDC"+str(user.id),"user_id":user.id})
                if customer_obj.is_valid():
                    customer_obj.save()
                    return Response({'data':serializer.data , 'message':"registered"},status=200)
                else:
                    print('invalid')
                    return Response(customer_obj.errors, status=status.HTTP_400_BAD_REQUEST) 
            else:
                print('invalid')
                return Response({'message':"invalid"})    

    def get(self,request):
        users = Customer.objects.all()
        serializer = CustomerSerializer(users , many = True)
        return Response(serializer.data,status=200)

class RegisterEmployee(APIView):
    def post(self,request):
        print(request.data)
        username = request.data['username']
        try:
            user = User.objects.get(username = username)
            print(user)
            return Response({'message':"User Exist"})
        except:
            serializer = UserSerializer(data={'username': request.data["username"],"first_name":request.data["first_name"],'last_name':request.data["last_name"],'email':request.data['email'],"mobile_number":request.data["mobile_number"],"age":request.data['age'],'address':request.data['address'],"pincode":request.data['pincode'] , "password":request.data['password']})
            print(serializer)
            if serializer.is_valid():
                user = serializer.save()
                employee_obj = EmployeeSerializer(data = {"staff_id":"MEDS"+str(user.id),"user_id":user.id ,"designation":request.data["designation"] , "qualification":request.data['qualification'],"salary":request.data['salary'],"years_of_experience":request.data['years_of_experience'] or None, 'branch':request.data['branch'] })
                print(employee_obj)
                if employee_obj.is_valid():
                    employee_obj.save()
                    return Response({'data':serializer.data , 'message':"registered"},status=200)
                else:
                    return Response( {'message':"not valid"}, status=status.HTTP_400_BAD_REQUEST)   
            else:
                print('invalid')
                return Response({'message':"not valid"})    


    def get(self,request):
        users = Customer.objects.all()
        serializer = CustomerSerializer(users , many = True)
        return Response(serializer.data,status=200)

class BranchHandler(APIView):
    def get(self,request):
        branches = Branch.objects.all()
        serializer = BranchSerializer( branches,many=True)
        return Response(serializer.data, status=200)    

@csrf_exempt 
@api_view(['POST'])
def loginUser(request):
    if request.method == 'POST':
        username = request.data.get("username")
        password = request.data.get('password')
        try:
            user = User.objects.get(username = username)
        except:
            return Response({'msg':"User Does not Exist"})

        user = authenticate(request, username = username, password = password)
        if user is not None:
            login(request, user)
            return Response({'msg':"logged in" , 'user': user.username} ,status=200)
        else:
            return Response({'msg':"password incorrect" })

    return Response({"msg":"not created"},status =200)

@csrf_exempt 
@api_view(['POST'])
def logoutUser(request):
    username = request.data.get("username")
    user  = User.objects.get(username=username)
    logout(request)
    return Response({"message":"logged out" , 'user':user.username},status =200)
