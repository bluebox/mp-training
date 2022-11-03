"""THIS IS  EXISTING POST SHARE DELETE"""
from rest_framework.views import APIView
from rest_framework.response import Response
from rest_framework.status import HTTP_200_OK
from rest_framework.permissions import IsAuthenticated
from ..models import  Post, PostLike, Comment, CommentLikes
from ..renderers import UserRenderer

class ExistingPostShareDelete(APIView):
    """THIS IS  EXISTING POST SHARE DELETE"""
    renderer_classes = [UserRenderer]
    permission_classes = [IsAuthenticated]
    def post(self, request, post_id):
        """THIS IS  EXISTING POST SHARE DELETE"""
        print(post_id)
        post_data = Post.objects.get(id=post_id)
        post_likes = PostLike.objects.filter(post=post_data)
        for post_like in post_likes:
            post_like.delete()
        comments_data = Comment.objects.filter(post=post_data)
        for comment_data in comments_data:

            comment_likes = CommentLikes.objects.filter(comment=comment_data)
            for comment_like in comment_likes:
                comment_like.delete()
            comments_data.delete()
        post_data.delete()
        return Response({"msg": "Successfully Deleted"}, status=HTTP_200_OK)
