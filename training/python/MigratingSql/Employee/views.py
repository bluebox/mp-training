from django.shortcuts import render,get_object_or_404
from Employee.models import *
from django.http import JsonResponse,HttpResponse,HttpResponseBadRequest
from django.views import View
from django.views.generic.list import ListView
from django.forms.models import model_to_dict

Tables={"Departments":Departments,"Employees":Employees,"Designations":Designations,"DepartmentHeads":DepartmentHeads,"PayScale":PayScale}


# Create your views here.
class Employees_Dgn_NotDetermined(View):
    def get(self,request):
        return JsonResponse(list(Employees.objects.select_related("designation").filter(designation=Designations.objects.get(designation=Designations.DesignationChoices.NOD)).values()),safe=False)


class ExecuteReadSpecific(View):
    def get(self,request,Table,id):
        pass

class UpdateData(View):
    pass

class DeleteData(View):
    pass

class LoginPage(View):
    def get(self,request,Name):
        return HttpResponse(f"<h1>Welcome to the login page {Name} !!</h1>")

