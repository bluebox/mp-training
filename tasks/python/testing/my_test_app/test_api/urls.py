from django.contrib.sitemaps.views import index
from django.urls import path
from .views import hello_api,view_user,user_management


urlpatterns = [
    path("hello/",hello_api),
    path('',view_user,name='index'),
    path('Users/',user_management),
]