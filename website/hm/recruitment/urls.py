from django.urls import path

from . import views

urlpatterns = [

    path('', views.vacancy, name='vacancy'),
    path('decider/', views.decider, name='decider'),
    path('signup/', views.signup.as_view(), name='signup'),
    path('apply/', views.apply.as_view(), name='apply'),
    path('create/', views.create.as_view(), name='apply'),
    path('delete/', views.delete.as_view(), name='apply'),
    path('applied/', views.applied.as_view(), name='apply'),
    path('application/', views.application.as_view(), name='apply'),
    path('applicant', views.applicant.as_view(), name='applicant'),
    path('action/', views.action.as_view(), name='action'),
    path('enter/', views.enter.as_view(), name='enter'),
 ]