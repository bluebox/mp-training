from django.urls import path
from . import views
from rest_framework_simplejwt.views import (
    TokenObtainPairView, 
)

urlpatterns = [
    path('product/', views.getMovies, name="movies"),
    path('product/<int:pk>/', views.getMovie , name="movie"),

    path('product/create/', views.createMovie , name="movie-create"),
    path('product/upload/', views.uploadImage , name="image-upload"),
    path('product/delete/<str:pk>/', views.deleteMovie , name="movie-delete"),
    path('product/update/<int:pk>/', views.updateMovie , name="movie-update"),
]