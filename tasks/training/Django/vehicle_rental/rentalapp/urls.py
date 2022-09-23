from django.contrib import admin
from django.urls import path, include
from . import views
from rest_framework.routers import DefaultRouter
from django.conf.urls.static import static
from django.conf import  settings

from .views import VehicleViewSet

router = DefaultRouter()
router.register('vehicle', VehicleViewSet, basename='vehicle')


urlpatterns = [

    path('', views.home, name='home'),
    path('customer_login', views.customer_login, name='customer_login'),
    path('owner_login', views.owner_login, name='owner_login'),
    path('owner_register', views.owner_register, name='owner_register'),
    path('customer_register', views.customer_register, name='customer_register'),
    path('c_login', views.c_login, name='c_login'),
    path('o_login', views.o_login, name="o_login"),
    path('add_vehicle/<int:para>', views.add_vehicle, name='add_vehicle'),
    path('show', views.show, name='show'),


    path('owner/', views.OwnerList.as_view(), name='owner'),
    path('owner/<int>/', views.OwnerDetail.as_view(), name='owner_pk'),
    path('vehicle/', include(router.urls)),
    path('vehiclestatus/', views.VehicleStatusList.as_view()),
    path('vehiclestatus/<string>', views.VehicleStatusDetail.as_view()),
    path('bill', views.BillList)
    

    # path()
]