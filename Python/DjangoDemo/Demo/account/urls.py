from django.urls import path
from .views import get_data, get_student_data, Employees, login_view, signup_view, dashboard_view, User_data
from .views import get_employee_data,EmployeeDetails

urlpatterns = [
    path('get_student/',get_data,name='get_data'),
    path('get_student_data/<int:pk>/',get_student_data,name="data using pk"),
    path('get_employee_data/',get_employee_data.as_view(),name="employee_data"),
    path('Employee/',Employees.as_view(),name="generics"),
    path('Employee/<int:pk>/',EmployeeDetails.as_view(),name="Details"),
    path('login/',login_view,name='login'),
    path('signup/',signup_view,name='sign'),
    path('dashboard/',dashboard_view,name='dashboard'),
    path('user/',User_data.as_view(),name="user")
]
