from django.urls import path
from . import views
urlpatterns = [
    path('path1/',views.index,name='index'),

    path('path2/',views.temp1,name='temp1'),
]