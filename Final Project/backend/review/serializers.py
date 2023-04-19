from rest_framework.serializers import ModelSerializer
from .models import Review
from rest_framework import serializers
from rest_framework.validators import UniqueValidator
from django.contrib.auth.models import User

class ReviewSerializer(ModelSerializer):
    name = serializers.SerializerMethodField(read_only=True)

    class Meta:
        model = Review
        fields = '__all__'
    
    def get_name(self, obj):
        if obj.user.first_name and obj.user.last_name:
            return obj.user.first_name + obj.user.last_name
        elif obj.user.last_name:
            return obj.user.first_name
        else:
            return obj.user.username

    def validate(self, data):
        product = data.get('product')
        user = data.get('user')
        if Review.objects.filter(product=product, user=user).exists():
            raise serializers.ValidationError('Product already reviewed')
        return data
    
    def validate_rating(self, value):
        if value == 0:
            raise serializers.ValidationError('Please give a rating')
        elif value < 1 and value > 5:
            raise serializers.ValidationError('Please select a valid rating')
        else:
            return value
          
        
    