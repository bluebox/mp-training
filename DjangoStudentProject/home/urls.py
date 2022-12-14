from django.contrib import admin
from django.urls import path
from home import views
urlpatterns = [
    path('', views.index, name='index'),
    path('index', views.index, name='home'),
    path('marks', views.marks, name='marks'),
    path('marks/<int:id>', views.save_marks, name="save_marks"),
    path('student_details/', views.student_detail, name='student_details'),
    path('subject', views.subject, name='subject'),
    path('student_profile/<int:id>', views.student_profile, name="student_profile"),
    path('student_profile/update_student/<int:id>', views.update_student, name="update_student"),
    path('std_profile/update_student/<int:id>', views.update_student, name="update_student"),
    path('student_profile/delete/<int:id>', views.delete_student, name="delete_student"),
    path('std_profile/delete/<int:id>', views.delete_student, name="delete_student"),
    path('student_profile/cancel/', views.cancel, name="cancel"),
    path('std_profile/cancel/', views.cancel, name="cancel"),
    path('filter_by_branch/', views.filter_by_branch, name='filter_by_branch'),
    path('std_profile/', views.std_profile, name="std_profile"),
    path('test/showform/', views.showform),
    path('test/', views.test)
]
