from rest_framework import serializers
from .models import *
# class UserSerializer(serializers.ModelSerializer):
#     class Meta:
#         model= User
#         fields =('username', 'password')


class AirportSerializer(serializers.ModelSerializer):
    class Meta:
        model = Airport
        fields = ('airport_id', 'airport_name', 'airport_owner', 'city')


class TerminalSerializer(serializers.ModelSerializer):
    class Meta:
        model = Terminal
        fields = ('terminal_id', 'terminal_description', 'airport_id',)


class AirlinesSerializer(serializers.ModelSerializer):
    class Meta:
        model = Airlines
        fields = ('airlines_name', 'origin_country', 'airport_id', 'terminal_id')


class PassengerSerializer(serializers.ModelSerializer):
    class Meta:
        model = Passenger
        fields = ('passenger_id', 'passenger_name', 'mobile_number', 'email',
                  'passenger_city', 'password')


class FlightSerializer(serializers.ModelSerializer):
    class Meta:
        model = Flight
        fields = ('flight_id', 'flight_name', 'destination', 'model_number',
                  'capacity', 'weight', 'airport_id', 'airlines_name')


class TicketSerializer(serializers.ModelSerializer):
    class Meta:
        model = Ticket
        fields = ('ticket_id', 'booking_from', 'booking_to', 'travel_date', 'flight_id', 'passenger_id')
