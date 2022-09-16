from django.contrib import admin
from django.urls import path
from . import views
urlpatterns = [
    path('', views.index),
    path('login/student/', views.studentlogin, name='student'),
    path('login/staff/', views.stafflogin, name='staff'),
    path('login/hod/', views.register, name='register'),
    path('login/',views.login,name='login'),
    path('login/student1/', views.student_details ),
    path('login/studentreg/', views.studentreg ),
    path('login/staff/student1/',views.student_details,name='student1'),
    path('login/staff/studentreg/',views.studentreg,name='studentreg'),
    path('login/edit/',views.edit,name='edit'),



]