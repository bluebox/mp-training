from django.urls import path, register_converter, re_path
from . views import index, home, parameter1, int_parameter, nested_int_parameter,home1,base, regex_parameter,comments, repath, nested_repath
from .converter1 import myconverter
register_converter(myconverter, 'shyam')
urlpatterns = [
    path('', index),
    path('home', home, name='home_url'),
    path('parameter/<parameter>', parameter1),
    path('int_parameter/<int:parameter>', int_parameter),
    path('nested_int_parameter/<int:parameter1>/<int:parameter2>/<int:parameter3>', nested_int_parameter),
    path('regex_parameter/<shyam:parameter>', regex_parameter),
    re_path(r'^repath/(?P<name>[0-9]{4}[a-z]{2})/$', repath),
    re_path('nested_repath/(?P<name1>[0-9]{4})/(?P<name2>[0-9]{2})/(?P<name3>[0-9]{2})', nested_repath),
    re_path(r'nested_comments/(?:Rama-(?P<number>[0-9]+)/)?$', comments),
    # templates
    path('home1/', home1, name='home1_url'),
    path('base/', base, name='base_url'),



    ]



