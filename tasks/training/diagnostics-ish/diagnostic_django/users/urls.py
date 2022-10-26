from django.urls import path
from .views import *


urlpatterns = [
    path('register-customer/' ,RegisterCustomer.as_view(),name='register-customer' ),
    path('filter_customer/', FilterCustomer.as_view(), name='filter-customer'),
    path('customer/<str:customer_id>/', DetailCustomer.as_view(), name='customer'),
    path('register-employee/' ,RegisterEmployee.as_view(),name='register-employee' ),
    path('branch/',BranchHandler.as_view(),name='branch'),
    path('logout/',LogoutView.as_view(), name='logout'),
    path('login/', LoginView.as_view(), name='login'),
    path('user/', userView, name='user'),
    path('refresh-token/', RefreshToken.as_view(), name='refresh-token'),
]

# chnges done