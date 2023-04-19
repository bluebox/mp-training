from django.urls import path, include
from . import views
urlpatterns = [
    path('', views.getCartItems, name='cart'),
    path('add/', views.addToCart, name='addToCart'),
    path('update/<int:product>/', views.updateCart, name='updateCart'),
    path('delete/<int:product>/', views.removeItem, name='removeItem')
]