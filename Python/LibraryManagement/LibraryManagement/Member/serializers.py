from rest_framework import serializers
from .models import Member
from django.contrib.auth.hashers import make_password

class MemberSerializer(serializers.ModelSerializer):
    password = serializers.CharField(write_only=True)

    class Meta:
        model = Member
        fields = ['id', 'email', 'password', 'address', 'mobile', 'gender','is_admin']

    def create(self, validated_data):
        password=validated_data.pop('password')
        user=Member(**validated_data)
        user.set_password(password)
        user.save()
        return user
