from django.urls import path, register_converter, re_path
from . import views, converter

register_converter(converter.FourDigitYearConverter, 'yyyy')


urlpatterns = {
    path('', views.home, name='home'),
    path('cart/<int:id>/<str:s>/<slug:sl>/<uuid:ui>/<n>', views.login2, name='login2'),
    # path('articles/<yyyy:year>', views.year_archive, name='year_archive'),
    re_path(r'^articles/(?P<year>[0-9]{4})/$', views.year_archive),
    re_path(r'^articles/(?P<year>[0-9]{4})/(?P<month>[0-9]{2})/$', views.month_archive),
    re_path(r'^articles/(?P<year>[0-9]{4})/(?P<month>[0-9]{2})/(?P<slug>[\w-]{2})/$', views.article_detail),
    re_path(r'^comments/(?:danush-(?P<page_number>[0-9]+)/)?$', views.comments),
        path('blog/<int:year>/', views.year_archive, {'t': 's', 'g': 'p','color':'red'}),
    }
