from rest_framework import serializers
from ecom_app.models import Customer,employee


class CustomerSerializer(serializers.ModelSerializer):
    class Meta:
        model = Customer
        fields = "__all__"




class Myserializer1(serializers.HyperlinkedModelSerializer):
    class Meta:
        model=employee
        fields='__all__'
