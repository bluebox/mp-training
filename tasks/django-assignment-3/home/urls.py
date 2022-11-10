from django.urls import path, include
from django.shortcuts import render
from .views import *
from django.contrib.auth import views as user_views

urlpatterns = [
    path('', homePage, name = 'home'),
    path('blog/', blogPage, name = 'blog'),
    path('about/', aboutPage, name = 'about'),
    path('signup/', signupPage, name = 'signup'),
    path('login/', user_views.LoginView.as_view(template_name='home/login.html'), name = 'login'),
    path('logout/', user_views.LogoutView.as_view(template_name='home/logout.html'), name = 'logout')
]