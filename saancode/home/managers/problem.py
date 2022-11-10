from functools import reduce
from django.contrib.auth.models import User
from home.models import *
from home.serializers import ProblemSerializer, solvedSerializer, sortProblemSerializer, tagsSerializer
from rest_framework.response import Response
from django.db.models import Q
import operator


class problemLogic:

    def getAllProblems(req):
        problems = Problem.objects.all()
        serializer = ProblemSerializer(problems, many = True)
        return Response(serializer.data)

    def getProblemWithId(req, id):
        problem = Problem.objects.get(problem_id = id)
        # tagsList = list(TopicTag.objects.filter(problem_id = problem.problem_id).iterator())
        # tags = []
        # for tagIndex in tagsList:
        #     tags.append(tagIndex.tag_id.tag_name)
        serializer = ProblemSerializer(problem, many = False)
        return Response({"problems": serializer.data})

    def sortAndGetProblems(req):
        params = []
        print(req.GET.get('accuracy', '0'))
        difficulty = req.GET.get('difficulty', '0')
        accuracy = req.GET.get('accuracy', '0')
        problems = None
        if difficulty == "1" and accuracy == "1":
            problems = Problem.objects.all().order_by('difficulty_level', 'accuracy')
            params.append("difficulty_level")
        elif accuracy == "1":
            problems = Problem.objects.all().order_by('accuracy')
        elif difficulty == "1":
            problems = Problem.objects.all().order_by('difficulty_level')
        if difficulty == "-1" and accuracy == "-1":
            problems = Problem.objects.all().order_by('-difficulty_level', '-accuracy')
            params.append("difficulty_level")
        elif accuracy == "-1":
            problems = Problem.objects.all().order_by('-accuracy')
        elif difficulty == "-1":
            problems = Problem.objects.all().order_by('-difficulty_level')
        serializer = sortProblemSerializer(problems, many = True)
        return Response(serializer.data)

    def get_tags(req):
        tags = ProblemTag.objects.all()
        serializer = tagsSerializer(instance=tags, many = True)
        return Response(serializer.data)

    def filter_all_problems(req):
        search, diff, tags = req.data['search'], req.data['diff'], req.data['tags']
        problems = None
        if diff != -1:
            if len(tags) != 0:
                problems = Problem.objects.filter(problem_name__icontains = search, difficulty_level = diff, tags__name__in = tags)
            else:
                problems = Problem.objects.filter(problem_name__icontains = search, difficulty_level = diff)
        elif len(tags) == 0:
            if diff != -1:
                problems = Problem.objects.filter(problem_name__icontains = search, difficulty_level = diff)
            else:
                problems = Problem.objects.filter(problem_name__icontains = search)                
        else:
            problems = Problem.objects.filter(problem_name__icontains = search, tags__name__in = tags)
        serializer = ProblemSerializer(instance=problems, many = True)
        print(serializer.data)
        return Response(serializer.data)

    def get_streak(req):
        username = req.data['username']
        easy = Solved.objects.filter(problem_id__difficulty_level = 0, status = 1, user_id__username = username).values('problem_id').distinct().count()
        medium = Solved.objects.filter(problem_id__difficulty_level = 1, status = 1, user_id__username = username).values('problem_id').distinct().count()
        hard = Solved.objects.filter(problem_id__difficulty_level = 2, status = 1, user_id__username = username).values('problem_id').distinct().count()
        return Response({'streak':(easy * 2) + (medium * 4) + (hard * 8)})

    def get_accuracy(problem_id):
        solved = Solved.objects.filter(problem_id=problem_id)
        correct = solved.filter(status = 1).count()
        # for query in solved:
        #     if query.status == 1:
        #         correct += 1
        accuracy = (correct / solved.count()) * 100
        return accuracy

    def all_submissions(req):
        username = req.data['username']
        solved = Solved.objects.filter(user_id__username = username)
        serializer = solvedSerializer(instance=solved, many = True)
        return Response(serializer.data)

class problemVoteLogic:

    def getVoteWithProblemId(req, id):
        problem = Problem.objects.get(problem_id = id)
        serializer = ProblemSerializer(instance = problem, data = req.data)
        if serializer.is_valid():
            serializer.save()
        return Response(serializer.data)

    def voteOrDelete(req, problemId, username):
        creator_id = User.objects.get(username = username)
        problem = Problem.objects.get(problem_id = problemId)
        voted = ''
        if (req.method == "GET"):
            try:
                vote = ProblemVotes.objects.get(problem_id = problem.problem_id, voter_id = creator_id)
                # vote.delete()
                voted = vote.vote
            except:
                pass
            votesLike = ProblemVotes.objects.filter(problem_id = problem.problem_id, vote = 'L').count()
            votesDislike = ProblemVotes.objects.filter(problem_id = problem.problem_id, vote = 'D').count()
            return Response({"like": votesLike, "dislike": votesDislike, "vote": voted})

        if (req.method == "POST"):
            try:
                vote = ProblemVotes.objects.get(problem_id = problem.problem_id, voter_id = creator_id)
                if req.data['vote'] == 'LA':
                    vote.delete()
                    problem.likes = problem.likes - 1
                    problem.save()
                    # problem.deleteLikes()
                # vote.delete()
                    voted = ''
                elif req.data['vote'] == "DA":
                    vote.delete()
                    problem.dislikes = problem.dislikes - 1
                    problem.save()
                    # problem.deleteDisLikes()
                    voted = ''
            except:
                vote = ProblemVotes.objects.create(problem_id = problem, voter_id = creator_id)
                if req.data['vote'] == "L":
                    vote.vote = 'L'
                    vote.save()
                    problem.likes = problem.likes + 1
                    problem.save()
                    # problem.addLikes()
                    voted = 'L'
                else:
                    vote.vote = 'D'
                    vote.save()
                    problem.dislikes = problem.dislikes + 1
                    problem.save()
                    # problem.addDisLikes()
                    voted = 'D'
            votesLike = ProblemVotes.objects.filter(problem_id = problem.problem_id, vote = 'L').count()
            votesDislike = ProblemVotes.objects.filter(problem_id = problem.problem_id, vote = 'D').count()
            return Response({"like": votesLike, "dislike": votesDislike, "vote": voted})