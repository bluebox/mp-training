from operator import truediv
from django.shortcuts import render
from django.http import HttpResponse,JsonResponse
from .serializers import AppointmentSerializer
from .models import Appointment
# Create your views here.

def home(request):
    apmts = Appointment.objects.all()
    # print(apmts)
    serialised_apmts = AppointmentSerializer(apmts,many=True)
    print(serialised_apmts.data[0])
    return JsonResponse({"data":serialised_apmts.data})