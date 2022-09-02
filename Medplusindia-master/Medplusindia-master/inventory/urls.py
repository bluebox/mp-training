from django.urls import path
from inventory.views import *

urlpatterns=[
    path('',index,name='index'),
    path('wish',wish,name="wish"),
]