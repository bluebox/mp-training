import json

from django.shortcuts import render
from rest_framework.views import APIView
from rest_framework.response import Response
from rest_framework.status import *

from ..models import Tag, Post
from ..renderers import UserRenderer
from rest_framework.permissions import IsAuthenticated

from ..models import FollowUsers, User
from ..serializers import PostSerializer, TagSerializer


class UserPostDelete(APIView):
    renderer_classes = [UserRenderer]
    permission_classes = [IsAuthenticated]

    def post(self, request, post_id):
        create_post = Post.objects.get(id=post_id)

        create_post.postPhoto = None

        create_post.save()
        print(create_post)

        return Response({"msg": "success"}, status=HTTP_200_OK)
