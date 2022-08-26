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
from django.urls import path, include, register_converter, re_path
from task1 import views
from task1 import converter
from django.http import HttpResponse

register_converter(converter.Username, 'uname')


def comments(request, page1):
    return HttpResponse("This is " + str(page1))


def home(request):
    return HttpResponse("Home page")


def one_para(request, p):
    return HttpResponse("The argument is " + str(p))


urlpatterns = [
    path('admin/', admin.site.urls),

    path("", home),

    # one parameter
    path("<p>", one_para),

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

    # nested arguments
    re_path(r'^comments/(?:page-(?P<page1>[0-9]+)/)?$', comments),
]
