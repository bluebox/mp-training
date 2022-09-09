from django.urls import include, path
from api import views

urlpatterns = [
    path('', views.movies_data),
]
