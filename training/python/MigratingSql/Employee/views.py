from django.shortcuts import render
from Employee.models import Departments
from django.http import JsonResponse

# Create your views here.
def ExecuteThis(request):
    departments = Departments.objects.values()  
    data = list(departments)  
    return JsonResponse(data, safe=False)
