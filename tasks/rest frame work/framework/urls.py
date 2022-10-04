from django.urls import path
from framework import views

urlpatterns = [
    path('', views.departmentApi),
    path('<int:id>/',views.departmentApi)
]
