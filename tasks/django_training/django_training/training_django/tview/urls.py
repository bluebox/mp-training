from django.urls import path, register_converter, re_path
from . import views

urlpatterns = [
    path('', views.tcalls, name="tcalls"),

]