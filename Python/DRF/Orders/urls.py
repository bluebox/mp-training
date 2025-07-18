from django.urls import  path,include
from rest_framework.routers import DefaultRouter

from .views import ProductViewSet, CustomerViewSet, OrderViewSet, OrderItemViewSet, QueryParmsExample, \
    Join_example_to_get_customer_and_hisOrder, request_user, LoginView, UserViewSet, PaginationViewSets

from rest_framework_simplejwt.views import (
    TokenObtainPairView,
    TokenRefreshView,
)
router = DefaultRouter()
router.register(r'products', ProductViewSet)
router.register(r'customers',CustomerViewSet)
router.register(r'orders',OrderViewSet)
router.register(r'orderItem',OrderItemViewSet)
router.register(r'users',UserViewSet)
router.register(r'pagination',PaginationViewSets,basename='pagination')

urlpatterns=[
    path('api/',include(router.urls)),
    path('api/QueryParmsExample/',QueryParmsExample.as_view(),name='QueryParmsExample'),
    path('api/join_example/<int:pk>/',Join_example_to_get_customer_and_hisOrder.as_view(),name='Join_example_to_get_customer_and_hisOrder'),
    path('api/login/',LoginView.as_view(),name='LoginView'),
    path('api/token/', TokenObtainPairView.as_view(), name='token_obtain_pair'),
    path('api/token/refresh/', TokenRefreshView.as_view(), name='token_refresh'),
    path('api/request/',request_user.as_view(),name='request_user')
]