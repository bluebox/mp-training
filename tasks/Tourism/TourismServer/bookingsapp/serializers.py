from rest_framework import serializers

from bookingsapp.models import BookingDetails, Feedback, PaymentDetails, User
from toursapp.serializers import TourSerializer


class UserSerializer(serializers.ModelSerializer):

    class Meta:
        model = User
        fields = '__all__'

class FeedbackSerializer(serializers.ModelSerializer):
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
