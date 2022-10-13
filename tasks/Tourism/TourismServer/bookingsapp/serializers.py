from rest_framework import serializers
from toursapp.serializers import TourSerializer
from bookingsapp.models import BookingDetails, CancellationDetails, Feedback, PaymentDetails, User


class UserSerializer(serializers.ModelSerializer):
    '''serializer for user'''

    class Meta:
        model = User
        fields = '__all__'

class FeedbackSerializer(serializers.ModelSerializer):
    '''serializer for Feedback'''


    class Meta:
        model = Feedback
        fields = '__all__'

class FeedbackDetailSerializer(serializers.ModelSerializer):
    '''Detail serializer for Feedback'''
    user_id = UserSerializer()

    class Meta:
        model = Feedback
        fields = '__all__'

class PaymentSerializer(serializers.ModelSerializer):
    '''serializer for Payment'''

    class Meta:
        model = PaymentDetails
        fields = '__all__'

class BookingSerializer(serializers.ModelSerializer):
    '''serializer for Booking'''

    class Meta:
        model = BookingDetails
        fields = '__all__'

class BookingDetailSerializer(serializers.ModelSerializer):
    '''Detail serializer for Booking'''
    userid = UserSerializer()
    paymentid = PaymentSerializer()
    tourid = TourSerializer()

    class Meta:
        model = BookingDetails
        fields = '__all__'


class CancellationSerializer(serializers.ModelSerializer):
    '''serializer for Cancellation'''

    class Meta:
        model = CancellationDetails
        fields = '__all__'


class CancellationDetailSerializer(serializers.ModelSerializer):
    '''Detail serializer for Cancellation'''
    bookingid = BookingDetailSerializer()

    class Meta:
        model = CancellationDetails
        fields = '__all__'
