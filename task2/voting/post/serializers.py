from rest_framework import serializers

from post.models import Post, Comments, Uploader, User


class PostSerializer(serializers.ModelSerializer):
    class Meta:
        model = Post
        fields = '__all__'


class CommentSerializer(serializers.ModelSerializer):
    class Meta:
        model = Comments
        fields = '__all__'


class UploadSerializer(serializers.ModelSerializer):
    class Meta:
        model = Uploader
        fields = '__all__'


class PostExcelSerializer(serializers.ModelSerializer):
    class Meta:
        model = Post
        fields = ['images', 'name', 'category', 'score', 'is_winner', 'is_completed']

class UserSerializer(serializers.ModelSerializer):
    class Meta:
        model = User
        fields = '__all__'
        extra_kwargs = {'password': {'write_only': True}}

