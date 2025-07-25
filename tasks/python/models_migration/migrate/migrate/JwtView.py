from rest_framework_simplejwt.views import TokenObtainPairView
from .serializers import CustomAuthSerializer

class CustomAuthView(TokenObtainPairView):
    serializer_class = CustomAuthSerializer