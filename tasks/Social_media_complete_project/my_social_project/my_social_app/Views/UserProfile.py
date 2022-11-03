from rest_framework.permissions import IsAuthenticated
from rest_framework.views import APIView
from rest_framework.response import Response
from rest_framework.status import *

from ..renderers import UserRenderer
from ..serializers import ProfileSerializer
from ..models import User


# class UserProfileView(APIView):
#     def get(self, request):
#         user1 = User.objects.get(email=request.data.get('email'))
#         print(user1)
#         serializer = ProfileSerializer(request.user)
#         return Response(serializer.data)
class UserProfileView(APIView):
    renderer_classes = [UserRenderer]
    permission_classes = [IsAuthenticated]

    def get(self, request, formate=None):
        serializer = ProfileSerializer(request.user)

        return Response(serializer.data, status=HTTP_200_OK)
