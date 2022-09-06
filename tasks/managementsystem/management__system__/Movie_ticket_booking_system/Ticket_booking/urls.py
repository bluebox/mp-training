from django.urls import path
from . import views
urlpatterns = [
    path('homepage/',views.template1,name='template1')
]