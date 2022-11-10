from django.contrib.auth.models import User
from django.http import HttpResponse
from home.models import *
from home.serializers import BlogsSerializer, PostDiscussionSerializer, blogCommentSerializer, commentSerializer, discussionsSerializer, editDiscussionComment
from rest_framework.response import Response
from functools import reduce
import operator
from django.db.models import Q

class discussionLogic:

    def getDiscussionWithId(req, problemId, discussionId):
        discuss = Discussion.objects.get(discussion_id = discussionId)
        print(discuss)
        comment = Comment.objects.filter(discussion_id = discussionId)
        commentSerial = commentSerializer(comment, many = True)
        serializer = discussionsSerializer(discuss, many = False)
        return Response({"discussion": serializer.data, "comments": commentSerial.data})

    def getDiscussionWithProblemId(req, problemId):
        problem = Problem.objects.get(problem_id = problemId)
        discussions = Discussion.objects.filter(problem_id = problem)
        serializer = discussionsSerializer(discussions, many = True)
        print(serializer.data)
    
        return Response(serializer.data)

    def postDiscussionWithProblemId(req, problem_id, username):
        creator_id = User.objects.get(username = username)
        problem = Problem.objects.get(problem_id = problem_id)
        discussion = Discussion.objects.create(username = creator_id, problem_id = problem, title = req.data['title'], discussion = req.data['discussion'])
        serializer = PostDiscussionSerializer(instance = discussion, data = req.data)
        if serializer.is_valid():
            return Response({"status":200, "message":"success", "response": serializer.data})
        discussion.delete()
        return Response({"status":403, "message":"invalid", "errors": serializer.errors})

    def deleteDiscusisonWithId(req):
        deletedDiscussion = Discussion.objects.get(discussion_id = req.data['discussion_id'])
        deletedDiscussion.delete()
        return Response({"status":200, "messege":"successfully deleted thread"})

    def editDiscussionWithId(req):
        print(req.data)
        editedDiscussion = Discussion.objects.get(discussion_id = req.data['discussion_id'])
        editedDiscussion.discussion = req.data['discussion']
        editedDiscussion.title = req.data['title']
        editedDiscussion.save()
        return Response({"status": 200, "messege": "discussion updated successfully"})

class discussionCommentsLogic:

    def postDiscussionComments(req, discussionId, username):
        discussion_id = Discussion.objects.get(discussion_id = discussionId)
        user_id = User.objects.get(username = username)
        comment = Comment.objects.create(discussion_id = discussion_id, user_id = user_id)
        serializer = commentSerializer(instance = comment, data = req.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        return Response(serializer.errors)

    def editCommentById(req):
        comment = Comment.objects.get(comment_id = req.data['comment_id'])
        serializer = editDiscussionComment(instance=comment, data=req.data)
        if serializer.is_valid():
            comment.comment = req.data['comment']
            comment.save()
            return Response({"status": 200})
        return Response(serializer.errors)