from django.contrib import admin
from django.urls import path,include
from . import views

urlpatterns = [

    # path('', hello),
    path('', views.LoginView.as_view()),
    path('facilities', views.facilities),
    path('complaint', views.ComplaintView.as_view()),
    path('facility/', views.FacilityView.as_view()),
    path('device', views.DeviceView.as_view()),
    path('tasks', views.TasksView.as_view()),
    path('manager', views.ManagerView.as_view()),
    path('employee/', views.EmployeeView.as_view()),
    path('assign', views.AssignView.as_view()),
    path('edit', views.EditView.as_view()),
    path('searchemployee', views.search_employee),
    path('updatestatus', views.update_status),
    path('complaintitem', views.ComplaintView.as_view()),
    # path('devicename', views.device_name),
    path('createemployee', views.CreateEmployee.as_view()),
    path('getassignedto', views.getassignedto),
    path('getdevices', views.devices_of_employee),
    path('compstatus', views.getstatus),
    path('deletecomplaint', views.ComplaintView.as_view()),


]