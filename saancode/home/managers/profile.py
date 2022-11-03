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

        totalEasy, totalMedium, totalHard = Problem.objects.filter(difficulty_level = 0).count(), Problem.objects.filter(difficulty_level = 1).count(), Problem.objects.filter(difficulty_level = 2).count()

        total = totalEasy + totalMedium + totalHard

        easy, medium, hard = 0, 0, 0

        easy = Solved.objects.select_related('problem_id').filter(status = 1, user_id__username = username, problem_id__difficulty_level = 0).values('problem_id').distinct().count()
        medium = Solved.objects.select_related('problem_id').filter(status = 1, user_id__username = username, problem_id__difficulty_level = 1).values('problem_id').distinct().count()
        hard = Solved.objects.select_related('problem_id').filter(status = 1, user_id__username = username, problem_id__difficulty_level = 2).values('problem_id').distinct().count()

        return Response({"total": total, "totalEasy": totalEasy, "totalMedium": totalMedium, "totalHard": totalHard, "easy": easy, "medium": medium, "hard": hard})