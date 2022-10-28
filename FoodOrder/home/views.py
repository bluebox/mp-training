from django.shortcuts import render

# Create your views here.

from django.shortcuts import HttpResponse, get_object_or_404
from django.views import View
from rest_framework.exceptions import AuthenticationFailed
from rest_framework.generics import RetrieveAPIView, CreateAPIView
from rest_framework.permissions import IsAuthenticated, AllowAny
from rest_framework_jwt.settings import api_settings

from .serializers import CustomerSerializer, FoodSerializer, RestaurantSerializer, EmployeeSerializer, MenuSerializer, \
    MenuListSerializer, CartSerializer, RestaurantRegistrationSerializer, CustomerRegistrationSerializer, \
    EmpRegistrationSerializer, UserLoginSerializer
from rest_framework.views import APIView
from rest_framework.response import Response
from rest_framework import authentication, permissions, status
from .models import Customer, Food, Restaurant, Employee, Menu, MenuList, OrderFood,User
import jwt
from rest_framework_simplejwt.authentication import JWTAuthentication
from rest_framework.parsers import JSONParser

JWT_DECODE_HANDLER = api_settings.JWT_DECODE_HANDLER
class Index(View):
    def get(self, request):
        return HttpResponse("Heyyyyyyyy get", status=405)

    def post(self, request):
        return HttpResponse("Heyyyyyyyy post", status=405)


class CustomerData(APIView):
    def get(self, request):
        try:
            customer = Customer.objects.all();
            serializer = CustomerSerializer(customer, many=True)
        except:
            return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)
        else:
            return Response(serializer.data)

    def post(self, request):
        try:

            serializer = CustomerSerializer(data=request.data)
            print(serializer)
        except:
            return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

        else:
            if serializer.is_valid():
                print("valid")
                # print(serializer)
                serializer.save()
                return Response(serializer.data, status=status.HTTP_201_CREATED)


class FoodData(APIView):
    def get(self, request):
        try:
            food = Food.objects.all();
            serializer = FoodSerializer(food, many=True)
        except:
            return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)
        else:

            return Response(serializer.data)

    def post(self, request):
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
    def get(self, request, id):
        print(id)
        try:
            food = Food.objects.get(food_id=id)
            serializer = FoodSerializer(food)
            return Response(serializer.data)
        except:
            return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)
        else:
            serializer = FoodSerializer(food)
            return Response(serializer.data)

    def post(self, request):
        return HttpResponse("Heyyyyyyyy post", status=405)

    def delete(self, request, id):
        print("in django")
        item = Food.objects.get(food_id=id)
        print(item)
        print("start delete")

        item.delete()
        print("deleted")
        return Response(status=status.HTTP_204_NO_CONTENT)


class FoodOneRes(APIView):
    def get(self, request, id):
        print(id)
        try:
            food = Restaurant.objects.get(restaurant_id=id)
            serializer = RestaurantSerializer(food)
        except:
            return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)
        else:
            return Response(serializer.data)

    def post(self, request,id):
        print(id)
        item = Restaurant.objects.get(restaurant_id=id)
        print(item)
        data = RestaurantSerializer(instance=item, data=request.data)

        if data.is_valid():
            data.save()
            print("saved")
            return Response(data.data)
        else:
            print(" not saved")
            return Response(status=status.HTTP_404_NOT_FOUND)

    def delete(self, request, id):
        print("in django")
        item = Restaurant.objects.get(restaurant_id=id)
        print(item)
        print("start delete")

        item.delete()
        print("deleted")
        return Response(status=status.HTTP_204_NO_CONTENT)

class RestaurantData(APIView):
    def get(self, request):
        print("hello")
        restaurant = Restaurant.objects.all();
        serializer = RestaurantSerializer(restaurant, many=True)
        return Response(serializer.data)

    def post(self, request):
        serializer = RestaurantSerializer(data=request.data)
        print(serializer)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)


class EmployeeData(APIView):
    def get(self, request):
        emp = Employee.objects.all()
        serializer = EmployeeSerializer(emp, many=True)
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
        # restaurant = []
        restaurant = Restaurant.objects.filter(food__food_name__icontains=item).values()
        #food = Food.objects.all().select_related("restaurant").filter(food_name__icontains=item)
        # print(food)
        # for f in food:
        #     print(f['restaurant_id'])
        #     restaurant.append(Restaurant.objects.get(restaurant_id=f['restaurant_id']).values())
        print(restaurant)

        # serializerres = FoodSerializer(food, many=True)
        # print(serializerres.data)
        return Response(restaurant)

    def post(self, request):
        return HttpResponse("Heyyyyyyyy post", status=405)


class AddMenu(APIView):

    def get(self, request):
        menu = Menu.objects.all()
        serializer = MenuSerializer(menu, many=True)
        return Response(serializer.data)

    def post(self, request):
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
        response=Response(serializer.data)
        return Response(serializer.data)

    def post(self, request):
        try:
            serializer = MenuListSerializer(data=request.data)
            print(serializer)
        except:
            response=Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)
            # return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

        else:
            if serializer.is_valid():
                serializer.save()
                response=Response(serializer.data, status=status.HTTP_201_CREATED)
                # return Response(serializer.data, status=status.HTTP_201_CREATED)

        return response

class OneResFoods(APIView):
    def get(self, request, item):
        food = Food.objects.filter(restaurant_id=item)

        serializerRes = FoodSerializer(food, many=True)
        return Response(serializerRes.data)


class CustomerLogin(APIView):
    def post(self, request):
        try:
            email = request.data['customer_email']
            password = request.data['customer_password']
            print(email)
            user = Customer.objects.filter(customer_email=email).first()

            if user is None:
                raise AuthenticationFailed('User not found! Try Again or register!')

            # if not user.check_password(password):
            #     raise AuthenticationFailed('Wrong password!')

            payload = {
                "id": user.customer_id
            }
            token = jwt.encode(payload, "secret")
            response = Response("login success")
            response.set_cookie(key="jwt", value=token, httponly=True, samesite="Lax")
            print(token)
            return response


        except:
            return Response("login Failed")

    def get(self, request):
        token = request.COOKIES.get("jwt")
        if not token:
            return Response("None")
        payload = jwt.decode(token, 'secret', algorithms=['HS256'])
        return Response(payload)


class Cart(APIView):

    def get(self, request):

        cart = OrderFood.objects.all()
        serializer = CartSerializer(cart, many=True)
        return Response(serializer.data)

    def post(self, request):
        try:
            print("this is me")
            serializer = CartSerializer(data=request.data)

            print(serializer)
            print(type(serializer))
        except:
            return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)
            # return Response("Bad request")
        else:
            print("valid34435")
            if serializer.is_valid():
                print("valid")
                serializer.save()

                return Response(serializer.data, status=status.HTTP_201_CREATED)

        return Response("Bad request")


class CartEdit(APIView):
    def delete(self, request, id):
        print("in django")
        item = OrderFood.objects.get(order_food_id=id)
        print(item)
        print("start delete")

        item.delete()
        print("deleted")
        return Response(status=status.HTTP_204_NO_CONTENT)


class UserProfileView(RetrieveAPIView):
    permission_classes = (IsAuthenticated,)

    authentication_class = JWTAuthentication

    def get(self, request):
        try:
            if request.user.is_restaurant == True:
                user_profile = Restaurant.objects.get(user=request.user)
                status_code = status.HTTP_200_OK
                response = {
                    'success': 'true',
                    'status code': status_code,
                    'message': 'Restaurant profile fetched successfully',
                    'data': [{
                        'restaurant_id': user_profile.restaurant_id,
                        'restaurant_name': user_profile.restaurant_name,
                        # 'phone_number': user_profile.phone_number,
                        # 'age': user_profile.age,
                        # 'gender': user_profile.gender,
                        # 'case': user_profile.case,
                    }]
                }
            if request.user.is_customer == True:
                user_profile = Customer.objects.get(user=request.user)
                status_code = status.HTTP_200_OK
                response = {
                    'success': 'true',
                    'status code': status_code,
                    'message': 'Doctor profile fetched successfully',
                    'data': [{
                        'customer_id': user_profile.customer_id,
                        'customer_name': user_profile.customer_name,
                        # 'phone_number': user_profile.phone_number,
                        # 'age': user_profile.age,
                        # 'gender': user_profile.gender,
                    }]
                }

            if request.user.is_emp == True:
                user_profile = Employee.objects.get(user=request.user)
                status_code = status.HTTP_200_OK
                response = {
                    'success': 'true',
                    'status code': status_code,
                    'message': 'Restaurant profile fetched successfully',
                    'data': [{
                        'emp_id': user_profile.emp_id,
                        'emp_name': user_profile.emp_name,
                        # 'phone_number': user_profile.phone_number,
                        # 'age': user_profile.age,
                        # 'gender': user_profile.gender,
                        # 'case': user_profile.case,
                    }]
                }
            if request.user.is_superuser == True:
                userprofile = User.objects.get(user=request.user)
                status_code = status.HTTP_200_OK
                response = {
                    'success': 'true',
                    'status code': status_code,
                    'message': 'Admin profile fetched successfully',
                    'data': [{
                        'email': user_profile.email,
                    }]
                }

        except Exception as e:
            status_code = status.HTTP_400_BAD_REQUEST
            response = {
                'success': 'false',
                'status code': status.HTTP_400_BAD_REQUEST,
                'message': 'User does not exists',
                'error': str(e)
            }
        return Response(response, status=status_code)


class RestaurantRegistrationView(APIView):

    serializer_class = RestaurantRegistrationSerializer
    permission_classes = (AllowAny,)
    print("point1")

    def post(self, request):
        print("point2")
        serializer = self.serializer_class(data=request.data)
        print(serializer)
        serializer.is_valid(raise_exception=True)
        serializer.save()
        response = {
            'success' : 'True',
            'status code' : status.HTTP_200_OK,
            'message': 'Restaurant registered  successfully',
            }
        status_code = status.HTTP_200_OK
        return Response(response, status=status_code)


class CustomerRegistrationView(CreateAPIView):

    serializer_class = CustomerRegistrationSerializer
    permission_classes = (AllowAny,)

    def post(self, request):
        serializer = self.serializer_class(data=request.data)
        print(serializer)
        serializer.is_valid(raise_exception=True)
        serializer.save()
        response = {
            'success' : 'True',
            'status code' : status.HTTP_200_OK,
            'message': 'Restaurant registered  successfully',
            }
        status_code = status.HTTP_200_OK
        return Response(response, status=status_code)

class EmployeeRegistrationView(CreateAPIView):
    serializer_class = EmpRegistrationSerializer
    permission_classes = (AllowAny,)

    def post(self, request):
        serializer = self.serializer_class(data=request.data)
        serializer.is_valid(raise_exception=True)
        serializer.save()
        response = {
            'success': 'True',
            'status code': status.HTTP_200_OK,
            'message': 'Restaurant registered  successfully',
        }
        status_code = status.HTTP_200_OK
        return Response(response, status=status_code)

class UserLoginView(RetrieveAPIView):
    permission_classes = ()
    serializer_class = UserLoginSerializer

    def post(self, request):
        print(request.data)
        serializer = self.serializer_class(data=request.data)
        # serializer.is_valid(raise_exception=True)
        # response=Response()
        # response.data = {
        #     'success': 'True',
        #     'status code': status.HTTP_200_OK,
        #     'message': 'User logged in  successfully',
        #     'token': serializer.data['token'],
        #     'user':serializer.data['email'],
        # }
        # response.set_cookie(key='jwt',value=serializer.data['token'],httponly=True)
        response = Response()
        try:

            serializer.is_valid(raise_exception=True)
            response.data = {
                'success': 'True',
                'status code': status.HTTP_200_OK,
                'message': 'User logged in  successfully',
                'token': serializer.data['token'],
                'user': serializer.data['email'],
            }
            response.set_cookie(key='jwt', value=serializer.data['token'], httponly=True)
            return response
        except:
            response.data={
                'login':'False'
            }
            return response

        else:
            response.data={
                'login':'False'
            }
            return response




class UserView(APIView):
    def get(self,request):
        token=request.COOKIES.get('jwt')
        if not token:
            print("not")
            raise AuthenticationFailed("not logged in")
        try:
            payload=jwt.decode(token,'secret')

        except jwt.ExpiredSignatureError:
            print("error")
            raise AuthenticationFailed("not logged in")

        id=payload['user_id']

        if(Restaurant.objects.filter(user=id).exists()):
            data=Restaurant.objects.get(user=id)
            serilizer=RestaurantSerializer(data)

        if(Customer.objects.filter(user=id).exists()):
            data=Customer.objects.get(user=id)
            serilizer=CustomerSerializer(data)


        if(Employee.objects.filter(user=id).exists()):
            data=Employee.objects.get(user=id)
            serilizer=EmployeeSerializer(data)


        # return Response(payload['user_id'])
        return Response(serilizer.data)



class Logout(APIView):
    def get(self,request):
        print("logging outrr")
        response=Response()
        print(response)
        response.delete_cookie('jwt')
        response.data={
            'message':"User logged out"
        }



        return response


class OneCustomer(APIView):
    def post(self,request,id):
        print(id)
        item = Customer.objects.get(customer_id=id)
        print(item)
        data = CustomerSerializer(instance=item, data=request.data)

        if data.is_valid():
            data.save()
            print("saved")
            return Response(data.data)
        else:
            print(" not saved")
            return Response(status=status.HTTP_404_NOT_FOUND)

    def delete(self, request, id):
        print("in django")
        item = Customer.objects.get(customer_id=id)
        print(item)
        print("start delete")

        item.delete()
        print("deleted")
        return Response(status=status.HTTP_204_NO_CONTENT)