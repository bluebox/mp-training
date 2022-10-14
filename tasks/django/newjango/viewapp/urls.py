from django.urls import path
from . import views

urlpatterns = [
    path('func', views.viewFunction, name='viewFunction'),
    path('dec_func', views.decorator_func, name = 'decorator_func'),
    path('class_func', views.ClassBasedView.as_view(), name = 'ClassBasedView')
]