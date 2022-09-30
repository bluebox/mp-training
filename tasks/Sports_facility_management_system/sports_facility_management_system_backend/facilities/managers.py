from django.http import HttpResponse
from rest_framework.renderers import JSONRenderer

from facilities.models import BookingData
from facilities.serilizers import BookingFormSerializer, GetBookingIDSerializer


def booking_form(json_data, request):
    serializer = BookingFormSerializer(json_data)
    if serializer.is_valid():
        serializer.save()
        booking_id = BookingData.objects.filter(user_id=request.data['user_id']).order_by('-booking_id')[0:1].value(
            "booking_id")
        serializer = GetBookingIDSerializer(booking_id)
        booking_data = JSONRenderer().render(data=serializer.data)
        return HttpResponse(booking_data, content_type='application/json')
    else:
        error_data = JSONRenderer().render(serializer.errors)
        return HttpResponse(error_data, content_type='application/json')

