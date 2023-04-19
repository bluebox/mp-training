from django.urls import path
from . import views

urlpatterns = [
    path('add/', views.addOrderItems, name='add_order_items'),
    path('my-orders/', views.getMyOrders, name='my_orders'),
    path('<int:pk>/', views.getOrderById, name='order_by_id'),
    path('', views.getOrders, name='orders'),
    path('<int:pk>/paid/', views.updateOrderToPaid, name='order_paid'),
    path('<int:pk>/delivered/', views.updateOrderToDelivered, name='order_delivered'),
    path('address/add/', views.saveShippingAddress, name='saveShippingAddress'),
    path('address/edit/', views.editShippingAddress, name='editShippingAddress'),
]