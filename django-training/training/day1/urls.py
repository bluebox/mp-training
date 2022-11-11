from django.urls import path,register_converter, re_path
from day1 import views, converters
register_converter(converters.customconverter, "custom")
urlpatterns = [
    # default url
    path('', views.default),

    # url with parameters.
    path("withparam/<parameter>", views.withparams),
    path("withparam2/<int:parameter1>/<int:parameter2>", views.withparams2),
    path("withparam3/<slug:parameter>", views.withparams3),
    path("withparam4/<uuid:parameter>", views.withparams4),

    # custom converters
    path("custom/<custom:param>", views.custom),

    # regex converters
    re_path(r'^regex/(?P<parameter>[A-Z]{2})/$', views.regex),

    # nested arguments
    re_path(r'^comments/(?:page-(?P<parameter>[0-9]+)/)?$', views.nest),  # good
    # extra options
    path('extra/<parameter>', views.extra, {'name': 'nikita'})



]