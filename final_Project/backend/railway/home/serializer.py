from rest_framework import serializers
from  .models import User, Coach,Train,Ticket,Seat_Chart,Schedule, Station,TrainStatus,Cancel,Passenger,Book,StartDateOfJourney,EndDateOfJourney

class UserSerializer(serializers.ModelSerializer):
    class Meta:
        model = User
        fields = '__all__'

class CoachSerializer(serializers.HyperlinkedModelSerializer):
    class Meta:
        model = Coach
        fields = '__all__';

class TrainSerializer(serializers.HyperlinkedModelSerializer):
    class Meta:
        model = Train
        fields = '__all__';

class TicketSerializer(serializers.HyperlinkedModelSerializer):
    class Meta:
        model = Ticket
        fields = '__all__';

class StationSerializer(serializers.HyperlinkedModelSerializer):
    class Meta:
        model = Station
        fields = '__all__';

class TrainStatusSerializer(serializers.HyperlinkedModelSerializer):
    class Meta:
        model = TrainStatus
        fields = '__all__';

class CancelSerializer(serializers.HyperlinkedModelSerializer):
    class Meta:
        model = Cancel
        fields = '__all__';

class PassengerSerializer(serializers.HyperlinkedModelSerializer):
    class Meta:
        model = Passenger
        fields = '__all__';


class BookSerializer(serializers.HyperlinkedModelSerializer):
    class Meta:
        model = Book
        fields = '__all__';


class SeatChartSerializer(serializers.HyperlinkedModelSerializer):
    class Meta:
        model = Seat_Chart
        fields = '__all__';

class ScheduleSerializer(serializers.HyperlinkedModelSerializer):
    class Meta:
        model = Schedule
        fields = '__all__';



class StartDateSerializer(serializers.HyperlinkedModelSerializer):
    class Meta:
        model = StartDateOfJourney
        fields = '__all__';


class EndDateSerializer(serializers.HyperlinkedModelSerializer):
    class Meta:
        model = EndDateOfJourney
        fields = '__all__';
