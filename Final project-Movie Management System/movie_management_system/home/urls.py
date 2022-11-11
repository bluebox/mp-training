'url page'
from rest_framework.routers import DefaultRouter
from rest_framework_simplejwt.views import (
    TokenObtainPairView,
    TokenRefreshView,
    TokenVerifyView
)
from django.urls import path
from home import views

router = DefaultRouter()
router.register(r'movie', views.MovieViewset, basename='movie')
router.register(r'theatre', views.TheatreViewset, basename='theatre')
# router.register(r'snacks', views.SnacksViewset, basename='snack')
router.register(r'Hall', views.HallViewset, basename="Hall")
router.register(r'User', views.UserViewset, basename="User")
# router.register(r'Seating', views.SeatingViewset, basename='seating')
# router.register(r'SelectedSeat', views.SelectedSeatsViewset, basename='selectedseats')
router.register(r'Booking', views.BookingViewset, basename='booking')
# router.register(r'Cart', views.CartViewset, basename='cart')
# router.register(r'Payment', views.PaymentViewset, basename='Payment')
# router.register(r'Bill', views.BillViewset, basename='Bill')
router.register(r'Promocode', views.PromomcodeViewset, basename='Promocode')
router.register(r'Date', views.DateViewset, basename='Date')


urlpatterns = router.urls
urlpatterns += [
    path('', views.index),
    # path('deleteMovie/<str:id>/',views.MovieDelete),
    # path('updateHall/<int:id>/', views.MovieDelete),
    path('api/token/', TokenObtainPairView.as_view(), name='token_obtain_pair'),
    path('api/token/refresh/', TokenRefreshView.as_view(), name='token_refresh'),
    path('api/token/verify/', TokenVerifyView.as_view(), name='token_verify'),
    path('register',views.Register.as_view()),
    path('login', views.Login.as_view()),
    path('getuser', views.GetUser.as_view()),
    path('logout', views.Logout.as_view()),
    path('selectedseats/<int:id>/',views.SelectedSeats.as_view()),
    path('ticket', views.Ticket.as_view()),
    path('history/<str:data>', views.BookingHistory.as_view()),
    path('date/<slug:date>/', views.GetFilteredData.as_view()),
    path('screen/<int:id>/',views.GetFilteredHalls.as_view()),
    path('theatre/<int:id>/', views.GetSingleTheatre.as_view()),
    path('deduct/<int:id>/<slug:date>/', views.DeductSeats.as_view()),
    path('singlehall/<int:id>/<slug:date>/<str:stime>', views.GetSingleHall.as_view()),
    path('cancel/<int:bid>/', views.Cancel.as_view()),
    path('lang/<str:lang>/',views.CateogorisedMovies.as_view()),
    path('owners', views.GetOwners.as_view()),
    path('ownerstheatre/<str:owner>', views.OwnersTheatre.as_view()),
    path('theatrelist/<int:id>/<slug:date>',views.TheatreList.as_view()),
    path('halllist/<int:id_>/', views.HallList.as_view()),
    path('cancelled', views.GetCancelledTickets.as_view()),
    path('search/<str:a>/', views.SearchMovie.as_view()),
    path('searcht/<int:movie_id>/<slug:date>/<str:a>/', views.SearchTheatre.as_view()),
    path('top', views.GetTop.as_view()),
    path('create', views.ObjectCreation.as_view()),
    # path('all/<int:user_id>', views.getAllHistory.as_view()),
    path('filter/<str:flist>/<str:search>', views.Filter.as_view()),
    path('page/<int:pno>', views.Pagination.as_view()),
    path('test/<int:id>', views.test.as_view()),
    path('all/<int:id>',views.GetAllBookings.as_view())
]
