from django.urls import path
from . import views
urlpatterns=[
    path('', views.home, name='home'),
    path('for', views.fortemp, name='fortemp'),
    path('if', views.iftemp, name='iftemp'),
]