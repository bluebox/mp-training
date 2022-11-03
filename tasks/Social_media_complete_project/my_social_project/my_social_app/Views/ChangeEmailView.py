"""THIS IS CHANGE EMAIL VIEW FILE"""
from rest_framework.views import APIView
from rest_framework.response import Response
from rest_framework.status import HTTP_400_BAD_REQUEST,HTTP_200_OK
from rest_framework.permissions import IsAuthenticated
from ..renderers import UserRenderer
from ..serializers import UserChangeEmailSerializer
class ChangeEmailView(APIView):
    """This is a change email view"""
    renderer_classes = [UserRenderer]
    permission_classes = [IsAuthenticated]

    def post(self, request):
        """This is a change email FUNCTION"""
        print(request.data)
        serializer = UserChangeEmailSerializer(data=request.data, context={'user': request.user})
        if serializer.is_valid(raise_exception=True):
            return Response({'msg': "Email Changed Successfull"}, status=HTTP_200_OK)
        return Response(serializer.errors, status=HTTP_400_BAD_REQUEST)
        