from django.shortcuts import render
from rest_framework.views import APIView
from rest_framework.response import Response
from rest_framework.status import *
from ..serializers import UserRegisterationsSerializer
from django.utils.encoding import force_bytes, DjangoUnicodeDecodeError, smart_str
from django.utils.http import urlsafe_base64_decode, urlsafe_base64_encode
from django.contrib.auth.tokens import PasswordResetTokenGenerator
from django.core.mail import EmailMessage, send_mail
from my_social_project.settings import EMAIL_HOST_USER

class UserRegistration(APIView):
    def get(self, request, format=None):
        return Response({'msg': "Success"})

    def post(self, request, format=None):
        serializer = UserRegisterationsSerializer(data=request.data)
        if serializer.is_valid(raise_exception=True):
            user = serializer.save()
            uid = urlsafe_base64_encode(force_bytes(user.id))
            print("encoded UID", uid)
            token = PasswordResetTokenGenerator().make_token(user)
            print("password Reset Token", token)
            link = 'http://localhost:4200/verify-email/' + uid + '/' + token+'/'

            print('password Reset Link', link)
            # send email
            body = link

            data = {
                'subject': 'Reset Your password',
                'body': body,
                'to_email': user.email,
            }

            email = EmailMessage(
                subject=data['subject'],
                body=data['body'],
                from_email=EMAIL_HOST_USER,
                to=[data['to_email']]

            )

            email.send()
           
            return Response({
               
                'msg': 'Registration succesfull'
            }, status=HTTP_201_CREATED)

        return Response(serializer.errors, status=HTTP_400_BAD_REQUEST)