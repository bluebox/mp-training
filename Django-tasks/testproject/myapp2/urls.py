from django.contrib import admin
from django.urls import path
from . import views

urlpatterns = [

    path('variables/', views.datavariable, name="variables"),
    path('menu/', views.menu, name="menu"),

]
