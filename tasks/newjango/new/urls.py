from django.urls import path, register_converter, re_path
from . import views, converter
register_converter(converter.Convertion, 'ID')
urlpatterns = [
    path('', views.calling, name='calling'),
    path('one_para/<para>', views.one_para, name = 'one_para'),
    path('two_para/<para>/<para1>', views.two_para, name = 'two_para'),
    path('int_para/<int:para>', views.int_para, name = 'int_para'),
    path('slug_para/<slug:para>', views.slug_para, name = 'slug_para'),
    path('uuid_para/<uuid:para>', views.uuid_para, name = 'uuid_para'),
    path('id_validation/<ID:para>', views.id_validation, name = 'id_validation'),
    re_path(r'^nested/(?P<para2>[0-9]{4})/$', views.nested),
    # re_path(r'^passing_objects/(?P<para>[0-9]{4})/$', views.passing_objects, {"name":"shyam", "age":"23"}),
    path('passing_objects/', views.passing_objects, {"Name":"shyam", "age":"23"}),

]