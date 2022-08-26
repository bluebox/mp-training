from django.contrib import admin
from django.urls import path, register_converter,re_path
from . import views, Converter
register_converter(Converter.UserName, 'ID')
urlpatterns = [
    path('', views.no_param, name='no_param'),
    path('one_param/<para>', views.one_param, name='one_param'),
    path('int_param/<int:para>', views.one_param, name='int_param'),
    path('two_param/<para>/<para1>', views.two_param, name='two_param'),
    path('slug_param/<slug:para>', views.slug_param, name='slug_param'),
    path('uuid_param/<uuid:para>', views.uuid_param, name='uuid_param'),
    path('id_validation/<ID:para>', views.id_validation, name='id_validation'),

    #nested argument
    re_path(r'^nested/(?:Rama-(?P<para2>[0-9]{5})/)?$', views.nested, name='nested'),

    #extra option
    path('blog/<int:year>/', views.year_archive, {'y': 'year', 'm': 'month'}),

    #regex converter
    re_path(r'regex_path/(?P<para>[0-9]{4}-[0-1]{1}[0-9]{1}-[0-9]{2})', views.re_path, name='re_path')
]


#'[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}'