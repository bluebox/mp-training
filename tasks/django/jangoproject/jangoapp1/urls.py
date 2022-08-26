from django.contrib import admin
from django.urls import path,register_converter,re_path
from . import views,converters
register_converter(converters.MyConverter,'myname')

urlpatterns = [
    path('',views.index,name='index'),

    path('para1/<param>',views.para1,name='para1'),

    path('paraint/<int:integer>',views.paraint,name='paraint'),

    path('paraslug/<slug:slug>',views.paraslug,name='paraslug'),

    path('uuid/<uuid:param>',views.para1,name='uuid'),

    path('custom/<myname:cust>',views.custom,name='custom'),

    re_path(r'^regex_path/(?P<parameter>[0-9]{4})/$',views.regex_path,name='regex_path'),

    re_path(r'^ondate/(?P<paraa>[0-9]{4})/$',views.ondate,name='ondate'),

    path('nestedarg/<int:sid>/<name>/<slug:branch>',views.nestedarg,name='nestedarg') ,

    path('option/<int:number>',views.option,{'number':2}),

]