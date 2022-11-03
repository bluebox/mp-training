"""THIS IS  USER CHANGE PASSWORD VIEW"""
from rest_framework.views import APIView
from rest_framework.response import Response
from rest_framework.status import HTTP_400_BAD_REQUEST,HTTP_200_OK
from rest_framework.permissions import IsAuthenticated
from ..renderers import UserRenderer
from ..serializers import UserChangePasswordSerializer
class UserChangePasswordView(APIView):
    """This is a change password """
    renderer_classes = [UserRenderer]
    permission_classes = [IsAuthenticated]

    def post(self, request):
        """A func docstring."""
        print(request.data)
        serializer = UserChangePasswordSerializer(data=request.data, context={'user': request.user})
        if serializer.is_valid(raise_exception=True):
            return Response({'msg': "Password Changed Successfully"}, status=HTTP_200_OK)
        return Response(serializer.errors, status=HTTP_400_BAD_REQUEST)
