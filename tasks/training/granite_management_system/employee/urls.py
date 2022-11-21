from django.urls import path

from .views import EmployeeAPI

urlpatterns = [
    path('employee', EmployeeAPI.as_view(), name="employees"),
    path('employee/<pk>', EmployeeAPI.as_view(), name="employee"),

]