from django.urls import path

from .views import VehicleAPI

urlpatterns = [
    path('vehicle', VehicleAPI.as_view(), name='vehicle'),
    path('vehicle/<vehicle_no>', VehicleAPI.as_view(), name='vehicle'),
]
