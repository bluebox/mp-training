from django.urls import path, register_converter, re_path
from . import views


# register_converter(converter.FourDigitYearConverter, 'yyyy')


urlpatterns = {
    path('',views.firsttemp,name="temp"),
    path('temp2/<int:id>/<str:s>/<slug:sl>/', views.temp2, name='temp2'),
    path('temp3', views.temp3, name='temp3')
    }
