from rest_framework import serializers
from .models import CustomerTable, EmployesTable, AccountTable, CustomerImageTable, TransactionTable, DebitCard, \
    CreditCard, CustomerRequests, CustomerLoanStatusTable, LoanDetails


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
        fields = ["customer_id", "customer_name", "phone_number", "customer_password", "customer_email", "customer_address", "avg_monthly_salary", "employee_type"]

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


class ImageSerializer(serializers.ModelSerializer):
    class Meta:
        model = CustomerImageTable
        fields = '__all__'

class TransactionSerializer(serializers.ModelSerializer):
    class Meta:
        model = TransactionTable
        fields = ["transaction_id", "transaction_amount","receiver_id", "sender_id", "receiver_status","sender_status", "transaction_time"]


class DebiCardSerializer(serializers.ModelSerializer):
    class Meta:
        model = DebitCard
        fields = '__all__'

class CreditCardSerializer(serializers.ModelSerializer):
    class Meta:
        model = CreditCard
        fields = '__all__'


class RequestSerializer(serializers.ModelSerializer):
    class Meta:
        model = CustomerRequests
        fields = '__all__'


class LoanStatusSerializer(serializers.ModelSerializer):
    class Meta:
        model = CustomerLoanStatusTable
        fields = '__all__'

class LoanSerializer(serializers.ModelSerializer):
    class Meta:
        model = LoanDetails
        fields = "__all__"


class CustomerTotalListSerializer(serializers.ModelSerializer):
    customer = ModSeri()

    class Meta:
        model = AccountTable
        fields = "__all__"


class LoansSerializer(serializers.ModelSerializer):
    customer = ModSeri()

    class Meta:
        model = LoanDetails
        fields = "__all__"
