
from django.urls import path,re_path
from . import views

urlpatterns = [
    path('', views.function,name="function"),
    path('decorator/', views.function1,name="function1"),
    path('class_view/', views.SecFun.as_view()),



]