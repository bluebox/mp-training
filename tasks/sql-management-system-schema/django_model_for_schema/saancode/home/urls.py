from django.urls import path
from .views import *

urlpatterns = [
    path('', homeView, name='home'),
    path('problems/', problems, name='problems'),
    path('discuss/', discuss, name='discuss'),
    path('notifications/', notifications, name='notifications'),
    path('streak/', streak, name='streak'),
    path('account/', account, name='account'),
    path('signin/', signin, name='signin'),
]