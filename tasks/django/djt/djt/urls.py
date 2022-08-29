"""djt URL Configuration

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/4.1/topics/http/urls/
Examples:
Function views
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  path('', views.home, name='home')
Class-based views
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  path('', Home.as_view(), name='home')
Including another URLconf
    1. Import the include() function: from django.urls import include, path
    2. Add a URL to urlpatterns:  path('blog/', include('blog.urls'))
"""
from django.contrib import admin
from django.urls import path,include,register_converter, re_path
from page1 import views
from . import converter
register_converter(converter.UserName, 'conv')
urlpatterns = [
    path('admin/', admin.site.urls),
    path("ind1/<month>", views.index, name='first'),
    path("ind2/<int:month>", views.index2, name='second'),
    path("ind3/<slug:month>", views.index3, name='third'),
    path("ind4/", include("page1.urls")),
    path("ind5/<conv:month>", views.index4),
    re_path(r'^ind6/(?P<month>[0-9]{4})/$', views.index3, name='par6'),
    re_path(r'^ind7/(?:index-(?P<month>[0-9]{4}))/$', views.index3, name='par6'),
    path("ind8/", include("page2.urls")),
]
