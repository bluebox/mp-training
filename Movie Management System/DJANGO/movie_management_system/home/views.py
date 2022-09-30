from django.shortcuts import render
from django.http import HttpResponse
from django.core.exceptions import ValidationError
from django.utils.translation import gettext_lazy as _
from django.http import HttpResponse
from rest_framework.decorators import api_view
from rest_framework.response import Response
from home.serializer import TheatreSerializer,SnacksSerializer,MovieSerializer,UserSerializer,CartSerializer,HallSerializer,SeatingSerializer,BookingSerializer,BillSerializer,PromocodeSerializer,PaymentSerializer
from .models import Movie,Theatre,Snacks,User,Bill,Booking,Cart,PROMOCODE,Hall,Seating,Payment
from rest_framework.views import APIView
from rest_framework import viewsets
from rest_framework.permissions import IsAuthenticated

# Create your views here.
def index(request):
    return HttpResponse("Movie Management System")

def validate_Phone(value):
    if len(value) < 10:
        raise ValidationError(
            _('%(value)s is not a right phone number'),
            params={'value': value},
        )
api_view(['POST'])
def MovieDelete(request,id):
    movie=Movie.objects.get(id=id)
    movie.delete()
    return Response(MovieSerializer.data)

def EditHall(request,id):
    hall=Hall.objects.get(id=id)
    serializer=HallSerializer(instance=hall,data=request.data)
    serializer.save()
    return Response(HallSerializer.data)


class MovieViewset(viewsets.ModelViewSet):
    serializer_class=MovieSerializer
    queryset = Movie.objects.all()
    serializer = MovieSerializer(queryset,many=True)


class TheatreViewset(viewsets.ModelViewSet):
    serializer_class=TheatreSerializer
    queryset = Theatre.objects.all()
    serializer = TheatreSerializer(queryset,many=True)


class SnacksViewset(viewsets.ModelViewSet):
    serializer_class=SnacksSerializer
    queryset = Snacks.objects.all()
    serializer = SnacksSerializer(queryset,many=True)

class UserViewset(viewsets.ModelViewSet):
    serializer_class=UserSerializer
    queryset = User.objects.all()
    serializer = UserSerializer(queryset,many=True)

class HallViewset(viewsets.ModelViewSet):
    serializer_class= HallSerializer
    queryset = Hall.objects.all()
    serializer = HallSerializer(queryset,many=True)

class SeatingViewset(viewsets.ModelViewSet):
    serializer_class=SeatingSerializer
    queryset = Seating.objects.all()
    serializer = SeatingSerializer(queryset,many=True)

# class SelectedSeatsViewset(viewsets.ModelViewSet):
#     serializer_class=Selected_seatsSerializer
#     queryset = Selected_seats.objects.all()
#     serializer = Selected_seatsSerializer(queryset,many=True)

class BookingViewset(viewsets.ModelViewSet):
    serializer_class=BookingSerializer
    queryset = Booking.objects.all()
    serializer = BookingSerializer(queryset,many=True)

class BillViewset(viewsets.ModelViewSet):
    serializer_class=BillSerializer
    queryset = Bill.objects.all()
    serializer = BillSerializer(queryset,many=True)

class PromomcodeViewset(viewsets.ModelViewSet):
    serializer_class=PromocodeSerializer
    queryset = PROMOCODE.objects.all()
    serializer = PromocodeSerializer(queryset,many=True)

class CartViewset(viewsets.ModelViewSet):
    serializer_class=CartSerializer
    queryset = Cart.objects.all()
    serializer = CartSerializer(queryset,many=True)

class PaymentViewset(viewsets.ModelViewSet):
    serializer_class=PaymentSerializer
    queryset = Payment.objects.all()
    serializer = PaymentSerializer(queryset,many=True)

# def description(request,id):
#     movie=Movie.objects.filter(Movie_id=id)
#     return movie

