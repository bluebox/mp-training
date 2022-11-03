import json

from django.shortcuts import render
from rest_framework.views import APIView
from rest_framework.response import Response
from rest_framework.status import *

from ..models import Tag, Post, PostLike, Comment, FollowUsers, Notification, User
from ..renderers import UserRenderer
from rest_framework.permissions import IsAuthenticated

from ..serializers import NotificationSerializer, UserSerializer


class GetFollowersData(APIView):
    def get(self, request, user_id=None):
        user = User.objects.get(id=user_id)
        print(user)
        users_followers = FollowUsers.objects.filter(followed=user)
        print("usre followers is",users_followers)
        users_following = FollowUsers.objects.filter(follower=user)
        print("usre followeing is",users_following)

        lis=[]
        for users_following in users_following:
            print(users_following)
            user_following_data= User.objects.get(id=users_following.followed.id)
            print(user_following_data)
            serializer= UserSerializer(user_following_data)
            lis.append(serializer.data)
        for users_follower in users_followers:
            print("bhvjkj")
            print(user)
            print(users_follower.follower)
            check_you_followed= FollowUsers.objects.filter(follower=user, followed=users_follower.follower).first()
            print("123")
            if check_you_followed is None:
                user_follower_data = User.objects.get(id=users_follower.follower.id)
                print(user_follower_data)
                serializer = UserSerializer(user_follower_data)
                lis.append(serializer.data)
        return Response(lis, status=HTTP_200_OK)





