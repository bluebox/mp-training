from django.shortcuts import render
from rest_framework.views import APIView
from rest_framework.response import Response
from rest_framework.status import *

from ..serializers import GetPostDataByIdSerializer
from ..models import Post, Tag


class GetPostsBytagView(APIView):
    def get(self, request, name=None):
        lis = []
        current_page = request.GET['page']
        require_size = request.GET['size']
        print(current_page)
        print(require_size)
        tag = Tag.objects.get(name=name)
        print(tag)
        i = int(current_page)
        largenumber = int(current_page) * int(require_size)
        smaller_number = (int(current_page) - 1) * int(require_size)
        posts = Post.objects.filter(postTags=tag)
        print(posts)
        for post in posts:
            if (smaller_number - 1) < i < (largenumber + 1):
                print(post)
                serializer = GetPostDataByIdSerializer(post)
                temp = {
                    "likedByAuthUser": False,
                    "post": serializer.data
                }
                print(temp)
                lis.append(temp)
            i = 1 + i

        return Response(lis, status=HTTP_200_OK)
