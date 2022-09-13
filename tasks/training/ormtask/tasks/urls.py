from django.urls import path
from . import views


urlpatterns = [
    path('', views.login, name='vallog'),
    path('validate', views.validate, name='validate'),
]
