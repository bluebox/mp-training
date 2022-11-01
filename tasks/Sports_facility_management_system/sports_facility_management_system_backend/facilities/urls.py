from django.urls import path

from .views import FacilityView, SportsInFacilityView, BookingFormView, GetBookedSlots, GetEquipmentsRelatedToSports, \
    GEtFacilitySportId, UserLogin, GetSports, \
    SearchFilterWithPagination, CheckRefreshToken, GetUserBookings, CancelBooking, UpdateFeedback, GetUserDetails, \
    SearchFilterWithLoadMore, \
    GetBookingsInFacility, GetSlotsInSportFacility, GetRentedEquip, FacilityRatings

urlpatterns = [
    path('sport-facility', SportsInFacilityView.as_view(), name='sports_facility'),
    path('facility', FacilityView.as_view(), name='facility'),
    path('GetSlotsInSportFacility/<int:fid>/<int:sid>', GetSlotsInSportFacility.as_view(), name='slots'),
    path('bookingform', BookingFormView.as_view(), name='booking_form'),
    path('get-booked-slots', GetBookedSlots.as_view(), name='slots'),
    path('get-equipments', GetEquipmentsRelatedToSports.as_view(), name='equipments'),
    path('get-fsid', GEtFacilitySportId.as_view(), name='fsid'),
    path('login', UserLogin.as_view(), name='login'),
    path('get-sports', GetSports.as_view(), name='get_sports'),
    path('get-facilities-contain-sport', SearchFilterWithPagination.as_view(), name='get_facilities_in_sports'),
    path('check-refresh-token', CheckRefreshToken.as_view(), name='check_access_token'),
    path('get-user-bookings/<int:uid>', GetUserBookings.as_view(), name='get_user_bookings'),
    path('cancel-booking/<int:bid>', CancelBooking.as_view(), name='cancel_booking'),
    path('update-feedback/<int:bid>', UpdateFeedback.as_view(), name='update_feedback'),
    path('user', GetUserDetails.as_view(), name='get_user'),
    path('Get-rented-equipments', GetRentedEquip.as_view(), name='get_rented_slots'),
    path('search-facilities', SearchFilterWithLoadMore.as_view(), name='search_function'),
    path('get-bookings-facility', GetBookingsInFacility.as_view(), name='get_bookings_facility'),
    path('get-ratings-facility',FacilityRatings.as_view(),name='facility-ratings')
]

