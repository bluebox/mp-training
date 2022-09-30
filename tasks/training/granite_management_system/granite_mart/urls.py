
from django.urls import path
from .views import *


urlpatterns = [
    path('', home, name="home"),
    path('registerCustomer', registerCustomer, name="registercustomer"),
    path('custloginpage', custloginPage, name='custlogin'),
    path('custregistrationpage', custregistrationPage, name='custregistration'),
    path('logincustomer', customerLogin, name='logincustomer'),
    path('logout', logoutc, name='logout'),
    path('custdashboard', custdashboard, name='custdashboard'),
    path('buyproducts', buyproducts, name='buyproducts'),
    path('productspage/<str:storeid>', productsPage, name='productspage'),
    path('viewCustomers', viewCustomers, name='viewCustomers'),
    path('viewCustomer/<customer_id>', viewCustomer, name='viewCustomer'),
    path('deleteCustomer/<customer_id>', deleteCustomer, name='deleteCustomer'),
    path('updateCustomer/<customer_id>', updateCustomer, name='updateCustomer'),
    path('viewEmployees', viewEmployees, name='viewEmployees'),
    path('viewEmployee/<employee_id>', viewEmployee,name='viewEmployee'),
    path('registerEmployee', registerEmployee, name="registerEmployee"),
    path('deleteEmployee/<employee_id>', deleteEmployee, name='deleteEmployee'),
    path('updateEmployee/<employee_id>', updateEmployee, name='updateEmployee'),
    path('viewStores', viewStores, name='viewStores'),
    path('getRoles', getRoles, name='getRoles'),
    path('viewStore/<store_id>', viewStore, name='viewStore'),
    path('viewVehicles', viewVehicles, name='viewVehicles'),
    path('viewVehicle/<vehicle_no>', viewVehicle, name='viewVehicle'),
    path('viewOrders', viewOrders, name='viewOrders'),
    path('viewOrder/<order_id>', viewOrder,name='viewOrder'),
    path('viewItems',viewItems,name='viewItems'),
    path('viewItem/<contains_id>',viewItem,name='viewItem'),
]
