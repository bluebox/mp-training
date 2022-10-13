"""Urls"""
from django.urls import path
from . import views


urlpatterns = [

    path('customer/', views.customerApi),
    path('customer/<str:id>', views.customerApi),
    path('employee/', views.employeeApi),
    path('employee/<str:id>', views.employeeApi),
    path('distributor/', views.distributorApi),
    path('distributor/<str:id>', views.distributorApi),
    path('doctor/', views.doctorApi),
    path('doctor/<str:id>', views.doctorApi),
    path('manufacturer/', views.manufacturerApi),
    path('manufacturer/<str:id>', views.manufacturerApi),
    path('drug/', views.drugApi),
    path('drug/<str:id>', views.drugApi),
    path('SaveFile', views.SaveFile),

]
 