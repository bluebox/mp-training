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
from django.http import HttpResponse

def comments(request, page1):
    return HttpResponse("This is " + str(page1))


def home(request):
    return HttpResponse("Home page")


def one_para(request, p):
    return HttpResponse("The argument is " + str(p))


def new(request, year, month):
    return HttpResponse(year+"0"+month )

def new1(request, year, month):
    return HttpResponse(year+"1"+month )


urlpatterns = [
    path('admin/', admin.site.urls),

    path("", home),

    # task1_app urls
    path("task1/", include("task1.urls")),

    # task2_app urls
    path("task2/", include('task2.urls')),

    path("va/", include('viewapp.urls')),

    path("apps/", include('modelapp.urls')),

    # one parameter
    # path("<p>", one_para),

    # nested arguments
    re_path(r'^comments/(?:page-(?P<page1>[0-9]+)/)?$', comments),

    re_path(r'^confirm-year/(?P<year>[0-9]{4})/(?:0(?P<month>[1-9]{1})/)?$', new),
    re_path(r'^confirm-year/(?P<year>[0-9]{4})/(?:1(?P<month>[0-2]{1})/)?$', new1),

]
