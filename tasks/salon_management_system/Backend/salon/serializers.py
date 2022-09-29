from dataclasses import fields

from rest_framework import serializers

from .models import Branch, Employee, User, services_provided,Client

class Userserializer(serializers.ModelSerializer):
    class Meta:
        model = User

        fields = ['id','username','first_name','last_name','email','is_staff'] 

class ClientSerializer(serializers.ModelSerializer):
    class Meta:
        model = Client
        fields = "__all__"

class EmployeeSerializer(serializers.ModelSerializer):
    class Meta:
        model = Employee
        fields = "__all__"

class BranchSerializer(serializers.ModelSerializer):

    class Meta:
        model = Branch

        fields = "__all__"

class ServicesSerializer(serializers.ModelSerializer):
     class Meta:
        model = services_provided

        fields = "__all__"

