from django.shortcuts import render,get_object_or_404
from Employee.models import Departments,Employees,Designations,DepartmentHeads,PayScale
from django.http import JsonResponse,HttpResponse,HttpResponseBadRequest
from django.views import View
from django.views.generic.list import ListView
from django.forms.models import model_to_dict

Tables={"Departments":Departments,"Employees":Employees,"Designations":Designations,"DepartmentHeads":DepartmentHeads,"PayScale":PayScale}


# Create your views here.
class Populate(View):
    pass

class ExecuteRead(ListView):
    # def get(self,request,Table):
    #     print(Tables,Table)
    #     return JsonResponse(list(Tables.get(Table).objects.values()),safe=False) if Table in Tables else HttpResponse(f"<h1>There is no table with this name try again!!</h1>")
    template_name='Employee/FlattenData.html'
    context_object_name="table_contents"
    paginate_by=5
    def get_queryset(self):
        model=Tables.get(self.kwargs['Table'])
        return model.objects.all()
    def get_context_data(self):
        context=super().get_context_data(self.kwargs['table_contents'])
        pass
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

