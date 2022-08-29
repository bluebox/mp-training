from django.urls import path
from . import views
urlpatterns = [
    path('path1/',views.index,name='index'),

    path('path2/',views.temp1,name='temp1'),

    path('path3/',views.temp2,name = 'temp2'),

    path('path4/',views.temp3,name = 'temp3'),

    path('path5/',views.temp4,name = 'temp4')
]