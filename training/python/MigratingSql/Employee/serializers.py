from rest_framework import serializers
from .models import Employees, EmployeeJobDetails, EmployeeOfficeAddressDetails, PayScale, Departments, Designations

class DepartmentSerializer(serializers.ModelSerializer):
    class Meta:
        model = Departments
        fields = '__all__'

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
    dept = DepartmentSerializer(read_only=True)
    is_active = serializers.SerializerMethodField()
    role = serializers.CharField(source='user.role', read_only=True)
    class Meta:
        model = Employees
        fields = '__all__'
    def get_is_active(self, obj):
        return 1 if obj.is_active else 0

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


class EmployeeJobUpdateSerializer(serializers.ModelSerializer):
    designation = serializers.PrimaryKeyRelatedField(queryset=Designations.objects.all())

    class Meta:
        model = EmployeeJobDetails
        fields = ['designation', 'effective_from', 'effective_till', 'team_id']
