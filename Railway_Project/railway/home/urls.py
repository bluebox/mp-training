
from django.urls import path
from . import views
from rest_framework import routers
router = routers.DefaultRouter()
router.register(r'user',views.UserViewSet,basename='user')
# router.register(r'register',views.UserView.as_view(),name='user')

router.register(r'ticket',views.TicketViewSet,basename='ticket')
router.register(r'train',views.TrainViewSet,basename='train')
router.register(r'trainStatus',views.TrainStatusViewSet,basename='trainStatus')
router.register(r'book',views.BookViewSet,basename='book')
router.register(r'schedule',views.ScheduleViewSet,basename='schedule')
router.register(r'seat-chart',views.SeatChartViewSet,basename='seat_chart')
router.register(r'station',views.StationViewSet,basename='station')
router.register(r'passenger',views.PassengerViewSet,basename='passenger')
router.register(r'cancel',views.CancelViewSet,basename='cancel')
router.register(r'coach', views.CoachViewSet,basename='coach')
router.register(r'start-date', views.StartDateViewSet,basename='startDate')
router.register(r'end-date', views.EndDateViewSet,basename='endDate')


urlpatterns = [
path('register/',views.UserView.as_view(), name="userView"),
path('login/',views.Login.as_view(), name="loginView"),
path('login-user/',views.GetUser.as_view(), name="loginUserView"),
path('logout/',views.Logout.as_view(), name="logoutView"),
path('tickets/<str:username>',views.TicketHistory.as_view()),
path('recent-tickets/<str:username>',views.RecentTicketHistory.as_view()),

path('cancel-ticket/',views.CancelTicket.as_view()),
path('search-train/',views.SearchTrain.as_view()),




]
urlpatterns+=router.urls
