from django.urls import path
from .views import RegisterEmployee,RegisterCustomer
urlpatterns = [
    # path('admin/', admin.site.urls),
    # path('users/',include('users.urls'))
    path('register-customer/' ,RegisterCustomer.as_view(),name='register-customer' ),
    path('register-employee/' ,RegisterEmployee.as_view(),name='register-employee' ),

   
]


