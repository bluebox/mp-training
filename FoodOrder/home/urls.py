from django.contrib import admin
from django.urls import path
from home.views import Index



urlpatterns = [
    path('', Index.as_view()),

]