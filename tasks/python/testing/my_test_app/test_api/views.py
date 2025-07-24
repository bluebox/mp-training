from rest_framework import viewsets
from .models import ModelUser
from .serializers import UserSerializer


class Users(viewsets.ModelViewSet):
    model = ModelUser
    serializer_class = UserSerializer
    queryset = ModelUser.objects.all()
