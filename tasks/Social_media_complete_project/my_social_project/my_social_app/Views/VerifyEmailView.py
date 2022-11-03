from django.shortcuts import render
from rest_framework.views import APIView
from rest_framework.response import Response
from rest_framework.status import *
from django.utils.encoding import force_bytes, DjangoUnicodeDecodeError, smart_str
from django.utils.http import urlsafe_base64_decode, urlsafe_base64_encode
from django.contrib.auth.tokens import PasswordResetTokenGenerator
from ..models import User

from ..serializers import VerifyEmailSerializer
from ..renderers import UserRenderer

class VerifyEmailView(APIView):
    renderer_classes = [UserRenderer]

    def post(self, request, uid, token, format=None):
        try:
           
           
            id = smart_str(urlsafe_base64_decode(uid))
            user = User.objects.get(id=id)
            if not PasswordResetTokenGenerator().check_token(user, token):
               return Response('Token is Not Valid or Expired',status=HTTP_400_BAD_REQUEST)

            user.email_verified=True
            user.save()
            return Response("success",status=HTTP_200_OK)
            
        except DjangoUnicodeDecodeError as identifier:
            PasswordResetTokenGenerator().check_token(user, token)
            return Response('Token is Not Valid or Expired',status=HTTP_400_BAD_REQUEST)
    
       
        return Response("error occured", status=HTTP_400_BAD_REQUEST)