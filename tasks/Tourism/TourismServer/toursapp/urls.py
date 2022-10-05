from django.urls import path
from django.contrib import admin

from toursapp import views
#
urlpatterns = [
    # path('', views.index),
    path('vehicles/', views.VehicleList.as_view(), name='vehicle' ),
    path('vehicles/<int:pk>', views.VehicleDetails.as_view(), name='vehicle' ),
    path('tours_list/', views.ToursListViewSet.as_view(), name='tours' ),
    path('tours_list/<int:pk>', views.TourDetails.as_view(), name='tours_details' ),
    path('enquiries/', views.EnquiryListViewSet.as_view(), name='vehicle' ),
    path('enquiries/<int:pk>', views.EnquiryDetails.as_view(), name='vehicle' ),
    path('places/', views.PlacesListViewSet.as_view(), name='places' ),
    path('places/<int:pk>', views.PlaceDetails.as_view(), name='places' ),
    path('coupons/', views.CouponsListViewSet.as_view(), name='coupons' ),
    path('coupons/<int:pk>', views.CouponDetails.as_view(), name='coupons' ),
]