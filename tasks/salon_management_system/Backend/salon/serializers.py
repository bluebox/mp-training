from rest_framework import serializers

from .models import Branch, User, services_provided

class Userserializer(serializers.ModelSerializer):
    class Meta:
        model = User

        fields = ['id','username','first_name','last_name','email','is_staff'] 

class BranchSerializer(serializers.ModelSerializer):

    class Meta:
        model = Branch

        fields = "__all__"

class ServicesSerializer(serializers.ModelSerializer):
     class Meta:
        model = services_provided

        fields = "__all__"