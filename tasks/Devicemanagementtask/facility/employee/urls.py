
from django.urls import path,include
from . import views
from rest_framework.routers import DefaultRouter
# route=DefaultRouter()
# route.register('ABC', views.EmployeeView1, basename="example_1")
urlpatterns = [
    path('',views.LoginView .as_view()),
    path('facilities',views.facilities),
    path('complaint',views.ComplaintView .as_view()),
    path('facility/',views.FacilityView .as_view()),
    path('device',views.DeviceView .as_view()),
    path('tasks',views.TasksView .as_view()),
    path('manager',views.ManagerView .as_view()),
    path('employee/', views.EmployeeView.as_view()),
    path('assign',views.AssignView .as_view()),
    path('edit',views.EditView .as_view()),
    path('searchemployee',views.searchemployee),
    path('updatestatus',views.updatestatus),
    path('complaintitem',views.complaintItem),
    path('devicename',views.devicename)
    # path('', views.home),
    # path('login/', views.login),
    # path('login/create/', views.create,name='create'),
    # # path('login/edit/', views.edit,name='edit'),
    # path('login/complaint/', views.complaint,name='complaint'),
    # path('login/issuedto/', views.issuedto,name='issuedto'),
    # path('login/yourcomplaints/', views.yourcomplaints,name='yourcomplaints'),
    # path('login/yourwork/', views.yourwork,name='yourwork'),
    # path('', views.EmployeeView.as_view()),
    # path('facility/', views.facility1)
]