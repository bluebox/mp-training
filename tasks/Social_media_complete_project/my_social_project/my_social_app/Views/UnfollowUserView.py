from django.shortcuts import render
from rest_framework.views import APIView
from rest_framework.response import Response
from rest_framework.status import *

from ..models import FollowUsers, User
from ..serializers import FollowUsersSerializer, FollowerDataSerializer
from ..renderers import UserRenderer


class UnfollowUserView(APIView):
    def post(self, request):
        serializer = FollowUsersSerializer(data=request.data)
        print("1")
        if serializer.is_valid():
            print("2")
            followed_user = User.objects.get(id=request.data['followed'])
            follower_user = User.objects.get(id=request.data['follower'])
            user = FollowUsers.objects.filter(followed=followed_user, follower=follower_user).first()
            print(user)
            
            
            if user is not None:
                user.delete()
                print(user)
                if follower_user.following_count>0:
                    follower_user.following_count= follower_user.following_count-1
                    follower_user.save()
                if followed_user.follower_count > 0:
                    followed_user.follower_count= followed_user.follower_count-1
                    followed_user.save()    
               
                follower_serializer=FollowerDataSerializer(follower_user)
                return Response({"msg":"Success", "body":follower_serializer.data})
            return Response({"msg":"user already unFollow"}, status=HTTP_400_BAD_REQUEST)
        return Response(serializer.errors)
