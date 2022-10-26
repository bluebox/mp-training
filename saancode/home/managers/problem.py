from django.contrib.auth.models import User
from home.models import *
from home.serializers import ProblemSerializer, sortProblemSerializer, tagsSerializer
from rest_framework.response import Response


class problemLogic:

    def getAllProblems(req):
        problems = Problem.objects.all()
        serializer = ProblemSerializer(problems, many = True)
        return Response(serializer.data)

    def getProblemWithId(req, id):
        problem = Problem.objects.get(problem_id = id)
        tagsList = list(TopicTag.objects.filter(problem_id = problem.problem_id).iterator())
        tags = []
        for tagIndex in tagsList:
            tags.append(tagIndex.tag_id.tag_name)
        serializer = ProblemSerializer(problem, many = False)
        return Response({"problems": serializer.data, "tags": tags})

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

    def get_streak(req):
        username = req.data['username']
        easy = len(Solved.objects.filter(problem_id__difficulty_level = 0, status = 1, user_id__username = username).values('problem_id').distinct())
        medium = len(Solved.objects.filter(problem_id__difficulty_level = 1, status = 1, user_id__username = username).values('problem_id').distinct())
        hard = len(Solved.objects.filter(problem_id__difficulty_level = 2, status = 1, user_id__username = username).values('problem_id').distinct())
        return Response({'streak':(easy * 2) + (medium * 4) + (hard * 8)})

    def get_accuracy(problem_id):
        solved = Solved.objects.filter(problem_id=problem_id)
        correct = 0
        for query in solved:
            if query.status == 1:
                correct += 1
        accuracy = (correct / len(solved)) * 100
        return accuracy

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
            votesLike = len(ProblemVotes.objects.filter(problem_id = problem.problem_id, vote = 'L'))
            votesDislike = len(ProblemVotes.objects.filter(problem_id = problem.problem_id, vote = 'D'))
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
            votesLike = len(ProblemVotes.objects.filter(problem_id = problem.problem_id, vote = 'L'))
            votesDislike = len(ProblemVotes.objects.filter(problem_id = problem.problem_id, vote = 'D'))
            return Response({"like": votesLike, "dislike": votesDislike, "vote": voted})