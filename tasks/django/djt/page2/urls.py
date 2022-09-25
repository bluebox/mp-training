from django.urls import path
from . import views

urlpatterns = [

    path("page2/", views.temp,name='page2'),
]
