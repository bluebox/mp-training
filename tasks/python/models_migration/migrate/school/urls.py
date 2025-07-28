from django.urls import path, include
from rest_framework.routers import DefaultRouter
from .views import *

router = DefaultRouter()
router.register('teacher', TeacherViewSet, basename='teacher')
router.register('classes', ClassesViewSet, basename='classes')
router.register('subjects', SubjectViewSet, basename='subjects')
router.register('results', ResultsViewSet, basename='results')

urlpatterns = [
    path('Student/',StudentsView.as_view()),
    path('',include(router.urls)),
    path('dashboard/',StudentResultsDashBoard.as_view()),
    path('studentDetails/',StudentDetails.as_view()),
    path('subjectsDashboard/',StudentSubjectsDashboard.as_view()),
]