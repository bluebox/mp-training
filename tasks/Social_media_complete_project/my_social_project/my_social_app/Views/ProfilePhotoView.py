from rest_framework.permissions import IsAuthenticated
from rest_framework.views import APIView
from rest_framework.response import Response
from rest_framework.status import *

from ..renderers import UserRenderer
from ..serializers import LoginDataSerializer, ProfilePhotoSerializer
from ..models import User


class UserProfilePhotoView(APIView):
    renderer_classes = [UserRenderer]
    permission_classes = [IsAuthenticated]

    def post(self, request, ):
        image = request.data['profile_photo']
        user = request.user
        user.profile_photo = image
        user.save()
        serializer = ProfilePhotoSerializer(user)
        print(image)
        print(user)
        return Response({'msg': "success", "body  ": serializer.data}, status=HTTP_200_OK)
