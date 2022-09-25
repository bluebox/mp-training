from dataclasses import fields
from email.policy import default
from pyexpat import model
from rest_framework import serializers
from .models import *
from django.contrib.auth.models import User
from rest_framework.validators import UniqueValidator

class registerSerializer(serializers.ModelSerializer):

    class Meta:
        model = User
        fields = ['username', 'email', 'password']

    def create(self, validated_data):
        user = User.objects.create(username = validated_data['username'])
        user.set_password(validated_data['password'])
        return user

class ProblemSerializer(serializers.ModelSerializer):
    class Meta:
        model = Problem
        fields = "__all__"

class profileSerializer(serializers.ModelSerializer):
    class Meta:
        model = Profile
        fields = "__all__"

class postQuestionSerializer(serializers.Serializer):
    problem_name = serializers.CharField(max_length = 30, allow_blank = False, trim_whitespace = True)
    description = serializers.CharField(max_length = 500, allow_blank = False)
    hints = serializers.CharField(max_length = 50, allow_blank = False)
    test_cases = serializers.CharField(max_length = 500, allow_blank = False)
    tags = serializers.ListField(child=serializers.CharField(min_length=1, max_length=100), allow_empty = True)

class discussionsSerializer(serializers.ModelSerializer):
    class Meta:
        model = Discussion
        fields = '__all__'

class solvedSerializer(serializers.ModelSerializer):
    class Meta:
        model = Solved
        fields = "__all__"

class categorySerializer(serializers.ModelSerializer):
    class Meta:
        model = Problem
        fields = "__all__"

class userSerializer(serializers.ModelSerializer):
    class Meta:
        model = User
        fields = ['username', 'password']

class PostDiscussionSerializer(serializers.ModelSerializer):

    class Meta:
        model = Discussion
        fields = "__all__"