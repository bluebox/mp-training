from rest_framework import serializers
from  .models import User, Coach,Train,Ticket,Seat_Chart,Schedule, Station,TrainStatus,Cancel,Passenger,Book,StartDateOfJourney,EndDateOfJourney

class UserSerializer(serializers.ModelSerializer):
    class Meta:
        model = User
        fields = '__all__'
        extra_kwargs = {
            'password':{'write_only': True}
        }

    # def create(self, validated_data):
    #     password = validated_data.pop('password', None)
    #     instance = self.Meta.model(**validated_data)
    #     if password is not None:
    #         instance.set_password(password)
    #     instance.save()
    #     return instance


class CoachSerializer(serializers.ModelSerializer):
    class Meta:
        model = Coach
        fields = '__all__';

class TrainSerializer(serializers.ModelSerializer):
    class Meta:
        model = Train
        fields = '__all__';

class TicketSerializer(serializers.ModelSerializer):
    class Meta:
        model = Ticket
        fields = '__all__';

class StationSerializer(serializers.ModelSerializer):
    class Meta:
        model = Station
        fields = '__all__';

class TrainStatusSerializer(serializers.ModelSerializer):
    class Meta:
        model = TrainStatus
        fields = '__all__';

class CancelSerializer(serializers.ModelSerializer):
    class Meta:
        model = Cancel
        fields = '__all__';

class PassengerSerializer(serializers.ModelSerializer):
    class Meta:
        model = Passenger
        fields = '__all__';


class BookSerializer(serializers.ModelSerializer):
    class Meta:
        model = Book
        fields = '__all__';


class SeatChartSerializer(serializers.ModelSerializer):
    class Meta:
        model = Seat_Chart
        fields = '__all__';

class ScheduleSerializer(serializers.ModelSerializer):
    class Meta:
        model = Schedule
        fields = '__all__';



class StartDateSerializer(serializers.ModelSerializer):
    class Meta:
        model = StartDateOfJourney
        fields = '__all__';


class EndDateSerializer(serializers.ModelSerializer):
    class Meta:
        model = EndDateOfJourney
        fields = '__all__';
