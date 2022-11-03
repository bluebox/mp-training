import json

from django.shortcuts import render
from rest_framework.views import APIView
from rest_framework.response import Response
from rest_framework.status import *

from ..models import Tag, Post, PostLike
from ..renderers import UserRenderer
from rest_framework.permissions import IsAuthenticated


class PostUnlikeView(APIView):
    renderer_classes = [UserRenderer]
    permission_classes = [IsAuthenticated]

    def post(self, request, post_id=None):
        print(post_id)
        post_data = Post.objects.get(id=post_id)
        print("post data is:", post_data)
        current_user = request.user
        print("current user is", current_user)

        post_unlike = PostLike.objects.get(post=post_data, liker=current_user)
        print(post_unlike)
        post_unlike.delete()
        post_data.likeCount = post_data.likeCount - 1
        post_data.save()

        return Response({"msg": "successfully deleted"}, status=HTTP_200_OK)
