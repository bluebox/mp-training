from django.http import JsonResponse

from .models import User

from .serializers import Userserializer

from rest_framework.views import APIView

from rest_framework.decorators import api_view

from rest_framework.response import Response

from rest_framework import status

# @api_view(['GET','POST'])
# def user_list(request):

#     if request.method  == 'GET':
#         users = User.objects.all()
#         serializer = Userserializer(users,many=True)
#         return JsonResponse(serializer.data,safe = False)

#     if request.method == 'POST':
#         serializer = Userserializer(data=request.data)
#         if serializer.is_valid():
#             serializer.save()
#             return Response(serializer.data,status = status.HTTP_201_CREATED)

class UserList(APIView):
    def get(self, request):
        sub = User.objects.all()
        serializer = Userserializer(sub, many=True)
        return Response(serializer.data)

    def post(self,request):
        serializer = Userserializer(data=request.data)
        print(serializer)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        else:
            print('invalid')
            return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)
