# updated
import json
from django.shortcuts import render
from django.http import HttpResponse, JsonResponse
from appointment import serializers
from rest_framework.parsers import JSONParser
from rest_framework.response import Response
from rest_framework import status
from rest_framework.views import APIView
from rest_framework.decorators import api_view
from rest_framework.exceptions import APIException
from rest_framework.authentication import get_authorization_header
from  rest_framework import exceptions
from django.db.models import Q

# jwt
from rest_framework.permissions import IsAuthenticated
from rest_framework.exceptions import APIException

from appointment.serializers import AppointmentSerializer
from .authentication import create_access_token,create_refresh_token , decode_access_token,decode_refresh_token
from users.serializers import UserSerializer, CustomerSerializer, EmployeeSerializer
from .models import User, Customer, Staff
from appointment.models import Branch
from appointment.serializers import BranchSerializer
from django.views.decorators.csrf import csrf_exempt
from django.contrib.auth import authenticate, login, logout


class DetailStaff(APIView):
    def get(self,request):
        pass


class DetailCustomer(APIView):
    # @staticmethod
    def get(self,request,customer_id):
        customer = Customer.objects.filter(customer_id = customer_id).first()
        # user_data = User.objects.filter(id=customer.user_id.id).first()
        customer_serializer = CustomerSerializer(customer,many=False)
        user_serializer = UserSerializer(customer.user_id, many=False)
        appointments = customer.appointment_set.all()
        appointments= AppointmentSerializer(appointments,many=True)
        return Response({"customer_details":customer_serializer.data,"user_details":user_serializer.data,'appointments':appointments.data}, status=status.HTTP_201_CREATED,)

    def delete(self,request,customer_id):
        customer = Customer.objects.filter(customer_id=customer_id).first()
        if customer:
            customer.delete()
            return JsonResponse(data={'success': 'Customer deleted successfully.'}, safe=False)
        else:
            return JsonResponse(data={'success': 'customer is not deleted successfully.'}, safe=False)
        return JsonResponse(
            data={'Failure': 'Customer Doesn\'t exists . So, Appointment Data cound not be deleted successfully.'},
            safe=False)

    def put(self,request,customer_id):
        customer_data = JSONParser().parse(request)
        customer = Customer.objects.filter(customer_id=customer_id).first()
        user = User.objects.filter(id=customer.user_id.id).first()
        serializer = UserSerializer(user,data=customer_data)
        if serializer.is_valid():
            serializer.save()
            return Response({'data': serializer.data, 'message': "updated"}, status=200)
        else:
            error_list = [serializer.errors[error][0] for error in serializer.errors]
            return Response({"message": error_list}, status=200)


class FilterCustomer(APIView):
    def get(self,request):
        text = request.GET['text']
        customers = Customer.objects.filter(Q(customer_id__icontains=text) | Q(user_id__username__icontains=text))
        customers = list(customers.values(
            'customer_id', 'user_id__username'
        ))
        return Response({'customers': json.dumps(customers)}, status=200)



class RegisterCustomer(APIView):
    def post(self, request):
        print(request.data)
        username = request.data['username']
        user = User.objects.filter(username=username).first()
        if user:
            return Response({'message': "username already exist "})
        else:
            data = request.data
            data['user_type'] = 'customer'
            serializer = UserSerializer(data=data)
            if serializer.is_valid():
                user = serializer.save()
                customer_obj = CustomerSerializer(data={"customer_id": "MEDC" + str(user.id), "user_id": user.id})
                if customer_obj.is_valid():
                    customer_obj.save()
                    return Response({'data': serializer.data, 'message': "registered"}, status=200)
                else:
                    error_list = [customer_obj.errors[error][0] for error in customer_obj.errors]
                    return Response(error_list, status=status.HTTP_400_BAD_REQUEST)
            else:
                error_list = [serializer.errors[error][0] for error in serializer.errors]
                return Response({"message": error_list}, status=200)


    def get(self, request):
        customers = Customer.objects.all()
        # users = list(users.values(
        #     'customer_id','user_id','user_id.username','user_id.mobile_number','user_id.age','user_id.address','user_id.pincode',
        # ))
        customers = list(customers.values(
            'customer_id','user_id__username'
        ))
        # serializer = CustomerSerializer(users, many=True)
        return Response({'customers':json.dumps(customers)}, status=200)


class RegisterEmployee(APIView):
    def post(self, request):
        username = request.data['username']
        user = User.objects.filter(username=username).first()
        if user:
            return Response({'message': "username already exist "})
        else:
            serializer = UserSerializer(
                    data={'username': request.data["username"], "first_name": request.data["first_name"],
                          'last_name': request.data["last_name"], 'email': request.data['email'],
                          "mobile_number": request.data["mobile_number"], "age": int(request.data['age']),
                          'address': request.data['address'], "pincode": request.data['pincode'],
                          "password": request.data['password'], "user_type": 'staff'})
            if serializer.is_valid():
                user = serializer.save()
                employee_obj = EmployeeSerializer(data={"staff_id": "MEDS" + str(user.id), "user_id": user.id,
                                                        "designation": request.data["designation"],
                                                        "qualification": request.data['qualification'],
                                                        "salary": int(request.data['salary']),
                                                        "years_of_experience": int(request.data[
                                                                                   'years_of_experience']) or 0,
                                                        'branch': request.data['branch']})
                if employee_obj.is_valid():
                    employee_obj.save()
                    return Response({'data': serializer.data, 'message': "registered"}, status=200)
                else:
                    error_list = [employee_obj.errors[error][0] for error in employee_obj.errors]
                    return Response(error_list, status=status.HTTP_400_BAD_REQUEST)
            else:
                error_list = [serializer.errors[error][0] for error in serializer.errors]
                return Response(error_list, status=status.HTTP_400_BAD_REQUEST)

    def get(self, request):
        staffs = Staff.objects.all()
        # users = list(users.values(
        #     'customer_id','user_id','user_id.username','user_id.mobile_number','user_id.age','user_id.address','user_id.pincode',
        # ))
        staffs = list(staffs.values(
            'staff_id', 'user_id__username', 'designation'
        ))
        return Response({'staffs': json.dumps(staffs)}, status=200)


class BranchHandler(APIView):
    def get(self, request):
        branches = Branch.objects.all()
        serializer = BranchSerializer(branches, many=True)
        return Response(serializer.data, status=200)




@csrf_exempt
@api_view(['POST'])
def logoutUser(request):
    username = request.data.get("username")
    user = User.objects.get(username=username)
    logout(request)
    return Response({"message": "logged out", 'user': user.username}, status=200)


class LoginView(APIView):
    def post(self,request):
        username = request.data.get("username")
        password = request.data.get('password')
        user = User.objects.filter(username=request.data['username']).first()
        if not user:
            return Response({"message": "user does not exist"}, status=200)
        user = authenticate(request, username=username, password=password)
        if user is None:
            return Response({'message': "Incorrect Password! "}, status=200)
        else:
            if user.user_type == 'customer':
                user_type_obj = Customer.objects.filter(user_id = user.id).first()
                user_type_id = user_type_obj.customer_id
            elif user.user_type == 'staff':
                user_type_obj = Staff.objects.filter(user_id=user.id).first()
                user_type_id = user_type_obj.staff_id
            else:
                user_type_id = user.id

        access_token = create_access_token(user.id)
        refresh_token = create_refresh_token(user.id)
        response = Response()

        response.set_cookie(key='refreshToken' , value= refresh_token , httponly = True) # refresh token in cookie n access token in response data
        response.data = {
            'message':"success",
            'token':access_token,
            'user_type' : user.user_type,
            'username' : user.username,
            'user_type_id':user_type_id
        }
        return response

        # return Response({"message": "logged out", 'user': user.username}, status=200)


@csrf_exempt
@api_view(['GET'])
def userView(request):
    auth = get_authorization_header(request).split()   # first param is bearer n 2nd is token (access token)
    if auth and len(auth)==2:
        token = auth[1].decode('utf-8')
        id = decode_access_token(token)
        user = User.objects.filter(id = id).first()
        if user.user_type == 'customer':
            user_type_obj = Customer.objects.filter(user_id=user.id).first()
            user_type_id = user_type_obj.customer_id
        elif user.user_type == 'staff':
            user_type_obj = Staff.objects.filter(user_id=user.id).first()
            user_type_id = user_type_obj.staff_id
        else:
            user_type_id = user.id
        return Response({
            'user_type' : user.user_type,
            'username' : user.username,
            'user_type_id':user_type_id
        })
    raise exceptions.AuthenticationFailed('unauthenticate')


# def userView(request):
#     auth = get_authorization_header(request).split()   # first param is bearer n 2nd is token (access token)
#     if auth and len(auth)==2:
#         token = auth[1].decode('utf-8')
#         id = decode_access_token(token)
#         user = User.objects.filter(id = id).first()
#         return Response(UserSerializer(user).data)
#     raise exceptions.AuthenticationFailed('unauthenticate')

class RefreshToken(APIView):
    # authentication_classes = []
    def post(self,request):
        refresh_token  = request.COOKIES.get('refreshToken')
        id = decode_refresh_token(refresh_token)
        access_token = create_access_token(id)
        print(access_token)
        return Response({
            'token' : access_token
        })


class LogoutView(APIView):
    def post(self,_):
        response = Response()
        response.delete_cookie(key= 'refreshToken')
        response.data = {
            'message' : 'success'
        }
        return response
