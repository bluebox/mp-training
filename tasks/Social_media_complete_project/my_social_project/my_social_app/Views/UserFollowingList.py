from django.shortcuts import render
from rest_framework.views import APIView
from rest_framework.response import Response
from rest_framework.status import *

from ..models import FollowUsers, User
from ..serializers import FollowUsersSerializer, FollowerDataSerializer
from ..renderers import UserRenderer


class UserFollowingListView(APIView):
    def get(self, request,id=None,page=None, size=None):
        
        lis = []
        current_page = request.GET['page']
        require_size = request.GET['size']
        
        following_data = FollowUsers.objects.filter(follower=id)
        print(current_page)
        print(require_size)
        i = int(current_page)
        largenumber = int(current_page) * int(require_size)
        smaller_number = (int(current_page) - 1) * int(require_size)

        for following in following_data:
            if (smaller_number - 1) < i < (largenumber + 1):
                user = User.objects.get(id=following.followed.id)
                followedByuser = FollowUsers.objects.filter(follower=request.user.id,
                                                            followed=following.followed.id).exists()
                print(followedByuser)
                following_serializer = FollowerDataSerializer(user)

                temp = {
                    "user": following_serializer.data,
                    "followedByAuthUser": followedByuser

                }
                lis.append(temp)
            i=1+i

        return Response({"msg": "Success", "list": lis})
        # follower_serializer = FollowUsersSerializer(follower_user)
        #
        #
        #
        #
        # serializer = FollowUsersSerializer(data=request.data)
        # if serializer.is_valid():
        #     followed_user = User.objects.get(id=request.data['followed'])
        #     follower_user = User.objects.get(id=request.data['follower'])
        #     user = FollowUsers.objects.filter(followed=followed_user, follower=follower_user).first()
        #     print(user)
        #     if user is None:
        #         follow = FollowUsers(followed=followed_user,follower=follower_user).save()
        #         follower_user.following_count= follower_user.following_count+1
        #         follower_user.save()
        #         followed_user.follower_count= follower_user.follower_count+1
        #         followed_user.save()
        #         follower_serializer=FollowerDataSerializer(follower_user)
        #         return Response({"msg":"Success", "body":follower_serializer.data})
        #     return Response({"msg":"user already Following"})
        # return Response(serializer.errors)
