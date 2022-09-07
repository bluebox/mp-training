from django.contrib import admin
from django.urls import path, include
from . import views


urlpatterns=[

    path('oneMethod', views.one_method, name='getMethod'),
    path('twoMethod', views.two_method, name='getpostMethod'),


]