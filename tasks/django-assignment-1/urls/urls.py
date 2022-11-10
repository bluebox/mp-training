from django.contrib import admin
from django.urls import path, register_converter, re_path
from urls import views, converters

register_converter(converters.CustomParameter, 'parameter')

urlpatterns = [
    path('', views.index, name="index"),

    path('str/<parameter>/', views.default_converter, name="default"), # defau;t parameter
    
    path('int/<int:parameter>/', views.int_converter, name="integer"),  # accepts '/'
    
    path('int/<int:parameter1>/<int:parameter2>/',
         views.int_two_parameter_converter, name="int_two_parameter_converter"),  # cascading

    path('regex/<parameter:parameter>/', views.customParameter,
         name="customerParameter"),  # custom_converters

    re_path(r'^re/(?P<parameter>[A-Z]{3}[0-9]{6})/$', views.repath),  # repath

    # nested-arguments
    re_path(r'comments/(?:page-(?P<parameter>[0-9]+)/)?$', views.nested),

    path('blog', views.test, {'parameter': 'This is parameter'})  # passing parameters

]
