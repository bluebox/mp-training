from django.contrib import admin
from django.urls import path
from . import views

urlpatterns = [

    path('test/', views.test_view, name="testview"),
    path('classtest/', views.TestView.as_view(), name="classtestview"),


]