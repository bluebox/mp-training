from TourismServer.managers.adminManager import listing
from bookingsapp.models import BookingDetails, CancellationDetails
from bookingsapp.serializers import BookingDetailSerializer, BookingSerializer, CancellationSerializer
from rest_framework.response import Response
from rest_framework import status
from rest_framework.exceptions import APIException
def get_bookings(request, bookings):
    serializer = BookingDetailSerializer(bookings, many=True)
    return Response(serializer.data)

def get_booking(request, booking):
    serializer = BookingSerializer(booking)
    return Response(serializer.data)

def get_bookings_by_pagination(request):
    text=request.GET.get('text')
    booking_list = (BookingDetails.objects.filter(passenger_details__icontains=text))
    bookings, totalPages, page = listing(request, booking_list)
    serializer = BookingDetailSerializer(bookings, many=True)
    return Response({
        'pageItems': serializer.data,
        'totalPages': totalPages,
        'page': page
    })

def post_booking(request):
    serializer = BookingSerializer(data=request.data)
    if serializer.is_valid():
        serializer.save()
        return Response(serializer.data, status=status.HTTP_201_CREATED)
    raise APIException(serializer.errors)

def edit_booking_by_admin(request, booking):
    if request.data['isCancelled']:
        cancellation_charges = float(booking.tourid.price) * 0.15
        cancelled = CancellationDetails(bookingid=booking,
                                    refund_status='processing',
                                    cancellation_charges=cancellation_charges
                                    )
    cancelled.save()

    serializer = BookingSerializer(booking, data=request.data)
    if serializer.is_valid():
        serializer.save()
        return Response(serializer.data)
    raise APIException(serializer.errors)

def delete_booking_by_admin(request, booking):
    booking.delete()
    serializer = BookingSerializer(booking)
    return Response(serializer.data)




def get_cancellation_by_pagination(request):
    text = request.GET.get('text')
    cancellation_list = CancellationDetails.objects.filter(refund_status__contains=text)
    items, totalPages, page = listing(request, cancellation_list)
    serializer = CancellationSerializer(items, many=True)
    return Response({
        'pageItems': serializer.data,
        'totalPages': totalPages,
        'page': page
    })