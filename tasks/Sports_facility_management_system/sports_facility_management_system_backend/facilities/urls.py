from django.urls import path, include
from rest_framework.routers import DefaultRouter

from .views import FacilityView, SportsInFacilityView, FacilitiesDetailsView, SlotView, AddSportsToFacilityView

urlpatterns = [
    path('facilities', FacilitiesDetailsView.as_view(), name='facilities'),
    path('sf/<int:fid>', SportsInFacilityView.as_view(), name='sports_facility'),
    path('addsport', AddSportsToFacilityView.as_view(), name='add_sport_facility'),

    path('facilities/<int:fid>', FacilityView.as_view(), name='facility'),
    path('slots/<int:fid>/<int:sid>',SlotView.as_view(), name='slots')

]

# router = DefaultRouter()
# router.register('facility', FacilityView, basename='Facility')
# urlpatterns = router.urls
