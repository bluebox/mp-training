from django.urls import path, include
from rest_framework.routers import DefaultRouter

from .managers import get_facility_sport_id
from .views import FacilityView, SportsInFacilityView, FacilitiesDetailsView, SlotView, AddSportsToFacilityView, \
    BookingFormView, GetBookedSlots, GetEquipments, GEtFacilitySportId

urlpatterns = [
    path('facilities', FacilitiesDetailsView.as_view(), name='facilities'),
    path('sf/<int:fid>', SportsInFacilityView.as_view(), name='sports_facility'),
    path('addsport', AddSportsToFacilityView.as_view(), name='add_sport_facility'),

    path('facilities/<int:fid>', FacilityView.as_view(), name='facility'),
    path('slots/<int:fid>/<int:sid>', SlotView.as_view(), name='slots'),
    path('bookingform', BookingFormView.as_view(), name='booking_form'),
    path('get-booked-slots', GetBookedSlots.as_view(), name='slots'),
    path('get-equipments', GetEquipments.as_view(), name='equipments'),
    path('get-fsid', GEtFacilitySportId.as_view(), name='fsid'),
    # path('try',Try.as_view(), name='try'),
]

# router = DefaultRouter()
# router.register('facility', FacilityView, basename='Facility')
# urlpatterns = router.urls
