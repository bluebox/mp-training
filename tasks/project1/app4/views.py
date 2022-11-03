# from django.shortcuts import render, redirect
# from django.contrib import messages
# from .forms import UserRegisterForm
# from django.contrib.auth.decorators import login_required
# from django.urls import reverse
from django.http import HttpResponse
from _testcapi import raise_exception


from django.views.decorators.csrf import csrf_exempt
from rest_framework import status
from rest_framework.decorators import api_view
from rest_framework.generics import ListAPIView
from rest_framework.parsers import JSONParser, MultiPartParser, FormParser
from django.http.response import JsonResponse
from django.core.files.storage import default_storage
from rest_framework.response import Response
from rest_framework.views import APIView
import jwt, datetime
from rest_framework.exceptions import AuthenticationFailed
from django.core.files.storage import default_storage

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


# @csrf_exempt


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
        passenger_serializer = PassengerSerializer(passenger, data=passenger_data)
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
    flightname= res_dict.get('flight_name')
    dest = res_dict.get('destination')
    flights = Flight.objects.filter(flight_name=flightname, destination=dest)
    serializer = FlightSerializer(flights, many=True)
    return JsonResponse(serializer.data, safe=False)
  if request.method == 'GET':
      flight = Flight.objects.all()
      flight_serializer = FlightSerializer(flight, many=True)
      return JsonResponse(flight_serializer.data, safe=False)

@csrf_exempt

def Bookticket(request):
    if request.method == 'POST':
        ticket_data = JSONParser().parse(request)
        ticket_serializer = TicketSerializer(data=ticket_data)
        if ticket_serializer.is_valid():
            ticket_serializer.save()
            return JsonResponse("Booked successfully!!", safe=False)
        return JsonResponse("Failed", safe=False)

    if request.method == 'GET':
        ticket = Ticket.objects.latest('ticket_id')
        ticket.ticket_id=ticket.ticket_id+1
        ticket_serializer = TicketSerializer(ticket)
        return JsonResponse(ticket_serializer.data, safe=False)





@csrf_exempt
@api_view(['GET', 'POST'])
def lookschedule(request):
    if request.method == 'POST':
        res_dict = json.loads(request.body.decode('utf-8'))
        departure = res_dict.get('departure_time')
        arrival = res_dict.get('arrival_time')
        schedule = Schedule.objects.filter(departure_time=departure, arrival_time=arrival)
        serializer = ScheduleSerializer(schedule, many=True)
        return JsonResponse(serializer.data, safe=False)

    if request.method == 'GET':
        schedule = Schedule.objects.all()
        schedule_serializer = ScheduleSerializer(schedule, many=True)
        return JsonResponse(schedule_serializer.data, safe=False)


# class RegisterView(APIView):
#     def post(self, request):
#         serializer = PassengerSerializer(data=request.data)
#         serializer.is_valid(raise_exception = True)
#         serializer.save()
#         return Response(serializer.data)

class LoginView(APIView):
    def post(self, request):
        email = request.data['email']
        password = request.data['password']
        user = Passenger.objects.filter(email=email).first()
        is_admin=user.is_admin
        if user is None:
            raise AuthenticationFailed('User not found')

        if user.password!=password:
            raise AuthenticationFailed('Incorrect password!')

        # payload ={
        #     'passenger_id': user.passenger_id,
        #     'exp': datetime.datetime.utcnow()+datetime.timedelta(minutes=120),
        #     'iat': datetime.datetime.utcnow()
        # }

        access_token = jwt.encode({
            'passenger_id': user.passenger_id,
            'exp': datetime.datetime.utcnow()+datetime.timedelta(minutes=5),
            'iat': datetime.datetime.utcnow()
        }, 'access_secret', algorithm='HS256').decode('utf-8')

        refresh_token = jwt.encode({
            'passenger_id': user.passenger_id,
            'exp': datetime.datetime.utcnow()+datetime.timedelta(days=120),
            'iat': datetime.datetime.utcnow()
        },'refresh_secret', algorithm='HS256').decode('utf-8')

        response = Response()
        response.set_cookie(key='refresh_token', value=refresh_token, httponly=True,)
        response.data ={
             'access_token': access_token,
             'user' : user.passenger_name,
             'userid' : user.passenger_id,
             'is_admin': is_admin,
             'msg': "successfully logged in"


        }

        return response


class UserView(APIView):

    def get(self, request):
        refresh_token = request.COOKIES.get('refresh_token')

        if not refresh_token:
            raise AuthenticationFailed('Unauthenticated!')

        try:
            payload = jwt.decode(refresh_token, 'refresh_secret', algorithm=['HS256'])

        except jwt.ExpiredSignatureError:
            raise AuthenticationFailed('Unauthenticated')

        user = Passenger.objects.filter(passenger_id=payload['passenger_id'].first())
        serializer= PassengerSerializer(user)
        return Response(refresh_token)


class LogoutView(APIView):
    def post(self, request):
        refresh_token = request.COOKIES.get('refresh_token')
        response = Response()
        response.delete_cookie(refresh_token)
        response.data={
            'message': 'success'
        }
        return response

@csrf_exempt
def staff_api(request, id=0):
    if request.method == 'GET':
        staff = Staff.objects.all()
        staff_serializer = StaffSerializer(staff, many=True)
        return JsonResponse(staff_serializer.data, safe=False)

    elif request.method == 'POST':
        staff_data = JSONParser().parse(request)
        staff_serializer = StaffSerializer(data=staff_data)
        if staff_serializer.is_valid():
            staff_serializer.save()
            return JsonResponse("added successfully!!", safe=False)
        return JsonResponse("Failed to add", safe=False)

    elif request.method == 'PUT':
        staff_data = JSONParser().parse(request)
        staff = Staff.objects.get(staff_id=staff_data['staff_id'])
        staff_serializer = StaffSerializer(staff, data=staff_data)
        if staff_serializer.is_valid():
            staff_serializer.save()
            return JsonResponse("Updated Successfully", safe=False)

    elif request.method == 'DELETE':
        staff = Staff.objects.get(staff_id=id)
        staff.delete()
        return JsonResponse("Deleted Successfully!!", safe=False)


@csrf_exempt
def staff_shifts_api(request, id=0):
    if request.method == 'GET':
        staff_shifts = StaffShifts.objects.all()
        staff_shifts_serializer = StaffShiftSerializer(staff_shifts, many=True)

        return JsonResponse(staff_shifts_serializer.data, safe=False)

def luggage_api(request, id=0):
    if request.method == 'GET':
        luggage = Luggage.objects.all()
        luggage_serializer = LuggageSerializer(luggage, many=True)
        return JsonResponse(luggage_serializer.data, safe=False)
    elif request.method == 'POST':
        luggage_data = JSONParser().parse(request)
        luggage_serializer = LuggageSerializer(data=luggage_data)
        if luggage_serializer.is_valid():
            luggage_serializer.save()
            return JsonResponse("added successfully!!", safe=False)
        return JsonResponse("Failed to add", safe=False)
@csrf_exempt
def ticketdetails(request):
    if request.method == 'GET':
        ticket = Ticket.objects.latest('ticket_id')
        ticket_serializer = TicketSerializer(ticket)
        return JsonResponse(ticket_serializer.data, safe=False)

@csrf_exempt
def ticket_all_details(request):
    if request.method == 'GET':
        ticket = Ticket.objects.all()
        ticket_serializer = Ticket_all_Serializer(ticket,many=True)
        return JsonResponse(ticket_serializer.data, safe=False)

@csrf_exempt
def my_bookings(request):
    if request.method == 'POST':
        res_dict = json.loads(request.body.decode('utf-8'))
        passid = res_dict.get('passenger_id')
        tickets = Ticket.objects.filter(passenger_id=passid)
        serializer = TicketSerializer(tickets, many=True)
        return JsonResponse(serializer.data, safe=False)
@csrf_exempt
def my_details(request):
    if request.method == 'POST':
        res_dict = json.loads(request.body.decode('utf-8'))
        passid = res_dict.get('userid')
        passenger = Passenger.objects.filter(passenger_id=passid)
        serializer = PassengerSerializer(passenger, many=True)
        return JsonResponse(serializer.data, safe=False)
# @csrf_exempt
# def upload_image(request):
#     if request.method == 'POST':
#
#         Airlines.object.create(airlines_name=name, airlines_image=image)
#         return HttpResponse({"Message": "uploaded"}, status=200)


# def savefile(request):
#     file=request.FILES['uploadedFile']
#     file_name=default_storage.save(file.name,file)
#     return JsonResponse(file_name,safe=False)
@csrf_exempt
def save_file(request):
    file = request.FILES['uploadFile']
    file_name = default_storage.save(file.name, file)
    return JsonResponse(file_name, safe=False)

class ImageViewSet(ListAPIView):
    parser_classes = [MultiPartParser, FormParser]
    serializer_class = ImageSerializer

    def post(self, request, *args, **kwargs):
        username = request.data['name']
        img = request.data['file']
        user = Airlines.objects.get(airlines_name=username)
        user.airlines_img = img
        user.save()
        return Response("Image updated!", status=status.HTTP_200_OK)


class ProfileViewSet(ListAPIView):
    parser_classes = [MultiPartParser, FormParser]
    serializer_class = ProfileSerializer

    def post(self, request, *args, **kwargs):
        name = request.POST['Username']
        file = request.FILES['file']
        profile = Passenger.objects.get(passenger_name=name)
        profile.passenger_image = file
        profile.save()
        return Response("Image updated!", status=status.HTTP_200_OK)