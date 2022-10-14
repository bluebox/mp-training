from rest_framework import serializers
from .models import UserDetails


class UserSerializers(serializers.Serializer):
    first_name = serializers.CharField(max_length=50)
    last_name = serializers.CharField(max_length=50)
    email = serializers.CharField(max_length=50)
    password = serializers.IntegerField()


    def create(self, validated_data):
        return UserDetails.objects.create(**validated_data)

    def update(self,instance, validated_data):
        instance.first_name = validated_data.get('first_name', instance.first_name)
        instance.last_name = validated_data.get('last_name', instance.last_name)
        instance.email = validated_data.get('email', instance.email)
        instance.password = validated_data.get('password', instance.password)
        instance.save()
        return instance

class ModSeri(serializers.ModelSerializer):
    class Meta:
        model = UserDetails
        fields = ["first_name", "email", "password"]

        # fields = '__all__'  # to get all keys from model