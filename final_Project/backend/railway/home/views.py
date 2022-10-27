from django.shortcuts import render
from django.core.exceptions import ValidationError
from django.utils.translation import gettext_lazy as _
from django.shortcuts import render, redirect
from django.contrib import messages
from django.contrib.auth import authenticate, login
from django.contrib.auth.decorators import login_required
from django.contrib.auth.forms import AuthenticationForm
from rest_framework.decorators import api_view
from rest_framework.response import Response
from rest_framework import viewsets
import home.models
from .serializer import UserSerializer,ScheduleSerializer,SeatChartSerializer ,CoachSerializer,TrainSerializer,TicketSerializer,StationSerializer,TrainStatusSerializer,CancelSerializer,PassengerSerializer,BookSerializer,StartDateSerializer,EndDateSerializer
from rest_framework.views import APIView
# Create your views here.


def validate_password(value):
    if len(value) < 6:
        raise ValidationError(
            _('%(value)s should be at least of length 6'),
            params={'value': value},
        )


def validate_phone(value):
    if len(value) < 10:
        raise ValidationError(
            _('%(value)s is not valid'),
            params={'value': value},
        )


def validate_userid(value):
    if len(value) < 4:
        raise ValidationError(
            _('%(value)s should be atleast of length 4'),
            params={'value': value},
        )


class UserViewSet(viewsets.ModelViewSet):
    serializer_class=UserSerializer
    queryset = home.models.User.objects.all()
    serializer_class = UserSerializer


class CoachViewSet(viewsets.ModelViewSet):
    queryset = home.models.Coach.objects.all()
    serializer_class = CoachSerializer


class TrainViewSet(viewsets.ModelViewSet):
    queryset = home.models.Train.objects.all()
    serializer_class = TrainSerializer


class TicketViewSet(viewsets.ModelViewSet):
    queryset = home.models.Ticket.objects.all()
    serializer_class = TicketSerializer


class StationViewSet(viewsets.ModelViewSet):
    queryset = home.models.Station.objects.all()
    serializer_class = StationSerializer


class PassengerViewSet(viewsets.ModelViewSet):
    queryset = home.models.Passenger.objects.all()
    serializer_class = PassengerSerializer


class CancelViewSet(viewsets.ModelViewSet):
    queryset = home.models.Cancel.objects.all()
    serializer_class = CancelSerializer


class ScheduleViewSet(viewsets.ModelViewSet):
    queryset = home.models.Schedule.objects.all()
    serializer_class = ScheduleSerializer

class SeatChartViewSet(viewsets.ModelViewSet):
    queryset = home.models.Seat_Chart.objects.all()
    serializer_class = SeatChartSerializer

class BookViewSet(viewsets.ModelViewSet):
    queryset = home.models.Book.objects.all()
    serializer_class = BookSerializer


class TrainStatusViewSet(viewsets.ModelViewSet):
    queryset = home.models.TrainStatus.objects.all()
    serializer_class = TrainStatusSerializer

class StartDateViewSet(viewsets.ModelViewSet):
    queryset = home.models.StartDateOfJourney.objects.all()
    serializer_class = TrainStatusSerializer

class EndDateViewSet(viewsets.ModelViewSet):
    queryset = home.models.EndDateOfJourney.objects.all()
    serializer_class = TrainStatusSerializer



class UserView(APIView):

    def get(self, request):
        user_obj = home.models.User.objects.all()
        serializer = UserSerializer(user_obj, many=True)
        return Response({
            'status': True,
            'message': 'user fetched',
            'data': serializer.data
        })

    def post(self,request):
        try:
            data = request.data
            serializer = UserSerializer(data=data)
            if serializer.is_valid():
                serializer.save()  # save data in database
                print(serializer.data)
                return Response({
                    'status': True,
                    'message': 'success data!!!',
                    'data': serializer.data
                })
            return Response({
                'status': False,
                'message': 'something went wrong!!',
                'data': serializer.errors
            })

        except Exception as e:
            print(e)
        return Response({
            'status': False,
            'message': 'something went wrong!!!'
        })
    # def patch(self,request):
    #     try:
    #         data = request.data
    #         if not data.get('uid'):
    #             return Response({
    #                 'status': False,
    #                 'message': 'uid is required'
    #             })
    #         obj = home.models.User.objects.get(uid=data.get('uid'))
    #         serializer = UserSerializer(obj, data=data, partial=True)
    #         if serializer.is_valid():
    #             serializer.save()  # save data in database
    #             print(serializer.data)
    #             return Response({
    #                 'status': True,
    #                 'message': 'success data!!!',
    #                 'data': serializer.data
    #             })
    #         return Response({
    #             'status': False,
    #             'message': 'something went wrong!!',
    #             'data': serializer.errors
    #         })
    #     except Exception as e:
    #         print(e)
    #     return Response({
    #         'status': False,
    #         'message': 'invalid uid!!!'
    #     })

