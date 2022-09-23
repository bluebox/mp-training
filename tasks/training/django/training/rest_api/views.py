from cmath import log
from django.shortcuts import render
from django.http import HttpResponse, JsonResponse
from .serializers import AppointmentSerializer
from .models import Appointment
from rest_framework.response import Response
from rest_framework import status
from rest_framework.decorators import api_view

from rest_api import serializers

# Create your views here.


@api_view(['GET', 'POST'])
def appointment(request):
    if request.method == 'GET':
        apmts = Appointment.objects.all()
        # print(apmts)
        serialised_apmts = AppointmentSerializer(apmts, many=True)
        print(serialised_apmts.data[0])
        return JsonResponse(serialised_apmts.data, safe=False)

    if request.method == 'POST':
        serialised_data = AppointmentSerializer(data=request.data)
        print(serialised_data)
        if serialised_data.is_valid():
            serialised_data.save()
        return Response(serialised_data.data, status=status.HTTP_201_CREATED)


@api_view(['GET', 'PUT', 'DELETE'])
def appointment_detail(request, id):
    appointment = Appointment.objects.get(id=id)
    print(appointment)

    if request.method == "GET":
        serialized_apmt = AppointmentSerializer(appointment)
        return Response(serialized_apmt.data,status=status.HTTP_200_OK)
    elif request.method == "PUT":
        serialized_apmt = AppointmentSerializer(appointment,data=request.data)
        print(serialized_apmt)
        if serialized_apmt.is_valid():
            print('valid')
            serialized_apmt.save()
            return Response(serialized_apmt.data,status=status.HTTP_200_OK)
        else:
            return Response({'msg': "enter all fields"},status=status.HTTP_206_PARTIAL_CONTENT)
    elif request.method == "DELETE":
        serialized_apmt = AppointmentSerializer(appointment)
        appointment.delete()
        return Response(status=status.HTTP_200_OK)
