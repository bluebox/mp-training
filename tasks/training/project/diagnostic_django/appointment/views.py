
from xml.dom import ValidationErr
from django.shortcuts import render
from django.http import HttpResponse, JsonResponse
from rest_framework.response import Response
from rest_framework import status
from rest_framework.views import APIView
from rest_framework.decorators import api_view
from django.views.decorators.csrf import csrf_exempt

from users.models import Staff
from users.serializers import EmployeeSerializer
from .serializers import AppointmentSerializer, BranchSerializer
from .models import Appointment, Branch
import json
# Create your views here.
# @csrf_exempt
class AppointmentBooking(APIView):
    def get(self,request):
        appointments = Appointment.objects.all()
        serializer = AppointmentSerializer(appointments , many=True)
        return Response(serializer.data , status = 200 )

    def post(self,request):
        data = request.data
        data['user'] = 'MEDC7'
        print(data)
        apmt = AppointmentSerializer( data =data )
        if apmt.is_valid():
            apmt.save()
            return Response({"message":"appointment_booked"} , status = 200 )
        else:
            return Response({"message":"appointment not booked"} , status = 200 )
        # return Response({"message":"appointment not booked"} , status = 200 )


@api_view(['GET'])
def getEmployees(request):
    if request.GET['role'] == 'doctor':
        doctors = Staff.objects.filter(designation="Doctor")
        serializer = EmployeeSerializer(doctors,many=True)
        return Response(serializer.data,status=200)
    if request.GET['role'] == 'nurse':
        doctors = Staff.objects.filter(designation="Nurse")
        serializer = EmployeeSerializer(doctors,many=True)
        return Response(serializer.data,status=200)
    if request.GET['role'] == 'lab':
        doctors = Staff.objects.filter(designation="Lab Technician")
        serializer = EmployeeSerializer(doctors,many=True)
        return Response(serializer.data,status=200)
    if request.GET['role'] == 'sample':
        doctors = Staff.objects.filter(designation="Sample Collector")
        serializer = EmployeeSerializer(doctors,many=True)
        return Response(serializer.data,status=200)
    return Response({"message":"not working"},status=200)
    