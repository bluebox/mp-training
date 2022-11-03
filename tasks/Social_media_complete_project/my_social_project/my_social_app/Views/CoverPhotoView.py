"""THIS IS USER COVER PHOTO VIEW"""
from rest_framework.permissions import IsAuthenticated
from rest_framework.views import APIView
from rest_framework.response import Response
from rest_framework.status import HTTP_202_ACCEPTED
from ..renderers import UserRenderer
from ..serializers import ProfilePhotoSerializer


class UserCoverPhotoView(APIView):
    """This is USER COVER pHOTO CLASS"""
    renderer_classes = [UserRenderer]
    permission_classes = [IsAuthenticated]
    def post(self, request):
        """This is USER COVER FUNCTION"""
        image = request.data['cover_photo']
        user = request.user
        user.cover_photo = image
        user.save()
        serializer = ProfilePhotoSerializer(user)
        print(image)
        print(user)
        return Response({'msg': "success", "body": serializer.data}, status=HTTP_202_ACCEPTED)
