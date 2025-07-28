from rest_framework import serializers
from .models import Employees, EmployeeJobDetails, EmployeeOfficeAddressDetails, PayScale, Departments, Designations


class PayScaleSerializer(serializers.ModelSerializer):
    class Meta:
        model = PayScale
        fields = ['salary']

class EmployeeJobDetailsSerializer(serializers.ModelSerializer):
    class Meta:
        model = EmployeeJobDetails
        fields = ['designation', 'effective_from', 'effective_till', 'team_id']

class EmployeeOfficeAddressSerializer(serializers.ModelSerializer):
    class Meta:
        model = EmployeeOfficeAddressDetails
        fields = ['country', 'state', 'city']

class EmployeeDetailSerializer(serializers.ModelSerializer):
    job_details = EmployeeJobDetailsSerializer(many=True, read_only=True)
    address_details = EmployeeOfficeAddressSerializer(read_only=True)
    pay_scales = PayScaleSerializer(many=True, read_only=True)

    class Meta:
        model = Employees
        fields = ['emp_id', 'emp_name', 'dob', 'date_joined', 'dept', 'is_active',
                  'job_details', 'address_details', 'pay_scales']

class EmployeeUpdateSerializer(serializers.ModelSerializer):
    dept = serializers.PrimaryKeyRelatedField(queryset=Departments.objects.all())
    class Meta:
        model = Employees
        fields = ['emp_name', 'dob', 'dept']

class OfficeAddressUpdateSerializer(serializers.ModelSerializer):
    class Meta:
        model = EmployeeOfficeAddressDetails
        fields = ['country', 'state', 'city']
from .models import Departments

class DepartmentSerializer(serializers.ModelSerializer):
    class Meta:
        model = Departments
        fields = ['id', 'dept_name']

class EmployeeJobUpdateSerializer(serializers.ModelSerializer):
    designation = serializers.PrimaryKeyRelatedField(queryset=Designations.objects.all())

    class Meta:
        model = EmployeeJobDetails
        fields = ['designation', 'effective_from', 'effective_till', 'team_id']
