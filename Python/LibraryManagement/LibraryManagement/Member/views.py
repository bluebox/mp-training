from rest_framework import viewsets
from rest_framework.decorators import api_view
from rest_framework.response import Response
from rest_framework.status import HTTP_200_OK, HTTP_400_BAD_REQUEST, HTTP_401_UNAUTHORIZED
from rest_framework_simplejwt.tokens import AccessToken
from rest_framework_simplejwt.exceptions import TokenError

from .models import Member
from .serializers import MemberSerializer
from Book.CustomPagination import CustomPagination


class MemberViewSet(viewsets.ModelViewSet):
    queryset = Member.objects.all()
    serializer_class = MemberSerializer
    pagination_class = CustomPagination

@api_view(['GET'])
def fetchParticularUser(request):
    serializer=MemberSerializer(request.user)
    return Response(serializer.data,status=HTTP_200_OK)

@api_view(['POST'])
def verify_access_token(request):
    access_token=request.data.get('access_token',None)
    if not access_token:
        return Response({'err':'No Token Provided'},status=HTTP_401_UNAUTHORIZED)
    try:
        token=AccessToken(access_token)
        return Response({'success':'token is valid'},status=HTTP_200_OK)
    except TokenError:
        return Response({'err':'token is not valid'},status=HTTP_401_UNAUTHORIZED)
