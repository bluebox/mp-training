from django.urls import path
from . import views
urlpatterns = [

    path('class_views/', views.class_views.asviews(), name='class_views'),
    ]