from django.contrib import admin
from django.urls import path, include
from Faculty import views

urlpatterns = [
    path('admin/', admin.site.urls),
    path('Admin/', include('Admin.urls')),
    path('Faculty/', include('Faculty.urls')),
    # path('Student/', include('Student.urls')),
    path('create-faculty', views.faculty_create, name='create-faculty'),

]
