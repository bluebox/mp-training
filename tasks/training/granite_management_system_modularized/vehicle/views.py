from django.shortcuts import render
from rest_framework.response import Response
from rest_framework.views import APIView

from .models import Vehicle
from .serializers import vehicleSerializer
from granite_mart.views import verifyToken


class VehicleAPI(APIView):
    def get(self, request, format=None, vehicle_no=None):
        token = request.COOKIES.get('token')
        refresh = request.COOKIES.get('refresh')
        valid = verifyToken(token, refresh, request)
        if valid == '200':
            if vehicle_no is None:
                vehicle = Vehicle.objects.all()
                serialize = vehicleSerializer(vehicle, many=True)
                return Response(serialize.data)
            else:
                vehicle = Vehicle.objects.get(vehicle_no=vehicle_no)
                serialize = vehicleSerializer(vehicle)
                return Response(serialize.data)

    def post(self, request, format=None):
        token = request.COOKIES.get('token')
        refresh = request.COOKIES.get('refresh')
        valid = verifyToken(token, refresh, request)
        if valid == '200':

            serializer = vehicleSerializer(data=request.data)
            if serializer.is_valid():
                vehicle_no = request.data['vehicle_no']
                model = request.data['model']
                owner_name = request.data['owner_name']
                permit_range = request.data['permit_range']
                fuel_efficiency = request.data['fuel_efficiency']
                load_capacity = request.data['load_capacity']
                vehicle = Vehicle(vehicle_no=vehicle_no, model=model, owner_name=owner_name, permit_range=permit_range,
                                  fuel_efficiency=fuel_efficiency, load_capacity=load_capacity)
                vehicle.save()
                return Response(vehicleSerializer(vehicle).data)
            return Response('failed')

    def put(self, request, format=None, vehicle_no=None):
        token = request.COOKIES.get('token')
        refresh = request.COOKIES.get('refresh')
        valid = verifyToken(token, refresh, request)
        if valid == '200':

            try:
                model = request.data['model']
                owner_name = request.data['owner_name']
                permit_range = request.data['permit_range']
                fuel_efficiency = request.data['fuel_efficiency']
                load_capacity = request.data['load_capacity']
                print(vehicle_no, model, owner_name, permit_range, fuel_efficiency, load_capacity)
                print(Vehicle.objects.get(vehicle_no=vehicle_no))

                vehicle = Vehicle(id=Vehicle.objects.get(vehicle_no=vehicle_no),model=model,
                                  owner_name=owner_name,permit_range=permit_range,fuel_efficiency=fuel_efficiency,load_capacity=load_capacity)
                print(vehicle)
                vehicle.save()
                print(vehicle)
                return Response(vehicleSerializer(vehicle).data)
            except:
                return Response('failed')

    def delete(self, request, vehicle_no):
        token = request.COOKIES.get('token')
        refresh = request.COOKIES.get('refresh')
        valid = verifyToken(token, refresh, request)
        if valid == '200':

            vehicle = Vehicle.objects.get(vehicle_no=vehicle_no)
            vehicle.delete()
            return Response(vehicleSerializer(vehicle).data)
