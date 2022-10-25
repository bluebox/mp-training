from django.urls import path
from . import views



urlpatterns = [

path('create-student', views.create_student, name='create-student'),
path('update-student/<str:pk>', views.update_student, name='update-student'),
path('display-student', views.display_student, name='display-student'),
path('search-student', views.search_student, name='search-student'),
path('delete-student/<str:pk>', views.delete_student, name='delete-student'),
path('all-classes', views.all_classes, name='all-classes'),
path('create-class', views.create_class, name='create-class'),

# path('test-url', views.test_url, name='test-url'),

]