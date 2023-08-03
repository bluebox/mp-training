from django.shortcuts import render
from base.product import movies  
from rest_framework.decorators import api_view, permission_classes
from rest_framework.permissions import IsAuthenticated, IsAdminUser
from rest_framework.response import Response
from django.core.paginator import Paginator, EmptyPage, PageNotAnInteger

from base.models import Movie
from django.contrib.auth.models import User
from base.serializers import *
from rest_framework_simplejwt.serializers import TokenObtainPairSerializer
from rest_framework_simplejwt.views import TokenObtainPairView
from rest_framework import status
from user.views import *
from .models import *
from .serializers import *
from datetime import datetime

from django.contrib.auth.hashers import make_password
# Create your views here.

@api_view(['POST'])
@permission_classes([IsAuthenticated])
def addBookingItems(request):
    user = request.user
    data = request.data
    print(data)
    bookingItems = data['bookingItems']

    if bookingItems and len(bookingItems) == 0:
        return Response({'detail': 'No Order Items'}, status=status.HTTP_400_BAD_REQUEST)
    else:

        # (1) Create order
        booking = Booking.objects.create(
        user=user,
        paymentMethod = data['paymentMethod'],
        taxPrice=data['taxPrice'],
        totalPrice=data['totalPrice']
        )


        # (2) Create booking address

        bookingAddress = BookingAddress.objects.create(
                order = booking,
                address = data['bookingAddress']['address'],
                city = data['bookingAddress']['city'],
                postalCode = data['bookingAddress']['postalCode'],
                country = data['bookingAddress']['country'], 
                bookingPrice=data['bookingPrice'],
            )
            # (3) Create order items and set orderItem relationship

        for i in bookingItems:
            movie = Movie.objects.get(_id=i['product'])
            item = BookingItem.objects.create(
                movie = movie, 
                order_detail = booking,
                name = movie.name,
                number_of_tickets = i['qty'],
                price = i['price']
            )
            # (4) Update the seats
            movie.number_of_screens -= item.number_of_tickets
            movie.save()


        serializer = BookingSerializer(booking, many=False)
        return Response(serializer.data)


@api_view(['GET'])
@permission_classes([IsAuthenticated])
def getBookingById(request, pk):
    
    user = request.user

    try:
        print(pk)
        booking = Booking.objects.get(pk=pk)
        if user.is_staff or booking.user == user:
            serializer = BookingSerializer(booking, many=False)
            return Response(serializer.data)
        else:
            Response({'detail':'Not authorized to view the order'}, status=status.HTTP_400_BAD_REQUEST)
    except:
        return Response({'detail':'Booking does not exists'}, status=status.HTTP_400_BAD_REQUEST)


@api_view(['PUT'])
@permission_classes([IsAuthenticated])
def updateBookingOrderToPaid(request, pk):
    bookingOrder = Booking.objects.get(_id=pk)

    bookingOrder.isPaid = True
    bookingOrder.paidAt = datetime.now()
    bookingOrder.save()
    
    return Response('Payment was done')
