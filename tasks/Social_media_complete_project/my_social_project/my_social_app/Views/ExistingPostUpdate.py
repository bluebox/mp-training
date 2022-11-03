"""THIS IS EXISTING POST UPDATE"""
import json
from rest_framework.views import APIView
from rest_framework.response import Response
from rest_framework.status import *
from rest_framework.permissions import IsAuthenticated
from ..models import Tag, Post
from ..renderers import UserRenderer
from ..serializers import  GetPostDataByIdSerializer


class ExistingPostUpdate(APIView):
    """THIS IS EXISTING POST UPDATE"""
    renderer_classes = [UserRenderer]
    permission_classes = [IsAuthenticated]

    def post(self, request, post_id=None):
        """THIS IS EXISTING POST UPDATE"""
        update_post = Post.objects.get(id=post_id)
        print(update_post)
        content = request.data['content']
        print(content)

        postPhoto = request.data['postPhoto']
        print(postPhoto)
        print(type(postPhoto))

        postTags = request.data['postTags']
        print(postTags)

        update_post.content = content

        if postPhoto != 'undefined':
            print("123")
            update_post.postPhoto = postPhoto

        update_post.save()
        postTags = json.loads(postTags)
        print(postTags)

        for tag in postTags:
            print("143")
            if tag['action'] == 'add':
                tag_exists = Tag.objects.filter(name=tag['tagName'])
                if tag_exists.exists():
                    updateTag = Tag.objects.get(name=tag['tagName'])
                    updateTag.tagUseCounter = updateTag.tagUseCounter + 1
                    update_post.postTags.add(updateTag)
                    update_post.save()
                    updateTag.save()
                else:
                    create_tag = Tag(name=tag['tagName'])
                    create_tag.tagUseCounter = 1
                    create_tag.save()
                    update_post.postTags.set(create_tag)
                    update_post.save()



            elif tag['action'] == 'saved':

                pass

            elif tag['action'] == 'remove':
                update_tag = Tag.objects.get(name=tag['tagName'])
                update_post.postTags.remove(update_tag)
                update_post.save()
                update_tag.tagUseCounter = update_tag.tagUseCounter - 1
                update_tag.save()

        serializer = GetPostDataByIdSerializer(update_post)
        return Response(serializer.data, status=HTTP_200_OK)

