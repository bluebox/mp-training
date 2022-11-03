"""THIS IS FollowUsers View"""
from rest_framework.views import APIView
from rest_framework.response import Response
from rest_framework.status import HTTP_200_OK,HTTP_400_BAD_REQUEST
from rest_framework.permissions import IsAuthenticated
from ..models import FollowUsers, User
from ..serializers import FollowUsersSerializer, FollowerDataSerializer
from ..renderers import UserRenderer


class FollowUserView(APIView):
    """THIS IS FollowUsers View"""
    renderer_classes = [UserRenderer]
    permission_classes = [IsAuthenticated]
    def post(self, request):
        """THIS IS FollowUsers View"""
        serializer = FollowUsersSerializer(data=request.data)
        if serializer.is_valid():
            followed_user = User.objects.get(id=request.data['followed'])
            follower_user = User.objects.get(id=request.data['follower'])
            user = FollowUsers.objects.filter(followed=followed_user,
             follower=follower_user).first()
            print(user)
            if user is None:
                follow = FollowUsers(followed=followed_user,follower=follower_user)
                follow.save()
                follower_user.following_count= follower_user.following_count+1
                follower_user.save()
                followed_user.follower_count= followed_user.follower_count+1
                followed_user.save()
                follower_serializer=FollowerDataSerializer(follower_user)
                return Response({"msg":"Success", "body":follower_serializer.data},
                 status=HTTP_200_OK)
            return Response({"msg":"user already Following"},status=HTTP_400_BAD_REQUEST)
        return Response(serializer.errors, status=HTTP_400_BAD_REQUEST)
