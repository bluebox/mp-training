from rest_framework.serializers import ModelSerializer
from rest_framework import serializers
from django.contrib.auth.models import User
from .models import Product
from rest_framework_simplejwt.tokens import RefreshToken
from django.contrib.auth.hashers import make_password
from review.serializers import ReviewSerializer

class ProductSerializer(ModelSerializer):
    reviews = ReviewSerializer(many=True, read_only=True)
    rating = serializers.SerializerMethodField(read_only=True)
    numReviews = serializers.SerializerMethodField(read_only=True)
    class Meta:
        model = Product
        fields = '__all__'
    
    def get_rating(self, obj):
        reviews = obj.reviews.all()
        if len(reviews) == 0:
            return 0
        total_rating = 0
        for review in reviews:
            total_rating += review.rating
        return total_rating/len(reviews)
    
    def get_numReviews(self, obj):
        reviews = obj.reviews.all()
        return len(reviews)


class UserSerializer(ModelSerializer):
    isAdmin = serializers.SerializerMethodField(read_only=True)
    class Meta:
        model = User
        fields = ['id', 'username', 'email', 'first_name', 'last_name','password', 'isAdmin']
        extra_kwargs = {
            'password': {'write_only': True}
        }

    def get_isAdmin(self, obj):
        return obj.is_staff


class UserSerializerWithToken(UserSerializer):
    token = serializers.SerializerMethodField(read_only=True)
    isAdmin = serializers.SerializerMethodField()
    class Meta:
        model = User
        fields = ['id', 'username', 'email', 'first_name', 'last_name', 'password', 'isAdmin', 'token']
    
    def get_token(self, obj):
        token = RefreshToken.for_user(obj)
        return str(token.access_token)

    def get_isAdmin(self, obj):
        return obj.is_staff