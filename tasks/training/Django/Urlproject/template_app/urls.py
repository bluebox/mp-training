from django.urls import path
from . import views

urlpatterns = [
    path('template1', views.template1, name='temp1'),
    path('template2', views.template2, name='temp2'),
    path('context' , views.context, name='context'),
    path('template3', views.directing, name='temp3'),
    path('filter', views.filter, name='filter'),
    path('conditions', views.conditions, name='conditions')

]