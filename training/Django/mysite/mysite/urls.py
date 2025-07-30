from django.contrib import admin
from . import views
from . import settings
from django.urls import path, include
app_name = 'blog'
urlpatterns = [

    path('admin/', admin.site.urls),
    # path(app_name,include(app_name+'.urls')),
    path('blog/', include('blog.urls')),
    path('Web_World/', include('Web_World.urls'),),
    path('bookStore/', include('bookStore.urls'),),
    path("",views.welcome,name='index'),
    # path('explore/', views.explore_view, name='explore'),
    # include app's URLs
]
if settings.DEBUG:
    import debug_toolbar
    urlpatterns += [
        path('__debug__/', include(debug_toolbar.urls)),
    ]