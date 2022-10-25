from django.urls import path
from . import views

urlpatterns = [
    path('create-faculty', views.create_faculty, name='create-faculty'),
    path('update-faculty/<str:pk>', views.update_faculty, name='update-faculty'),
    path('display-faculty', views.display_faculty, name='display-faculty'),
    path('delete-faculty/<str:pk>', views.delete_faculty, name='delete-faculty'),
    path('all-subjects', views.all_subjects, name='all-subjects'),
    path('all-departments', views.all_departments, name='all-departments'),

    # path('register-new-faculty', views.register_new_faculty, name='register-new-faculty'),
    # path('display-faculty', views.display_faculty, name='display-faculty'),
]