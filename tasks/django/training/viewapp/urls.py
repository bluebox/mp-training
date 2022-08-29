from django.urls import path,re_path, register_converter
from . import views


urlpatterns=[
    path("index", views.index, name="index"),
    path("methodshow", views.MethodShow, name="methodshow"),
    path("classmethod", views.BuiltInView.as_view(), name="builtinmethods")

]