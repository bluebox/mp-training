from django.shortcuts import render
from django.views.decorators.csrf import csrf_exempt
from rest_framework.parsers import JSONParser
from django.http.response import JsonResponse

from framework.models import department,employee
from framework.serializers import DepartmentSerializer,EmployeeSerializer



# Create your views here.

@csrf_exempt
def departmentApi(request,id=0):
    
    if request.method=='GET':
        departments = department.objects.all()
        dept_ser = DepartmentSerializer(departments,many = True)
        return JsonResponse(dept_ser.data,safe = False)
    elif request.method == 'POST':
        dept_data = JSONParser().parse(request)
        dept_ser = DepartmentSerializer(data = dept_data)
        if dept_ser.is_valid():
            dept_ser.save()
            return JsonResponse("successfully posted",safe = False)    
        return JsonResponse("failed to add")
    elif request.method == 'PUT':
        dept_data = JSONParser().parse(request)
        dept_y = department.objects.get(deptid = dept_data['deptid'])
        dept_ser = DepartmentSerializer(data=dept_data)
        if dept_ser.is_valid():
            dept_ser.save()
            return JsonResponse("SUCCESFULLY UPDATED",safe=False)
        return JsonResponse("FAILED TO UPDATE")
    elif request.method == 'DELETE':
        DATA = department.objects.get(deptid = id)     
        DATA.delete()
        return JsonResponse("SUCCESFULLY DELETED",safe=False)   