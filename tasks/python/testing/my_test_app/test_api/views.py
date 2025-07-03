from time import sleep
from django.http import JsonResponse
from django.shortcuts import render
from django.views.decorators.csrf import csrf_exempt
from .models import User
from .serializers import UserSerializer
from rest_framework import status
from rest_framework.response import Response
import json

@csrf_exempt
def hello_api(request):
    if request.method == 'POST':
        params = json.loads(request.body)
        for key,val in params.items():
            print(key,":",val)
            sleep(2)
        return JsonResponse({'message':dict(params)})
    elif request.method == 'GET':
        params = request.GET.dict()
        for key,val in params.items():
            print(key,":",val)
            sleep(10)
        return JsonResponse({'message':dict(params)})
    return None

@csrf_exempt
def user_management(request,pk=None):
    try:
        if(pk != None):
            user = User.objects.get(pk=pk)
    except User.DoesNotExist:
        return JsonResponse({'message':'User not found'})
    if request.method == 'GET':
        users = User.objects.all()
        serializer = UserSerializer(users, many=True)
        return JsonResponse(serializer.data,safe=False)
    elif request.method == "POST":
        print(request.body)
        data = json.loads(request.body)
        serializer = UserSerializer(data=data)
        if serializer.is_valid():
            serializer.save()
            return JsonResponse(serializer.data,safe=False, status = status.HTTP_201_CREATED)
        return JsonResponse(serializer.errors, status=status.HTTP_400_BAD_REQUEST)
    elif request.method == "PUT":
        data = json.loads(request.body)
        serializer = UserSerializer(user,data=data)
        if serializer.is_valid():
            serializer.save()
            return JsonResponse(serializer.data,safe=False, status = status.HTTP_200_OK)
        return JsonResponse(serializer.errors, status=status.HTTP_400_BAD_REQUEST)
    elif request.method == "DELETE":
        user.delete()
        return JsonResponse({'message':'User deleted'})
    return None

def view_user(request):
    return render(request, 'test_api/UserManagement.html')





