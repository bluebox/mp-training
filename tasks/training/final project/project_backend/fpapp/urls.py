from django.contrib import admin
from django.urls import path
from  . import views

urlpatterns = [
    path('', views.index ),
    path('userstudent', views.RegisterStudent.as_view()),
    path('userteacher', views.RegisterTeacher.as_view()),
    path("userlogin", views.loginUser),
    path('teacherlist', views.teacher_list),
    path('courselist', views.course_list),
    # path('subjectcreate', views.SubjectCreate.as_view())
    path('courseregister', views.RegisterCourse.as_view()),
    path('questionregister', views.RegisterQuestion.as_view()),
    path('courselist/delete',views.delete_course),
    path('question/delete',views.delete_question),
    path('addcourse', views.AddCourse.as_view()),
    path('displayquestion', views.DisplayQuestion.as_view()),
    path('attemptexam',views.AttemptExam.as_view())

]
