from django.urls import path

from . import views

urlpatterns = [
    path('', views.logindata.as_view(),name='logindata'),
]