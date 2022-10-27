import datetime
import json
from TourismServer.managers.adminManager import listing
from bookingsapp.JwtAuthentication import create_access_token, create_refresh_token
from bookingsapp.models import User, UserToken
from bookingsapp.serializers import UserSerializer
from rest_framework.response import Response
from rest_framework.exceptions import APIException
from django.core import serializers
from rest_framework import status


def get_all_users_by_admin(request):
        user_list = User.objects.all()
        serializer = UserSerializer(user_list, many=True)
        return Response(serializer.data)

def get_users_by_admin_using_pagination(request):
        text=request.GET.get('text')
        if text != '':
                user_list = (User.objects.filter(name__icontains=text) | 
                        User.objects.filter(email__icontains=text))
        else:
                user_list = User.objects.all()
        users, totalPages, page = listing(request, user_list)
        serializer = UserSerializer(users, many=True)
        return Response({
        'pageItems': serializer.data,
        'totalPages': totalPages,
        'page': page
        })

def register_user_and_get_token(request):
        serializer = UserSerializer(data=request.data)
        if not serializer.is_valid():
                raise APIException(json.dumps(serializer.errors))
        serializer.save()
        user = User.objects.get(email=request.data['email'])
        access_token = create_access_token(user.id)
        refresh_token = create_refresh_token(user.id)
        UserToken.objects.create(
        user_id = user.id, 
        token = refresh_token,
        expired_at = datetime.datetime.utcnow() + datetime.timedelta(days = 5)
        )
        response = Response()

        response.set_cookie(key='refresh_token', value=refresh_token, httponly=True)
        response.set_cookie(key='access_token', value=access_token, httponly=True)
        response.data = serializers.serialize('json', [user])
        return response

def get_user(request, user):
        serializer = UserSerializer(user)
        return Response(serializer.data)

def edit_user(request, user):
        
        serializer = UserSerializer(user, data=request.data)
        if serializer.is_valid():
                serializer.save()
                return Response(serializer.data)
        raise APIException(serializer.errors)


def delete_user(request, user):
        
        user.delete()
        return Response(status=status.HTTP_204_NO_CONTENT)


def login(request):
        email = request.data['email']
        password = request.data['password']
        # encryptedPassword = make_password(password)
        if User.objects.filter(email=email).exists():
                user = User.objects.get(email=email)
                if user.password != password:
                        raise APIException('Wrong Password!')

        else:
                raise APIException("email doesn't exist, Try again")

        user = User.objects.get(email=email, password=password)

        access_token = create_access_token(user.id)
        refresh_token = create_refresh_token(user.id)

        UserToken.objects.create(
        user_id=user.id,
        token=refresh_token,
        expired_at=datetime.datetime.utcnow() + datetime.timedelta(days=5)
        )
        response = Response()
        response.set_cookie(key='refresh_token', value=refresh_token, httponly=True)
        response.set_cookie(key='access_token', value=access_token, httponly=True)
        # userObj = json.loads(user)
        
        response.data = serializers.serialize('json', [user])
        return response

def logout(request):
        refresh_token = request.COOKIES.get('refresh_token')
        UserToken.objects.filter(
        token=refresh_token
        ).delete()

        response = Response()
        response.delete_cookie(key='refresh_token')
        response.delete_cookie(key='access_token')

        response.data = {
        'message': 'successfully logged out',
        }
        return response