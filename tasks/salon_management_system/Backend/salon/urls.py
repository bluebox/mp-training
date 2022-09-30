
from salon import views
from django.urls import path

urlpatterns = [
    path('user/',views.UserList.as_view()),
    path('branch/',views.BranchList.as_view()),
    path('services/',views.ServicesList.as_view()),
    path('employees/',views.EmployeeList.as_view()),
    path('ClientRegistration',views.ClientRegistration.as_view()),
    path('login',views.LoginUser.as_view()),

    # path('usercreate/',views.UserCreate.as_view()),

]