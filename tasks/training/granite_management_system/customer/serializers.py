from rest_framework import serializers
from .models import Customer

# serializer for customer table
class customerSerializer(serializers.ModelSerializer):

    class Meta:
        model = Customer
        fields = "__all__"
