

from django.urls import path
from . import views
from view_s.views import ViewClass

urlpatterns = [


    path('function', views.function, name='fun'),
    path('class',  ViewClass.as_view(), name='class'),
]