from django.contrib import admin
from django.urls import path, register_converter, re_path, include
from . import views, converter
register_converter(converter.UserNameConverter, 'username')
urlpatterns = [
    #two parameter
    path("two-parameter/<parameter1>/<parameter2>", views.twoparameter, name="twoparameter"),

    #one-parameter and nested url
    path("one-parameter/<parameter1>", views.oneparameter, name="oneparameter"),

    #int-parameter
    path("int-parameter/<int:parameter>", views.intparameter, name="intparameter"),

    #uuid-parameter
    path("uuid-parameter/<uuid:parameter>", views.uuidparameter, name="uuidparameter"),

    #slug-parameter
    path("slug-parameter/<slug:parameter>", views.slugparameter, name="slugparameter"),
    #converter parameter
    path("cparameter/<username:parameter>", views.slugparameter, name="slugparameter"),

    #regex-path
    re_path(r'^regex-path/(?P<parameter>[0-9]{4})$', views.slugparameter, name="slugparameter"),

    # passing-extra argumnets
    # The path() function can take an optional third argument which should be a
    # dictionary of extra keyword arguments to pass to the view function.
    path("one-parameter/<parameter1>", views.oneparameter, {'user_id': 3}, name="oneparameter"),

]
