from django.contrib import admin
from django.urls import path
from home.views import Index, CustomerData, FoodData, FoodOneData, RestaurantData, EmployeeData, \
    SearchData, AddMenu, AddFoodtoMenu, OneResFoods, CustomerLogin, Cart, CartEdit, UserProfileView, \
    RestaurantRegistrationView, EmployeeRegistrationView, CustomerRegistrationView, UserLoginView, UserView, Logout, \
    FoodOneRes, OneCustomer
from rest_framework_simplejwt.views import (
    TokenObtainPairView,
    TokenRefreshView,
    TokenVerifyView
)

urlpatterns = [
    path('', Index.as_view()),
    path('customer/', CustomerData.as_view()),
    path('food/', FoodData.as_view()),
    path('food/<str:id>/', FoodOneData.as_view()),
    path('restaurant/', RestaurantData.as_view()),
    path('employee/', EmployeeData.as_view()),
    path('search/<str:item>', SearchData.as_view()),
    path('menu/add', AddMenu.as_view()),
    path('menu/addfood', AddFoodtoMenu.as_view()),
    path('restaurant/<str:id>/', FoodOneRes.as_view()),
    path('customer/<str:id>/', OneCustomer.as_view()),
    path('restaurant/foodlist/<str:item>/', OneResFoods.as_view()),
    path('customer/login/', CustomerLogin.as_view()),
    path('customer/cart/', Cart.as_view()),
    path('customer/cart/<str:id>', CartEdit.as_view()),
    path('api/token/', TokenObtainPairView.as_view(), name='token_obtain_pair'),
    path('api/token/refresh/', TokenRefreshView.as_view(), name='token_refresh'),
    path('api/token/verify/', TokenVerifyView.as_view(), name='token_verify'),
    # path('customer/login/', CustomerLogin.as_view()),
    path('profile/', UserProfileView.as_view()),
    path('restaurant/signinn/', RestaurantRegistrationView.as_view(), name='signup_res'),
    path('customer/signin/', CustomerRegistrationView.as_view(), name='signup_cus'),
    path('emp/signin/', EmployeeRegistrationView.as_view(), name='signup_doctor'),
    path('login/', UserLoginView.as_view()),

    path('user/', UserView.as_view()),

    path('logout/',Logout.as_view())
    # path('user/signin/',UserSignin.as_view())
]
