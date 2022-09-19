from django.contrib import admin
from django.urls import path
from .views import *

urlpatterns = [
    path('',IndexSerializer,name="index"),
    path("post",IndexPostSerializer,name="indexpost"),
    path("update/<int:id>/",updateSerializer,name="update"),
    path("delete/<int:id>/",deleteSerializer,name="delete")
    # path("freelance_details",views.freelance_details_page,name="freelance_details"),
    # path('login',views.loginpage,name="login_page")
    # path('first/',freelanceViewSet.as_view(),name="freelance")
]
