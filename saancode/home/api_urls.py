from distutils.command.install_egg_info import safe_name
from django.urls import include, path
from .views import *
from django.contrib.auth import views as user_views
from rest_framework.authtoken import views

# problem based apis

urlpatterns = [

path('problems', problemsList, name='api'),
path('problem-detail/<str:id>/', problemDetail, name='problemDetail'),
path('problems/sort', sortProblemsApi, name = 'sortProblemsApi'),
path('problems/category/<str:category>/', categoryApi, name='categoryApi'),
path('problem-vote/<str:id>/', problemVote, name='problemVote'),
path('problems-statistics', problem_stats_api),
path('post-question/<str:username>/', postQuestionApi, name = 'postQuestionApi'),
path('tags', tags),
path('votes/<str:problemId>/<str:username>', votesApi, name='votesApi'),
path('filter-problems', filterProblems),

# problem discussion apis

path('edit-discussion', edit_discussion_api),
path('edit-discussion-comment', editDiscusionComment),
path('delete-discussion', delete_discussion_api),
path('post-discussion/<str:problem_id>/<str:username>', postDiscussionApi),
path('discussion/<str:problemId>/<str:discussionId>', getDiscussionApi, name='getDiscussionApi'),
path('discussions/<str:problemId>/', discussionsApi, name='discussionsApi'),
path('comment/post/<str:discussionId>/<str:username>', postCommentApi, name="loginApi"),

# profile apis

path('profiles/', profilesApi, name = 'profilesApi'),
path('profile/<str:username>/', profileApi, name = 'profileApi'),
path('edit-profile/<str:username>/', editProfileApi, name = 'editProfileApi'),

# blog apis

path('add-blog', add_blog_api),
path('edit-blog-comment', edit_blog_comment_api.as_view()),
path('add-blog-reply', add_blog_reply),
path('delete-blog-comment-reply', delete_blog_comment_reply),
path('edit-blog-discussion', edit_blog_discussion_api),
path('blog-search', blog_search),
path('blog-category', blog_category_api),
path('get-blog-comments', blog_comments_api),
path('blogs', blogs_api),
path('blog', blog_api),
path('blog-comment', add_blog_comment_api),
path('delete-blog-discussion', delete_blog_api),

# problem submission apis

path('submissions', all_submissions),
path('submissions/<str:problem_id>/<str:username>', submissionsApi),
path('addSubmission/<str:problem_id>/<str:username>', addSubmission),
path('submit-problem/<str:problem_id>', submitProblem, name="submitProblem"),
path('submissions/<str:problemName>/', submissionsApi, name='submissionsApi'),
path('problems/submissions/<str:submission>/', submission, name='submission'),

# login apis

path('', api, name='api'),
path('create-data', add_data),
path('streak', problem_streak),
path('login', loginApi, name="loginApi"),
path('register/', registerApi, name = 'register'),

]