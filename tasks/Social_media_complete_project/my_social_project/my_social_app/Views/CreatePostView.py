"""THIS IS  CREATE POST VIEW"""
import json
from rest_framework.views import APIView
from rest_framework.response import Response
from rest_framework.status import HTTP_200_OK
from rest_framework.permissions import IsAuthenticated
from ..models import Tag, Post
from ..renderers import UserRenderer
from ..models import User
from ..serializers import PostSerializer


class CreatePostView(APIView):
    """THIS IS  CREATE POST VIEW"""
    renderer_classes = [UserRenderer]
    permission_classes = [IsAuthenticated]
    def post(self, request):
        """THIS IS  CREATE POST VIEW"""
        content = request.data['content']
        postPhoto = request.data['postPhoto']
        postTags = request.data['postTags']
        user = request.user
        print(user)
        print(user.id)
        user1 = User.objects.filter(id=user.id).first()
        print(user1, "user id is")
        create_post = Post(postPhoto=postPhoto, content=content, author=user1)

        create_post.save()
        print("username is :", create_post.author.username)
        print(create_post)
        print(content)
        print(postPhoto)
        postTags = json.loads(postTags)
        print(postTags)
        for i in postTags:
            print(i["tagName"], "helo")
            tag = Tag.objects.filter(name=i["tagName"]).exists()
            print(tag)
            if not tag:
                create_tag = Tag(name=i["tagName"])
                create_tag.tagUseCounter = 1
                create_tag.save()
            else:
                update_tag = Tag.objects.get(name=i["tagName"])
                update_tag.tagUseCounter = update_tag.tagUseCounter + 1
                update_tag.save()
            post_tag = Tag.objects.get(name=i["tagName"])
            create_post.postTags.add(post_tag.id)
            serializer = PostSerializer(create_post)
            create_post.save()

        print(postTags)
        print(serializer.data)

        return Response(serializer.data, status=HTTP_200_OK)
