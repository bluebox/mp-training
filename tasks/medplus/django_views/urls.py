
from django.urls import path
from . import views

urlpatterns = [
    path('', views.function,name="function"),
    path('decorator', views.function1,name="function1"),
    path('class_view', views.SecFun.as_view(),name="class_view"),

]