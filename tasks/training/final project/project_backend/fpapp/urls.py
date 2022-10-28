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
    path('courseregister', views.RegisterCourse.as_view()),
    path('questionregister', views.RegisterQuestion.as_view()),
    path('courselist/delete',views.delete_course),
    path('question/delete',views.delete_question),
    path('addcourse', views.AddCourse.as_view()),
    path('displayquestion', views.DisplayQuestion.as_view()),
    path('attemptexam',views.AttemptExam.as_view()),
    path('startexam', views.StartExam.as_view()),
    path('checkmarks', views.CheckMarks.as_view()),
    path('detailquestion/<int:id>/', views.DetailQuestion.as_view()),
    path('detailcourse/<int:id>/', views.DetailCourse.as_view()),
    path('coursefilter', views.courseFilter),
    path('questionfilter', views.courseFilter),
    path('score' ,views.Scorecard.as_view()),
    path('adminstudentdisplay', views.AdminPanelStudent.as_view()),
    path('adminteacherdisplay', views.AdminPanelTeacher.as_view()),
    path('attemptexam1', views.AttemptExam1.as_view())

]
