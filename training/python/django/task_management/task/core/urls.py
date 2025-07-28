from django.urls import path
from rest_framework_simplejwt.views import TokenObtainPairView, TokenRefreshView
from .views import (
    RegisterAPIView, UserDetailView,
    LeadTaskView, AdminAllTasksView,
    TaskCreateView, TaskDetailView,
    TaskCommentCreateView, AssignTaskView,
    TeamView, ProjectView
)

urlpatterns = [

    path('register/', RegisterAPIView.as_view(), name='register'),
    path('login/', TokenObtainPairView.as_view(), name='token_obtain_pair'),
    path('token/refresh/', TokenRefreshView.as_view(), name='token_refresh'),
    path('profile/', UserDetailView.as_view(), name='user-profile'),
    path('profile/<int:pk>/', UserDetailView.as_view(), name='user-profile'),


    path('tasks/create/', TaskCreateView.as_view(), name='task-create'),
    path('tasks/<int:pk>/', TaskDetailView.as_view(), name='task-detail'),
    path('tasks/<int:task_id>/comment/', TaskCommentCreateView.as_view(), name='task-comment'),
    path('tasks/assign/', AssignTaskView.as_view(), name='task-assign'),


    path('lead/tasks/', LeadTaskView.as_view(), name='lead-tasks'),
    path('admin/tasks/', AdminAllTasksView.as_view(), name='admin-tasks'),


    path('teams/', TeamView.as_view(), name='teams'),
    path('projects/', ProjectView.as_view(), name='projects'),
]
