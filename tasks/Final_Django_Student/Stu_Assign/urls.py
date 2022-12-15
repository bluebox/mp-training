from django.conf import settings
from django.conf.urls.static import static
from django.urls import path
from . import views

urlpatterns = [
    path('', views.stu, name='stu'),
    path('list_item/', views.list_item, name='list_item'),
    path('<int:id>', views.view_student, name='view_student'),
    path('edit/<int:id>/', views.edit, name='edit'),
    path('stu_profile/<int:id>', views.stu_profile, name='student_profile'),
    path('add/', views.add, name='add'),
    path('stu_delete/<int:id>', views.stu_delete, name='dltstudent'),
    path('list_item/mar_delete/<int:id>', views.mar_delete, name='delete_marks'),
    path('list_item/add_marks/', views.add_marks, name='add_marks'),
    path('list_item/add_marks/add_mar_record/', views.add_marks_record, name='marks_add'),
    path('search/', views.search_student, name='search_student'),
    path('filter_result/', views.filter_stu, name='filter_student'),
    path('upload/', views.image_upload_view)
]
if settings.DEBUG:
    urlpatterns += static(settings.MEDIA_URL, document_root=settings.MEDIA_ROOT)
