'''urls defined'''
from django.urls import path
from .views import AppointmentAPI, FilterAppointment, CustomerAppointments, DetailAppointment,\
    BranchAPI, FilterBranches, DetailBranch, LabAPI, DetailLab, BillAPI, DetailBill,\
    TestAPI, ReviewAPI, ReportAPI, DetailsForBooking , DetailTest,\
    DetailReview, DetailReport

urlpatterns = [
    # appointment
    path('book-appointment/', AppointmentAPI.as_view(), name='booking-appointment'),
    path('search-appointment/', FilterAppointment.as_view(), name='search-appointment'),
    path('customer-appointments/<cust_id>/',
         CustomerAppointments.as_view(), name='customer-appointments'),
    path('appointment/<int:app_id>/', DetailAppointment.as_view(), name='appointment'),
    # branch
    path('branches/', BranchAPI.as_view(), name="branches"),
    path('branch/<id>/', DetailBranch.as_view(), name="branch"),
    path('search-branches/', FilterBranches.as_view(), name="filterbranches"),
    # lab
    path('labs/', LabAPI.as_view(), name="labs"),
    path('lab/<id>', DetailLab.as_view(), name="lab"),
    # bill
    path('bills/', BillAPI.as_view(), name="bills"),
    path('bill/<id>/', DetailBill.as_view(), name="bill"),
    # test
    path('tests/', TestAPI.as_view(), name="tests"),
    path('test/<id>', DetailTest.as_view()),
    # review
    path('reviews/', ReviewAPI.as_view(), name="reviews"),
    path('review/<id>', DetailReview.as_view()),
    # report
    path('reports/', ReportAPI.as_view(), name="reports"),
    path('report/<id>', DetailReport.as_view(), name="report"),
    # details for booking
    path('get-details-for-booking-appointment/',
         DetailsForBooking.as_view(), name='get-details-for-booking-appointment')
]
