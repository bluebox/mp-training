"""
URL configuration for MigratingSql project.

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/5.2/topics/http/urls/
Examples:
Function views
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  path('', views.home, name='home')
Class-based views
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  path('', Home.as_view(), name='home')
Including another URLconf
    1. Import the include() function: from django.urls import include, path
    2. Add a URL to urlpatterns:  path('blog/', include('blog.urls'))
"""
from django.contrib import admin
from django.urls import path
from Employee.views import *
from rest_framework_simplejwt.views import (
    TokenObtainPairView,
    TokenRefreshView,
)

from Employee.views import (
    EmployeeProfileView,
    TeamUpdateView,
    UpdateSalaryView,
    AllEmployeesListView,
    CreateEmployeeView,
    DeleteEmployeeView,
    DesignationCRUDView,
)

urlpatterns = [
    path('admin/', admin.site.urls),
    # path('employee/profile/', EmployeeProfileView.as_view(), name='employee-profile'),
    # path('manager/update-team/', ManagerTeamUpdateView.as_view(), name='manager-update-team'),
    # path('hr/update-salary/', HRUpdateSalaryView.as_view(), name='hr-update-salary'),
    # path('ceo/employees/', AllEmployeesListView.as_view(), name='ceo-all-employees'),
    # path('hr/create-employee/', HRCreateEmployeeView.as_view(), name='hr-create-employee'),
    # path('hr/delete-employee/', HRDeleteEmployeeView.as_view(), name='hr-delete-employee'),
    # path('designation/', DesignationCRUDView.as_view(), name='designation-crud'),
    # path('departments/add/', DepartmentCRUDView.as_view(), name='designation-crud'),
    # path('api/token/', TokenObtainPairView.as_view(), name='token_obtain_pair'),
    # path('api/token/refresh/', TokenRefreshView.as_view(), name='token_refresh'),
    # path('employee/update-job/', UpdateAnyEmployeeJobView.as_view(), name='update-job'),
    path('api/token/', TokenObtainPairView.as_view(), name='token_obtain_pair'),
    path('api/token/refresh/', TokenRefreshView.as_view(), name='token_refresh'),
    path('employee/profile/', EmployeeProfileView.as_view(), name='employee-profile'),
    path('employee/create/', CreateEmployeeView.as_view(), name='employee-create'),
    path('employee/delete/', DeleteEmployeeView.as_view(), name='employee-delete'),
    path('employee/update-job/', UpdateAnyEmployeeJobView.as_view(), name='employee-update-job'),
    path('employee/update-salary/', UpdateSalaryView.as_view(), name='employee-update-salary'),
    path('employee/list/', AllEmployeesListView.as_view(), name='employee-list'),

    # Team Management
    path('employee/update-team/', TeamUpdateView.as_view(), name='employee-update-team'),

    # Master Data
    path('designation/', DesignationCRUDView.as_view(), name='designation-crud'),
    path('departments/', DepartmentCRUDView.as_view(), name='department-crud'),
]



