#
# from django.urls import path
from .views import *
#
from django.urls import path
from rest_framework_simplejwt.views import TokenObtainPairView, TokenRefreshView, TokenVerifyView

urlpatterns = [
# urlpatterns = [
#     path('', home, name="home"),
#     path('custloginpage', custloginPage, name='custlogin'),
#     path('custregistrationpage', custregistrationPage, name='custregistration'),
#     path('logincustomer', customerLogin, name='logincustomer'),
#     path('logout', logoutc, name='logout'),
#     path('custdashboard', custdashboard, name='custdashboard'),
#     path('buyproducts', buyproducts, name='buyproducts'),
#
#     path('productspage/<str:storeid>', productsPage, name='productspage'),
#     path('registerCustomer', registerCustomer, name="registercustomer"),
#     path('viewCustomers', viewCustomers, name='viewCustomers'),
#     path('viewCustomer/<customer_id>', viewCustomer, name='viewCustomer'),
#     path('deleteCustomer/<customer_id>', deleteCustomer, name='deleteCustomer'),
#     path('updateCustomer/<customer_id>', updateCustomer, name='updateCustomer'),
#     path('viewEmployees', viewEmployees, name='viewEmployees'),
#     path('viewEmployee/<employee_id>', viewEmployee, name='viewEmployee'),
#     path('registerEmployee', registerEmployee, name="registerEmployee"),
#     path('deleteEmployee/<employee_id>', deleteEmployee, name='deleteEmployee'),
#     path('updateEmployee/<employee_id>', updateEmployee, name='updateEmployee'),
#     path('viewStores', viewStores, name='viewStores'),
    path('getRoles', getRoles, name='getRoles'),
#     path('viewStore/<store_id>', viewStore, name='viewStore'),
#     path('registerStore', registerStore, name='registerStore'),
#     path('updateStore/<store_id>', updateStore, name='updateStore'),
#     path('deleteStore/<store_id>', deleteStore, name='deleteStore'),
#     path('registerVehicle', registerVehicle, name="registerVehicle"),
#     path('viewVehicles', viewVehicles, name='viewVehicles'),
#     path('viewVehicle/<vehicle_no>', viewVehicle, name='viewVehicle'),
#     path('updateVehicle/<vehicle_no>', updateVehicle, name='updateVehicle'),
#     path('deleteVehicle/<vehicle_no>', deleteVehicle, name='deleteVehicle'),
#     path('viewOrders', viewOrders, name='viewOrders'),
#     path('viewOrder/<order_id>', viewOrder,name='viewOrder'),
#     path('viewItems', viewItems, name='viewItems'),
#     path('viewItem/<contains_id>', viewItem, name='viewItem'),
    path('logout', logout, name='logout'),
    path('login', customerLogin.as_view(), name='login'),
    path('api/token/', TokenObtainPairView.as_view(), name='token_obtain_pair'),
    path('api/token/refresh/', TokenRefreshView.as_view(), name='token_refresh'),
    path('api/token/verify/', TokenVerifyView.as_view(), name='token_refresh'),
   ]
