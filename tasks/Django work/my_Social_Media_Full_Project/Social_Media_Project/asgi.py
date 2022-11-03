"""
ASGI config for Social_Media_Project project.

It exposes the ASGI callable as a module-level variable named ``application``.

For more information on this file, see
https://docs.djangoproject.com/en/4.1/howto/deployment/asgi/
"""

import os

from channels.auth import AuthMiddlewareStack
from django.core.asgi import get_asgi_application
from channels.routing import ProtocolTypeRouter, URLRouter
from Social_Media_App import router
from channels.security.websocket import AllowedHostsOriginValidator
os.environ.setdefault('DJANGO_SETTINGS_MODULE', 'Social_Media_Project.settings')

application = ProtocolTypeRouter(
    {
        'http':get_asgi_application(),
        
        "websocket": AllowedHostsOriginValidator(
            AuthMiddlewareStack(URLRouter( router.websocket_urlpatterns))
        ),
    }
)