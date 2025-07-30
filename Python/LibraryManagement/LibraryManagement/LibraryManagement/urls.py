from debug_toolbar.toolbar import debug_toolbar_urls
from django.contrib import admin
from django.urls import path, include
from django.conf import settings

# Regular URL patterns
urlpatterns = [
    path('admin/', admin.site.urls),
    path('api/member/', include('Member.urls')),
    path('api/book/', include('Book.urls')),
    path('api/issue/', include('Issue.urls')),
]
if settings.DEBUG:
    urlpatterns += debug_toolbar_urls()