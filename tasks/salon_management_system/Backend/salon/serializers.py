from rest_framework import serializers

from .models import User

class Userserializer(serializers.ModelSerializer):
    class Meta:
        model = User

        fields = ['id','username','first_name','last_name','email','is_staff'] 

# class 