"""Urls"""
from django.urls import path
from salon import views

urlpatterns = [
    path('branch/', views.BranchList.as_view()),
    path('newbranch/', views.NewBranch.as_view()),
    path('services/', views.ServicesList.as_view()),
    path('employees/', views.EmployeeList.as_view()),
    path('clientlist/', views.ClientList.as_view()),
    path('clientregistration', views.ClientRegistration.as_view()),
    path('employeelist', views.EmployeeList.as_view()),
    path('appointments/', views.AppointmentList.as_view()),
    path('employeeregistration', views.EmployeeRegistration.as_view()),
    path('listclients', views.ListOfClients.as_view()),
    path('listemployees', views.ListOfEmployees.as_view()),
    path('empbranch', views.EmpBranch.as_view()),
    path('newappointment', views.NewAppointment.as_view()),
    path('delete/', views.DeleteBranch.as_view()),
    path('deleteservice/', views.DeleteService.as_view()),
    path('updatebranch/<int:branch_id>', views.UpdateBranch.as_view()),
    path('getbranch/<int:branch_id>', views.OneBranch.as_view()),
    path('searchclients', views.SearchClients.as_view()),
    path('serachemp', views.SearchEmployee.as_view()),
    path('searchbranch', views.SearchBranches.as_view()),
    path('getservice/<int:service_id>', views.OneService.as_view()),
    path('updateservice/<int:service_id>', views.UpdateService.as_view()),
    path('searchservice', views.SearchService.as_view()),
    path('getappointment/<int:appointment_id>', views.OneAppointment.as_view()),
    path('updateappointment/<int:appointment_id>', views.UpdateAppointment.as_view()),
    path('confirmappointment', views.ConfirmAppointment.as_view()),
    path('completeappointment', views.CompleteAppointment.as_view()),
    path('rejorcancel', views.RejectAppointment.as_view()),
    path('reviews', views.NewReview.as_view())
]