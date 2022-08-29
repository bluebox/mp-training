from django.urls import path,register_converter,re_path
from . import views
urlpatterns = [
    path('temp1/', views.temp1),
    path('filters/', views.filters),
    ]