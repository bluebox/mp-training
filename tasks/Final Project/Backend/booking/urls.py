from django.urls import path
from .views import addBookingItems, getBookingById, updateBookingOrderToPaid

urlpatterns = [ 
    path('add/', addBookingItems, name='orders-add'),
    path('<int:pk>/', getBookingById, name='user-order'),
    path('<int:pk>/pay/', updateBookingOrderToPaid, name='pay'),   
]
