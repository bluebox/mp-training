from django.urls import path
from .views import TaskAPIView, TaskListCreateView, CustomPaginationTaskView

urlpatterns = [
    path('tasks_api/', TaskAPIView.as_view(), name='task-api'),
    path('tasks/', TaskListCreateView.as_view(), name='task-list-create'),
    path('tasks_custom_api/',CustomPaginationTaskView.as_view(), name='custom-task-api')
]
