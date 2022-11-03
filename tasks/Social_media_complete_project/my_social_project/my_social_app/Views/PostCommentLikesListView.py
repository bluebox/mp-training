import json

from django.shortcuts import render
from rest_framework.views import APIView
from rest_framework.response import Response
from rest_framework.status import *

from ..models import Tag, Post, PostLike, Comment, CommentLikes, User
from ..renderers import UserRenderer
from rest_framework.permissions import IsAuthenticated

from ..serializers import commentserializer, UserSerializer


class PostCommentLikesListView(APIView):
    renderer_classes = [UserRenderer]
    permission_classes = [IsAuthenticated]

    def get(self, request, commentId=None):
        current_page = request.GET['page']
        require_size = request.GET['size']
        print(current_page)
        print(require_size)

        print(commentId)
        comment_data = Comment.objects.get(id=commentId)
        print("comment data is:", comment_data)
        comment_like_datas = CommentLikes.objects.filter(comment=comment_data)
        print(comment_like_datas)
        lis = []
        i = int(current_page)
        largenumber = int(current_page) * int(require_size)
        smaller_number = (int(current_page) - 1) * int(require_size)
        for comment_like_data in comment_like_datas:
            print(comment_like_data)
            if (smaller_number - 1) < i < (largenumber + 1):
                print(123)
                user = User.objects.get(id=comment_like_data.liker.id)
                serializer = UserSerializer(user)
                lis.append(serializer.data)
            i = i + 1
        return Response(lis, status=HTTP_200_OK)

        #
        # lis=[]
        # i=1
        # for comment in comments:
        #     if int(current_page) - 1 < i < int(require_size) + 1:
        #         serializer=commentserializer(comment)
        #         # likedbyUser = PostLike.objects.filter(post=post_data, liker=request.user).exists()
        #         likedbyUser = CommentLikes.objects.filter(comment=comment, liker=request.user).exists()
        #         print(likedbyUser)
        #         temp = {
        #             "likedByAuthUser": likedbyUser,
        #             "comment": serializer.data
        #         }
        #         lis.append(temp)
        #     i=i+1
        # print("comments are",comments)
        # current_user = request.user
        # print("current user is", current_user)
        # return  Response(lis, status=HTTP_200_OK)
