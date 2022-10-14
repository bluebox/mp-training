from django.urls import path

from .views import FacilityView, SportsInFacilityView, FacilitiesDetailsView, SlotView, AddSportsToFacilityView, \
    BookingFormView, GetBookedSlots, GetEquipments, GEtFacilitySportId, UserLogin, SearchFacilities, GetSports, \
    GetFacilitiesInSports, CheckAccessToken, GetUserBookings, CancelBooking, UpdateFeedback, GetUserDetails, \
    AddSportsToFacility, GetAllSlots

urlpatterns = [
    path('facilities', FacilitiesDetailsView.as_view(), name='facilities'),
    path('sf/<int:fid>', SportsInFacilityView.as_view(), name='sports_facility'),
    path('addsport', AddSportsToFacilityView.as_view(), name='add_sport_facility'),

    path('facility', FacilityView.as_view(), name='facility'),
    path('slots/<int:fid>/<int:sid>', SlotView.as_view(), name='slots'),
    path('bookingform', BookingFormView.as_view(), name='booking_form'),
    path('get-booked-slots', GetBookedSlots.as_view(), name='slots'),
    path('get-equipments', GetEquipments.as_view(), name='equipments'),
    path('get-fsid', GEtFacilitySportId.as_view(), name='fsid'),
    path('login', UserLogin.as_view(), name='login'),
    # path('try',Try.as_view(), name='try'),
    path('search-facilities', SearchFacilities.as_view(), name='search_facilities'),
    path('get-sports', GetSports.as_view(), name='get_sports'),

    path('get-facilities-contain-sport/<int:sid>', GetFacilitiesInSports.as_view(), name='get_facilities_in_sports'),
    path('check-refresh-token', CheckAccessToken.as_view(), name='check_access_token'),
    path('get-user-bookings/<int:uid>', GetUserBookings.as_view(), name='get_user_bookings'),
    path('cancel-booking/<int:bid>', CancelBooking.as_view(), name='cancel_booking'),
    path('update-feedback/<int:bid>', UpdateFeedback.as_view(), name='update_feedback'),
    path('user', GetUserDetails.as_view(), name='get_user'),
    path('add-sports', AddSportsToFacility.as_view(), name='add-sports'),
    path('get-slots',GetAllSlots.as_view(), name='get_slots')
]

# router = DefaultRouter()
# router.register('facility', FacilityView, basename='Facility')
# urlpatterns = router.urls
