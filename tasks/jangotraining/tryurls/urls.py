from django.urls import path,register_converter,re_path

from . import views
from .converter import Validate
register_converter(Validate,'val')

urlpatterns = [
    path('noparams/', views.noparams),
    path('oneparams/', views.noparams),
    path('intparams/', views.noparams),
    path('oneparams/<params>', views.one_param),
    path('twoparams/<param1>/<param2>',views.two_param),
    path('intparam/<int:params>', views.int_param),
    path('validate/<uuid:params>', views.validate),
    re_path(r'^temp/(?P<params>[0-9]{5})/$', views.validate),
    re_path(r'^pages/(?:page-(?P<params>[0-9]+)/)?$',views.validate),
    path('', views.home),
]






