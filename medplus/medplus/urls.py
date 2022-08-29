
from django.contrib import admin
from django.urls import path,include


urlpatterns = [
    path('admin/', admin.site.urls),
    path('', include('medplus_app.urls')),
    path('variables', include('django_templates.urls')),

]
