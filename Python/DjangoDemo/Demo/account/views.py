from django.views.decorators.csrf import csrf_exempt
from django.http import JsonResponse
import json

from rest_framework.authentication import SessionAuthentication
from rest_framework.permissions import AllowAny
from rest_framework.views import APIView
from rest_framework import generics
from .models import Student, Example, Employee
from .serializers import StudentSerializer, ExampleSerializer, EmployeeSerializer, UserSerializer
from rest_framework.response import Response
from rest_framework import status
from rest_framework.decorators import api_view


@csrf_exempt
def add_student(request):
    if request.method == 'POST':
        try:
            data = json.loads(request.body)
            student = Student(name=data.get('name'), age=data.get('age'))
            student.save()
            return JsonResponse({'success': "data stored in db"}, status=200)
        except Exception as e:
            return JsonResponse({'error': str(e)}, status=500)
    return JsonResponse({'failed': 'Invalid request'}, status=400)


@api_view(['GET', 'POST'])
def get_data(request):
    if request.method == 'GET':
        try:
            students = Student.objects.all()
            serializer = StudentSerializer(students, many=True).data
            return Response({'data': serializer}, status=status.HTTP_200_OK)
        except Exception as e:
            return Response({'err': str(e)}, status=status.HTTP_500_INTERNAL_SERVER_ERROR)

    elif request.method == 'POST':
        serializer = StudentSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)


@api_view(['GET'])
def get_student_data(request, pk):
    # if request.method=='GET':
    #     student=Student.objects.filter(id=pk).first()
    #     serializer=StudentSerializer(student).data
    #     return Response({'data':serializer},status=status.HTTP_200_OK)
    # return Response({'err':" make a valid request"}) this using filter it will return [] if not found so we use get
    if request.method == 'GET':
        try:
            student = Student.objects.get(id=pk)
            serializer = StudentSerializer(student).data
            return Response({'data': serializer}, status=status.HTTP_200_OK)
        except Exception as e:
            return Response({'err': 'student not found'}, status=status.HTTP_404_NOT_FOUND)
    return Response({'err': " make a valid request"})

class get_employee_data(APIView):
    def get(self,request):
        emp_id=request.GET.get('id')
        employee=Example.objects.get(pk=emp_id)
        employee=ExampleSerializer(employee).data
        return Response({'data':employee},status=status.HTTP_200_OK)
    def post(self,request):
        employee=ExampleSerializer(data=request.data)
        if employee.is_valid():
            employee.save()
            return Response(status=status.HTTP_200_OK)
        return Response(status=status.HTTP_401_UNAUTHORIZED)

class Employees(generics.ListCreateAPIView):
    queryset = Employee.objects.all()
    serializer_class = EmployeeSerializer

class EmployeeDetails(generics.RetrieveUpdateDestroyAPIView):
    queryset = Employee.objects.all()
    serializer_class = EmployeeSerializer
    lookup_field = 'pk'

from django.contrib.auth.models import User
from django.contrib.auth import authenticate, login, logout
from django.http import JsonResponse
from django.views.decorators.csrf import csrf_exempt
from django.contrib.auth.decorators import login_required
import json

@csrf_exempt
def signup_view(request):
    if request.method == 'POST':
        data = json.loads(request.body)
        username = data.get("username")
        password = data.get("password")
        if User.objects.filter(username=username).exists():
            return JsonResponse({'error': 'User already exists'}, status=400)
        User.objects.create_user(username=username, password=password)
        return JsonResponse({'message': 'Signup successful'}, status=201)
    return JsonResponse({'error': 'Invalid request method'}, status=405)

@csrf_exempt
def login_view(request):
    if request.method == 'POST':
        data = json.loads(request.body)
        username = data.get("username")
        password = data.get("password")
        user = authenticate(username=username, password=password)
        if user:
            login(request, user)
            request.session['user_id'] = user.id
            return JsonResponse({'message': 'Login successful'})
        return JsonResponse({'error': 'Invalid credentials'}, status=401)
    return JsonResponse({'error': 'Invalid request method'}, status=405)


@login_required()
def dashboard_view(request):
    if request.method == 'POST':
        data = json.loads(request.body)
        return JsonResponse({'message': 'Received', 'data': data})
    return JsonResponse({'error': 'Invalid method'}, status=405)

@csrf_exempt
def logout_view(request):
    logout(request)
    return JsonResponse({'message': 'Logged out'})

class CsrfExemptSessionAuthentication(SessionAuthentication):
    def enforce_csrf(self, request):
        return
class User_data(generics.ListCreateAPIView):
    queryset = User.objects.all()
    serializer_class =UserSerializer
    authentication_classes = [CsrfExemptSessionAuthentication]
    permission_classes = [AllowAny]
