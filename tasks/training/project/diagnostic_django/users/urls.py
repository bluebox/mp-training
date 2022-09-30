from django.urls import path
from .views import BranchHandler, RegisterEmployee,RegisterCustomer, loginUser , logoutUser
urlpatterns = [
    # path('admin/', admin.site.urls),
    # path('users/',include('users.urls'))
    path('register-customer/' ,RegisterCustomer.as_view(),name='register-customer' ),
    path('register-employee/' ,RegisterEmployee.as_view(),name='register-employee' ),
    path('branch/',BranchHandler.as_view(),name='branch'),
    path('login/',loginUser,name='login'),
    path('logout/',logoutUser, name='logout')
]


