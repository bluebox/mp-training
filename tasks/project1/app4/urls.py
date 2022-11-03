from django.conf import settings
from django.conf.urls.static import static
from django.urls import path
from django.contrib.auth import views as auth_views
from .import views
from .views import LoginView, UserView, LogoutView, ImageViewSet

app_name = 'app4'
urlpatterns = [

    # path('model/', views.index, name="homepage"),
    # path('model/register/', views.register, name='register'),
    # path('model/login/', auth_views.LoginView.as_view(template_name='m_app4/login.html'), name='login'),
    # path('model/logout/', auth_views.LogoutView.as_view(template_name='m_app4/logout.html'), name='logout'),
    # path('model/password-reset/', auth_views.PasswordResetView.as_view(template_name='m_app4/password_reset.html'),
    #      name='password_reset'),
    # path('model/password-reset/done/', auth_views.PasswordResetDoneView.as_view
    # (template_name='m_app4/password_reset_done.html'), name='password_reset_done'),
    # path('model/password-reset-confirm/<uidb64>/<token>', auth_views.PasswordResetConfirmView.as_view
    # (template_name='m_app4/password_reset_confirm.html'), name='password_reset_confirm'),
    # path('model/password-reset-complete/', auth_views.PasswordResetCompleteView.as_view(template_name='m_app4/password_reset_complete.html'),
    #      name='password_reset_complete'),
    path('demo/', views.airport_api, name='airport'),
    path('demo/<int:id>/', views.airport_api, name='airport'),
    path('savefile/', views.save_file, name='savefile'),
    path('terminal/', views.terminal_api, name='terminal'),
    path('terminal/<int:id>/', views.terminal_api, name='terminal'),
    path('passenger/', views.passenger_api, name='passenger'),
    path('passenger/<int:id>/', views.passenger_api, name='passenger'),
    path('airlines/', views.airlines_api, name='airlines'),
    path('airlines/<int:id>/', views.airlines_api, name='airlines'),
    path('flight/', views.Filterflights, name='flight'),
    path('ticket/', views.Bookticket, name='ticket'),
    path('ticket-details/', views.ticketdetails, name='ticket-details'),
    path('ticket_all_details/', views.ticket_all_details, name='ticket_all_details'),
    path('my-bookings/',views.my_bookings, name='my-bookings'),
    # path('ticket-count/', views.ticketcount, name="ticket-count"),
    path('schedule/', views.lookschedule, name='schedule'),
    # path('register/', RegisterView.as_view()),
    path('login/', LoginView.as_view()),
    path('user/', UserView.as_view()),
    path('logout/', LogoutView.as_view()),
    path('staff/', views.staff_api, name='staff'),
    path('staff/<int:id>', views.staff_api, name='staff'),
    path('staff-shifts/', views.staff_shifts_api, name='staff_shifts'),
    path('luggage/', views.luggage_api, name='luggage'),
    path('upload/', views.ImageViewSet.as_view(), name='upload'),
    path('profile-img/', views.ProfileViewSet.as_view(), name='profile-img'),
    path('my-details/', views.my_details, name='my-details'),
    # path('upload-image/',views.upload_image,name="image"),
    # path('savefile/',views.savefile,name="savefile")
] + static(settings.MEDIA_URL, document_root=settings.MEDIA_ROOT)


