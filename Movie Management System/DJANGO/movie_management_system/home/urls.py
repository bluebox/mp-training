
from django.urls import path
from home import views
from rest_framework.routers import DefaultRouter
router = DefaultRouter()
router.register(r'movie', views.MovieViewset, basename='movie')
router.register(r'theatre', views.TheatreViewset, basename='theatre')
router.register(r'snacks', views.SnacksViewset, basename='snack')
router.register(r'Hall', views.HallViewset, basename="Hall")
router.register(r'User', views.UserViewset, basename="User")
router.register(r'Seating', views.SeatingViewset, basename='seating')
# router.register(r'SelectedSeat', views.SelectedSeatsViewset, basename='selectedseats')
router.register(r'Booking', views.BookingViewset, basename='booking')
router.register(r'Cart', views.CartViewset, basename='cart')
router.register(r'Payment', views.PaymentViewset, basename='Payment')
router.register(r'Bill', views.BillViewset, basename='Bill')
router.register(r'Promocode', views.PromomcodeViewset, basename='Promocode')

urlpatterns = router.urls
urlpatterns += [
    path('', views.index),
    path('deleteMovie/<str:id>/',views.MovieDelete),
    path('updateHall/<int:id>/', views.MovieDelete)

]
