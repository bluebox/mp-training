from django.contrib.auth.models import User
from rest_framework import status
from rest_framework.permissions import IsAuthenticated
from rest_framework.response import Response
from rest_framework.views import APIView

from .businessLogic.manager import getCustomerDetails, addCustomer, updateCustomer
from granite_mart.views import verifyToken


# Create your views here.

class CustomerAPI(APIView):
    def get(self, request, username=None, format=None):
        token = request.COOKIES.get('token')
        refresh = request.COOKIES.get('refresh')
        valid = verifyToken(token, refresh, request)
        print("current user is: ", request.user)
        if valid == '200':
            return Response(getCustomerDetails(username=username), status.HTTP_200_OK)
        else:
            raise Exception('user is not authorized')

    def post(self, request, format=None):

        response = addCustomer(request)
        return Response(response)

    def put(self, request, username=None, format=None):
        token = request.COOKIES.get('token')
        refresh = request.COOKIES.get('refresh')
        valid = verifyToken(token, refresh, request)
        if valid == '200':
            response = updateCustomer(request, username)
            return Response(response)
        else:
            return Response(" an error occured")

    def delete(self, request, formate=None, username=None):
        token = request.COOKIES.get('token')
        refresh = request.COOKIES.get('refresh')
        valid = verifyToken(token, refresh, request)

        if valid == '200':
            user = User.objects.get(username=username)
            user.delete()
            return Response('deleted')
