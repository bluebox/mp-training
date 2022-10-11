from rest_framework import serializers

from bookingsapp.models import BookingDetails, CancellationDetails, Feedback, PaymentDetails, User
from toursapp.serializers import TourSerializer


class UserSerializer(serializers.ModelSerializer):

    class Meta:
        model = User
        fields = '__all__'

class FeedbackSerializer(serializers.ModelSerializer):
    class Meta:
        model = Feedback
        fields = '__all__'

class FeedbackDetailSerializer(serializers.ModelSerializer):
    user_id = UserSerializer()

    class Meta:
        model = Feedback
        fields = '__all__'

class PaymentSerializer(serializers.ModelSerializer):

    class Meta:
        model = PaymentDetails
        fields = '__all__'

class BookingSerializer(serializers.ModelSerializer):

    class Meta:
        model = BookingDetails
        fields = '__all__'

class BookingDetailSerializer(serializers.ModelSerializer):
    userid = UserSerializer()
    paymentid = PaymentSerializer()
    tourid = TourSerializer()


    class Meta:
        model = BookingDetails
        fields = '__all__'


class CancellationSerializer(serializers.ModelSerializer):

    class Meta:
        model = CancellationDetails
        fields = '__all__'
class CancellationDetailSerializer(serializers.ModelSerializer):
    bookingid = BookingDetailSerializer()

    class Meta:
        model = CancellationDetails
        fields = '__all__'