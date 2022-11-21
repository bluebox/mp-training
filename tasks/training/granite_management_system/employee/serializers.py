from rest_framework import serializers

from .models import Employee, Role


class roleSerializer(serializers.ModelSerializer):

    class Meta:
        model = Role
        fields = "__all__"


class employeeSerializer(serializers.ModelSerializer):

    role_id = roleSerializer()

    class Meta:
        model = Employee
        fields = '__all__'
