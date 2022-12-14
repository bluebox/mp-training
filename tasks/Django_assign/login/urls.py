from django.urls import path
from . import views

urlpatterns = [
    path('', views.loginaction, name='loginaction'),
    path('studentlogin/', views.student_login, name='studentlogin'),
    path('facultylogin/', views.faculty_login, name='facultylogin'),
    path('add/', views.addaction, name='addaction'),
    path('add/addrecord/', views.addrecord, name="addrecord"),
    path('update/<str:id>', views.update, name='update'),
    path('update/updaterecord/', views.updaterecord, name='updaterecord'),
    path('del/<str:id>', views.delete, name='delete')
]
