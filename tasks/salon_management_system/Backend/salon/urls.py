"""Urls"""
from pathlib import Path
from django.urls import path
from salon import views

urlpatterns = [
    path('user/',views.UserList.as_view()),
    path('branch/',views.BranchList.as_view()),
    path('newbranch/',views.NewBranch.as_view()),
    path('services/',views.ServicesList.as_view()),
    path('employees/',views.EmployeeList.as_view()),
    path('clientlist/',views.ClientList.as_view()),
    path('clientregistration',views.ClientRegistration.as_view()),
    path('employeelist',views.EmployeeList.as_view()),
    # path('login',views.LoginUser.as_view()),
    path('appointments/',views.AppointmentList.as_view()),
    path('employeeregistration',views.EmployeeRegistration.as_view()),
    path('listclients',views.ListOfClients.as_view()),
    path('listemployees',views.ListOfEmployees.as_view()),
    path('empbranch',views.EmpBranch.as_view()),
    path('newappointment',views.NewAppointment.as_view()),
    path('delete/',views.deleteBranch.as_view()),
    path('deleteservice/',views.deleteService.as_view()),
    path('updatebranch/<int:pk>',views.UpdateBranch.as_view()),
    path('getbranch/<int:id>',views.OneBranch.as_view()),
    path('searchclients',views.SearchClients.as_view()),
    path('serachemp',views.SearchEmployee.as_view()),
    path('searchbranch',views.SearchBranches.as_view()),
    path('search',views.branchFilter),
    path('getservice/<int:id>',views.OneService.as_view()),
    path('updateservice/<int:pk>',views.UpdateService.as_view()),
    path('searchservice',views.SearchService.as_view()),
    path('getappointment/int:id>',views.OneAppointment.as_view()),
    path('updateappointment/<int:pk>',views.UpdateAppointment.as_view()),
]
