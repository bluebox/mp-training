from django.urls import path
from .views import *
urlpatterns=[
    # path("home/", home, name='home'),
    path("login/", login, name='login'),
    path("login1/", login1, name='login1'),
    path("", signup, name="signup")

]