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
    path('tours_by_type/<str:tour_type>', views.ToursFilterByType.as_view(), name='tours_type_filter' ),
    path('tours_by_type/', views.ToursFilterByType.as_view(), name='tours_type_filter' ),
    path('enquiries/', views.EnquiryListViewSet.as_view(), name='vehicle' ),
    path('enquiries/<int:pk>', views.EnquiryDetails.as_view(), name='vehicledetail' ),
    path('places/', views.PlacesListViewSet.as_view(), name='places' ),
    path('places/<int:pk>', views.PlaceDetails.as_view(), name='placedetail' ),
    path('coupons/', views.CouponsListViewSet.as_view(), name='coupons' ),
    path('coupons/<int:pk>', views.CouponDetails.as_view(), name='coupondetail' ),
    path('packages/', views.PackagesList.as_view(), name='packages]' ),
    path('packages/<int:pk>', views.PackageDetails.as_view(), name='packagedetail' ),
    path('packageDetailed/<int:pk>', views.PackageDetailed.as_view(), name='packagedetail' ),
    path('employees/', views.EmployeeList.as_view(), name='employees]' ),
    path('employees/<int:pk>', views.EmployeeDetails.as_view(), name='employeedetail' ),
    path('tour_places/<int:pk>', views.TourPlaceDelete.as_view(), name='TourPlaceDelete')
]