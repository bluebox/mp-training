from django.urls import path,register_converter, re_path
from . import  views

urlpatterns = [
    # default page
    path('',views.homepage,name='homepage'),
    path('/second',views.secondpage,name='secondpage')

]
