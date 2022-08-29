from django.contrib import admin
from django.urls import path,include, register_converter, re_path
from . import views, converter


register_converter(converter.UserConverter, 'username')

urlpatterns = [

    path('', views.par1, name='par1'),
    path('par2/<uuid:parameter>/', views.par2, name='par2'),
    path('par3/<int:parameter1>/<slug:parameter2>', views.par3, name='par3'),
    path('par4/<slug:parameter>/', views.par4, name='par4'),
    path('par5/<username:parameter>/', views.par5, name='par5'),
    re_path(r'^par6/(?P<parameter>[0-9]{4})/$', views.par6, name='par6'),
    path('par7/', views.par7, {'parameter': 'Ok'}, name='par7'),

]