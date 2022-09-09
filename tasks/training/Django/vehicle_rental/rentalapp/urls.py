from django.contrib import admin
from django.urls import path
from . import views
urlpatterns = [
    path('', views.home, name='home'),
    path('customer_login', views.customer_login, name='customer_login'),
    path('owner_login', views.owner_login, name='owner_login')
]