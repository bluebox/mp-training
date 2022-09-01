from django.contrib import admin
from django.urls import path,register_converter,re_path
from urls import views,converters
register_converter(converters.custompara,'xyz')
urlpatterns = [
    path('',views.index,name="index"),
    path('int/<path:parameter>/',views.inte,name="integer"), #accepts '/'
    path('int/<parameter>/<parameter2>/',views.intex,name="intex"), #cascading
    path('regix/<xyz:parameter>',views.custompara,name="custompara"),#custom_converters
    re_path(r'^re/(?P<empid>[a-z]{4})/$',views.repath),#repath
    re_path(r'comments/(?:page-(?P<page_number>[0-9]+)/)?$',views.nested), #nested-arguments
    path('blog',views.test,{'name':'shashi'}),#passing parameters
    path('kwargs',views.kwaargs,{'one':'okay', 'two':'okayok'})

    ]
