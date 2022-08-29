from django.contrib import admin
from django.urls import path, include
from . import views


urlpatterns=[

    path('function', views.function_based, name='f_based'),
    # path('examples', views.examplesview, name='example'),
    # path('hello', views.helloview, name='hello'),
    # path('namedurl',views.namedurlview, name='namedurl'),
]