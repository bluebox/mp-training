from django.contrib import admin
from django.urls import path
from  . import views

urlpatterns = [
    path('', views.index ),
    path('userstudent', views.RegisterStudent.as_view()),
    path('userteacher', views.RegisterTeacher.as_view()),

]
