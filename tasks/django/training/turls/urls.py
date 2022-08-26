from django.urls import path,re_path, register_converter
from . import views, converter

register_converter(converter.RegexParam, "name")

urlpatterns=[
    path("", views.index, name="index"),
    path("<argument>", views.one_param, name="one_parameter"),
    path("<arg1>/<int:arg2>", views.two_param, name="two_parameter"),
    path("slug/<slug:slug>", views.slug_param, name="slug_parameter"),
    re_path(r'^repath/(?P<reg>[A-Z]{5}[0-9]{3}$)', views.regex_param,name="regex_param"),
    path("regex_converter/<name:reg1>/", views.regcon, name="regex_converter"),
    path("extraoptions/<arg1>",views.ExtraOptions,{"arg2":"arg"},name="extra_options"),
    re_path(r'^nest/(?:page-(?P<arg1>[A-Z]{5}[0-9]{3}))$', views.regex_nest_param, name="regex_nest_param"),

]