from django.urls import path
from day2 import views

urlpatterns = [
    # default url
    path('', views.withoutTemplate),
    path("1", views.withTemplate),
    path('2', views.withTemplate2)
]