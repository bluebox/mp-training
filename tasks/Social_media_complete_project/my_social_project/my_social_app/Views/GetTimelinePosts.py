from django.shortcuts import render
from rest_framework.views import APIView
from rest_framework.response import Response
from rest_framework.status import *

from ..models import FollowUsers, Post, User, PostLike
from ..serializers import FollowUsersSerializer, FollowerDataSerializer, GetTimelinePostDataSerializer
from ..renderers import UserRenderer


class GetTimelinePostsview(APIView):
    def get(self, request, page=None, size=None):
        follower_data = FollowUsers.objects.filter(followed=request.user.id)
        print(follower_data)
        lis = []
        current_page = request.GET['page']
        require_size = request.GET['size']
        print(current_page)
        print(require_size)
        i = int(current_page)
        largenumber= int(current_page)*int(require_size)
        smaller_number=(int(current_page)-1)*int(require_size)
        for following in follower_data:
            if (smaller_number - 1) < i < (largenumber + 1):
                posts = Post.objects.filter(author=following.follower.id)
                for post in posts:
                    timelineposts_serializer = GetTimelinePostDataSerializer(post)
                    likedbyUser = PostLike.objects.filter(post=post, liker=request.user).exists()

                    temp = {
                        "likedByAuthUser": likedbyUser,
                        "post": timelineposts_serializer.data
                    }
                    lis.append(temp)
            i = 1 + i

        return Response(lis, status=HTTP_200_OK)
