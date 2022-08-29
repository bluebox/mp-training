
from django.contrib import admin
from django.urls import path,include


urlpatterns = [
    path('admin/', admin.site.urls),
    path('', include('medplus_app.urls')),
    path('django_templates', include('django_templates.urls')),
    path('django_urls', include('django_urls.urls')),
    path('django_views', include('django_views.urls')),



]
