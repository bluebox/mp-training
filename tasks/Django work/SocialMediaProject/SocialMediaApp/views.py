import json
from django.shortcuts import render
from rest_framework.views import APIView
from rest_framework.response import Response
from rest_framework_simplejwt.tokens import RefreshToken
from rest_framework.status import *
from .renderers import UserRenderer  #errors
from django.contrib.auth import authenticate
from rest_framework.permissions import IsAuthenticated, IsAdminUser, IsAuthenticatedOrReadOnly
from rest_framework_simplejwt.authentication import JWTAuthentication, JWTTokenUserAuthentication
from rest_framework_simplejwt.tokens import RefreshToken
from .models import User
# Create your views here.

# from rest_framework_j

def get_tokens_for_user(user):
    refresh = RefreshToken.for_user(user)

    return {
        'refresh': str(refresh),
        'access': str(refresh.access_token),
    }
from .serializers import SendPasswordEmailResetSerializer, UserChangePasswoordSerializer, UserLoginSerializer, UserPasswordResetSerializer, UserProfileSerilaizer, UserRegisterationsSerializer, serializer1
class UserRegistration(APIView):
    def get(self, request, format=None):
     return Response({'msg':"Success"})
    def post(self, request, format=None):
        serializer = UserRegisterationsSerializer(data=request.data)
        if serializer.is_valid(raise_exception=True):
            user = serializer.save()
            token= get_tokens_for_user(user)
            return Response({
                'token':token,
                'msg':'Registration succesfull'
            }, status=HTTP_201_CREATED)

        return Response(serializer.errors, status=HTTP_400_BAD_REQUEST)    

# login authentication\\



class UserLoginView(APIView):
    renderer_classes=[UserRenderer]
    def post(self, request, format=None):
        serializer=UserLoginSerializer(data= request.data)
        if serializer.is_valid(raise_exception=True):
            email = serializer.data.get('email')
            password=serializer.data.get('password')
            user = authenticate(email=email, password=password)
            if user is not None:
                token= get_tokens_for_user(user)
                print(user,"user details")
                user=User.objects.get(email=user)
                print(user.first_name,": After user details")
                
                return Response({'token':token,'user_id':user.id, 'user_name':user.username,'email':user.email,'msg':"login success"}, status=HTTP_200_OK)
            return Response({'errors':{"non field_errors are occured":['password or email Is not valid']}}, status=HTTP_404_NOT_FOUND)    





class UserProfileView(APIView):
    renderer_classes=[UserRenderer]
    permission_classes=[IsAuthenticated]
    def get(self, request, formate=None):
        serializer = UserProfileSerilaizer(request.user)
       
        return Response(serializer.data, status=HTTP_200_OK)



class UserChangePasswordView(APIView):
    renderer_classes=[UserRenderer]
    permission_classes=[IsAuthenticated]
    def post(self, request, formate=None):
        serializer = UserChangePasswoordSerializer(data=request.data, context={'user':request.user})
        if serializer.is_valid(raise_exception=True):
            return Response({'msg':"Password Changed Successfully"}, status=HTTP_200_OK)
        return Response(serializer.errors, status=HTTP_400_BAD_REQUEST)    






class SendPasswordResetEmailView(APIView):
    renderer_classes=[UserRenderer]
    def post(self, request, format=None):
        serializer= SendPasswordEmailResetSerializer(data=request.data)
        if serializer.is_valid(raise_exception=True):
            return  Response({'msg':"Password Reset lint send. Please Check Your Email"}, status=HTTP_200_OK)
        return Response(serializer.errors, status=HTTP_400_BAD_REQUEST)        

class UserPasswordResetView(APIView):
    renderer_classes=[UserRenderer]
    def post(self, request,uid, token, format=None):
        serializer=UserPasswordResetSerializer(data= request.data,context={'uid':uid,'token':token})
        if serializer.is_valid(raise_exception=True):
            return  Response({'msg':"Password Reset Successfully"}, status=HTTP_200_OK)
        return Response(serializer.errors, status=HTTP_400_BAD_REQUEST) 




class LogoutView(APIView):
    permission_classes = (IsAuthenticated,)

    def post(self, request):
        try:
            refresh_token = request.data["refresh_token"]
            token = RefreshToken(refresh_token)
            token.blacklist()

            return Response(status=HTTP_205_RESET_CONTENT)
        except Exception as e:
            return Response(status=HTTP_400_BAD_REQUEST)