from django.urls import path
from .views import *

urlpatterns = [
    path('',base, name='home'),
    path('variables/',variables, name='variables'),
    path('tags/',tags, name='tags'),
    path('filters/',filters, name='filters')
]