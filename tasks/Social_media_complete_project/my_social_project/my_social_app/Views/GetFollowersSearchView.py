import json

from django.shortcuts import render
from rest_framework.views import APIView
from rest_framework.response import Response
from rest_framework.status import *

from ..models import Tag, Post, PostLike, Comment, FollowUsers, Notification, User
from ..renderers import UserRenderer
from rest_framework.permissions import IsAuthenticated

from ..serializers import NotificationSerializer, UserSerializer
from django.db.models import Q


class GetSearchFollowersData(APIView):
    def get(self, request):
        key = request.GET['key']
        print(key)
        user = request.user
        print(user)
        users_followers = FollowUsers.objects.filter(followed=user)
        print("usre followers is", users_followers)
        users_following = FollowUsers.objects.filter(follower=user)
        print("usre followeing is", users_following)

        lis = []
        for users_following in users_following:
            print(users_following)
            user_following_data = User.objects.get(
                id=users_following.followed.id)
            print(user_following_data)
            serializer = UserSerializer(user_following_data)
            followedByAuthUser = FollowUsers.objects.filter(follower=user, followed=user_following_data).exists()
            temp = {
                    "user": serializer.data,
                    "followedByAuthUser":followedByAuthUser
                }
            lis.append(temp)
        for users_follower in users_followers:
            print("bhvjkj")
            print(user)
            print(users_follower.follower)
            check_you_followed = FollowUsers.objects.filter(
                follower=user, followed=users_follower.follower).first()
            print("123")
            if check_you_followed is None:
                user_follower_data = User.objects.get(
                    id=users_follower.follower.id)
                print(user_follower_data)
                serializer = UserSerializer(user_follower_data)
                followedByAuthUser = FollowUsers.objects.filter(follower=user, followed=user_follower_data).exists()
                temp = {
                    "user": serializer.data,
                    "followedByAuthUser":followedByAuthUser
                }
                lis.append(temp)
        print("before :", lis)
        newList = []
        for i in lis:
            if i['user']['first_name'].count(key) > 0 or i['user']['last_name'].count(key) > 0 or i['user']['email'].count(key) > 0:
                print(key, "is in :", i['user']['id'])
                newList.append(i)
        print(newList)
        return Response(newList, status=HTTP_200_OK)
