

from rest_framework.views import APIView
from rest_framework.response import Response

from ..models import  Post, PostLike, Comment, FollowUsers, Notification
from ..renderers import UserRenderer
from rest_framework.permissions import IsAuthenticated

from ..serializers import  GetTimelinePostDataSerializer


class PostShareCreteView(APIView):
    renderer_classes = [UserRenderer]
    permission_classes = [IsAuthenticated]

    def post(self, request, post_id=None):
        print(post_id)
        post_data = Post.objects.filter(id=post_id).first()
        print("post data is:", post_data)
        current_user = request.user
        print(post_data.postTags)
        print("current user is", current_user)
        content = request.data['content']
        print(content)
        new_shared_post = Post(content=content, postPhoto=post_data.postPhoto, isTypeShare=True, author=current_user)
        followers_data = FollowUsers.objects.filter(followed=current_user)
        print(followers_data)

        new_shared_post.save()
        for follower_data in followers_data:
            new_notification = Notification(type='POST_SHARE', owningPost=new_shared_post, sender=current_user,
                                            receiver=follower_data.follower)
            new_notification.save()

        post_data.shareCount = post_data.shareCount + 1
        post_data.save()
        serializer = GetTimelinePostDataSerializer(new_shared_post)
        return Response(serializer.data)

