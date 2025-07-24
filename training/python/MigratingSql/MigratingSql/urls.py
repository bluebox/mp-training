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

urlpatterns = [
    path('admin/', admin.site.urls),
    # path('create/',Populate.as_view(), name="create_path"),
    # path('read/<str:Table>/',ExecuteRead.as_view(), name="read_path"),
    # path('read/<str:Table>/<int:id>/',ExecuteReadSpecific.as_view(), name="read_specific_path"),
    # path('update/<str:Table>/<int:id>/', UpdateData.as_view(), name="update_path"),
    # path('delete/<str:Table>/<int:id>', DeleteData.as_view(), name="delete_path"),
    # path('<str:Name>/',LoginPage.as_view(),name="login_path"),
path('EmpDes/',Employees_Dgn_NotDetermined.as_view(),name="Emp_D_path")
]
