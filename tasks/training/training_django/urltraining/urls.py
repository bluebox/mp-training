from django.urls import path,re_path
from . import views
urlpatterns=[
    path('', views.home, name='home'), # default url
    path('parameter/<param>', views.parameter, name='param'), # url with parameter
    path('parameter_int/<int:param>', views.parameter_int, name='param_int'), # url with parameter converter integer
    path('parameter_str/<str:param>', views.parameter_str, name='param_str'), # url with parameter converter string
    path('parameter_slug/<slug:param>', views.parameter_slug, name='param_slug'), # url with parameter converter slug
    path('parameter_uuid/<uuid:param>', views.parameter_uuid, name='param_uuid'), # url with parameter converter uuid
    re_path(r'^parameter_reg/(?P<param>[A-Z]{3}[0-9]{4})$', views.parameter_reg, name='param_reg'), # url with parameter and regular expression
    re_path(r'^parameter_regnest/(?:page-(?P<param>[0-9]{4})/)$', views.parameter_regnest, name='param_regnest'), # url with parameter nested regular expression
]