from django.shortcuts import render
from rest_framework.response import Response
from rest_framework.views import APIView

from .models import Role, Employee
from .serializers import employeeSerializer
from granite_mart.views import verifyToken


# Create your views here.

class EmployeeAPI(APIView):

    def get(self, request, pk=None, format=None):
        token = request.COOKIES.get('token')
        refresh = request.COOKIES.get('refresh')
        valid = verifyToken(token, refresh, request)
        if valid == '200':
            if pk is not None:
                emp = Employee.objects.get(employee_id=pk)
                serialize = employeeSerializer(emp)
                return Response(serialize.data)

            emp = Employee.objects.all()
            serialize = employeeSerializer(emp, many=True)
            return Response(serialize.data)

    def post(self, request, format=None):
        token = request.COOKIES.get('token')
        refresh = request.COOKIES.get('refresh')
        valid = verifyToken(token, refresh, request)
        if valid == '200':

            serializer = employeeSerializer(data=request.data)
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
            serialize = employeeSerializer(emp)
            return Response(serialize.data)

    def put(self, request, pk, format=None):
        token = request.COOKIES.get('token')
        refresh = request.COOKIES.get('refresh')
        valid = verifyToken(token, refresh, request)
        if valid == '200':

            serializer = employeeSerializer(data=request.data)
            print(serializer.is_valid())
            print(serializer.errors)
            employee_name = request.data['employee_name']
            doj = request.data['doj']
            role = request.data['role']
            salary = request.data['salary']
            phone = request.data['phone']
            email = request.data['email']
            address = request.data['address']
            emp = Employee(employee_id=pk, employee_name=employee_name, doj=doj,
                           role_id=Role.objects.get(role_name=role), salary=salary, phone=phone, email=email,
                           address=address)
            emp.save()
            serialize = employeeSerializer(emp)
            return Response(serialize.data)

    def delete(self, request, pk, format=None):
        token = request.COOKIES.get('token')
        refresh = request.COOKIES.get('refresh')
        valid = verifyToken(token, refresh, request)
        if valid == '200':

            emp = Employee.objects.get(employee_id=pk)
            emp.delete()
            return Response('deleted')