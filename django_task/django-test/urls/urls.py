from django.contrib import admin
from django.urls import path,register_converter, re_path
from . import  views,convertors
register_converter(convertors.UserNameConverter, 'username')
register_converter(convertors.PanCardConverter, 'pancard')

urlpatterns = [
    # default page
    path('', views.homepage,name="homepage"),

    # Parameters
    path('param/<param1>', views.param1, name="paramone"),
    path('param/<param1>/<param2>', views.param2, name="paramtwo"),

    #urls with convertors
    path('params/<int:para1>', views.paramint, name="paramone"),
    path('params/<int:para1>/<str:para2>', views.paramstr, name="paramone"),

    #urls with slug and uuid
    path("uuid-parameter/<uuid:param>", views.uuidparameter, name="uuidparameter"),
    path("slug-parameter/<slug:param>", views.slugparameter, name="slugparameter"),

    # urls with custom convetors
    path("username/<username:param>", views.uuidparameter, name="uuidparameter"),
    path("pancard/<pancard:param>", views.slugparameter, name="slugparameter"),

    #re_path
    re_path(r'^regex-path/(?P<param>[0-9]{4})$', views.re_path, name="re_path"),

    #extra parameter
    path("pancardextra/<pancard:param>", views.pancardextra, {"userid":10},name="slugparameter"),

]
