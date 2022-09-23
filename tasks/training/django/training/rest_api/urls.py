from django.urls import path
from . import views
urlpatterns = [
path("",views.appointment,name='home'),
path('appointment/<id>',views.appointment_detail,name='app-detail')
]   