from django.urls import path, include, register_converter, re_path
from django.http import HttpResponse
from . import views


urlpatterns = [
    path('', views.NewView.as_view())
    ]