from django.contrib import admin
from django.urls import path,include
from . import views
from app3.views import Classview

urlpatterns = [

    # path('', views.demo),
    path('fview/', views.function_based_view, name='view1'),
    path('cview/',  Classview.as_view(), name='cview'),
    path('show/', views.show, name='show-details')


]