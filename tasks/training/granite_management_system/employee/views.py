from rest_framework.response import Response
from rest_framework.views import APIView

from .businessLogic.manager import getEmployeeDetails, addEmployee, updateEmployee
from .models import  Employee
from granite_mart.views import verifyToken


# Create your views here.

class EmployeeAPI(APIView):

    def get(self, request, pk=None, format=None):
        token = request.COOKIES.get('token')
        refresh = request.COOKIES.get('refresh')
        valid = verifyToken(token, refresh, request)

        if valid == '200':
            response = getEmployeeDetails(request, pk)
            return Response(response)
        else:
            return Response(' an Error Occured')

    def post(self, request, format=None):
        token = request.COOKIES.get('token')
        refresh = request.COOKIES.get('refresh')
        valid = verifyToken(token, refresh, request)

        if valid == '200':
            response = addEmployee(request)
            return Response(response)
        else:
            return Response(' an Error Occured')

    def put(self, request, pk, format=None):
        token = request.COOKIES.get('token')
        refresh = request.COOKIES.get('refresh')
        valid = verifyToken(token, refresh, request)

        if valid == '200':
            response = updateEmployee(request, pk)
            return Response(response)
        else:
            return Response('an error occured')

    def delete(self, request, pk, format=None):
        token = request.COOKIES.get('token')
        refresh = request.COOKIES.get('refresh')
        valid = verifyToken(token, refresh, request)

        if valid == '200':
            emp = Employee.objects.get(employee_id=pk)
            emp.delete()
            return Response('deleted')