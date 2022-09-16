from django.urls import path
from . import views


urlpatterns = [
    path('add_customer_details', views.add_customer_details),
    path('display_customer', views.display_customer),
    path('add_employee_details', views.add_employee_details),
    path('display_employee', views.display_employee),
    path('delete_customer/<str:id>', views.delete_customer),

]
