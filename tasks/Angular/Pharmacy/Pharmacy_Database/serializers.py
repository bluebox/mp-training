from dataclasses import fields
from pyexpat import model
from rest_framework import serializers
from Pharmacy_Database.models import *


class CustomerSerializer(serializers.ModelSerializer):
    class Meta:
        model = Customer
        fields = ('cust_id', 'first_name', 'last_name', 'contact', 'email', 'address')

class ManufacturerSerializer(serializers.ModelSerializer):
    class Meta:
        model = Manufacturer
        fields = ('company_id', 'company_name', 'contact', 'email', 'address' )
        
class DoctorSerializer(serializers.ModelSerializer):
    class Meta:
        model = Doctor
        fields = ('doc_id', 'first_name', 'last_name', 'specialization', 'contact', 'email', 'address')
        

class EmployeeSerializer(serializers.ModelSerializer):
    class Meta:
        model = Employee
        fields = ('emp_id', 'first_name', 'last_name', 'doj', 'designation', 'job_type', 'salary', 'contact', 'email', 'address' )
        
class DistributorSerializer(serializers.ModelSerializer):
    class Meta:
        model = Distributor
        fields = ('dist_id', 'dist_name', 'contact', 'email', 'address' )
        
class DrugSerializer(serializers.ModelSerializer):
    class Meta:
        model = Drug
        fields = ('drug_id', 'drug_name', 'dist_id', 'company_id', 'mrp', 'discount', 'stock', 'mfg_date')
        
class SalesSerializer(serializers.ModelSerializer):
    class Meta:
        model = Sales
        fields = ('sale_id', 'sale_date', 'emp_id', 'cust_id', 'drug_id', 'quantity', 'price')
        
class PrescribeSerializer(serializers.ModelSerializer):
    class Meta:
        model = Prescribe
        fields = ('sale_id', 'sale_date', 'emp_id', 'cust_id', 'drug_id', 'quantity', 'price' )
        
class PurchaseSerializer(serializers.ModelSerializer):
    class Meta:
        model = Purchase
        fields = ('purchase_id', 'purchase_date', 'drug_id', 'drug_name')
        
class SupplySerializer(serializers.ModelSerializer):
    class Meta:
        model = Supply
        fields = ('purchase_id', 'purchase_date', 'drug_id', 'drug_name')
        
class CartSerializer(serializers.ModelSerializer):
    class Meta:
        model = Cart
        fields = ('purchase_id', 'purchase_date', 'drug_id', 'drug_name')
        
class OrderSerializer(serializers.ModelSerializer):
    class Meta:
        model = Cart
        fields = ('purchase_id', 'purchase_date', 'drug_id', 'drug_name')
        
class InvoiceSerializer(serializers.ModelSerializer):
    class Meta:
        model = Invoice
        fields = ('bill_id', 'billing_date', 'order_id', 'cust_id', 'drug_id', 'payment_mode', 'quantity', 'price' )