from django.\
    urls import path, register_converter, re_path
from .import views, converter
register_converter(converter.UserConverter, 'user_con')

urlpatterns = [
    # default
    path('', views.without_param, name='home'),
    # parameter
    path('with_param/<int:param>/', views.with_one_param, name='with_param'),
    # slug
    path('with_slug/<slug:param>/', views.with_slug, name='with_slug'),
    # uuid
    path('with_uuid/<uuid:param>/', views.with_uuid, name="with_uuid"),

    # converters
    path('with_con/<user_con:param>/', views.with_con, name="with_con"),

    # repath
    re_path(r'^regex-path/(?P<parameter>[0-9]{4})/$', views.with_repath, name='regex-path'),
    re_path(r'^regex-path/(?P<parameter>[A-Z]{3})/$', views.with_repath, name='regex-path'),

    # nested arguments
    re_path(r'^comments/(?:page-(?P<parameter>[0-9]+)/)?$', views.with_repath, name='comments'),

    re_path(r'^example/(?:num-(?P<parameter>[A-Z]+)/)?$', views.with_repath, name='example'),


    # extra options to view functions
    path('extra/<slug:param>/', views.with_extra, name='extra'),

  ]