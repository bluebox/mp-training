from rest_framework.decorators import api_view
from rest_framework.response import Response
from rest_framework.views import APIView

from .businessLogic.manager import tokenVerification, authenticate1, logout
from .models import *
from .serializers import roleSerializer
@api_view(['GET'])
def getRoles(request):
    roles = Role.objects.all()
    serialize = roleSerializer(roles, many=True)
    return Response(serialize.data)

def verifyToken(token, refresh, request):
    response = tokenVerification(token, refresh)
    return response


class customerLogin(APIView):
    def post(self, request, format=None):
        response = authenticate1(request)
        print(response)
        return response

@api_view(['GET'])
def logout1(request):
    response = logout(request)
    return response
