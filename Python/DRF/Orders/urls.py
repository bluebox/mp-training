from django.urls import  path,include
from rest_framework.routers import DefaultRouter

from .views import ProductViewSet, CustomerViewSet, OrderViewSet, OrderItemViewSet, QueryParmsExample

router = DefaultRouter()
router.register(r'products', ProductViewSet)
router.register(r'customers',CustomerViewSet)
router.register(r'orders',OrderViewSet)
router.register(r'orderItem',OrderItemViewSet)

urlpatterns=[
    path('api/',include(router.urls)),
    path('api/QueryParmsExample/',QueryParmsExample.as_view(),name='QueryParmsExample')
]