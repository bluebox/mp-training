from django.contrib import admin
from django.urls import path, include, register_converter, re_path

from . views import *

urlpatterns = [
    path('log', log, name='log'),
    path('login/', login, name="login")
]