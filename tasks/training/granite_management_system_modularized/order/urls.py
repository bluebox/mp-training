from django.urls import path

from .views import OrderedItemsAPI, DeliveryAPI

urlpatterns = [
    path('orders', OrderedItemsAPI.as_view(), name="orders"),
    path('orders/<pk>', OrderedItemsAPI.as_view(), name="orders"),
    path('delivery/<order_id>', DeliveryAPI.as_view(), name='delivery')
]
