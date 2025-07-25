from django.shortcuts import render,get_object_or_404
from Employee.models import *
from django.http import JsonResponse,HttpResponse,HttpResponseBadRequest
from django.views import View
from django.views.generic.list import ListView
from django.forms.models import model_to_dict

Tables={"Departments":Departments,"Employees":Employees,"Designations":Designations,"DepartmentHeads":DepartmentHeads,"PayScale":PayScale}
from django.db.models import Sum
from django.db.models import F
from django.db.models.functions import Coalesce
from django.db.models import Value
# Create your views here.
class Employees_by_Designation(View):
    def get(self,request):
        filter_param=request.GET.get('designation')
        if filter_param:
            return JsonResponse(list(Employees.objects.filter(designation__designation=filter_param).values()),safe=False)
        else:
            return JsonResponse({"Oops!":"Please enter the designation"},safe=False,status=400)


class GetSalaryById(View):
    def get(self,request):
        filter_param=request.GET.get('id')
        if filter_param:
            return JsonResponse(list(Employees.objects.get(emp_id=filter_param).payscale.all().values()),safe=False)
        else:
            return JsonResponse({"Oops!":"Please enter the id"},safe=False,status=400)

class GetEmployeesJoinedBefore(View):
    def get(self,request):
        filter_param=request.GET.get('date_joined')
        if filter_param:
            return JsonResponse(list(Employees.objects.filter(date_joined__lte=filter_param)),safe=False)
        else:
            return JsonResponse({"Oops!":"Please enter the date"},safe=False,status=400)

class GetSeniorOrEarlyEmployees(View):
    def get(self, request):
        filter_param = request.GET.get('date')
        if filter_param:
            from django.db.models import Q
            data = Employees.objects.filter(
                Q(designation__designation="SSE") | Q(date_joined__lt=filter_param)
            ).values()
            return JsonResponse(list(data), safe=False)
        else:
            return JsonResponse({"Oops!": "Please provide a valid date"}, status=400)

class TopThreePaidEmployees(View):
    def get(self, request):
        data = PayScale.objects.select_related('emp').values(
            emp_name=F('emp__emp_name'),
            salary=F('salary')
        ).order_by('-salary')[:3]
        return JsonResponse(list(data), safe=False)

class GetEmployeesWithSalary(View):
    def get(self, request):
        employees = Employees.objects.prefetch_related('payscale')
        data = []

        for emp in employees:
            data.append({
                "emp_id": emp.emp_id,
                "emp_name": emp.emp_name,
                "salary": emp.payscale.first().salary if emp.payscale.exists() else "No Salary"
            })

        return JsonResponse(data, safe=False)

from django.db.models.functions import Coalesce
from django.db.models import Value

class GetSalaryWithDefault(View):
    def get(self, request):
        data = PayScale.objects.annotate(
            final_salary=Coalesce('salary', Value(0))
        ).values('emp__emp_id', 'emp__emp_name', 'final_salary')
        return JsonResponse(list(data), safe=False)

from django.db.models import CharField
from django.db.models.functions import Cast

class CastSalaryToString(View):
    def get(self, request):
        data = PayScale.objects.annotate(
            salary_str=Cast('salary', CharField())
        ).values('emp__emp_id', 'emp__emp_name', 'salary_str')
        return JsonResponse(list(data), safe=False)

from django.db.models.functions import Abs
from django.db.models import F

class SalaryDifferenceFrom50K(View):
    def get(self, request):
        data = PayScale.objects.annotate(
            diff=Abs(F('salary') - 50000)
        ).values('emp__emp_id', 'emp__emp_name', 'salary', 'diff')

        return JsonResponse(list(data), safe=False)


from django.db.models.functions import ExtractYear
from django.db.models import ExpressionWrapper, IntegerField
from datetime import date

class GetEmployeesOlderThan(View):
    def get(self, request):
        current_year = date.today().year
        data = Employees.objects.annotate(
            age=ExpressionWrapper(current_year - ExtractYear('dob'), output_field=IntegerField())
        ).filter(age__gte=4).values('emp_id', 'emp_name', 'age')  

        return JsonResponse(list(data), safe=False)
