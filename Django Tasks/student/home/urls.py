from django.contrib import admin
from django.urls import path, register_converter, re_path
from . import views, converter
register_converter(converter.UserNameConverter, 'username')
urlpatterns = [

    path('', views.parameter, name="parameter"),
    path("two-parameter/<parameter1>/<parameter2>", views.twoparameter, name="twoparameter"),
    # path("one-parameter/<parameter3>", views.oneparameter, name="oneparameter"),
    path("one-parameter/<parameter3>", views.oneparameter1,{"p_id":10}, name="oneparameter"),
    path("int-parameter/<int:parameter>", views.intparameter, name="intparameter"),
    path("uuid-parameter/<uuid:parameter>", views.uuidparameter, name="uuidparameter"),
    path("slug-parameter/<slug:parameter>", views.slugparameter, name="slugparameter"),
    path("cparameter/<username:parameter>", views.slugparameter, name="slugparameter"),
    re_path(r'^regex-path/(?P<parameter>[0-9]{4})$', views.slugparameter, name="slugparameter")


]