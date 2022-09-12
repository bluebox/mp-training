from django.urls import path

from .consumer import Mysync1, Myasync

websocket_urlpatterns = [
    path('ws/as/', Myasync.as_asgi()),
    path('ws/sc/', Mysync1.as_asgi())
]