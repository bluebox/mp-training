from django.urls import path

from .views import ItemAPI, GraniteStoreAPI, CartAPI, rawItemAPI

urlpatterns = [
    path('item', ItemAPI.as_view(), name='items'),
    path('item/<pk>', ItemAPI.as_view(), name='item'),
    path('store', GraniteStoreAPI.as_view(), name='stores'),
    path('store/<pk>', GraniteStoreAPI.as_view(), name='store'),
    path('viewCart/<customer_id>', CartAPI.as_view(), name='cart'),
    path('addToCart', CartAPI.as_view(), name='cart'),
    path('rawItems', rawItemAPI.as_view(), name='raw items')
]