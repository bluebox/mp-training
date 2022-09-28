from distutils.command.install_egg_info import safe_name
from django.urls import path
from .views import *
from django.contrib.auth import views as user_views
from rest_framework.authtoken import views

urlpatterns = [
    path('', homeView, name='home'),
    path('api/', api, name='api'),
    path('api/votes/<str:problemId>/<str:username>', votesApi, name='votesApi'),
    path('api/submit-problem', submitProblem, name="submitProblem"),
    path('api/login', loginApi, name="loginApi"),
    path('api/register/', registerApi, name = 'register'),
    path('api/problems/sort', sortProblemsApi, name = 'sortProblemsApi'),
    path('api/profiles/', profilesApi, name = 'profilesApi'),
    path('api/profile/<str:username>/', profileApi, name = 'profileApi'),
    path('api/edit-profile/<str:username>/', editProfileApi, name = 'editProfileApi'),
    path('api/post-discussion/<str:problemId>/<str:username>/', postDiscussionApi, name = 'postDiscussionApi'),
    path('api/problems/category/<str:category>/', categoryApi, name='categoryApi'),
    path('api/discussions/<str:problemId>/', discussionsApi, name='discussionsApi'),
    path('api/submissions/<str:problemName>/', submissionsApi, name='submissionsApi'),
    path('api/post-question/<str:username>/', postQuestionApi, name = 'postQuestionApi'),
    path('api/problems', problemsList, name='api'),
    path('api-token-auth', views.obtain_auth_token),
    path('api/problem-detail/<str:id>/', problemDetail, name='problemDetail'),
    path('api/problem-vote/<str:id>/', problemVote, name='problemVote'),
    path('problems/', problems, name='problems'),
    path('problems/<str:problem>/', problem, name='problem'),
    path('problems/submissions/<str:submission>/', submission, name='submission'),
    path('problems/<str:problem>/discuss', problemDiscuss, name="problemDiscuss"),
    path('problems/<str:problem>/discuss/post', postDiscussion, name="postDiscussion"),
    path('problems/<str:problem>/discuss/<int:id>', discussionThread, name="discussionThread"),
    path('discuss/', discuss, name='discuss'),
    path('notifications/', notifications, name='notifications'),
    path('streak/', streak, name='streak'),
    path('account/', account, name='account'),
    path('account/edit-profile', editProfile, name='editProfile'),
    path('account/post-question', postQuestion, name='postQuestion'),
    path('signin/', signin, name='signin'),
    path('login/', user_views.LoginView.as_view(template_name='home/login.html'), name='login'),
    path('logout/', user_views.LogoutView.as_view(template_name='home/logout.html'), name = 'logout')
]