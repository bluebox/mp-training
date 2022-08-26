from django.urls import path
from .views import nest

urlpatterns = [
    path("<month>", nest),
]