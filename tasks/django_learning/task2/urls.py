from django.contrib import admin
from . import views
from django.urls import path, include, register_converter, re_path
from django.http import HttpResponse


def new(request, year, month):
    return HttpResponse(year + "" + month)


urlpatterns = [

    path("index/", views.index),

]
