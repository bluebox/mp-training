from django.core.exceptions import ObjectDoesNotExist
from django.shortcuts import render,get_object_or_404
from httpcore import Response

from Employee.models import *
from django.http import JsonResponse,HttpResponse,HttpResponseBadRequest
from django.views import View
from django.views.generic.list import ListView
from django.forms.models import model_to_dict
from django.db.models import Sum
from django.db.models import F
from django.db.models.functions import Coalesce
from django.db.models import Value
from django.db.models.functions import ExtractYear
from django.db.models import ExpressionWrapper, IntegerField
from datetime import date
from django.db.models import CharField
from django.db.models.functions import Cast
from django.db.models.functions import Abs


# Create your views here.
class EmployeesByDesignation(View):
    def get(self,request):
        filter_param=request.GET.get('designation')
        try:
            if filter_param:
                return JsonResponse(list(Employees.objects.filter(designation__designation=filter_param).values()),safe=False)
            else:
                return JsonResponse({"Oops!":"Please enter the designation"},safe=False,status=400)
        except ObjectDoesNotExist as e:
            return JsonResponse({"Oops!":"Sorry object not found"},safe=False,status=400)


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


class FilteredEmployees(View):
    def get(self, request):
        filtered = Employees.objects.filter(
            Q(dept__dept_name__in=['R&D', 'FSD']) &
            Q(date_joined__year__gt=2022)
        ).select_related('dept', 'designation').values('emp_id', 'emp_name', 'dept__dept_name', 'date_joined')
        return JsonResponse(list(filtered), safe=False)


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
                "salary": emp.payscale.salary if emp.payscale.exists() else "No Salary"
            })

        return JsonResponse(data, safe=False)


class GetSalaryWithDefault(View):
    def get(self, request):
        data = PayScale.objects.annotate(
            final_salary=Coalesce('salary', Value(0.00))
        ).values('emp__emp_id', 'emp__emp_name', 'final_salary')
        return JsonResponse(list(data), safe=False)


class CastSalaryToString(View):
    def get(self, request):
        data = PayScale.objects.annotate(
            salary_str=Cast('salary', CharField())
        ).values('emp__emp_id', 'emp__emp_name', 'salary_str')
        return JsonResponse(list(data), safe=False)


class GetEmployeesOlderThan(View):
    def get(self, request):
        current_year = date.today().year
        data = Employees.objects.annotate(
            age=ExpressionWrapper(current_year - ExtractYear('dob'), output_field=IntegerField())
        ).filter(age__gte=4).values('emp_id', 'emp_name', 'age')

        return JsonResponse(list(data), safe=False)
