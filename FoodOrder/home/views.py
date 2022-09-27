from django.shortcuts import render

# Create your views here.

from django.shortcuts import HttpResponse,get_object_or_404
from django.views import View
from .serializers import CustomerSerializer, FoodSerializer, RestaurantSerializer, EmployeeSerializer
from rest_framework.views import APIView
from rest_framework.response import Response
from rest_framework import authentication, permissions,status
from .models import Customer, Food, Restaurant,Employee


class Index(View):
    def get(self, request):
        return HttpResponse("Heyyyyyyyy get", status=405)

    def post(self,request):
        return HttpResponse("Heyyyyyyyy post", status=405)

class CustomerData(APIView):
    def get(self, request):
        customer=Customer.objects.all();
        serializer=CustomerSerializer(customer,many=True)
        return Response(serializer.data)


    def post(self,request):
        serializer = CustomerSerializer(data=request.data)
        print(serializer)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

class FoodData(APIView):
    def get(self, request):
        food=Food.objects.all();
        serializer=FoodSerializer(food,many=True)
        return Response(serializer.data)



    def post(self,request):
        return HttpResponse("Heyyyyyyyy post", status=405)


class FoodOneData(APIView):
    def get(self, request,id):
        print(id)
        food=Food.objects.get(food_id=id)
        serializer=FoodSerializer(food)
        return Response(serializer.data)



    def post(self,request):
        return HttpResponse("Heyyyyyyyy post", status=405)




class RestaurantData(APIView):
    def get(self, request):
        restaurant=Restaurant.objects.all();
        serializer=RestaurantSerializer(restaurant,many=True)
        return Response(serializer.data)


    def post(self,request):
        serializer = RestaurantSerializer(data=request.data)
        print(serializer)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)



class EmployeeData(APIView):
     def get(self,request):
         emp=Employee.objects.all()
         serializer=EmployeeSerializer(emp,many=True)
         return Response(serializer.data)

     def post(self, request):
         serializer = EmployeeSerializer(data=request.data)
         print(serializer)
         if serializer.is_valid():
             serializer.save()
             return Response(serializer.data, status=status.HTTP_201_CREATED)
         return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)
