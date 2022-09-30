from django.urls import path

from . import views

urlpatterns = [

    path('', views.emplogin, name='emplogin'),
    path('candiatelogin', views.candiatelogin.as_view(), name='candiatelogin'),
 ]