from django.urls import path,include
from .views import *
from rest_framework.routers import *
router = DefaultRouter()
router.register('viewset', Customer1, basename='Cust_name')
# router.register('product', Product_list, basename='products_list')

# router1 = DefaultRouter()
# router1.register('viewset1', employeeViewModelset1, basename='Custname')
urlpatterns=[
    # # path("home/", home, name='home'),
    # path("login/", login, name='login'),
    # path("login1/", login1, name='login1'),
    # path("", signup, name="signup")
    path("home/<int:pk>/", customer_list, name="customer_list"),
    path("home/", customer_list, name="customer_list"),
    path('modelview/', include(router.urls)),
    # path('modelview1/', include(router1.urls)),
    path('products/', Product_list.as_view(), name="products_list"),
    path('product_type/', Productype.as_view(), name="products_list"),
    path('user_login/', UserLogin.as_view(), name="user_login"),
    path('buyers/', Buyer_List.as_view(), name="products_list"),
    path('auth/', include('rest_framework.urls', namespace='session_auth')),
    path('api-auth/', include('rest_framework.urls'))
]