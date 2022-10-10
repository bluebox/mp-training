from dataclasses import fields
from email.policy import default
from pyexpat import model
from rest_framework import serializers
from .models import *
from django.contrib.auth.forms import UserCreationForm
from django.contrib.auth.models import User
from rest_framework.validators import UniqueValidator
from django.contrib.auth.hashers import make_password

class registerSerializer(serializers.ModelSerializer):

    class Meta:
        model = User
        fields = ['username', 'email', 'password']
    
    def create(self, validated_data):
        validated_data['password'] = make_password(validated_data['password'])
        return super(registerSerializer, self).create(validated_data)
    

class ProblemSerializer(serializers.ModelSerializer):
    class Meta:
        model = Problem
        fields = "__all__"

class UserSerializer(serializers.ModelSerializer):
    class Meta:
        model = User
        fields = ['username']

class sortProblemSerializer(serializers.ModelSerializer):
    class Meta:
        model = Problem
        fields = "__all__"

class profileSerializer(serializers.ModelSerializer):
    class Meta:
        model = Profile
        fields = "__all__"

class submissionSerializer(serializers.ModelSerializer):
    class Meta:
        model = Solved
        fields = "__all__"

class TagSerializer(serializers.ModelSerializer):
    class Meta:
        model = Tag
        fields = ['tag_name']

class commentSerializer(serializers.ModelSerializer):
    class Meta:
        model = Comment
        fields = '__all__'

class editProfileSerializer(serializers.ModelSerializer):
    class Meta:
        model = Profile
        fields = ['full_name', 'gender', 'location', 'work', 'bio', 'education', 'mobile', 'github', 'facebook', 'instagram']

class postQuestionSerializer(serializers.ModelSerializer):
    class Meta:
        model = Problem
        fields = ['problem_name', 'description', 'hints', 'test_cases', 'outputs', 'json_test_cases']

class topicTagSerializer(serializers.ModelSerializer):
    class Meta:
        model = TopicTag
        fields = ['tag_id', 'tag_name']

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
        fields = ['title', 'discussion']