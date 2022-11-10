from django.contrib.auth.models import User
from home.models import *
from home.serializers import BlogsSerializer, blogCommentSerializer, editProfileSerializer, profileSerializer
from rest_framework.response import Response
from functools import reduce
import operator
from django.db.models import Q

class profileLogic:

    def getProfileWithUsername(req, username):
        profile = None
        try:
            profile = Profile.objects.get(user__username = username)
        except:
            profile = Profile.objects.get(user_id = username)
        following = profile.get_following()
        followers = profile.get_followers()
        serializer = profileSerializer(profile, many = False)
        following_serializer = profileSerializer(following, many = True)
        follower_serializer = profileSerializer(followers, many = True)
        print(following, followers, profile)
        return Response({ "profile": serializer.data, "following": following_serializer.data, "followers": follower_serializer.data })

    def editProfileWithUsername(req, username):
        profile = User.objects.get(username = username).profile
        serializer = editProfileSerializer(instance = profile, data = req.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        print(req.data)
        print(serializer.errors)
        return Response({"data":"invalid", "status": 403})

    def unfollow_user(req):
        print("/////////")
        print(req.data)
        profile = Profile.objects.get(user_id = req.data['following_id'])
        try:
            profile.followed_by.remove(req.data['profile_id'])
        except:
            profile_id = Profile.objects.get(user__username = req.data['profile_id'])
            profile.followed_by.remove(profile_id.user_id)
        return Response({"msg": "success"})

    def follow_user(req):
        print("/////////")
        print(req.data)
        profile = Profile.objects.get(user__username = req.data['profile_id'])
        print(profile.user_id)
        try:
            profile.follows.add(req.data['following_id'])
        except:
            profile_id = Profile.objects.get(user__username = req.data['profile_id'])
            profile.followed_by.add(profile_id.user_id)
        return Response({"msg": "success"})

    def get_profiles_by_id(user_id):
        profile = Profile.objects.get(user = user_id)
        serialzer = profileSerializer(profile)
        return Response(serialzer.data)

    def problemStatistics(req):
        username = req.data['username']

        totalEasy, totalMedium, totalHard = Problem.objects.filter(difficulty_level = 0).count(), Problem.objects.filter(difficulty_level = 1).count(), Problem.objects.filter(difficulty_level = 2).count()

        total = totalEasy + totalMedium + totalHard

        easy, medium, hard = 0, 0, 0

        easy = Solved.objects.select_related('problem_id').filter(status = 1, user_id__username = username, problem_id__difficulty_level = 0).values('problem_id').distinct().count()
        medium = Solved.objects.select_related('problem_id').filter(status = 1, user_id__username = username, problem_id__difficulty_level = 1).values('problem_id').distinct().count()
        hard = Solved.objects.select_related('problem_id').filter(status = 1, user_id__username = username, problem_id__difficulty_level = 2).values('problem_id').distinct().count()

        return Response({"total": total, "totalEasy": totalEasy, "totalMedium": totalMedium, "totalHard": totalHard, "easy": easy, "medium": medium, "hard": hard})