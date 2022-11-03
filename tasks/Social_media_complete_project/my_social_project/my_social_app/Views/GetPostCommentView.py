import json

from django.shortcuts import render
from rest_framework.views import APIView
from rest_framework.response import Response
from rest_framework.status import *

from ..models import Tag, Post, PostLike, Comment, CommentLikes
from ..renderers import UserRenderer
from rest_framework.permissions import IsAuthenticated

from ..serializers import commentserializer


class GetPostCommentView(APIView):
    renderer_classes = [UserRenderer]
    permission_classes = [IsAuthenticated]

    def get(self, request, post_id=None):
        current_page = request.GET['page']
        require_size = request.GET['size']
        print(current_page)
        print(require_size)

        print(post_id)
        post_data = Post.objects.get(id=post_id)
        print("post data is:", post_data)

        comments= Comment.objects.filter(post=post_data)
        lis=[]
        i = int(current_page)
        largenumber = int(current_page) * int(require_size)
        smaller_number = (int(current_page) - 1) * int(require_size)
        for comment in comments:
            if (smaller_number - 1) < i < (largenumber + 1):
                serializer=commentserializer(comment)
                likedbyUser = CommentLikes.objects.filter(comment=comment, liker=request.user).exists()

                temp = {
                    "likedByAuthUser": likedbyUser,
                    "comment": serializer.data
                }
                lis.append(temp)
            i=i+1
        print("comments are",comments)
        current_user = request.user
        print("current user is", current_user)
        return  Response(lis, status=HTTP_200_OK)
