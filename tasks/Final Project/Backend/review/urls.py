from django.urls import path
from .views import createMovieReview

urlpatterns = [
    path('<str:pk>/', createMovieReview, name="create-review" )
]