


import json

from django.shortcuts import render
from rest_framework.views import APIView
from rest_framework.response import Response
from rest_framework.status import *

from ..models import Tag, Post, PostLike, Comment, FollowUsers, Notification
from ..renderers import UserRenderer
from rest_framework.permissions import IsAuthenticated

from ..serializers import NotificationSerializer


class NotificationMarkAllReadView(APIView):
    def post(self, request):
        set_notifications_read = Notification.objects.filter(receiver=request.user, isRead=False)
        for set_notification_read in set_notifications_read:
            set_notification_read.isRead=True
            set_notification_read.save()

        print(set_notifications_read, "notifications is")
        return Response({"msg":"success"}, status=HTTP_200_OK)
