import csv
import datetime

import xlwt
from django.core.paginator import Paginator
from django.db.models import Q
from datetime import datetime as dt
from django.http import HttpResponse
import tablib
from post.models import Post, Comments
from post.serializers import PostSerializer, CommentSerializer, PostExcelSerializer
from cloudinary.uploader import upload


def upload_to_cloudinary(request):
    temp = upload(request.data.get('image'))
    return temp['secure_url']


def pagination(request, object_list, per_page):
    paginator = Paginator(object_list, per_page)
    try:
        page_number = int(request.GET.get('page'))
    except:
        page_number = 1
    page_obj = paginator.get_page(page_number)
    return page_obj, paginator.num_pages, page_number


class PostView:
    @staticmethod
    def add_post(request):
        serializer_data = PostSerializer(data=request.data)
        if serializer_data.is_valid():
            serializer_data.save()
            return serializer_data.data
        return serializer_data.errors

    @staticmethod
    def get_posts(request):
        posts = Post.objects.filter()
        count = posts.count()
        start = int(6 * int(request.GET['page']))
        endpage = 6 * (int(request.GET['page']) + 1)
        posts = posts[start: endpage]
        serializer_data = PostSerializer(posts, many=True)
        return serializer_data.data, count

    @staticmethod
    def bulk_uploader(request):
        objs = request.data.get('objs')
        to_be_created = []
        err = []
        i = 0
        for obj in objs:
            serializer_data = PostSerializer(data=obj)
            if serializer_data.is_valid():
                to_be_created.append(
                    Post(name=obj['name'], email=obj['email'], mobile=obj['mobile'], images=obj['images'],
                         category=obj['category']))
                print(1)
            else:
                err.append((serializer_data.data, i))
            i += 1
        Post.objects.bulk_create(to_be_created)
        return err

    @staticmethod
    def getPost(request):
        post = Post.objects.get(post_id=request.GET['post_id'])
        serializer_data = PostSerializer(post)
        return serializer_data.data

    @staticmethod
    def add_likes(request):
        post = Post.objects.get(post_id=request.GET['post_id'])
        post.likes = post.likes + 1
        post.save()
        return PostSerializer(post).data

    @staticmethod
    def add_shares(request):
        post = Post.objects.get(post_id=request.GET['post_id'])
        post.shares = post.shares + 1
        post.save()
        return PostSerializer(post).data

    @staticmethod
    def get_posts_with_filters(request):
        category = request.GET['category']
        start_date = request.data.get('start_date')
        end_date = request.data.get('end_date')
        status = request.GET['status']
        query = Q()
        print(query)

        if status != 'all':
            is_completed = True if (status != 'yettocomplete') else False
            is_winner = True if (status == 'winner') else False
            query = query & Q(is_winner=is_winner, is_completed=is_completed)

        if start_date is not None:
            start_date = dt.strptime(start_date[5:7] + "/" + start_date[8:10] + "/" + start_date[0:4], "%m/%d/%Y")
            end_date = dt.strptime(end_date[5:7] + "/" + end_date[8:10] + "/" + end_date[0:4], "%m/%d/%Y")
            start_date += datetime.timedelta(1)
            end_date += datetime.timedelta(2)
            print(start_date, end_date)
            # query &= Q(date__gt=start_date, date__lte=end_date)
            query &= Q(date__range=(start_date, end_date))
        else:
            if category != 'all':
                query = query & Q(category=category)
        posts = Post.objects.filter(query)
        data, total_pages, current_page = pagination(request, posts, 6)
        return PostSerializer(data, many=True).data, total_pages, current_page

    @staticmethod
    def get_excel(request):
        category = request.GET['category']
        start_date = request.data.get('start_date')
        end_date = request.data.get('end_date')
        status = request.GET['status']
        query = Q()
        print(query)
        if status != 'all':
            is_completed = True if (status != 'yettocomplete') else False
            is_winner = True if (status == 'winner') else False
            query = query & Q(is_winner=is_winner, is_completed=is_completed)
        if start_date is not None:
            start_date = dt.strptime(start_date[5:7] + "/" + start_date[8:10] + "/" + start_date[0:4], "%m/%d/%Y")
            end_date = dt.strptime(end_date[5:7] + "/" + end_date[8:10] + "/" + end_date[0:4], "%m/%d/%Y")
            start_date += datetime.timedelta(1)
            end_date += datetime.timedelta(2)
            # query &= Q(date__gte=start_date, date__lte=end_date)
            query &= Q(date__range=(start_date, end_date))

        else:

            if category != 'all':
                query = query & Q(category=category)
        posts = Post.objects.filter(query)
        return PostExcelSerializer(posts, many=True).data

    @staticmethod
    def evaluate(request):
        category = request.GET['category']
        posts = Post.objects.filter(category=category)
        max_score = 0
        winner = None
        for obj in posts:
            obj.is_completed = True
            obj.score = int(obj.likes + (obj.shares * 2) + (obj.comments * 4))
            if obj.score > max_score:
                max_score = obj.score
                winner = obj
            winner.is_winner = True
                # posts.update('is_winner', 'is_completed', 'score')
        Post.objects.bulk_update(posts, fields=['is_winner', 'is_completed', 'score'])
        return PostSerializer(posts, many=True).data

    @staticmethod
    def get_updates(request):
        posts = Post.objects.filter()[0: 6 * (int(request.GET['page']) + 1)]
        serializer_data = PostSerializer(posts, many=True)
        return serializer_data.data


class CommentsView:
    @staticmethod
    def post_comment(request):
        serializer_data = CommentSerializer(data=request.data)
        if serializer_data.is_valid():
            serializer_data.save()
            post = Post.objects.get(post_id=request.GET['post_id'])
            post.comments = post.comments + 1
            post.save()
            return serializer_data.data

    @staticmethod
    def getComments(request):
        comments = Comments.objects.filter(post_id=request.GET['post_id'])
        data, total_pages, current_page = pagination(request, comments, 6)
        serializer_data = CommentSerializer(data, many=True)
        return serializer_data.data, total_pages, current_page

    @staticmethod
    def export_post_xls(request):
        posts = Post.objects.all()
        response = HttpResponse(
            content_type='application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=UTF-8'
        )
        response['Content-Disposition'] = 'attachment;'
        wb = xlwt.Workbook(encoding='utf-8')
        ws = wb.add_sheet("MyModel")
        writer = csv.writer(response)
        font_style = xlwt.XFStyle()
        bold = xlwt.XFStyle()
        bold.font.bold = True
        ws.write(0, 0, "name", bold)
        ws.write(0, 1, "category", bold)
        ws.write(0, 2, "score", bold)
        ws.write(0, 3, "status", bold)
        ws.write(0, 4, "images", bold)
        row = 1
# font_style.font.bold = True
        # writer.writerow(['name', 'email', 'mobile', 'images', 'status'])
        for  post in posts :
            # ws.writerow(post)
            ws.write(row, 0, post.name, font_style)
            ws.write(row, 1, post.category, font_style)
            ws.write(row, 2, post.score, font_style)
            ws.write(row, 4, post.images, font_style)
            status = 'winner' if (post.is_completed and post.is_winner) else 'participant' if (
                        post.is_completed and not post.is_winner) else 'yet to complete'
            ws.write(row, 3, status, font_style)
            # writer.writerow([post.name, post.email, post.mobile, post.images])
            row += 1
        wb.save(response)
        return response
        # from django.http import HttpResponse
        # headers = ('name', 'mobile', 'email', 'images', 'score', 'is_completed', 'is_winner')
        # data = []
        # data = tablib.Dataset(*data, headers=headers)
        # posts = Post.objects.all()
        # for post in posts:
        #     data.append(
        #         (post.name, post.mobile, post.email, post.images, post.score, post.is_completed, post.is_winner))
        # response = HttpResponse(data.xls, content_type='application/vnd.ms-excel;charset=utf-8')
        # response['Content-Disposition'] = "attachment; filename=export.xls"
        # wb.save(response)
        # return response
    #     # response = HttpRespon
#     se(content_type='application/ms-excel')
#     # response['Content-Disposition'] = 'attachment; filename="users.xls"'
#     ws = wb.add_sheet('Users')
#     # Sheet header, first row
#     row_num = 0
#     font_style = xlwt.XFStyle()
#     font_style.font.bold = True
#     columns = ['name', 'mobile', 'email', 'images', 'score', 'is_completed', 'is_winner']
#     for col_num in range(len(columns)):
#         ws.write(row_num, col_num, columns[col_num], font_style)
#
#     # Sheet body, remaining rows
#     font_style = xlwt.XFStyle()
#
#     rows = Post.objects.all().values_list('name', 'mobile', 'email', 'images', 'score', 'is_completed', 'is_winner')
#     for row in rows:
#         row_num += 1
#         for col_num in range(len(row)):
#             ws.write(row_num, col_num, row[col_num], font_style)
#
#     file1 = wb.save("execl1.xls")
#     return file1
