from django.urls import path, register_converter, re_path
from . import views

from . import converter

register_converter(converter.RollNumber, 'c_id')
urlpatterns = [
    # with no parameter
    path('', views.no_parameter, name='home'),
    # with int as path converter
    path('intparameter/<int:parameter>', views.int_parameter, name='cparameter'),
    # with srt as path converter
    path('strparameter/<str:parameter>', views.str_parameter, name='cparameter'),
    # with slug as path converter
    path('slugparameter/<slug:parameter>', views.slug_parameter, name='cparameter'),
    # with uuid as path converter
    # import uuid
    # print(uuid.uuid4())
    path('uuidparameter/<uuid:parameter>', views.uuid_parameter, name='cparameter'),
    # with 2 parameters
    path('2parameter/<int:parameter>/<str:parameter1>', views.two_parameter, name='2parameters'),
    #with re path and regex function in path itself
    re_path(r'^rpath/(?P<parameter>[0-9]{4})/$',views.r_parameter, name='rpath'),
    #with custom path converter
    path('cparameter/<c_id:parameter>', views.converter_parameter, name='cparameter'),
    #nested arguments
    re_path(r'^nested/(?:args-(?P<parameter>[0-9]+)/)?$', views.nested_arguments, name='nestedarguments'),
    # Passing extra options to view functions
    path('extra-args/',views.extra_args,  {'name': "Harsha", "place": "hyderabad"},name='extra-args'),
    path('extra-args1/',views.extra_args,  {'name': "sree", "place": "vizag"}, name='extra-args'),

]

