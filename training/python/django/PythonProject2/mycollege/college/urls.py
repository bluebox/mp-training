from django.urls import path, include
from django.urls import path
from .views import (
    StudentAPIView, StudentProfileAPIView, DepartmentAPIView,
    CourseAPIView, TeacherAPIView, EnrollmentAPIView, TeacherCourseAPIView, StudentFullProfileView
)

urlpatterns = [
    path('students/', StudentAPIView.as_view()),
    path('students/<int:id>/', StudentAPIView.as_view()),

    path('student-profiles/', StudentProfileAPIView.as_view()),
    path('student-profiles/<int:id>/', StudentProfileAPIView.as_view()),

    path('departments/', DepartmentAPIView.as_view()),
    path('departments/<int:id>/', DepartmentAPIView.as_view()),

    path('courses/', CourseAPIView.as_view()),
    path('courses/<int:id>/', CourseAPIView.as_view()),

    path('teachers/', TeacherAPIView.as_view()),
    path('teachers/<int:id>/', TeacherAPIView.as_view()),

    path('enrollments/', EnrollmentAPIView.as_view()),
    path('enrollments/<int:id>/', EnrollmentAPIView.as_view()),

    path('teacher-courses/', TeacherCourseAPIView.as_view()),
    path('teacher-courses/<int:id>/', TeacherCourseAPIView.as_view()),

    path('student/',StudentFullProfileView.as_view()),
    path('student/<int:id>/',StudentFullProfileView.as_view()),
]

# from .views import StudentViewSet, DepartmentViewSet, StudentView, DepartmentView, StudentProfileView, \
#     StudentFullProfileView, CourseView
# from rest_framework.routers import DefaultRouter
#
# router = DefaultRouter()
# router.register(r'department', DepartmentViewSet)
# router.register(r'student', StudentViewSet)
#
# urlpatterns = [
#     path('', include(router.urls)),
#
#     path('department/',DepartmentView.as_view()),
#     path('student_api/', StudentView.as_view()),
#     path('student_api/<int:id>/', StudentView.as_view()),
#     path('student_profile/',StudentProfileView.as_view()),
#     path('student_profile/<int:id>/',StudentProfileView.as_view()),
#     path('student_full_profile/',StudentFullProfileView.as_view()),
#     path('student_full_profile/<int:id>/',StudentFullProfileView.as_view()),
#     path('course/',CourseView.as_view()),
#     path('course/<int:id>/',CourseView.as_view())
# ]
