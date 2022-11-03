import json

from django.shortcuts import render
from rest_framework.views import APIView
from rest_framework.response import Response
from rest_framework.status import *

from ..models import Tag, Post, PostLike, Comment, CommentLikes
from ..renderers import UserRenderer
from rest_framework.permissions import IsAuthenticated

from ..serializers import commentserializer


class PostCommentUnikeView(APIView):
    renderer_classes = [UserRenderer]
    permission_classes = [IsAuthenticated]

    def post(self, request, commentId=None):
        print(commentId)
        comment_data = Comment.objects.get(id=commentId)
        print("post data is:", comment_data)
        current_user = request.user
        print("current user is", current_user)

        comment_unlike = CommentLikes.objects.filter(comment=comment_data, liker=current_user).first()
        print("comment unlike is done for ",comment_unlike)

        comment_unlike.delete()
        comment_data.likeCount = comment_data.likeCount - 1
        comment_data.save()
        likedbyUser = CommentLikes.objects.filter(comment=comment_data, liker=request.user).exists()
        print(likedbyUser)
        serializer = commentserializer(comment_data)
        print(serializer.data)
        temp = {
            "likedByAuthUser": likedbyUser,
            "comment": serializer.data

        }

        return Response(temp, status=HTTP_200_OK)
