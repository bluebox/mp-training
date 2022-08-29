from django.urls import path, register_converter, re_path
from . import views, converters

register_converter(converters.UsernamePathConverter, 'username')

urlpatterns = [

    path('', views.o_parameter, name='o_parameter'),
    path('one/<a_parameter>', views.one_parameter, name='one_parameter'),
    path('articles/<username:uname>', views.user_detail, name="user_detail"),
    path('slug/<slug:slug>/', views.sludge, name='sludge'),
    path('uu/<uuid:id>/', views.uu, name='uu'),
    re_path(r'^index/$', views.index, name='index'),

]
