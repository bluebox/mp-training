from asyncio import exceptions
from urllib import response
from django.shortcuts import render
from django.core.exceptions import ValidationError
from django.utils.translation import gettext_lazy as _
from django.shortcuts import render, redirect
from django.contrib import messages
from django.contrib.auth import authenticate, login
from django.contrib.auth.decorators import login_required
from django.contrib.auth.forms import AuthenticationForm
from rest_framework.decorators import api_view
from rest_framework.exceptions import AuthenticationFailed
from rest_framework.response import Response
from rest_framework import viewsets
import home.models
import json
from .serializer import UserSerializer,ScheduleSerializer,SeatChartSerializer ,CoachSerializer,TrainSerializer,TicketSerializer,StationSerializer,TrainStatusSerializer,CancelSerializer,PassengerSerializer,BookSerializer,StartDateSerializer,EndDateSerializer
from rest_framework.views import APIView
import jwt, datetime
from home.models import Seat_Chart, Ticket,Train,Passenger
from datetime import timedelta;
from home import serializer
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

class Login(APIView):
    def post(self,request):
        user_id=request.data['user_id']
        password=request.data['password']
        print(user_id, password)
        print(home.models.User.objects.all())
        user=home.models.User.objects.filter(user_id=user_id).first()
        print(user.password, password)
        if user is None:
            raise AuthenticationFailed("user not found")
        if user.password != password:
            raise AuthenticationFailed("password incorrect")
        payload={
            'user_id':user.user_id,
            "exp":datetime.datetime.utcnow()+datetime.timedelta(minutes=60),
            'iat':datetime.datetime.utcnow()
        }
        token=jwt.encode(payload,'secret',algorithm='HS256').decode('utf-8')
        response=Response()
        response.set_cookie(key="jwt",value=token,httponly=True,samesite="Lax")
        response.data={
            'message':'sucess',
            'jwt':token
        }
        return response


class GetUser(APIView):
    def get(self,request):
        token=request.COOKIES.get('jwt')
        print(token)
        if not token:
            raise AuthenticationFailed("unauthenticated")

        try:
            print('in')
            payload=jwt.decode(token,'secret',algorithm=['HS256']);
            print(payload)
        except:
            raise AuthenticationFailed("invalid")
        user = home.models.User.objects.filter(user_id=payload['user_id']).first()
        print(user)
        serializer = UserSerializer(user)
        print(serializer.data)
        return Response(serializer.data)


class Logout(APIView):
    def post(self,request):
        response=Response()
        response.delete_cookie('jwt')
        response.data={
            'message':"sucess"
        }
        return response

class TicketHistory(APIView):
    def get(self,request,username):
        ticket=Ticket.objects.filter(user=username)
        t=[]
        for i in range(len(ticket)):
            train={
                'train_name':ticket[i].train.train_name,
                'train_no':ticket[i].train.train_no,
                'source':ticket[i].train.source.station_name,
                'source_time':ticket[i].train.source_time,
                'dest':ticket[i].train.dest.station_name,
                'dest_time':ticket[i].train.dest_time,
                'duration_h':ticket[i].train.duration_h,
                'distance':ticket[i].train.distance,
                'date':ticket[i].date,
                'type':ticket[i].type,
                'seats':ticket[i].seats,
                'status':ticket[i].status,
                'fare':ticket[i].fare,
                'id':ticket[i].id,
                'p_id':ticket[i].passenger.all().values()
            }
            t.append(train)
        return Response(t)

class RecentTicketHistory(APIView):
    def get(self,request,username):
        ticket=Ticket.objects.filter(user=username)
        l=len(ticket)
         # print(train[i],seat[j])
        x = ticket[l-1].train.duration_h+ticket[l-1].train.source_time.hour
        y = ticket[l-1].train.duration_m+ticket[l-1].train.source_time.minute
        train={
            'train_name':ticket[l-1].train.train_name,
            'train_no':ticket[l-1].train.train_no,
            'source':ticket[l-1].train.source.station_name,
            'source_time':ticket[l-1].train.source_time,
            'dest':ticket[l-1].train.dest.station_name,
            'dest_time':ticket[l-1].train.dest_time,
            'duration_h':ticket[l-1].train.duration_h,
            'distance':ticket[l-1].train.distance,
            'date':ticket[l-1].date,
            'type':ticket[l-1].type,
            'seats':ticket[l-1].seats,
            'status':ticket[l-1].status,
            'fare':ticket[l-1].fare,
            'id':ticket[l-1].id,
            'p_id':ticket[l-1].passenger.all().values(),
            'dest_date':ticket[l-1].date+timedelta(hours=x,minutes=y)
        }
           
        
        return Response(train)

class CancelTicket(APIView):
    def post(self,request):
        ticket_id=request.data['ticket_id']
        ticket=Ticket.objects.get(id=ticket_id)
        ticket.status='Cancelled'
        ticket.save()
        c=ticket.passenger.count()
        seat=Seat_Chart.objects.get(id=ticket.chart.id)
        if(ticket.type=="sl"):
            final_seat=seat.sleeper+c
            seat.sleeper=final_seat
            seat.save()
        elif(ticket.type=="1A"):
            final_seat=seat.first_ac+c
            seat.first_ac=final_seat
            seat.save()
        elif(ticket.type=="2A"):
            final_seat=seat.second_ac+c
            seat.second_ac=final_seat
            seat.save()
        elif(ticket.type=="3A"):
            final_seat=seat.third_ac+c
            seat.third_ac=final_seat
            seat.save()
        return Response("cancelled")


class SearchTrain(APIView):
    def post(self,request):
        source=request.data['source']
        dest=request.data['dest']
        date=request.data['date']
        
        train=Train.objects.filter(source=source, dest=dest).values()
        seat=Seat_Chart.objects.filter(date=date,).values()
        t=[]
        for i in range(len(train)):
            for j in range(len(seat)):
                if(train[i]['train_no']==seat[j]['train_id']):
                    # print(train[i],seat[j])
                    x = train[i]['duration_h']+train[i]['source_time'].hour
                    y = train[i]['duration_m']+train[i]['source_time'].minute

                    print(x,y)
                    data={
                        'train_name':train[i]['train_name'],
                        'train_no':train[i]['train_no'],
                        'source':train[i]['source_id'],
                        'source_time':train[i]['source_time'],
                        'dest':train[i]['dest_id'],
                        'dest_time':train[i]['dest_time'],
                        'duration_h':train[i]['duration_h'],
                        'duration_m':train[i]['duration_m'],
                        'distance':train[i]['distance'],
                        'date':seat[j]['date'],
                        'sleeper':seat[j]['sleeper'],
                        'first_ac':seat[j]['first_ac'],
                        'second_ac':seat[j]['second_ac'],
                        'third_ac':seat[j]['third_ac'],
                        'seat_id':seat[j]['id'],
                        'dest_date':seat[j]['date']+timedelta(hours=x,minutes=y)
                        
                    }
                    t.append(data)

        return Response(t)
