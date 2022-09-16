from django.contrib import admin
from django.urls import path, include
from . import views
urlpatterns = [
    path('', views.loginpage),
    path('login', views.login,name='login'),

]