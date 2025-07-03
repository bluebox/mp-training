from django.urls import path
from .views import get_data,get_student_data

urlpatterns = [
    path('get_student/',get_data,name='get_data'),
    path('get_student_data/<int:pk>/',get_student_data,name="data using pk")
]
