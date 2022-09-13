from django.urls import path

from . import views

urlpatterns = [
    path('', views.index, name='index'),
    path('homehr/', views.homehr, name='homehr'),
    path('emplogin/', views.emplogin, name='emplogin'),
    path('base/', views.base, name='base'),
    path('about/', views.about, name='about'),
    path('whatwedo/', views.whatwedo, name='whatwedo'),
    path('career/', views.career, name='career'),
    path('contactus/', views.contactus, name='contactus'),
]