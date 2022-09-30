from django.urls import path
from Faculty import views

urlpatterns = [
    path('display-faculty', views.display_faculty, name='display-faculty'),
    path('add-faculty', views.add_faculty, name='add-faculty'),
    path('register-new-faculty', views.register_new_faculty, name='register-new-faculty'),
    path('faculty-serializer', views.faculty_serializer, name='faculty-serializer'),
    path('create-faculty', views.faculty_create, name='create-faculty'),

]