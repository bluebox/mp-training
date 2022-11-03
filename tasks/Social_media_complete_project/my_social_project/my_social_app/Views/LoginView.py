from django.shortcuts import render
from rest_framework.views import APIView
from rest_framework.response import Response
from rest_framework.status import *
import json

from ..serializers import UserLoginSerializer, ProfileInfoSerializer, LoginDataSerializer
from ..renderers import UserRenderer  # error handling
from django.contrib.auth import authenticate
from ..models import User
from rest_framework_simplejwt.tokens import RefreshToken


def get_tokens_for_user(user):
    refresh = RefreshToken.for_user(user)

    return {
        'refresh': str(refresh),
        'access': str(refresh.access_token),
    }


class UserLoginView(APIView):
    renderer_classes = [UserRenderer]

    def post(self, request, format=None):

        serializer = UserLoginSerializer(data=request.data)

        if serializer.is_valid(raise_exception=True):
            email = serializer.data.get('email')
            password = serializer.data.get('password')
            user = authenticate(email=email, password=password)
            if user is not None:
                token = get_tokens_for_user(user)
                print(user, "user details")
                user = User.objects.get(email=email)
                print(user.first_name, ": After user details")
                data = User.objects.get(email=email)
                serializer1=LoginDataSerializer(data, many=False)


                print(type(data))

                return Response({'token': token, "body": serializer1.data,
                                 'msg': "login success"}, status=HTTP_200_OK)
            return Response({'errors': {"non field_errors are occured": ['password or email Is not valid']}},
                            status=HTTP_404_NOT_FOUND)
