from django.shortcuts import render

# Create your views here.

from django.shortcuts import HttpResponse,get_object_or_404
from django.views import View
from rest_framework.exceptions import AuthenticationFailed

from .serializers import CustomerSerializer, FoodSerializer, RestaurantSerializer, EmployeeSerializer, MenuSerializer, \
    MenuListSerializer
from rest_framework.views import APIView
from rest_framework.response import Response
from rest_framework import authentication, permissions,status
from .models import Customer, Food, Restaurant, Employee, Menu, MenuList
from rest_framework.parsers import JSONParser

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
        print(request.data)
        # data=JSONParser().parse(request)
        serializer = FoodSerializer(data=request.data)
        if serializer.is_valid(raise_exception=True):
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        else:
            return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)


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


class SearchData(APIView):
    def get(self, request, item):
        print(item)
        restaurant=[]
        food = Food.objects.all().filter(food_name__icontains=item)
        print(food)
        for f in food:
            # print(f.restaurant_id)
            restaurant.append(Restaurant.objects.get(restaurant_id__contains=f.restaurant_id.restaurant_id))
        print(restaurant)

        serializerRes = RestaurantSerializer(restaurant, many=True)
        return Response(serializerRes.data)

    def post(self, request):
        return HttpResponse("Heyyyyyyyy post", status=405)



class AddMenu(APIView):

    def get(self, request):
        menu = Menu.objects.all()
        serializer = MenuSerializer(menu, many=True)
        return Response(serializer.data)

    def post(self,request):
        serializer = MenuSerializer(data=request.data)
        print(serializer)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)


class AddFoodtoMenu(APIView):

    def get(self, request):
        menu = MenuList.objects.all()
        serializer = MenuListSerializer(menu, many=True)
        return Response(serializer.data)

    def post(self,request):
        serializer = MenuListSerializer(data=request.data)
        print(serializer)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)


# class CustomerLogin(APIView):
#     def post(self,request):
#         email=request.data['customer_email']
#         password=request.data['customer_password']
#         user=Customer.objects.filter(customer_email=email).first()
#
#         if user is None:
#             raise AuthenticationFailed('User not found! Try Again or register!')
#
#         if not user.check_password(password):
#             raise AuthenticationFailed('Wrong password!')
#
#         return Response(user)