# from django.contrib import admin
from django.urls import path, include
from .import views
urlpatterns = [
    path('1', views.HtmlResponse, name='1'),
    path('2', views.HtmlResponseWithVariable, name='2'),
    path('3', views.HtmlResponseWithTags, name='3'),
    path('4', views.HtmlResponseWithFilters, name='4')
]