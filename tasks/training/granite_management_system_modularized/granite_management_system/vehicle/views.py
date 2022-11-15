from django.shortcuts import render
from rest_framework.response import Response
from rest_framework.views import APIView

from .businessLogic.manager import getVehicle, addVehicle, updateVehicle
from .models import Vehicle
from .serializers import vehicleSerializer
from granite_mart.views import verifyToken


class VehicleAPI(APIView):
    def get(self, request, format=None, vehicle_no=None):
        token = request.COOKIES.get('token')
        refresh = request.COOKIES.get('refresh')
        valid = verifyToken(token, refresh, request)

        if valid == '200':
            response = getVehicle(vehicle_no)
            return Response(response)
        return Response("Authenticaion Failed")

    def post(self, request, format=None):
        token = request.COOKIES.get('token')
        refresh = request.COOKIES.get('refresh')
        valid = verifyToken(token, refresh, request)

        if valid == '200':
            response = addVehicle(request)
            return Response(response)
        return Response('Authentication Failed')

    def put(self, request, format=None, vehicle_no=None):
        token = request.COOKIES.get('token')
        refresh = request.COOKIES.get('refresh')
        valid = verifyToken(token, refresh, request)

        if valid == '200':
            response = updateVehicle(request, vehicle_no)
            return Response(response)
        return Response("Authentication Failed")

    def delete(self, request, vehicle_no):
        token = request.COOKIES.get('token')
        refresh = request.COOKIES.get('refresh')
        valid = verifyToken(token, refresh, request)

        if valid == '200':
            vehicle = Vehicle.objects.get(vehicle_no=vehicle_no)
            vehicle.delete()
            return Response(vehicleSerializer(vehicle).data)
