from django.urls import path
from .views import add_student,get_data

urlpatterns = [
    path('add-student/', add_student, name='add_student'),
    path('get_student',get_data,name='get_data')
]
