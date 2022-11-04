from django.urls import path
from . import views

urlpatterns = [
    path('filter/', views.jangofilters, name='jangofilters'),
    path('tags/<para>', views.jangotags, name='jangotags'),
    path('loops/', views.jangoloops, name='jangoloops'),
]