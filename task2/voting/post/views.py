from django.http import JsonResponse
from rest_framework.decorators import api_view
from rest_framework.response import Response
from rest_framework.views import APIView
from post.manager import PostView, upload_to_cloudinary, CommentsView
from post.models import Post
from post.serializers import PostSerializer
import tablib


# from post.models import Post, User
# from post.serializers import PostSerializer, UserSerializer


class AddPost(APIView):
    @staticmethod
    def get(request):
        try:
            data, count = PostView.get_posts(request)
            res = {
                "count": count,
                'data': data
            }

            return JsonResponse(res, safe=False)
        except Exception as e:
            return JsonResponse(str(e), safe=False)

    @staticmethod
    def post(request):
        try:
            return JsonResponse(PostView.add_post(request), safe=False)
        except Exception as e:
            return JsonResponse(str(e), safe=False)


class ImageView(APIView):
    @staticmethod
    def post(request):
        try:
            return Response(upload_to_cloudinary(request))
        except Exception as e:
            return JsonResponse(str(e), safe=False)


class ViewPost(APIView):
    @staticmethod
    def get(request):
        try:
            return JsonResponse(PostView.getPost(request), safe=False)
        except Exception as e:
            return JsonResponse(str(e), safe=False)


class CommentView(APIView):
    @staticmethod
    def get(request):
        try:
            data, total_pages, current_page = CommentsView.getComments(request)
            res = {
                "data": data,
                "total_pages": total_pages,
                "current_page": current_page
            }
            return JsonResponse(res, safe=False)
        except Exception as e:
            return JsonResponse(str(e), safe=False)

    @staticmethod
    def post(request):
        try:
            return JsonResponse(CommentsView.post_comment(request), safe=False)
        except Exception as e:
            return JsonResponse(str(e), safe=False)


def update_likes(request):
    try:
        return JsonResponse(PostView.add_likes(request), safe=False)
    except Exception as e:
        return JsonResponse(str(e), safe=False)


def update_shares(request):
    try:
        return JsonResponse(PostView.add_shares(request), safe=False)
    except Exception as e:
        return JsonResponse(str(e), safe=False)


@api_view(["post"])
def get_posts_with_filter(request):
    try:
        data, total_pages, current_page = PostView.get_posts_with_filters(request)
        res = {
            "data": data,
            "total_pages": total_pages,
            "current_page": current_page
        }
        return JsonResponse(res, safe=False)
    except Exception as e:
        return JsonResponse(str(e), safe=False)


class EvaluateView(APIView):
    @staticmethod
    def get(request):
        try:
            data = PostView.evaluate(request)
            return JsonResponse(data, safe=False)
        except Exception as e:
            return JsonResponse(str(e), safe=False)


def get_updates(request):
    try:
        return JsonResponse(PostView.get_updates(request), safe=False)
    except Exception as e:
        return JsonResponse(str(e), safe=False)


class BulkUpload(APIView):
    @staticmethod
    def post(request):
        try:
            err = PostView.bulk_uploader(request)
            return JsonResponse({"err": err}, safe=False)
        except Exception as e:
            return JsonResponse(str(e), safe=False)


class Chunks(APIView):
    @staticmethod
    def post(request):
        chunks = request.data.get('chunks')
        for chunk in chunks:
            print(chunk)


@api_view(['post'])
def get_excel_file(request):
    try:
        return JsonResponse(PostView.get_excel(request), safe=False)
    except Exception as e:
        return JsonResponse(str(e), safe=False)


@api_view(['get'])
def get_backend_excel(request):
    try:
        return CommentsView.export_post_xls(request)
    except Exception as e:
        return JsonResponse(str(e), safe=False)


def update(request):
    posts = Post.objects.all()
    posts.update(is_completed=False, is_winner=False, score=0)
    return JsonResponse(PostSerializer(posts, many=True).data, safe=False)


class Uplod(APIView):
    @staticmethod
    def post(request):
        # for ele in request.data.get('objs'):
        #     print(ele)
        #     serializer = UploadSerializer(data=ele)
        #     if serializer.is_valid():
        #         serializer.save()
        return JsonResponse("sucessfully uploaded", safe=False)

# class ChoiceViewSet(ModelViewSet):
# queryset = Post.objects.all()
#
# def sample(request):
#     # list_1 = ['samba', 'sam', 'hari', 'sairam', 'srikar', 'tharun']
#     # try __icontains__in
#     # posts = Post.objects.exclude(name__in=list_1).aggregate(count=count('samba'))
#     # posts = Post.objects.filter(name__in=list_1).annotate(count='Count(name)')
#     # posts = Post.objects.latest(name= "samba")
#     posts = Post.objects.all()
#     return JsonResponse(PostSerializer(posts, many=True).data, safe=False)
#
#
# class UserView(APIView):
#     @staticmethod
#     def get(self):
#         return JsonResponse(UserSerializer(User.objects.all()).data, safe=False)
