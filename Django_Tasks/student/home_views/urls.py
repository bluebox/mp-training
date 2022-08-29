
from django.urls import path

from . import views



urlpatterns = [
    path('', views.ViewEx.as_view()),

]