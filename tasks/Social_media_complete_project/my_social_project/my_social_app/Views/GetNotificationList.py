import json

from django.shortcuts import render
from rest_framework.views import APIView
from rest_framework.response import Response
from rest_framework.status import *

from ..models import Tag, Post, PostLike, Comment, FollowUsers, Notification
from ..renderers import UserRenderer
from rest_framework.permissions import IsAuthenticated

from ..serializers import NotificationSerializer


class GetNotification(APIView):
    def get(self, request):
        current_page = request.GET['page']
        require_size = request.GET['size']
        print(current_page)
        print(require_size)

        i = int(current_page)
        largenumber = int(current_page) * int(require_size)
        smaller_number = (int(current_page) - 1) * int(require_size)
        get_notifications = Notification.objects.filter(receiver=request.user,
                                                        isSeen=False)
        print(get_notifications, "notifications is")
        lis = []
        for get_notification in get_notifications:
            if (smaller_number - 1) < i < (largenumber + 1):

                serializer = NotificationSerializer(get_notification)
                lis.append(serializer.data)
            i = i + 1
        return Response(lis, status=HTTP_200_OK)
