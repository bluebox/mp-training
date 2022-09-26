from django.urls import path,include
from .views import *
from rest_framework.routers import *
router = DefaultRouter()
router.register('viewset', Customer1, basename='Cust_name')
router1 = DefaultRouter()
router1.register('viewset1', employeeViewModelset1, basename='Custname')
urlpatterns=[
    # # path("home/", home, name='home'),
    # path("login/", login, name='login'),
    # path("login1/", login1, name='login1'),
    # path("", signup, name="signup")
    path("home/<int:pk>/", customer_list, name="customer_list"),

    path("home/", customer_list, name="customer_list"),
    path('modelview/', include(router.urls)),
    path('modelview1/', include(router1.urls)),

    path('auth/', include('rest_framework.urls', namespace='session_auth')),
]