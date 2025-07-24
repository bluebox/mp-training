from rest_framework import serializers
from .models import ModelUser

class UserSerializer(serializers.ModelSerializer):
    class Meta:
        model = ModelUser
        fields = '__all__'

