from django.urls import path, include
from rest_framework.routers import DefaultRouter
from .views import *

router = DefaultRouter()
router.register('teacher', TeacherViewSet, basename='teacher')
router.register('classes', ClassesViewSet, basename='classes')
router.register('subjects', SubjectViewSet, basename='subjects')
router.register('results', ResultsViewSet, basename='results')
# router.register('subjectTeacherClass',SubjectTeacherRelationView,basename='subjectTeacherClass')

urlpatterns = [
    path('Student/',StudentsView.as_view()),
    path('dashboard/',StudentResultsDashBoard.as_view()),
    path('studentDetails/',StudentDetails.as_view()),
    path('subjectsDashboard/',StudentSubjectsDashboard.as_view()),
    path('teacherSubjects/',TeacherSubjectsStudents.as_view()),
    path('teacherResults/',TeacherResultsView.as_view()),
    path('allTeachers/',AllTeachers.as_view()),
    path('subjectTeachers/',SubjectTeacher.as_view()),
    path('classTeachers/',TeacherClass.as_view()),
    path('subject/classes/',SubjectTeacherRelationView.as_view()),
    path('Student/all/',ALLStudentsView.as_view()),
    path('allTeachers/all/',AllTeachersWp.as_view()),
    path('classes/all/',AllClassesView.as_view()),
    path('subjects/all/',AllSubjectsView.as_view()),
    path('',include(router.urls)),
]