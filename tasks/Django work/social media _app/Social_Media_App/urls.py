from django.urls import path
from .views import *

urlpatterns = [
    path('', home),
    path('signup', signup, name='signup'),
    path('signin', signin, name='signin'),
    path('signout', signout, name='signout'),
    path('settings', settings, name='settings'),
    path('like_post', like_post, name='like-posts'),
    path('follow', follow, name='follow'),
    path('search',search, name='search'),
    path('example/', example, name='example'),
    path('example1', example1, name='example1'),
    path('profile/<str:pk>', profile, name='profile'),
    path('upload', upload_post, name='upload_post'),
    path('activate/(?P<uidb64>[0-9A-Za-z_\-]+)/(?P<token>[0-9A-Za-z]{1,13}-[0-9A-Za-z]{1,20})/',  
        activate, name='activate'),  
    path("channel", channel, name='channel'),

]
