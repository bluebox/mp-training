from django.contrib import admin
from django.urls import path
from . import views

urlpatterns = [
    # path('',IndexSerializer,name="index"),
    # path("post",IndexPostSerializer,name="indexpost"),
    # path("update/<int:id>/",updateSerializer,name="update"),
    # path("delete/<int:id>/",deleteSerializer,name="delete")
    # path("freelance_details",views.freelance_details_page,name="freelance_details"),
    path("",views.freelancerRegister.as_view(),name="freelance_details"),
    path("freelancer/<str:email>",views.freelanceUpdate.as_view(),name="freelance_details"),

]