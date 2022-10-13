"""Serializers"""
from rest_framework import serializers
from Pharmacy_Database.models import Customer, Manufacturer, Drug, Prescribe, Purchase
from Pharmacy_Database.models import Doctor, Employee, Distributor
from Pharmacy_Database.models import Cart, Invoice, Order, Supply, Sales
# pylint: disable=too-few-public-methods



class CustomerSerializer(serializers.ModelSerializer):
    """Class"""
    class Meta:
        """Class"""
        model = Customer
        fields = ('cust_id', 'first_name', 'last_name', 'contact', 'email', 'address')

class ManufacturerSerializer(serializers.ModelSerializer):
    """Class"""
    class Meta:
        """Class"""
        model = Manufacturer
        fields = ('company_id', 'company_name', 'contact', 'email', 'address' )


class DoctorSerializer(serializers.ModelSerializer):
    """Class"""
    class Meta:
        """Class"""
        model = Doctor
        fields = ('doc_id', 'first_name',
                  'last_name', 'specialization',
                  'contact', 'email', 'address')


class EmployeeSerializer(serializers.ModelSerializer):
    """Class"""
    class Meta:
        """Class"""
        model = Employee
        fields = ('emp_id', 'first_name', 'last_name',
                  'doj', 'designation', 'job_type',
                  'salary', 'contact', 'email', 'address' )


class DistributorSerializer(serializers.ModelSerializer):
    """Class"""
    class Meta:
        """Class"""
        model = Distributor
        fields = ('dist_id', 'dist_name', 'contact', 'email', 'address' )

class DrugSerializer(serializers.ModelSerializer):
    """Class"""
    class Meta:
        """Class"""
        model = Drug
        fields = ('drug_id', 'drug_name', 'dist_name',
                  'company_name', 'mrp', 'discount', 'stock', 'mfg_date')


class SalesSerializer(serializers.ModelSerializer):
    """Class"""
    class Meta:
        """Class"""
        model = Sales
        fields = ('sale_id', 'sale_date', 'emp_id', 'cust_id', 'drug_id', 'quantity', 'price')


class PrescribeSerializer(serializers.ModelSerializer):
    """Class"""
    class Meta:
        """Class"""
        model = Prescribe
        fields = ('sale_id', 'sale_date', 'emp_id', 'cust_id', 'drug_id', 'quantity', 'price' )


class PurchaseSerializer(serializers.ModelSerializer):
    """Class"""
    class Meta:
        """Class"""
        model = Purchase
        fields = ('purchase_id', 'purchase_date', 'drug_id', 'drug_name')


class SupplySerializer(serializers.ModelSerializer):
    """Class"""
    class Meta:
        """Class"""
        model = Supply
        fields = ('purchase_id', 'purchase_date', 'drug_id', 'drug_name')


class CartSerializer(serializers.ModelSerializer):
    """Class"""
    class Meta:
        """Class"""
        model = Cart
        fields = ('purchase_id', 'purchase_date', 'drug_id', 'drug_name')


class OrderSerializer(serializers.ModelSerializer):
    """Class"""
    class Meta:
        """Class"""
        model = Order
        fields = ('purchase_id', 'purchase_date', 'drug_id', 'drug_name')


class InvoiceSerializer(serializers.ModelSerializer):
    """Class"""
    class Meta:
        """Class"""
        model = Invoice
        fields = ('bill_id', 'billing_date', 'order_id',
                  'cust_id', 'drug_id', 'payment_mode',
                  'quantity', 'price' )
