from django.urls import path

from . import views

urlpatterns = [

    path('', views.vacancy, name='vacancy'),
    path('decider/', views.decider, name='decider'),
    path('signup/', views.signup.as_view(), name='signup'),
    path('apply/', views.apply.as_view(), name='apply'),
 ]