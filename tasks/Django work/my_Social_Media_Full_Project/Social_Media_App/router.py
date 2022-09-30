from django.urls import path

from .consumer import Mysync1, Myasync
from messageapp.consumers import ChatConsumer
websocket_urlpatterns = [
    path('ws/as/1', Myasync.as_asgi()),
    path('ws/as/', ChatConsumer.as_asgi()),
    path('ws/sc/<str:grp>', Mysync1.as_asgi()),
]
   