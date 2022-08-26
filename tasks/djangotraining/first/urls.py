from django.contrib import admin
from django.urls import path, re_path, register_converter
from . import views
from .converter import UserConverter
register_converter(UserConverter, 'username')

urlpatterns = [
    # passing no parameters
    path('', views.no_parameter, name='no_parameter'),
    # passing one parameter without converters
    # by default it will be taken as string
    path('one_parameter/<params>', views.one_parameter, name='one_parameter'),
    # passing multiple parameters
    path('multiple_parameter/<params>/<param1>', views.multiple_parameter, name='multiple_parameter'),

    # passing parameter with int converter
    # only int parameters are accepted
    path('int_parameter/<int:params>', views.int_parameter, name='int_parameter'),
    # customized converter params
    path('custom_parameter/<username:params>', views.custom_parameter, name='custom_parameter'),
    # using regex in url directly using re_path
    re_path(r'^re_path_parameter/(?P<params>[aeiou]{2}[0-9]{2})/$', views.re_path_parameter, name='re_path_parameter'),
    # passing uuid parameter
    path('uuid_parameter/<uuid:params>', views.uuid_parameter, name='uuid_parameter'),
    #passing slug parameter
    path('slug_parameter/<slug:params>', views.slug_parameter, name='slug_parameter'),
]