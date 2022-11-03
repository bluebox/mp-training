from rest_framework.authentication import BaseAuthentication
from django.contrib.auth.models import User
from rest_framework import exceptions
class CustomAuthentication(BaseAuthentication):
    def authenticate(self, request):
        username = request.GET.get('username')
        if not username:
            return None

        try:
            user = User.objects.get(username=username)
        except User.DoesNotExist:
            raise exceptions.AuthenticationFailed('No such user')

        return (user, None)