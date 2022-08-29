from django.urls import path,re_path, register_converter
from . import views, templates


urlpatterns=[
    path("index", views.index, name="index"),
    path("home", views.home, name="home"),
    path("something", views.loop, name="loop"),
    path("count", views.count, name="count"),
    path("filters", views.filters, name="filters"),
    path("ifstatement/<int:num>", views.condition, name="ifstatement"),

]