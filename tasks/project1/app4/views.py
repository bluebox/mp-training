# from django.shortcuts import render, redirect
# from django.contrib import messages
# from .forms import UserRegisterForm
# from django.contrib.auth.decorators import login_required
# from django.urls import reverse
# from django.http import HttpResponseRedirect
from django.views.decorators.csrf import csrf_exempt
from rest_framework.parsers import JSONParser
from django.http.response import JsonResponse
from django.core.files.storage import default_storage
from rest_framework.response import Response
from rest_framework.views import APIView

from .models import *
from .serializers import *
import json


# def index(request):
#     return render(request, 'm_app4/base.html')
# def login(request):
#     if request.method == 'GET':
#         users = User.objects.all()
#         user_serializer = UserSerializer(users, many=True)
#         return JsonResponse(user_serializer.data, safe=False)
#
# def register(request):
#     if request.method == 'POST':
#         form = UserRegisterForm(request.POST)
#         if form.is_valid():
#             form.save()
#             username = form.cleaned_data.get('username')
#             messages.success(request, f'Account created for {username}')
#             return HttpResponseRedirect('/model/')
#     else:
#         form = UserRegisterForm()
#     return render(request, 'm_app4/register.html', {'form': form})
# @login_required
# def profile(request):
#     return render(request, 'm_app4/profile.html')

@csrf_exempt
def airport_api(request, id=0):
    if request.method == 'GET':
        airports = Airport.objects.all()
        airport_serializer = AirportSerializer(airports, many=True)
        return JsonResponse(airport_serializer.data, safe=False)

    elif request.method == 'POST':
        airport_data = JSONParser().parse(request)
        airport_serializer = AirportSerializer(data=airport_data)
        if airport_serializer.is_valid():
            airport_serializer.save()
            return JsonResponse("added successfully!!", safe=False)
        return JsonResponse("Failed to add", safe=False)

    elif request.method == 'PUT':
        airport_data = JSONParser().parse(request)
        airport = Airport.objects.get(airport_id=airport_data['airport_id'])
        airport_serializer = AirportSerializer(airport, data=airport_data)
        if airport_serializer.is_valid():
            airport_serializer.save()
            return JsonResponse("Updated Successfully", safe=False)

    elif request.method == 'DELETE':
        airport = Airport.objects.get(airport_id=id)
        airport.delete()
        return JsonResponse("Deleted Successfully!!", safe=False)
@csrf_exempt
def save_file(request):
    file = request.FILES['uploadFile']
    file_name = default_storage.save(file.name, file)
    return JsonResponse(file_name, safe=False)

@csrf_exempt
def terminal_api(request, id=0):
    if request.method == 'GET':
        terminals = Terminal.objects.all()
        terminal_serializer = TerminalSerializer(terminals, many=True)
        return JsonResponse(terminal_serializer.data, safe=False)

    elif request.method == 'POST':
        terminal_data = JSONParser().parse(request)
        terminal_serializer = TerminalSerializer(data=terminal_data)
        if terminal_serializer.is_valid():
            terminal_serializer.save()
            return JsonResponse("added successfully!!", safe=False)
        return JsonResponse("Failed to add", safe=False)

    elif request.method == 'PUT':
        terminal_data = JSONParser().parse(request)
        terminal = Terminal.objects.get(terminal_id=terminal_data['terminal_id'])
        terminal_serializer = TerminalSerializer(terminal, data=terminal_data)
        if terminal_serializer.is_valid():
            terminal_serializer.save()
            return JsonResponse("Updated Successfully", safe=False)

    elif request.method == 'DELETE':
        terminal = Terminal.objects.get(terminal_id=id)
        terminal.delete()
        return JsonResponse("Deleted Successfully!!", safe=False)

@csrf_exempt
def airlines_api(request, id=0):
    if request.method == 'GET':
        airlines = Airlines.objects.all()
        airlines_serializer = AirlinesSerializer(airlines, many=True)
        return JsonResponse(airlines_serializer.data, safe=False)

    elif request.method == 'POST':
        airlines_data = JSONParser().parse(request)
        airlines_serializer = AirlinesSerializer(data=airlines_data)
        if airlines_serializer.is_valid():
            airlines_serializer.save()
            return JsonResponse("added successfully!!", safe=False)
        return JsonResponse("Failed to add", safe=False)

    elif request.method == 'PUT':
        airlines_data = JSONParser().parse(request)
        airlines = Airlines.objects.get(airlines_id=airlines_data['airlines_id'])
        airlines_serializer = AirlinesSerializer(airlines, data=airlines_data)
        if airlines_serializer.is_valid():
            airlines_serializer.save()
            return JsonResponse("Updated Successfully", safe=False)

    elif request.method == 'DELETE':
        airlines = Airlines.objects.get(airlines_id=id)
        airlines.delete()
        return JsonResponse("Deleted Successfully!!", safe=False)

@csrf_exempt
def passenger_api(request, id=0):
    if request.method == 'GET':
        passenger = Passenger.objects.all()
        passenger_serializer = PassengerSerializer(passenger, many=True)
        return JsonResponse(passenger_serializer.data, safe=False)

    elif request.method == 'POST':
        passenger_data = JSONParser().parse(request)
        passenger_serializer = PassengerSerializer(data=passenger_data)
        if passenger_serializer.is_valid():
            passenger_serializer.save()
            return JsonResponse("added successfully!!", safe=False)
        return JsonResponse("Failed to add", safe=False)

    elif request.method == 'PUT':
        passenger_data = JSONParser().parse(request)
        passenger = Passenger.objects.get(passenger_id=passenger_data['passenger_id'])
        passenger_serializer = TerminalSerializer(passenger, data=passenger_data)
        if passenger_serializer.is_valid():
            passenger_serializer.save()
            return JsonResponse("Updated Successfully", safe=False)

    elif request.method == 'DELETE':
        passenger = Passenger.objects.get(passenger_id=id)
        passenger.delete()
        return JsonResponse("Deleted Successfully!!", safe=False)


@csrf_exempt
# def flight_api(request):
#     print(request)
#     # if request.method == 'GET':
#     #     flight = Flight.objects.filter(flight_id=flightid, destination=dest)
#     #     flight_serializer = FlightSerializer(flight, many=True)
#     #     return JsonResponse(flight_serializer.data, safe=False)
#
#     if request.method == 'POST':
#         flight = request.data.get
#         flight_data = Flight.objects.filter(flight_id=flightid, destination=dest)
#         flight_serializer = FlightSerializer(data=flight_data)
#         if flight_serializer.is_valid():
#             flight_serializer.save()
#             return JsonResponse("added successfully!!", safe=False)
#         return JsonResponse("Failed to add", safe=False)




@csrf_exempt
def Filterflights(request):
  if request.method == 'POST':
    res_dict = json.loads(request.body.decode('utf-8'))
    flightid= res_dict.get('flight_id')
    dest = res_dict.get('destination')
    flights = Flight.objects.filter(flight_id=flightid, destination=dest)
    serializer = FlightSerializer(flights, many=True)
    return JsonResponse(serializer.data, safe=False)
@csrf_exempt
def Bookticket(request):
    if request.method == 'POST':
        ticket_data = JSONParser().parse(request)
        ticket_serializer = TicketSerializer(data=ticket_data)
        if ticket_serializer.is_valid():
            ticket_serializer.save()
            return JsonResponse("Booked successfully!!", safe=False)
        return JsonResponse("Failed to add", safe=False)

    if request.method == 'GET':
        ticket = Ticket.objects.all()
        ticket_serializer = TicketSerializer(ticket, many=True)
        return JsonResponse(ticket_serializer.data, safe=False)




