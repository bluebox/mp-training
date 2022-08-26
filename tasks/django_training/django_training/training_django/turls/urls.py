from django.contrib import admin
from django.urls import path, register_converter, re_path
from . import views, convertor

register_converter(convertor.UserNameConverter, 'uname')
urlpatterns = [
    path('', views.calls, name="callsme"),
    path('intparam/<int:param1>', views.callintparam, name='callintparam'),

    path('strparam/<str:param1>', views.callstrparam, name='callstrparam'),
    path('pathparam/<path:param1>', views.callpathparam, name='callstrparam'),
    # convertor
    path('cparam/<uname:param1>', views.convertor_param, name='convertor_param'),
    path('slagparam/<slug:param1>', views.slagintparam, name='slagintparam'),
    re_path(r'^rpathe/(?P<rpath>[A-Z]{3})$', views.callrpath),
    path('gudhu',views.callgudhu,{'sashi':'gattiga'}),

]
