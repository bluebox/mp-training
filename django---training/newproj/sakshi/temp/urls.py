from . import views
from django.urls import path


urlpatterns = [

    path('', views.temp1, name='temp1'),
    path('testing/', views.testing, name='testing'),

]