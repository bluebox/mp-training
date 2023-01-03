# pylint:disable=E1101
# pylint:disable=R1705

"""views """
# jwt
import json
from rest_framework.parsers import JSONParser
from rest_framework.response import Response
from rest_framework import status
from rest_framework.views import APIView
from rest_framework.decorators import api_view
from rest_framework.authentication import get_authorization_header
from rest_framework import exceptions

# updated
from django.db.models import Q
from django.views.decorators.csrf import csrf_exempt
from django.contrib.auth import authenticate, logout
from django.http import JsonResponse

from appointment.models import Branch
from appointment.serializers import BranchSerializer
from appointment.models import Appointment
from appointment.serializers import AppointmentSerializer
from users.serializers import UserSerializer, CustomerSerializer, EmployeeSerializer
from .authentication import create_access_token, create_refresh_token, \
    decode_access_token, decode_refresh_token
from .models import User, Customer, Staff

from .managers import StaffManager, CustomerManager


# --------------------- Detail staff------------------


class DetailStaff(APIView):
    """detail view for employee"""

    @staticmethod
    def get(request, employee_id):
        """get employee"""
        try:
            response_data = StaffManager.get_detail_staff(request, employee_id)
            return Response(response_data, status=status.HTTP_201_CREATED, )
        except Exception as e:
            return Response(str(e), status=status.HTTP_400_BAD_REQUEST)

    @staticmethod
    def delete(request, employee_id):
        """delete employee"""
        try:
            response_data = StaffManager.delete_staff(request, employee_id)
            return JsonResponse(data=response_data, safe=False)
        except Exception as e:
            return Response(str(e), status=status.HTTP_400_BAD_REQUEST)

    @staticmethod
    def put(request, employee_id):
        """update staff"""
        try:
            response_data = StaffManager.update_staff(request, employee_id)
            return Response(data=response_data, status=status.HTTP_200_OK)
        except Exception as e:
            return Response(str(e), status=status.HTTP_400_BAD_REQUEST)


class FilterEmployee(APIView):
    """filter staffs"""

    @staticmethod
    def get(request):
        """filter employees"""
        try:
            response_data = StaffManager.filter_employee(request)
            return JsonResponse(data=response_data, status=status.HTTP_200_OK)
        except Exception as e:
            return Response(str(e), status=status.HTTP_400_BAD_REQUEST)


# ------------------- detail customer------------------

class DetailCustomer(APIView):
    """deatil view for customer"""

    @staticmethod
    def get(request, customer_id):
        """get customer"""
        try:
            response_data = CustomerManager.get_detail_customer(request, customer_id)
            return Response(data=response_data, status=status.HTTP_201_CREATED)
        except Exception as e:
            return Response(str(e), status=status.HTTP_400_BAD_REQUEST)

    @staticmethod
    def delete(request, customer_id):
        """delete customer"""
        try:
            response_data = CustomerManager.delete_customer(request, customer_id)
            return Response(data=response_data, status=status.HTTP_200_OK)
        except Exception as e:
            return Response(str(e), status=status.HTTP_400_BAD_REQUEST)

    @staticmethod
    def put(request, customer_id):
        """update customer object"""
        customer_data = JSONParser().parse(request)
        customer = Customer.objects.filter(customer_id=customer_id).first()
        user = User.objects.filter(id=customer.user_id.id).first()
        serializer = UserSerializer(user, data=customer_data)
        if serializer.is_valid():
            serializer.save()
            return Response({'data': serializer.data, 'message': "updated"},
                            status=200)
        else:
            error_list = [serializer.errors[error][0] for error in serializer.errors]
            return Response({"message": error_list}, status=200)


class FilterCustomer(APIView):
    """filter customer"""

    @staticmethod
    def get(request):
        """get filtered customers"""
        text = request.GET['text']
        customers = Customer.objects.filter(Q(customer_id__icontains=text) |
                                            Q(user_id__username__icontains=text))
        customers = list(customers.values(
            'customer_id', 'user_id__username'
        ))
        return Response({'customers': json.dumps(customers)}, status=200)


class RegisterCustomer(APIView):
    """add customer"""

    @staticmethod
    def post(request):
        """add customer"""
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
                customer_obj = CustomerSerializer(data={"customer_id":
                                                            "MEDC" + str(user.id),
                                                        "user_id": user.id})
                if customer_obj.is_valid():
                    customer_obj.save()
                    return Response({'data': serializer.data, 'message': "registered"}, status=200)
                else:
                    error_list = [customer_obj.errors[error][0] for error in customer_obj.errors]
                    return Response(error_list, status=status.HTTP_400_BAD_REQUEST)
            else:
                error_list = [serializer.errors[error][0] for error in serializer.errors]
                return Response({"message": error_list}, status=200)

    @staticmethod
    def get(request):
        """get all customers"""
        customers = Customer.objects.all()
        customers = list(customers.values(
            'customer_id', 'user_id__username'
        ))
        return Response({'customers': json.dumps(customers)}, status=503)


class RegisterEmployee(APIView):
    """register employee"""

    @staticmethod
    def post(request):
        """add employee"""
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
                employee_obj = EmployeeSerializer(data={"staff_id": "MEDS" + str(user.id),
                                                        "user_id": user.id,
                                                        "designation": request.data["designation"],
                                                        "qualification": request.data['qualification'],
                                                        "salary": int(request.data['salary']),
                                                        "years_of_experience": int(
                                                            request.data['years_of_experience']) or 0,
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

    @staticmethod
    def get(request):
        """get all employees"""
        staffs = Staff.objects.all()
        staffs = list(staffs.values(
            'staff_id', 'user_id__username', 'designation'
        ))  
        return Response({'staffs': json.dumps(staffs)}, status=200)


class BranchHandler(APIView):
    """branch view"""

    @staticmethod
    def get():
        """get all branches"""
        branches = Branch.objects.all()
        serializer = BranchSerializer(branches, many=True)
        return Response(serializer.data, status=200)


@csrf_exempt
@api_view(['POST'])
def logout_user(request):
    """logout user"""
    username = request.data.get("username")
    user = User.objects.get(username=username)
    logout(request)
    return Response({"message": "logged out", 'user': user.username}, status=200)


class LoginView(APIView):
    """login view"""

    @staticmethod
    def post(request):
        """login """
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
                user_type_obj = Customer.objects.filter(user_id=user.id).first()
                user_type_id = user_type_obj.customer_id
            elif user.user_type == 'staff':
                user_type_obj = Staff.objects.filter(user_id=user.id).first()
                user_type_id = user_type_obj.staff_id
            else:
                user_type_id = user.id

        access_token = create_access_token(user.id)
        refresh_token = create_refresh_token(user.id)
        response = Response()

        response.set_cookie(key='refreshToken', value=refresh_token,
                            httponly=True)  # refresh token in cookie n access token in response data
        response.data = {
            'message': "success",
            'token': access_token,
            'user_type': user.user_type,
            'username': user.username,
            'user_type_id': user_type_id
        }
        return response


@csrf_exempt
@api_view(['GET'])
def user_view(request):
    """get authenticated user"""
    auth = get_authorization_header(request).split()  # first param is bearer n 2nd is token (access token)
    if auth and len(auth) == 2:
        token = auth[1].decode('utf-8')
        _id = decode_access_token(token)
        user = User.objects.filter(id=_id).first()
        if user.user_type == 'customer':
            user_type_obj = Customer.objects.filter(user_id=user.id).first()
            user_type_id = user_type_obj.customer_id
        elif user.user_type == 'staff':
            user_type_obj = Staff.objects.filter(user_id=user.id).first()
            user_type_id = user_type_obj.staff_id
        else:
            user_type_id = user.id
        return Response({
            'user_type': user.user_type,
            'username': user.username,
            'user_type_id': user_type_id
        })
    raise exceptions.AuthenticationFailed('unauthenticate')


class RefreshToken(APIView):
    """refresh token view"""

    # authentication_classes = []
    @staticmethod
    def post(request):
        """refreshing token"""
        refresh_token = request.COOKIES.get('refreshToken')
        _id = decode_refresh_token(refresh_token)
        access_token = create_access_token(_id)
        print(access_token)
        return Response({
            'token': access_token
        })


class LogoutView(APIView):
    """view to logout"""

    @staticmethod
    def post(request):
        """logout method"""
        response = Response()
        response.delete_cookie(key='refreshToken')
        response.data = {
            'message': 'success'
        }
        return response


class AllCountApi(APIView):
    """get counts for admin page"""

    @staticmethod
    def get(request):
        """get counts"""
        employees = Staff.objects.all().count()
        customers = Customer.objects.all().count()
        branches = Branch.objects.all().count()
        appointments = Appointment.objects.all().count()

        return JsonResponse({'appointments': appointments, 'employees': employees,
                             'customers': customers, 'branches': branches})
