from rest_framework.authtoken.models import Token
from django.db.models import Count
from django.http import HttpResponse
from django.shortcuts import render, redirect
from django.contrib.auth.models import User

from    home.managers.problemDiscussion import discussionCommentsLogic, discussionLogic
from home.managers.profile import profileLogic
from .models import *
from .forms import UserRegistrationForm
from .managers.problem import *
from django.contrib import messages
from django.contrib.auth.decorators import login_required, user_passes_test
import requests, json
from json import dumps
from rest_framework.decorators import api_view, permission_classes, authentication_classes
from rest_framework.response import Response
from rest_framework import permissions
from .serializers import *
from rest_framework.permissions import AllowAny
from rest_framework.views import APIView
from django.contrib.auth.models import User
from rest_framework import generics
from rest_framework.authentication import TokenAuthentication
from rest_framework.permissions import IsAuthenticated
from .managers.blogs import blogsCommentLogic, blogsLogic

@permission_classes((permissions.AllowAny,))
@api_view(['GET'])
def api(request):
    api_urls = {
        'problems List':'/problems/',
        'problems detail':'/problem-detail/<str:id>',
        'problem vote':'/problem-vote<str:id>',
    }
    return Response(api_urls)

@api_view(['GET'])
def blogs_api(request):
    return blogsLogic.getAllBlogs(request)

@api_view(['POST'])
def blog_api(request):
    return blogsLogic.getBlogWithId(request)

@api_view(['GET'])
def problemsList(request):
    return problemLogic.getAllProblems(request)

@api_view(['POST'])
def delete_comment_api(request):
    Comment.objects.get(comment_id = request.data.get('comment_id')).delete()
    return Response({"status": 200})

@api_view(['GET'])
def sortProblemsApi(request):
    return problemLogic.sortAndGetProblems(request)

@api_view(['GET'])
def categoryApi(request, category):
    tag_id = Tag.objects.get(tag_name = category)
    problems = Problem.objects.filter(topictag__tag_id=tag_id)
    serializer = categorySerializer(problems, many = True)

    return Response({"problem" : serializer.data})

@api_view(['POST'])
def loginApi(request):
    try:
        user = User.objects.get(username = request.data['username'])
        if user.check_password(request.data['password']):
            token_obj, _ = Token.objects.get_or_create(user = user)
            return Response({"status":200, 'username': request.data['username'], 'token':str(token_obj), "message": "valid register found"})
        return Response({"status":403})
    except:
        return Response({"status":403})

@api_view(['POST'])
def registerApi(request):

    serializer = registerSerializer(data = request.data)
    if not serializer.is_valid():
        return Response({"status":403, "errors": serializer.errors, "message": "invalid register found"})
    serializer.save()
    # user = User.objects.create(username = request.data['username'], email = request.data['email'])
    # user.set_password(request.data['password1'])
    # user.save()
    user = User.objects.get(username = serializer.data['username'])
    token_obj, _ = Token.objects.get_or_create(user = user)
    return Response({'username': request.data['username'], "status":200, 'token':str(token_obj), 'is_staff': user.is_staff, "message": "valid register found"},)

@api_view(['GET'])
def submissionsApi(request, problem_id, username):
    user = User.objects.get(username = username)
    print(problem_id)
    problem = Problem.objects.get(problem_id = problem_id)
    print(problem.problem_id)
    solved = Solved.objects.filter(problem_id = problem, user_id = user)
    print(solved)
    serializer = solvedSerializer(solved, many = True)
    return Response(serializer.data)

@api_view(['POST'])
def postCommentApi(request, discussionId, username):
    return discussionCommentsLogic.postDiscussionComments(request, discussionId, username)

@api_view(['POST'])
def add_blog_comment_api(request):
    return blogsCommentLogic.addBlogComment(request)

@api_view(['GET'])
def getDiscussionApi(request, problemId, discussionId):
    return discussionLogic.getDiscussionWithId(request, problemId, discussionId)

def test(request):
    comment = BlogComment.objects.filter(blog_id__blog_id = 2, user_id__id = 1)
    print(comment)

@api_view(['POST'])
def problem_stats_api(request):
    return profileLogic.problemStatistics(request)

@api_view(['GET'])
def discussionsApi(request, problemId):
    return discussionLogic.getDiscussionWithProblemId(request, problemId)

@api_view(['POST'])
def blog_category_api(request):
    return blogsLogic.getBlogsWithCategory(request)

@api_view(['POST'])
def blog_search(request):
    return blogsLogic.getBlogsWithSearchWord(request)

@api_view(['POST'])
def editDiscusionComment(request):
    return discussionCommentsLogic.editCommentById(request)

@api_view(['POST'])
def editProfileApi(request, username):
    return profileLogic.editProfileWithUsername(request, username)

@api_view(['GET'])
def profilesApi(request):
    profile = Profile.objects.all()
    serialzer = profileSerializer(profile, many = True)
    return Response(serialzer.data)

@api_view(['GET'])
def profileApi(request, username):
    return profileLogic.getProfileWithUsername(request, username)

@api_view(['POST'])
def postDiscussionApi(request, problem_id, username):
    return discussionLogic.postDiscussionWithProblemId(request, problem_id, username)

@api_view(['GET'])
def problemDetail(request, id):
    return problemLogic.getProblemWithId(request, id)

@api_view(['GET', 'POST'])
def votesApi(request, problemId, username):
    return problemVoteLogic.voteOrDelete(request, problemId, username)
            
@api_view(['POST'])
def postDiscussionApi(request, problem_id, username):
    return discussionLogic.postDiscussionWithProblemId(request, problem_id, username)


@api_view(['POST'])
def postQuestionApi(request, username):
    print(request.data)
    creator_id = User.objects.get(username = username)

    try:
        if (Problem.objects.get(problem_name = request.data['data']['problem_name'])):
            return HttpResponse("problem with same name already exists")
    except:
        pass
    try:
        if (Problem.objects.get(description = request.data['data']['description'])):
            return HttpResponse("problem with same description already exists")
    except:
        pass
    problem = Problem.objects.create(creator_id = creator_id, problem_name = request.data['data']['problem_name'], description = request.data['data']['description'], hints = request.data['data']['hints'], test_cases = request.data['data']['test_cases'], outputs = request.data['data']['outputs'], json_test_cases = request.data['data']['inbuilt_code'])
    for i in request.data['data']['selected']:
        tagg = Tag.objects.get(tag_name = str(i))
        tag = TopicTag.objects.create(problem_id = problem, tag_id = tagg)
    # tagg = Tag.objects.get(tag_id = int(request.data['selected']))
    # tag = TopicTag.objects.create(problem_id = problem, tag_id = tagg)
    serializer = postQuestionSerializer(instance = problem, data = request.data['data'])
    if serializer.is_valid():
        serializer.save()
        return Response(serializer.data)
    problem.delete()
    return Response(serializer.errors)
    # tags = dict(request.data)["tags"]
    # print(tags, type(tags))
    # for tag in tags:
        # print(tag)
        # t = Tag.objects.get(tag_name = tag)
        # TopicTag.objects.create(problem_id = problem, tag_id = t)
    return HttpResponse("question posted successfully")

@api_view(['POST'])
def problemVote(request, id):
    return problemVoteLogic.getVoteWithProblemId(request, id)

# Create your views here.

@login_required(login_url = 'login')
def homeView(request):
    return render(request, 'home/home.html')

@api_view(['POST'])
def add_blog_api(request):
    return blogsLogic.add_blog_with_username(request)

@api_view(['POST'])
def add_blog_reply(request):
    return blogsCommentLogic.add_blog_reply_by_comment_id(request)

class edit_blog_comment_api(APIView):

    def post(self, request):
        return blogsCommentLogic.edit_blog_comment_by_id(request)

    def delete(self, request):
        return blogsCommentLogic.delete_blog_comment_by_id(request)

@api_view(['POST'])
def blog_comments_api(request):
    return blogsCommentLogic.getBlogComments(request)

@api_view(['POST'])
def delete_blog_comment_reply(request):
    return blogsCommentLogic.delete_blog_comment_reply_by_id(request)

@api_view(['POST'])
def delete_discussion_api(request):
    return discussionLogic.deleteDiscusisonWithId(request)

@api_view(['POST'])
def delete_blog_api(request):
    return blogsLogic.deleteBlogWithId(request)

@api_view(['POST'])
def edit_discussion_api(request):
    return discussionLogic.editDiscussionWithId(request)

@api_view(['POST'])
def edit_blog_discussion_api(request):
    return blogsLogic.editBlogWithId(request)

@login_required(login_url = 'login')
def problems(request):
    problems = Problem.objects.all()
    return render(request, 'home/problems.html', {'problems':problems})

@api_view(['POST'])
def addSubmission(request, problem_id, username):
    problem = Problem.objects.get(problem_id = problem_id)
    user_id = User.objects.get(username = username)
    if int(request.data['status']) == 1:
        profile = User.objects.get(username = username).profile
        
    solved = Solved.objects.create(problem_id = problem, user_id = user_id, solution = request.data['solution'], status = request.data['status'], result = request.data['result'])    
    problem.accuracy = problemLogic.get_accuracy(problem_id)
    problem.save()

@api_view(['POST'])
def submitProblem(request, problem_id):
    language = request.data.get('languages')
    code = request.data.get('code')
    stdin = request.data.get('stdin')
    URL = "https://api.jdoodle.com/v1/execute"
    CONTENT_TYPE = "application/json"
    HEADERS = {'Content-Type':CONTENT_TYPE}
    data = {
        "clientId": "1e2bb0b54435a27dbbd3407ffd3de205",
        "clientSecret": "ea7f4d76cb233af9a36c2dfa9ad3177f646659f66f2e5e30ed8dd4a42938faf0",
        "script": code,
        "language": language,
        "stdin" : stdin,
        "Content-Type" : "application.json"
    }
    response = requests.post(URL, headers = HEADERS, json = data)
    problem = Problem.objects.get(problem_id = problem_id)
    json_test_cases = problem.json_test_cases
    print(response.json())
    output = response.json()['output'].split('\n')
    output.pop(-1)
    c = 0
    for i in json_test_cases:
        if json_test_cases[i]['output'] != output[c]:
            inp = json.dumps(json_test_cases[i]['input'])
            if output[c] == '':
                print("//////")
                print(inp)
                return Response({'output':'wrong answer', 'input': inp, 'expected': json_test_cases[i]['output'], 'result': response.json()['output']})
            return Response({'output':'wrong answer', 'input': inp, 'expected': json_test_cases[i]['output'], 'result': output[c]})
        c += 1
    print("////////////")
    print(json_test_cases)
    print("////////////")
    print(output)
    return Response(response.json())

@login_required(login_url = 'login')
def problem(request, problem):
    problem1 = Problem.objects.get(problem_name = problem)
    if (request.method == "POST"):
        language = request.POST.get('language')
        theme = request.POST.get('theme')
        code = request.POST.get('codeArea')
        # URL = "https://api.hackerearth.com/v4/partner/code-evaluation/submissions/"
        URL = "https://api.jdoodle.com/v1/execute"
        # KEY = "a4cc15e0c12d53f4c28968684baf69ddaf55c55f"
        CONTENT_TYPE = "application/json"
        HEADERS = {'Content-Type':CONTENT_TYPE}
        # data = {
        #     "lang": "PYTHON",
        #     "source": "print 'Hello World'\nprint 'newline'",
        #     "input": ""
        # }
        data = {
            "clientId":"1e2bb0b54435a27dbbd3407ffd3de205",
            "clientSecret":"ea7f4d76cb233af9a36c2dfa9ad3177f646659f66f2e5e30ed8dd4a42938faf0",
            "script":"for i in range(10):\n\tprint(i)",
            "language":"python3"
        }
        print(language, theme, code)
        response = requests.post(URL, headers = HEADERS, json = data)
        # STATUS_URL = response.json()['status_update_url']
        print(response.json())
        # RESULT_URL = requests.get(STATUS_URL, headers = HEADERS)
        # result_url = RESULT_URL.json()['result']['run_status']['output']
        # print(result_url)
        # output = requests.get(result_url).content
        # print(output)
        return redirect('submission', problem)
    print(problem1)
    votes = ProblemVotes.objects.filter(problem_id = problem1.problem_id)
    likes = len(ProblemVotes.objects.filter(problem_id = problem1.problem_id, vote = 'L'))
    dislikes = len(ProblemVotes.objects.filter(problem_id = problem1.problem_id, vote = 'D'))
    return render(request, 'home/problem.html', {'problem':problem1, "votes":votes, 'likes':likes, 'dislikes':dislikes})

def problemDiscuss(request, problem):
    problem1 = Problem.objects.get(problem_name = problem)
    discussions = Discussion.objects.filter(user_id = request.user, problem_id = problem1)
    print(discussions)
    return render(request, 'home/problemDiscuss.html', {'problem':problem1, 'discussions':discussions})

def discussionThread(request, problem, id):
    print(problem, id)
    problem1 = Problem.objects.get(problem_name = problem)
    thread1 = Discussion.objects.get(discussion_id = id)

    return render(request, 'home/discussionThread.html', {'problem':problem1, 'id':thread1})

def postDiscussion(request, problem):
    if (request.method == "POST"):
        print(type(problem))
        title = request.POST.get('discussionTitle')
        discussion = request.POST.get('discussionDescription')
        print(Problem.objects.all().first().problem_name)
        problem1 = Problem.objects.get(problem_name = problem)
        Discussion.objects.create(user_id = request.user, problem_id = problem1, title = title, discussion = discussion)
        return redirect('problemDiscuss', problem)

    problem1 = problem
    return render(request, 'home/postDiscussion.html', {'problem':problem1})

@login_required(login_url = 'login')
def submission(request, submission):
    # if (request.method == "POST"):
    #     language = request.POST.get('language')
    #     theme = request.POST.get('theme')
    #     code = request.POST.get('codeArea')
    #     print(language, theme, code)
    #     submission1 = Problem.objects.get(problem_name = submission)
    #     redirect('submission')
    submission1 = Problem.objects.get(problem_name = submission)
    return render(request, 'home/submission.html', {'submission':submission1})

@login_required(login_url = 'login')
def discuss(request):
    return render(request, 'home/base.html')

@login_required(login_url = 'login')
def notifications(request):
    return render(request, 'home/base.html')

@login_required(login_url = 'login')
def streak(request):
    return render(request, 'home/base.html')

def account(request):
    return render(request, 'home/account.html')

def editProfile(request):

    if (request.method == "POST"):
        user = User.objects.get(username=request.user)
        if (request.POST.get('full_name') != ''):
            user.profile.full_name = request.POST.get('full_name')
        if (request.POST.get('location') != ''):
            user.profile.location = request.POST.get('location')
        if (request.POST.get('gender') != ''):
            user.profile.gender = request.POST.get('gender')
        if (request.POST.get('work') != ''):
            user.profile.work = request.POST.get('work')
        if (request.POST.get('education') != ''):
            user.profile.education = request.POST.get('education')
        if (request.POST.get('bio') != ''):
            user.profile.bio = request.POST.get('bio')
        user.profile.save()

    return render(request, 'home/editProfile.html')

def postQuestion(request):

    if (request.method == "POST"):
        print(request.POST)
        user = User.objects.get(username=request.user)
        newProblem = Problem.objects.create(creator_id = request.user, problem_name = request.POST.get('problem_name'), description = request.POST.get('description'), hints = request.POST.get('hints'), test_cases = request.POST.get('test_cases'))
        tags = dict(request.POST)['tags']
        print(tags, type(tags))
        tags = set(tags)
        for tag in tags:
            print(tag)
            t = Tag.objects.get(tag_name = tag)
            TopicTag.objects.create(problem_id = newProblem, tag_id = t)

    return render(request, 'home/postQuestion.html')

def signin(request):

    if (request.method == "POST"):
        form = UserRegistrationForm(request.POST)
        print(form)
        if (form.is_valid()):
            form.save()
            print(form['username'])
            # createdUserProfile = Profile.objects.get(username = form['username'])
            # createdUserProfile.password = form['password1']
            # createdUserProfile.save()
            message = messages.success(request, f'account created, you can now login')
            return redirect('login')
    else:
        form = UserRegistrationForm()

    return render(request, 'home/signin.html', {'form':form})

# def login(request):
#     return render(request, 'home/login.html')