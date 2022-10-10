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
import jwt
from rest_framework_simplejwt.authentication import JWTAuthentication
from rest_framework.parsers import JSONParser

class Index(View):
    def get(self, request):
        return HttpResponse("Heyyyyyyyy get", status=405)

    def post(self,request):
        return HttpResponse("Heyyyyyyyy post", status=405)

class CustomerData(APIView):
    def get(self, request):
        try:
            customer=Customer.objects.all();
            serializer=CustomerSerializer(customer,many=True)
        except:
            return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)
        else:
            return Response(serializer.data)


    def post(self,request):
        try:
            serializer = CustomerSerializer(data=request.data)
            print(serializer)
        except:
            return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

        else:
            if serializer.is_valid():
                serializer.save()
                return Response(serializer.data, status=status.HTTP_201_CREATED)



class FoodData(APIView):
    def get(self, request):
        try:
            food=Food.objects.all();
            serializer=FoodSerializer(food,many=True)
        except:
            return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)
        else:

            return Response(serializer.data)



    def post(self,request):
        print(request.data)
        # data=JSONParser().parse(request)
        try:
            serializer = FoodSerializer(data=request.data)
        except:
            return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

        else:

            if serializer.is_valid(raise_exception=True):
                serializer.save()
                return Response(serializer.data, status=status.HTTP_201_CREATED)
            else:
                return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)


class FoodOneData(APIView):
    def get(self, request,id):
        print(id)
        try:
            food=Food.objects.get(food_id=id)
            serializer=FoodSerializer(food)
            return Response(serializer.data)
        except:
            return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)
        else:
            serializer = FoodSerializer(food)
            return Response(serializer.data)

    def post(self,request):
        return HttpResponse("Heyyyyyyyy post", status=405)


class FoodOneRes(APIView):
    def get(self, request,id):
        print(id)
        try:
            food=Restaurant.objects.get(restaurant_id=id)
            serializer=RestaurantSerializer(food)
        except:
            return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)
        else:
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
        try:
            serializer = MenuListSerializer(data=request.data)
            print(serializer)
        except:
            return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

        else:
            if serializer.is_valid():
                serializer.save()
                return Response(serializer.data, status=status.HTTP_201_CREATED)

class OneResFoods(APIView):
    def get(self,request,item):


        food= Food.objects.filter(restaurant_id=item)

        serializerRes = FoodSerializer(food, many=True)
        return Response(serializerRes.data)





class CustomerLogin(APIView):
    def post(self,request):
        try:
            email=request.data['customer_email']
            password=request.data['customer_password']
            print(email)
            user=Customer.objects.filter(customer_email=email).first()

            if user is None:
                raise AuthenticationFailed('User not found! Try Again or register!')

            # if not user.check_password(password):
            #     raise AuthenticationFailed('Wrong password!')

            payload={
                "id" : user.customer_id
            }
            token=jwt.encode(payload,"secret")
            response=Response("login success")
            response.set_cookie(key="jwt",value=token,httponly=True,samesite="Lax")
            print(token)
            return response


        except:
            return Response("login Failed")

    def get(self,request):
        token=request.COOKIES.get("jwt")
        if not token:
            return Response("None")
        payload = jwt.decode(token, 'secret', algorithms=['HS256'])
        return Response(payload)