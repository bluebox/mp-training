from django.contrib.auth.models import User
from home.models import *
from home.serializers import BlogsSerializer, blogCommentSerializer, editProfileSerializer, profileSerializer
from rest_framework.response import Response
from functools import reduce
import operator
from django.db.models import Q

class profileLogic:

    def getProfileWithUsername(req, username):
        profile = User.objects.get(username = username).profile
        serialzer = profileSerializer(profile, many = False)
        return Response(serialzer.data)

    def editProfileWithUsername(req, username):
        profile = User.objects.get(username = username).profile
        serializer = editProfileSerializer(instance = profile, data = req.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        print(req.data)
        print(serializer.errors)
        return Response({"data":"invalid", "status": 403})

    def problemStatistics(req):
        username = req.data['username']

        totalEasy, totalMedium, totalHard = len(Problem.objects.filter(difficulty_level = 0)), len(Problem.objects.filter(difficulty_level = 1)), len(Problem.objects.filter(difficulty_level = 2))

        total = totalEasy + totalMedium + totalHard

        easy, medium, hard = 0, 0, 0

        easy = len(Solved.objects.select_related('problem_id').filter(status = 1, user_id__username = username, problem_id__difficulty_level = 0))
        medium = len(Solved.objects.select_related('problem_id').filter(status = 1, user_id__username = username, problem_id__difficulty_level = 1))
        hard = len(Solved.objects.select_related('problem_id').filter(status = 1, user_id__username = username, problem_id__difficulty_level = 2))

        return Response({"total": total, "totalEasy": totalEasy, "totalMedium": totalMedium, "totalHard": totalHard, "easy": easy, "medium": medium, "hard": hard})