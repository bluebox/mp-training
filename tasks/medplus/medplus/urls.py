
from django.contrib import admin
from django.urls import path,include


urlpatterns = [
    path('admin/', admin.site.urls),
    path('', include('medplus_app.urls')),
    path('dt', include('django_templates.urls')),
    path('du', include('django_urls.urls')),
    path('dv', include('django_views.urls')),



]
