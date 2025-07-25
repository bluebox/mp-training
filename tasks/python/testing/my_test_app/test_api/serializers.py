from rest_framework import serializers
from .models import *

class UserSerializer(serializers.ModelSerializer):
    class Meta:
        model = ModelUser
        fields = '__all__'

class BaseUserSerializer(serializers.ModelSerializer):
    class Meta:
        model = MyBaseUser
        fields = '__all__'

class LanguageSerializer(serializers.ModelSerializer):
    class Meta:
        model = Languages