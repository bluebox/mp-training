from django.urls import path, register_converter,re_path
from . import views
urlpatterns = [
    path('views', views.func_view, name='views'),
    path("views1", views.func_view1, name="views1"),
    path("views2", views.func_view_deco, name="views2"),
    path("cviews1", views.ClassBasedView.as_view(), name="class_view"),


    # path('owner/', views.as_view()),
]