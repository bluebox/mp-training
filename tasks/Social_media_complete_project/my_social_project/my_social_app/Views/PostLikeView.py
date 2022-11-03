import json

from django.shortcuts import render
from rest_framework.views import APIView
from rest_framework.response import Response
from rest_framework.status import *

from ..models import Tag, Post, PostLike, FollowUsers, Notification
from ..renderers import UserRenderer
from rest_framework.permissions import IsAuthenticated


class PostLikeView(APIView):
    renderer_classes = [UserRenderer]
    permission_classes = [IsAuthenticated]

    def post(self, request, post_id=None):
        print(post_id)
        post_data = Post.objects.get(id=post_id)
        print("post data is:", post_data)
        current_user = request.user
        print("current user is", current_user)

        post_like = PostLike(post=post_data, liker=current_user).save()
        print(post_like)
        post_data.likeCount = post_data.likeCount + 1
        post_data.save()
        followers_data = FollowUsers.objects.filter(followed=current_user)

        new_notification = Notification(type='POST_LIKE', owningPost=post_data, sender=current_user,
                                        receiver=post_data.author)
        new_notification.save()

        return Response({"msg": "success"}, status=HTTP_200_OK)
