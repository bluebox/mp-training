import json
from threading import Thread

from django.shortcuts import render
from rest_framework.views import APIView
from rest_framework.response import Response
from rest_framework.status import *


from ..models import Tag, Post, PostLike, Comment, FollowUsers, Notification, ThreadChatMessage, Threads
from ..renderers import UserRenderer
from rest_framework.permissions import IsAuthenticated

from ..serializers import ChatThreadSerializer, NotificationSerializer


class GetChatMessagesList(APIView):
    def get(self, request):
        current_user= request.user
        target_chat_user = request.GET['target_user']
        print(target_chat_user)
        print(current_user)
        lis=[]
        threads= Threads.objects.filter(first_person=current_user,second_person=target_chat_user)|Threads.objects.filter(first_person=target_chat_user,second_person=current_user)
        if threads.exists():
            chatmessages=ThreadChatMessage.objects.filter(thread=threads.first()).order_by('timestamp')
            for chat_message in chatmessages:
                serialize= ChatThreadSerializer(chat_message)
                lis.append(serialize.data)

         
        return Response(lis, status=HTTP_200_OK)
