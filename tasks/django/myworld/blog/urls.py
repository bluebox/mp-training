from django.urls import path
from .import views


urlpatterns = [

     path('home/', views.home, name="blog"),
     path('about/', views.about, name="blog")
 ]