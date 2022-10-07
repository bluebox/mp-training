from rest_framework import serializers
from .models import CustomerTable, EmployesTable, AccountTable


# class UserSerializers(serializers.Serializer):
#     # first_name = serializers.CharField(max_length=50)
#     # last_name = serializers.CharField(max_length=50)
#     # email = serializers.CharField(max_length=50)
#     # password = serializers.IntegerField()
#
#     customer_id = serializers.IntegerField(primary_key=True)
#     customer_name = serializers.CharField(max_length=50)
#     phone_number = serializers.CharField(max_length=100)
#     customer_email = serializers.CharField(max_length=50)
#     customer_password = serializers.IntegerField()
#     customer_address = serializers.CharField(max_length=100)
#
#
#     def create(self, validated_data):
#         return CustomerTable.objects.create(**validated_data)
#
#     def update(self,instance, validated_data):
#         instance.first_name = validated_data.get('first_name', instance.first_name)
#         instance.last_name = validated_data.get('last_name', instance.last_name)
#         instance.email = validated_data.get('email', instance.email)
#         instance.password = validated_data.get('password', instance.password)
#         instance.save()
#         return instance

class ModSeri(serializers.ModelSerializer):
    class Meta:
        model = CustomerTable
        fields = ["customer_id", "customer_name", "phone_number", "customer_password", "customer_email"]

        # fields = '__all__'  # to get all keys from model

class EmpSerializer(serializers.ModelSerializer):

    class Meta:
        model = EmployesTable
        fields = '__all__'
    # to get all keys from model

class EmployeeEmailAndPass(serializers.ModelSerializer):
    class Meta:
        model = AccountTable
        fields = '__all__'