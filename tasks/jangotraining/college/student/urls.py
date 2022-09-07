from django.contrib import admin
from django.urls import path
from . import views
urlpatterns = [
    path('', views.index),
    path('student/', views.studentlogin, name='student'),
    path('staff/', views.stafflogin, name='staff'),
    path('hod/', views.register, name='register'),
    path('login/',views.login,name='login'),
    path('staff/student1/',views.student_details,name='student1'),


]