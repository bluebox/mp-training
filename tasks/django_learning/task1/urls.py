"""djangotask URL Configuration

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
from . import views
from django.urls import path, include, register_converter, re_path
from . import converter


register_converter(converter.Username, 'uname')


urlpatterns = [
    path('admin/', admin.site.urls),
    # path('home/<para>', views.home, name="home")

    # integer parameter
    path('int_para/<int:a>', views.int_para),

    # string parameter
    path('str_para/<str:sa>', views.str_para),

    # multi arguments
    path('home/<para>/<arg>', views.home, name="home"),

    # user_converter argument type
    path('name1/<uname:nm>', views.name1, name="name"),

    # regex repath argument
    re_path(r'^confirm-year/(?P<year>[0-9]{4})$', views.confirm_year, name="confirm_year"),

]

