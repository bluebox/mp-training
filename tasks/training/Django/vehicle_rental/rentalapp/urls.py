from django.contrib import admin
from django.urls import path
from . import views
from django.conf.urls.static import static
from django.conf import  settings

urlpatterns = [
    path('', views.home, name='home'),
    path('customer_login', views.customer_login, name='customer_login'),
    path('owner_login', views.owner_login, name='owner_login'),
    path('owner_register', views.owner_register, name='owner_register'),
    path('customer_register', views.customer_register, name='customer_register'),
    path('c_login', views.c_login, name='c_login'),
    path('o_login', views.o_login, name="o_login"),
    path('add_vehicle/<int:para>', views.add_vehicle, name='add_vehicle'),
    path('show', views.show, name='show')
] 