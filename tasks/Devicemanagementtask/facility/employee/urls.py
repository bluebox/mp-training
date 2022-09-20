
from django.urls import path
from . import views
urlpatterns = [
    # path('', views.home),
    # path('login/', views.login),
    # path('login/create/', views.create,name='create'),
    # # path('login/edit/', views.edit,name='edit'),
    # path('login/complaint/', views.complaint,name='complaint'),
    # path('login/issuedto/', views.issuedto,name='issuedto'),
    # path('login/yourcomplaints/', views.yourcomplaints,name='yourcomplaints'),
    # path('login/yourwork/', views.yourwork,name='yourwork'),
    # path('', views.EmployeeView.as_view()),
    path('facility/', views.FacilityView.as_view()),
    # path('facility/', views.facility1),
    path('employee/', views.EmployeeView.as_view())

]