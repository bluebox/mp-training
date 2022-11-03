from rest_framework import serializers
from .models import *


# class UserSerializer(serializers.ModelSerializer):
#     class Meta:
#         model = User
#         fields = ['id', 'name', 'email', 'password', 'user_type']
#         extra_kwargs= {
#             'password': {'write_only': True}
#         }
#
#     def create(self, validated_data):
#
#         password = validated_data.pop('password', None)
#         instance = self.Meta.model(**validated_data)
#         if password is not None:
#             instance.set_password(**validated_data)
#
#         instance.save()
#         return instance
#
#

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
        fields = ('airlines_name', 'origin_country', 'airport_id', 'terminal_id','airlines_img')


class PassengerSerializer(serializers.ModelSerializer):
    class Meta:
        model = Passenger
        fields = ('passenger_id', 'passenger_name', 'mobile_number', 'email',
                  'passenger_city', 'password',"is_admin","passenger_image")
        # extra_kwargs = {
        #             'password': {'write_only': True}
        #         }

        # def create(self, validated_data):
        #
        #     password = validated_data.pop('password', None)
        #     instance = self.Meta.model(**validated_data)
        #     if password is not None:
        #         instance.set_password(**validated_data)
        #
        #     instance.save()
        #     return instance

# class Passenger_imgSerializer(serializers.ModelSerializer):
#     class Meta:
#         model = Passenger
#         fields=['passenger_image']
#

class FlightSerializer(serializers.ModelSerializer):
    class Meta:
        model = Flight
        fields = ['flight_id', 'flight_name', 'destination', 'model_number',
                  'capacity', 'weight', 'airport_id', 'airlines_name']


class TicketSerializer(serializers.ModelSerializer):
    class Meta:
        model = Ticket

        fields = ('ticket_id', 'booking_from', 'booking_to', 'travel_date', 'flight_id', 'passenger_id','seat_no')


class Ticket_all_Serializer(serializers.ModelSerializer):
    class Meta:
        model =Ticket
        fields = ('ticket_id', 'booking_from', 'booking_to','seat_no')


class ScheduleSerializer(serializers.ModelSerializer):
    airport_id = AirportSerializer()
    class Meta:
        model = Schedule
        fields = ['departure_time', 'arrival_time','flight_id', 'terminal_id', 'airport_id']

    def create(self, validated_data):
        airport_details = validated_data.pop("airport_details")
        schedule = Schedule.objects.create(**validated_data)
        for airport_detail in airport_details:
            airport_detail.object.create(**airport_detail, schedule=schedule)
        return schedule


class StaffSerializer(serializers.ModelSerializer):
    class Meta:
        model = Staff
        fields = ('staff_id', 'staff_designation', 'staff_name', 'staff_phone', 'airport_id', 'airlines_name')


class StaffShiftSerializer(serializers.ModelSerializer):
    class Meta:
        model = StaffShifts
        fields = ('shift_from', 'shift_to', 'staff_id', 'flight_id')


class LuggageSerializer(serializers.ModelSerializer):
    class Meta:
        model = Luggage
        fields = ('luggage_type', 'luggage_weight', 'ticket_id')


class ImageSerializer(serializers.ModelSerializer):
    class Meta:
        model = Airlines
        fields = ['airlines_name', 'airlines_img']


class ProfileSerializer(serializers.ModelSerializer):
    class Meta:
        model = Passenger
        fields = ['passenger_name', 'passenger_image']

