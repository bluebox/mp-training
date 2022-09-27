from rest_framework import serializers
from ecom_app.models import Customer,employee, Products_Details


class CustomerSerializer(serializers.ModelSerializer):
    class Meta:
        model = Customer
        fields = "__all__"




class Myserializer1(serializers.HyperlinkedModelSerializer):
    class Meta:
        model =employee
        fields = '__all__'



class ProductListSerializer(serializers.ModelSerializer):
    class Meta:
        model = Products_Details
        fields = "__all__"