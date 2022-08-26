from django.contrib import admin
from django.urls import path, include,register_converter,re_path
from . import views
from . import converter

register_converter(converter.UserNameConverter,'username')



urlpatterns = [

    # without parameters
    path('default', views.defaultview,name='default'),

    # with default string parameter
    path('one_param/<parameter>',views.one_parameter,name="one_param"),

    # with int parameter
    path('int_param/<int:parameter>',views.int_parameter,name="int_param"),

    # with multiple parameters
    path('multi_param/<a>/<b>/<c>',views.multi_paramter,name="multi_param"),

    # with converter username , regex
    path('username/<username:parameter>',views.user_converter,name="user_converter"),

    # with re_path : regex pattern in url
    re_path(r'^regex_path/(?P<parameter>[0-9]{3})$',views.regex_in_url,name='re_path'),


    # with re_path
    # [\w-]+ means word characters,numbers,underscores, - for hyphen , + for repititions
    re_path(r'^articles/(?P<yyyy>[0-9]{4})/(?P<mm>[0-9]{2})/(?P<slug>[\w-]+)/$',views.articles,name='articles'),

    # with options

    path('blog/<int:year>/', views.year_archive, {'foo': 'bar'}),

    re_path(r'^blog/(page-([0-9]+)/)?$', views.blog_articles,name='bad_nested'),  # bad
    re_path(r'^comments/(?:page-(?P<page_number>[0-9]+)/)?$', views.blog_articles,name="good_nested"),  # good
]