from django.contrib import admin
from django.urls import path
from home.views import Index, CustomerData, FoodData, FoodOneData, RestaurantData, EmployeeData

urlpatterns = [
    path('', Index.as_view()),
    path('customer/', CustomerData.as_view()),
    path('food/', FoodData.as_view()),
    path('food/<str:id>/', FoodOneData.as_view()),
    path('restaurant/', RestaurantData.as_view()),
    path('employee/', EmployeeData.as_view()),

]