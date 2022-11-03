import json

from django.shortcuts import render
from rest_framework.views import APIView
from rest_framework.response import Response
from rest_framework.status import *

from ..models import Tag, Post, PostLike, Comment, CommentLikes, User
from ..renderers import UserRenderer
from rest_framework.permissions import IsAuthenticated

from ..serializers import commentserializer, UserSerializer


class PostLikesListView(APIView):
    renderer_classes = [UserRenderer]
    permission_classes = [IsAuthenticated]

    def get(self, request, post_id=None):
        current_page = request.GET['page']
        require_size = request.GET['size']
        print(current_page)
        print(require_size)

        print(post_id)
        post_data = Post.objects.get(id=post_id)
        print("comment data is:", post_data)
        post_like_datas = PostLike.objects.filter(post=post_data)
        print(post_like_datas)
        lis = []
        i = int(current_page)
        largenumber = int(current_page) * int(require_size)
        smaller_number = (int(current_page) - 1) * int(require_size)
        for post_like_data in post_like_datas:
            print(post_like_data)
            if (smaller_number - 1) < i < (largenumber + 1):
                print(123)
                user = User.objects.get(id=post_like_data.liker.id)
                serializer = UserSerializer(user)
                lis.append(serializer.data)
            i = i + 1
        return Response(lis, status=HTTP_200_OK)

