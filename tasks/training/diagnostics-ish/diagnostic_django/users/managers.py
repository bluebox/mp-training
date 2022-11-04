""" manager classes for users views"""
from rest_framework import status

# jwt
import json
# rest framework
from rest_framework.parsers import JSONParser
from django.db.models import Q

from appointment.serializers import AppointmentSerializer
from .models import User, Customer, Staff
from users.serializers import UserSerializer, CustomerSerializer, EmployeeSerializer


class StaffManager:
    """staff manager method"""

    @staticmethod
    def get_detail_staff(request, employee_id):
        """get staff detail"""
        employee = Staff.objects.filter(staff_id=employee_id).first()
        employee_serializer = EmployeeSerializer(employee, many=False)
        user_serializer = UserSerializer(employee.user_id, many=False)

        return ({"employee_details": employee_serializer.data,
                 "user_details": user_serializer.data})

    @staticmethod
    def delete_staff(request, employee_id):
        """delete employee"""
        employee = Staff.objects.filter(staff_id=employee_id).first()
        if employee:
            employee.delete()
            return {'success': 'Employee deleted successfully.'}
        else:
            return {'success': 'Employee is not deleted successfully.'}

    @staticmethod
    def update_staff(request, employee_id):
        """update staff"""
        employee_data = JSONParser().parse(request)
        employee = Staff.objects.filter(staff_id=employee_id).first()
        user = User.objects.filter(id=employee.user_id.id).first()
        serializer = UserSerializer(user, data=employee_data)
        empl_serializer = EmployeeSerializer(employee,
                                             data={"designation":
                                                   employee_data["designationControl"],
                                                   "qualification": employee_data['qualification'],
                                                   "staff_id": employee.staff_id,
                                                   "user_id": user.id,
                                                   "salary": int(employee_data['salary']), "years_of_experience": int(
                                                     employee_data['years_of_experience']) or 0,
                                                   'branch': employee_data['branchControl']})

        if serializer.is_valid():
            serializer.save()
            if empl_serializer.is_valid():
                empl_serializer.save()
                return {'data': serializer.data, 'message': "updated"}
            else:
                error_list = [serializer.errors[error][0] for error in serializer.errors]
                return {"message": error_list}
        else:
            error_list = [serializer.errors[error][0] for error in serializer.errors]
            return {"message": error_list}

    @staticmethod
    def filter_employee(request):
        """filter searched employees"""
        text = request.GET['text']
        employees = Staff.objects.filter(Q(staff_id__icontains=text)
                                         | Q(user_id__username__icontains=text))
        employees = list(employees.values(
            'staff_id', 'user_id__username'
        ))
        return {'employees': json.dumps(employees)}


class CustomerManager:
    """manager for customner"""
    @staticmethod
    def get_detail_customer(request, customer_id):
        """get customer detail"""
        customer = Customer.objects.filter(customer_id=customer_id).first()
        customer_serializer = CustomerSerializer(customer, many=False)
        user_serializer = UserSerializer(customer.user_id, many=False)
        appointments = customer.appointment_set.all()
        appointments = AppointmentSerializer(appointments, many=True)
        return {"customer_details": customer_serializer.data,
                "user_details": user_serializer.data,
                'appointments': appointments.data}

    @staticmethod
    def delete_customer(request, customer_id):
        """delete customer"""
        customer = Customer.objects.filter(customer_id=customer_id).first()
        if customer:
            customer.delete()
            return {'success': 'Customer deleted successfully.'}
        else:
            return {'success': 'customer is not deleted successfully.'}
