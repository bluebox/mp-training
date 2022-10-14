from django.urls import path
from . import views



urlpatterns = [
  path('home', views.homepage, name = "homepage"),
  path('emp_login/', views.login, name='login'),
  path('emp_login/employee/', views.employee, name= 'employee' ),
  path('customer_login/', views.user_login, name = 'user_login'),
  path('customer_login/user_login_details/', views.user_login_details, name = "user_login_details"),
  path('customer_login/user_login_details/view_balance/', views.view_balance, name = "view_balance"),
  # path('customer_login/user_login_details/log_out_page/', views.log_out_page, name= 'log_out_page' ),
  path('logout/' , views.UserLogout, name='logout'),
  path('Emp_logout/', views.EmployeeLogout, name='Emp_logout'),
]