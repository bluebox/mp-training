from django.contrib import admin
from django.urls import path, include
from . import views


urlpatterns=[

    path('default', views.defaultview, name='default'),
    path('examples', views.examplesview, name='example'),
    path('hello', views.helloview, name='hello'),
    path('namedurl',views.namedurlview, name='namedurl'),
]