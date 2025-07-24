from django.urls import path, include

from .views import StudentViewSet, DepartmentViewSet, StudentView, DepartmentView, StudentProfileView, \
    StudentFullProfileView, CourseView
from rest_framework.routers import DefaultRouter

router = DefaultRouter()
router.register(r'department', DepartmentViewSet)
router.register(r'student', StudentViewSet)

urlpatterns = [
    path('', include(router.urls)),

    path('department/',DepartmentView.as_view()),
    path('student_api/', StudentView.as_view()),
    path('student_api/<int:id>/', StudentView.as_view()),
    path('student_profile/',StudentProfileView.as_view()),
    path('student_profile/<int:id>/',StudentProfileView.as_view()),
    path('student_full_profile/',StudentFullProfileView.as_view()),
    path('student_full_profile/<int:id>/',StudentFullProfileView.as_view()),
    path('course/',CourseView.as_view()),
    path('course/<int:id>/',CourseView.as_view())
]
