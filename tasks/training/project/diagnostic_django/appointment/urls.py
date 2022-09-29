from django.urls import path
from .views import AppointmentBooking, getEmployees
urlpatterns = [
    path('book-appointment/' ,AppointmentBooking.as_view(),name='book-appointment' ),
    path('get-employee/' ,getEmployees,name='get-employee' ),

]


