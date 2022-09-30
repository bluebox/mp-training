from django.contrib import admin
from django.urls import path
from home.views import Index, CustomerData, FoodData, FoodOneData, RestaurantData, EmployeeData, SearchData, AddMenu,AddFoodtoMenu, FoodOneRes, OneResFoods

urlpatterns = [
    path('', Index.as_view()),
    path('customer/', CustomerData.as_view()),
    path('food/', FoodData.as_view()),
    path('food/<str:id>/', FoodOneData.as_view()),
    path('restaurant/', RestaurantData.as_view()),
    path('employee/', EmployeeData.as_view()),
    path('search/<str:item>', SearchData.as_view()),
    path('menu/add',AddMenu.as_view()),
    path('menu/addfood',AddFoodtoMenu.as_view()),
    path('restaurant/<str:id>/', FoodOneRes.as_view()),
    path('restaurant/foodlist/<str:item>/', OneResFoods.as_view()),


    # path('customer/login/', CustomerLogin.as_view()),


]