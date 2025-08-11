import re

from django.db import transaction
from django.db.utils import IntegrityError
from rest_framework.views import APIView
from rest_framework.response import Response
from rest_framework.permissions import IsAuthenticated
from rest_framework.pagination import PageNumberPagination
from rest_framework import status

from .permissions import IsEmployee, IsManager, IsHR, IsCEO
from .serializers import (
    EmployeeDetailSerializer,
    EmployeeUpdateSerializer,
    OfficeAddressUpdateSerializer, DepartmentSerializer, EmployeeJobUpdateSerializer, EmployeeJobDetailsSerializer
)
from .ORManager import DatabaseOperationManager
from .models import Employees, EmployeeJobDetails, EmployeeOfficeAddressDetails, PayScale, CustomUser, Departments


class EmployeeProfileView(APIView):
    permission_classes = [IsAuthenticated]
    def get(self, request):
        try:
            emp = DatabaseOperationManager.get_employee_full_data(request.user)
            serializer = EmployeeDetailSerializer(emp)
            return Response({"result": "Success", "data": serializer.data}, status=200)
        except Exception as e:
            return Response({"result": "Failure", "message": str(e)}, status=500)

    @transaction.atomic
    def put(self, request):
        try:
            emp = DatabaseOperationManager.get_employee_full_data(request.user)

            # employee_data = {k: v for k, v in request.data.items() if k in ['emp_name', 'dob', 'dept','is_active']}
            # serializer = EmployeeUpdateSerializer(emp, data=employee_data, partial=True)
            data = request.data.copy()
            if isinstance(data.get("dept"), dict):
                data["dept"] = data["dept"].get("id")

            employee_data = {k: v for k, v in data.items() if k in ['emp_name', 'dob', 'dept', 'is_active']}
            serializer = EmployeeUpdateSerializer(emp, data=employee_data, partial=True)

            if serializer.is_valid():
                try:
                    serializer.save()
                except IntegrityError as e:
                    return Response({"result": "Failure", "message": "Database integrity error: " + str(e)}, status=400)

            else:
                return Response({"result": "Failure", "errors": serializer.errors}, status=400)

            if "address" in request.data:
                DatabaseOperationManager.update_office_address(emp, request.data["address"])

            return Response({"result": "Success", "message": "Profile updated successfully"}, status=200)
        except Exception as e:
            return Response({"result": "Failure", "message": str(e)}, status=500)

class TeamUpdateView(APIView):
    permission_classes = [IsAuthenticated & IsManager | IsCEO | IsHR]

    def patch(self, request):
        emp_id = request.data.get("emp_id")
        team_id = request.data.get("team_id")
        if not emp_id or team_id is None:
            return Response({"result": "Failure", "message": "emp_id and team_id are required"}, status=400)

        try:
            DatabaseOperationManager.change_team_id(emp_id, team_id)
            return Response({"result": "Success", "message": "Team ID updated"})
        except Exception as e:
            return Response({"result": "Failure", "message": str(e)}, status=500)

class UpdateSalaryView(APIView):
    permission_classes = [IsAuthenticated & (IsHR | IsCEO)]

    def post(self, request):
        emp_id = request.data.get("emp_id")
        salary = request.data.get("salary")
        if not emp_id or salary is None:
            return Response({"result": "Failure", "message": "emp_id and salary are required"}, status=400)
        try:
            salary = float(salary)
            if salary < 0:
                raise ValueError("Salary cannot be negative")
        except (ValueError, TypeError):
            return Response({"result": "Failure", "message": "Invalid salary value"}, status=400)
        try:
            DatabaseOperationManager.update_payscale(emp_id, salary)
            return Response({"result": "Success", "message": "Pay scale updated"})
        except Employees.DoesNotExist:
            return Response({"result": "Failure", "message": "Employee not found"}, status=404)
        except Exception as e:
            return Response({"result": "Failure", "message": str(e)}, status=500)

class AllEmployeesListView(APIView):
    # permission_classes = [IsAuthenticated & IsCEO]

    def get(self, request):
        try:
            paginator = PageNumberPagination()
            paginator.page_size = 10
            queryset = DatabaseOperationManager.get_all_employees()
            result_page = paginator.paginate_queryset(queryset, request)
            serializer = EmployeeDetailSerializer(result_page, many=True)
            return paginator.get_paginated_response(serializer.data)
        except Exception as e:
            return Response({"result": "Failure", "message": str(e)}, status=500)

from django.shortcuts import get_object_or_404
from django.core.exceptions import ObjectDoesNotExist
from django.utils.crypto import get_random_string

class CreateEmployeeView(APIView):
    permission_classes = []
    @transaction.atomic()
    def post(self, request):
        required_fields = ["emp_id", "username", "password", "emp_name", "dob", "dept", "role","is_active"]
        missing = [k for k in required_fields if k not in request.data]
        if missing:
            return Response({
                "result": "Failure",
                "message": f"Missing required fields: {', '.join(missing)}"
            }, status=400)

        emp_id = request.data["emp_id"]
        username = request.data["username"]
        password = request.data["password"]
        role = request.data["role"]

        if not re.match(r'^OTG\d{4}$', emp_id):
            return Response({
                "result": "Failure",
                "message": "Employee ID must be in format OTGXXXX"
            }, status=400)

        if len(password) < 6:
            return Response({
                "result": "Failure",
                "message": "Password must be at least 6 characters long"
            }, status=400)

        valid_roles = dict(CustomUser.ROLE_CHOICES).keys()
        if role not in valid_roles:
            return Response({
                "result": "Failure",
                "message": f"Invalid role. Must be one of: {', '.join(valid_roles)}"
            }, status=400)

        try:
            dept = Departments.objects.get(id=request.data["dept"])
        except Departments.DoesNotExist:
            return Response({
                "result": "Failure",
                "message": "Invalid department ID"
            }, status=400)

        try:
            user = CustomUser.objects.create_user(
                username=username,
                password=password,
                role=role
            )

            emp = Employees.objects.create(
                emp_id=emp_id,
                emp_name=request.data["emp_name"],
                dob=request.data["dob"],
                dept=dept,
                user=user,
                is_active=request.data["is_active"]
            )

            if "address" in request.data:
                DatabaseOperationManager.update_office_address(emp, request.data["address"])

            return Response({
                "result": "Success",
                "emp_id": emp.emp_id,
                "username": username,
                "role": role
            }, status=200)

        except IntegrityError:
            return Response({
                "result": "Failure",
                "message": "Username or Employee ID already exists"
            }, status=400)
        except Exception as e:
            return Response({
                "result": "Failure",
                "message": str(e)
            }, status=500)


class DeleteEmployeeView(APIView):
    permission_classes = [IsAuthenticated & IsHR | IsCEO]
    def delete(self, request):
        emp_id = request.data.get("emp_id")
        print(emp_id)
        if not emp_id:
            return Response({"result": "Failure", "message": "emp_id required"}, status=400)
        try:
            emp = Employees.objects.get(emp_id=emp_id)
            emp.delete()
            emp.user.is_active =0
            emp.user.save()
            return Response({"result": "Success", "message": "Employee deleted"})
        except Employees.DoesNotExist:
            return Response({"result": "Failure", "message": "Employee not found"}, status=404)
        except Exception as e:
            return Response({"result": "Failure", "message": str(e)}, status=500)

from .models import Designations, EmployeeJobDetails

class DesignationCRUDView(APIView):
    def get_permissions(self):
        if self.request.method in ['POST', 'DELETE']:
            return [IsAuthenticated(), IsCEO()]
        return [IsAuthenticated()]

    def post(self, request):
        data = request.data
        is_many = isinstance(data, list)
        valid_choices = [choice[0] for choice in Designations.DesignationChoices.choices]

        if is_many:
            invalid = [entry.get("designation") for entry in data if entry.get("designation") not in valid_choices]
        else:
            invalid = [data.get("designation")] if data.get("designation") not in valid_choices else []

        if invalid:
            return Response({
                "result": "Failure",
                "message": f"Invalid designation(s): {', '.join(filter(None, invalid))}"
            }, status=400)

        try:
            if is_many:
                objs = [Designations(designation=entry["designation"]) for entry in data]
                Designations.objects.bulk_create(objs, ignore_conflicts=True)
                return Response({"result": "Success", "message": f"{len(objs)} designations processed"}, status=201)
            else:
                Designations.objects.create(designation=data["designation"])
                return Response({"result": "Success", "message": "Designation created"}, status=201)
        except IntegrityError:
            return Response({"result": "Failure", "message": "Designation already exists"}, status=400)
        except Exception as e:
            return Response({"result": "Failure", "message": str(e)}, status=500)

    def get(self, request):
        designations = Designations.objects.all().values("id", "designation")
        return Response({"result": "Success", "data": list(designations)})

    def delete(self, request):
        title = request.data.get("designation")
        if not title:
            return Response({"result": "Failure", "message": "designation is required"}, status=400)

        try:
            des = Designations.objects.get(designation=title)

            if EmployeeJobDetails.objects.filter(designation=des).exists():
                return Response({"result": "Failure", "message": "Designation is in use and cannot be deleted"}, status=400)

            des.delete()
            return Response({"result": "Success", "message": "Designation deleted"})
        except Designations.DoesNotExist:
            return Response({"result": "Failure", "message": "Designation not found"}, status=404)
        except Exception as e:
            return Response({"result": "Failure", "message": str(e)}, status=500)

from rest_framework.utils.serializer_helpers import ReturnList

class DepartmentCRUDView(APIView):
    def get_permissions(self):
        if self.request.method in ['POST', 'DELETE']:
            return [IsAuthenticated(), IsCEO()]
        return [IsAuthenticated()]
    def post(self, request):
            data = request.data
            is_many = isinstance(data, list)

            serializer = DepartmentSerializer(data=data, many=is_many)
            if serializer.is_valid():
                serializer.save()
                result_data = serializer.data
                return Response({"result": "Success", "data": result_data}, status=201)
            return Response({"result": "Failure", "errors": serializer.errors}, status=400)

    def get(self, request):
        departments = Departments.objects.all()
        serializer = DepartmentSerializer(departments, many=True)
        return Response({"result": "Success", "data": serializer.data})

    def delete(self, request):
        dept_id = request.data.get("id")
        if not dept_id:
            return Response({"result": "Failure", "message": "id is required"}, status=400)
        try:
            dept = Departments.objects.get(id=dept_id)
            dept.delete()
            return Response({"result": "Success", "message": "Department deleted"})
        except Departments.DoesNotExist:
            return Response({"result": "Failure", "message": "Department not found"}, status=404)

class UpdateAnyEmployeeJobView(APIView):
    permission_classes = [IsAuthenticated & (IsHR | IsCEO | IsManager)]

    def put(self, request):
        emp_id = request.data.get("emp_id")
        job_data = request.data.get("job")

        if not emp_id or not job_data:
            return Response({"result": "Failure", "message": "emp_id and job fields are required"}, status=400)

        serializer = EmployeeJobUpdateSerializer(data=job_data)
        if not serializer.is_valid():
            return Response({"result": "Failure", "errors": serializer.errors}, status=400)

        try:
            new_job = DatabaseOperationManager.assign_or_update_job(emp_id, serializer.validated_data)
            return Response({
                "result": "Success",
                "message": f"Job details updated for {emp_id}",
                "job": EmployeeJobDetailsSerializer(new_job).data
            }, status=200)
        except Employees.DoesNotExist:
            return Response({"result": "Failure", "message": "Employee not found"}, status=404)
        except Designations.DoesNotExist:
            return Response({"result": "Failure", "message": "Invalid designation ID"}, status=400)
        except Exception as e:
            return Response({"result": "Failure", "message": str(e)}, status=500)

