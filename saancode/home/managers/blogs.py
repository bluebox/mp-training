from django.contrib.auth.models import User
from home.models import *
from home.serializers import BlogsSerializer, blogCommentSerializer
from rest_framework.response import Response
from functools import reduce
import operator
from django.db.models import Q

class blogsLogic:

    def add_blog_with_username(req):
        user = User.objects.get(username=req.data['user_id'])
        Blog.objects.create(user_id = user, title = req.data['title'], discussion = req.data['discussion'], tag = req.data['tag'])
        return Response({"status": 200})

    def getAllBlogs(req):
        blogs = Blog.objects.all().order_by("created_date_time")
        serializer = BlogsSerializer(instance=blogs, many=True)
        return Response(serializer.data)

    def getBlogWithId(req):
        blog = Blog.objects.get(blog_id = req.data.get('id'))
        serializer = BlogsSerializer(instance=blog)
        return Response(serializer.data)

    def getBlogsWithCategory(req):
        try:
            blogs = Blog.objects.filter(tag = req.data['tag'])
            serializer = BlogsSerializer(instance=blogs, many = True)
            return Response(serializer.data)
        except Exception as e:
            return Response({"messege": "page not found", "status": 200})

    def getBlogsWithSearchWord(req):
        search_word = req.data['search']
        search_word = search_word.strip(' ')
        word = ""
        for i in range(len(search_word)):
            if search_word[i] == " " and search_word[i - 1] == " ":
                continue
            else:
                word += search_word[i]
        keywords = word.split(" ")
        queryset = Blog.objects.filter(reduce(operator.or_, (Q(title__contains = x) for x in keywords))).order_by("created_date_time")

        if len(queryset) == 0:
            return Response({"status": 404})    

        serializer = BlogsSerializer(instance=queryset, many = True)
        return Response(serializer.data)

    def deleteBlogWithId(req):
        deletedDiscussion = Blog.objects.get(blog_id = req.data['blog_id'])
        deletedDiscussion.delete()
        return Response({"status":200, "messege":"successfully deleted thread"})

    def editBlogWithId(req):
        editedDiscussion = Blog.objects.get(blog_id = req.data['blog_id'])
        editedDiscussion.discussion = req.data['discussion']
        editedDiscussion.title = req.data['title']
        editedDiscussion.save()
        return Response({"status": 200, "messege": "discussion updated successfully"})

class blogsCommentLogic:
    def addBlogComment(req):
        blog_id = Blog.objects.get(blog_id = req.data['blog_id'])
        user_id = User.objects.get(username = req.data['user_id'])
        comment = BlogComment.objects.create(blog_id = blog_id, user_id = user_id, comment = req.data['comment'])
        serializer = blogCommentSerializer(instance=comment)
        return Response(serializer.data)

    def getBlogComments(req):
        data = BlogComment.objects.prefetch_related('blogcommentreply_set').filter(blog_id = req.data['blog_id'])
        serializer = blogCommentSerializer(instance=data, many = True)
        return Response(serializer.data)

    def edit_blog_comment_by_id(req):
        blog = BlogComment.objects.get(comment_id = req.data['comment_id'])
        blog.comment = req.data['comment']
        blog.save()
        return Response({"status": 200})

    def delete_blog_comment_by_id(req):
        BlogComment.objects.get(comment_id = req.data['comment_id']).delete()
        return Response({'status': 200})

    def add_blog_reply_by_comment_id(req):
        user = User.objects.get(username = req.data['username'])
        comment_id = BlogComment.objects.get(comment_id = req.data['comment_id'])
        BlogCommentReply.objects.create(user_id = user, comment_id = comment_id, reply = req.data['reply'])
        return Response({'status': 200})

    def delete_blog_comment_reply_by_id(req):
        BlogCommentReply.objects.get(reply_id = req.data['reply_id']).delete()
        return Response({"status": 200})