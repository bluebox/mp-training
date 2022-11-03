from django.shortcuts import render
from rest_framework.views import APIView
from rest_framework.response import Response
from rest_framework.status import *

from ..serializers import GetPostDataByIdSerializer
from ..models import Post


class GetPostsByIdView(APIView):
    def get(self, request, id=None):
        post_id = id
        post = Post.objects.get(id=post_id)
        print(post)
        serializer = GetPostDataByIdSerializer(post)
        temp = {
            "likedByAuthUser": False,
            "post": serializer.data
        }
        return Response(temp, status=HTTP_200_OK)
