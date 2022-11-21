# url mapping to API Views
from django.urls import path

from .views import CustomerAPI

urlpatterns = [
    path('customer/<username>', CustomerAPI.as_view(), name="customer"),
    path('customer', CustomerAPI.as_view(), name="rcustomer"),
]
