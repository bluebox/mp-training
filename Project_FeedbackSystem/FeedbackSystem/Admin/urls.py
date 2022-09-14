from django.contrib import admin
from django.urls import path
from Admin import views

urlpatterns = [
    path('admin/', admin.site.urls),
    path('display-faculty', views.display_faculty, name='display-faculty'),
]