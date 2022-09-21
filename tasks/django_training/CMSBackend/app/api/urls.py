from django.contrib import admin
from django.urls import path, include
from app.api.views import *
from rest_framework.urlpatterns import format_suffix_patterns


urlpatterns = [
    path('owners', FlatOwnerAV.as_view(),name='owners'),
    path('owners/<int:pk>/', FlatOwnerDetailsAV.as_view(),name='ownerDetails'),
]
urlpatterns = format_suffix_patterns(urlpatterns)