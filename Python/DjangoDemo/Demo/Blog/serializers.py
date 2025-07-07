from rest_framework import serializers
from .models import Comments,Blog
class CommentSerializer(serializers.ModelSerializer):
    class Meta:
        model=Comments
        fields='__all__'

class BlogSerializer(serializers.ModelSerializer):
    comments=CommentSerializer(many=True,read_only=True)
    class Meta:
        model=Blog
        fields='__all__'