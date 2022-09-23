from django.contrib import admin
from django.urls import path
from home.views import Index, CustomerData

urlpatterns = [
    path('', Index.as_view()),
    path('customer/', CustomerData.as_view()),

]