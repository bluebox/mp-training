from django.shortcuts import render
from rest_framework.permissions import IsAuthenticated
from rest_framework.views import APIView
from rest_framework.response import Response
from rest_framework.status import *

from ..models import FollowUsers, User
from ..renderers import UserRenderer
from ..serializers import ProfileInfoSerializer, LoginDataSerializer


class ProfileDataView(APIView):
    renderer_classes = [UserRenderer]
    permission_classes = [IsAuthenticated]

    def post(self, request, id=None):
        data = User.objects.get(id=id)
        print("hello",request.user.id)
        follower_exists= FollowUsers.objects.filter(follower=request.user.id,followed=id).exists()
        print(follower_exists,"exists or not")
        if data is not None:
            serializer1 = LoginDataSerializer(data, many=False)
            response = Response()
            response.data = {"data":serializer1.data,"follower_exists":follower_exists}
            response.followedByAuthUser = follower_exists
            print(response.data["follower_exists"],"hjbehjyh")
            print(response.data["data"],"ndkjvjl")
            return response
        return Response({"error": "not a valid id"}, status=HTTP_400_BAD_REQUEST)
