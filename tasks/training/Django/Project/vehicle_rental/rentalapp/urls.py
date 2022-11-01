from django.contrib import admin
from django.urls import path, include
from . import views
from rest_framework.routers import DefaultRouter
from django.conf.urls.static import static
from django.conf import  settings

# from .views import VehicleViewSet
#
# router = DefaultRouter()
# router.register('vehicle', VehicleViewSet, basename='vehicle')


urlpatterns = [

    # path('', views.home, name='home'),
    # path('customer_login', views.customer_login, name='customer_login'),
    # path('owner_login', views.owner_login, name='owner_login'),
    # path('owner_register', views.owner_register, name='owner_register'),
    # path('customer_register', views.customer_register, name='customer_register'),
    # path('c_login', views.c_login, name='c_login'),
    # path('o_login', views.o_login, name="o_login"),


    path('owner/', views.OwnerList.as_view(), name='owner'),
    path('ownerdetails/', views.OwnerDetail.as_view(), name='owner_pk'),
    path('customer/', views.CustomerList.as_view(), name='owner'),
    path('customerdetails/', views.CustomerDetail.as_view(), name='owner_pk'),
    path('vehicle/', views.VehicleList.as_view()),
    path('add-vehicle/', views.AddVehicle.as_view()),
    path('owner-vehicle/', views.VehicleDetail.as_view()),
    path('updateprice/<str:pk>/', views.UpdateVehiclePrice.as_view()),

    path('getbill/<int:id>', views.BillDetail.as_view()),
    path('bill/<int:pk>', views.BillList.as_view()),
    path('bill-list/', views.BillList.as_view()),

    path('trip', views.RentTripList.as_view()),
    path('book/', views.Book.as_view()),
    path('trip/<int:pk>/', views.RentTripDetail.as_view()),
    path('recieved-orders', views.getOrders.as_view()),
    path('generatebill/<int:id>', views.BillList.as_view()),

    path('customer-review/<int:pk>/', views.CustomerReview.as_view()),
    path('owner-review/<int:pk>/', views.OwnerReview.as_view()),
    path('search-vehicle/', views.Search.as_view()),
    path('cancel-order/<int:pk>/', views.UpdateOrderStatus.as_view()),
    path("add-odoreading/<int:pk>/",views.AddOdoReading.as_view()),

    path("vehicle-status/<str:pk>/", views.ChangeVehicleStatus.as_view()),

    path('get-vehicle-details/<str:id>', views.GetVehicle.as_view()),
    path('get-owner-details/<str:id>', views.GetVehiclesOwner.as_view()),

                  #JWT API
    path('customer-login/', views.LoginCustomerJwt.as_view()),
    path('owner-login/', views.LoginOwnerJwt.as_view()),
    path('customer-logout/', views.LogoutCustomerJwt.as_view()),
    path('owner-logout/', views.LoginOwnerJwt.as_view()),
    path('logout-customer/', views.LogoutCustomerJwt.as_view()),
    path('logout-owner/', views.LogoutOwnerJwt.as_view()),


    path('owner-vehicle/<str:id>', views.GetVehiclesOwner.as_view()),
    path('delete-vehicle/<str:numid>', views.DeleteVehicle.as_view()),
    path('save-file/', views.save_file, name='SaveFile')



] + static(settings.MEDIA_URL, document_root=settings.MEDIA_ROOT)

    # path()

'''

{
    "vehicle_no": "KA09HM7023",
    "customer_id": "5",
    "owner_d" : 102,
    "pickup_time_date" : "2020-05-23 10:40",
    "return_date_time" : "2020-05-24 12:30",
    "odo_start_reading" : 20900,
    "odo_end_reading" : 20100,
    "customer_review" : "Good",
    "owner_review" : "Good"
}

'''