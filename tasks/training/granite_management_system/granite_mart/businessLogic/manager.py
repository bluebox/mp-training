import json

import requests
from django.contrib.auth import authenticate, login
from django.contrib.auth.models import User
from rest_framework.response import Response



def authenticate1(request):
    print("entered into authentication")
    uname = request.data['username']
    upass = request.data['password']
    user = authenticate(username=uname, password=upass)
    print(login(request=request, user=user))

    try:
        user1 = User.objects.get(username=uname)
        print(user)
        print(user1, user1.password)
    except Exception as e:
        print(e)
        return Response("500")
    print(uname, upass)
    if user1:
        isAdmin = User.objects.get(username=uname).is_superuser
        token_pair = requests.post("http://127.0.0.1:8000/granite_mart/api/token/",
                                   {'username': uname, 'password': upass})

        data = json.loads(token_pair.text)
        print(data)
        response = Response()

        response.set_cookie(key='token', value=data['access'], httponly=True, samesite='None', secure=True)
        response.set_cookie(key='refresh', value=data['refresh'], httponly=True, samesite='None', secure=True)
        response.set_cookie(key='username', value=uname, samesite='None', secure=True)
        response.set_cookie(key='isAdmin', value=isAdmin, samesite='None', secure=True)
        response.set_cookie(key='login', value='true', samesite='None', secure=True)
        print(uname, isAdmin)
        response.data = {

            'token': token_pair,
            "msg": "success",
            "isAdmin": str(isAdmin),
            "username": uname,
            "login": 'true'
        }
        print("response created")
        return response
    print("response creation failed")
    return Response('authentication failed')


def logout(request):
    response = Response()
    response.set_cookie('token', '')
    response.set_cookie('refresh', '')
    response.set_cookie('login', 'false')

    return response

def tokenVerification(token, refresh):
    verify = requests.post('http://127.0.0.1:8000/granite_mart/api/token/verify/', {'token': token})
    if verify.status_code != 200:
        refreshed = requests.post('http://127.0.0.1:8000/granite_mart/api/token/refresh/', {'refresh': refresh})
        if refreshed.status_code == 200:
            response = Response()
            data = json.loads(refreshed.text)
            response.set_cookie('token', data['access'], httponly=True)
            verify.status_code = 200

    return str(verify.status_code)
