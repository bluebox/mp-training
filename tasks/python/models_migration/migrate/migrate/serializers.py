from rest_framework_simplejwt.serializers import TokenObtainPairSerializer
from typing import Dict, Any


class CustomAuthSerializer(TokenObtainPairSerializer):
    def validate(self, attrs: Dict[str, Any]) -> Dict[str, str]:
        data = super().validate(attrs)
        data['role'] = self.user.role
        data['id'] = self.user.id
        data['user_name'] = self.user.username
        return data