from django.contrib import admin
from django.urls import path,include
from . import views
from app3.views import Cview

urlpatterns = [

    # path('', views.demo),
    path('fview', views.fview, name='view1'),
    path('cview', Cview.as_view(), name='cview'),


]