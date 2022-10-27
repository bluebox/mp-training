from django.shortcuts import render
from django.http import HttpResponse
from django.core.exceptions import ValidationError
from django.utils.translation import gettext_lazy as _
from django.http import HttpResponse
from rest_framework.decorators import api_view
from rest_framework.response import Response
from home.serializer import TheatreSerializer,MovieSerializer,UserSerializer,CartSerializer,HallSerializer,SeatingSerializer,BookingSerializer,BillSerializer,PromocodeSerializer,PaymentSerializer,DatewiseHallSerializer
from .models import Movie,Theatre,Users,Bill,Booking,Cart,PROMOCODE,Hall,Seating,Payment,DatewiseHall
from rest_framework.views import APIView
from rest_framework import viewsets
from rest_framework.exceptions import AuthenticationFailed
from rest_framework.permissions import IsAuthenticated
import jwt
import datetime
from .managers import getSelectedSeats,getTicket,getBookingHistory,getHallsFilteredByDate,getHallsFilteredByMovie,getDataFilteredByHallandDate,getSingleTheatrebyId,hallFilteredByDate,cancelTickets,getowners,langmovies,ownertheatre,theatrelist,halllist,getcancelledtickets,searchmovies,searchtheatre
from django.core.mail import send_mail

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


# class SnacksViewset(viewsets.ModelViewSet):
#     serializer_class=SnacksSerializer
#     queryset = Snacks.objects.all()
#     serializer = SnacksSerializer(queryset,many=True)


class UserViewset(viewsets.ModelViewSet):
    serializer_class=UserSerializer
    queryset = Users.objects.all()
    serializer = UserSerializer(queryset,many=True)


class HallViewset(viewsets.ModelViewSet):
    serializer_class= HallSerializer
    queryset = Hall.objects.all()
    serializer = HallSerializer(queryset,many=True)


class SeatingViewset(viewsets.ModelViewSet):
    serializer_class=SeatingSerializer
    queryset = Seating.objects.all()
    serializer = SeatingSerializer(queryset,many=True)


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


class DateViewset(viewsets.ModelViewSet):
    serializer_class=DatewiseHallSerializer
    queryset = DatewiseHall.objects.all()
    serializer = DatewiseHallSerializer(queryset,many=True)


class Register(APIView):
    def post(self,request):
        serializer=UserSerializer(data=request.data)
        serializer.is_valid(raise_exception=True)
        serializer.save()
        return Response(serializer.data)

    def get(self,request):
        serializer = UserSerializer()
        queryset = Users.objects.all()
        serializer = UserSerializer(queryset, many=True)
        return Response(serializer.data)


class Login(APIView):
    def post(self,request):
        email=request.data['User_email']
        password=request.data['password']
        print(email, password)
        print(Users.objects.all())
        user=Users.objects.filter(User_email=email).first()
        print(user.password)
        if user is None:
            raise AuthenticationFailed("user not found")
        if user.password != password:
            raise AuthenticationFailed("incorrect password")
        payload={
            'id':user.User_id,
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
        user = Users.objects.filter(User_id=payload['id']).first()
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


class SelectedSeats(APIView):
    def get(self,request,id):
        res=getSelectedSeats(id)
        return Response(res)


class Ticket(APIView):
    def get(self,request,id):
        return Response(getTicket(id))

    def delete(self,request,id):
        res = getTicket(id)
        print(res)
        response=Response()
        response.data={
            'message':'deleted'
        }
        return response


class BookingHistory(APIView):
    def get(self,request,id,data):
        res=getBookingHistory(id,data)
        print("res",res)
        return Response(res)


class GetFilteredData(APIView):
    def get(self,request,date):
        try:
            res = getHallsFilteredByDate(date)
            return Response(res)
        except:
            raise AuthenticationFailed("date entry does not exist")


class GetFilteredHalls(APIView):
    def get(self,request,id):
       res=getHallsFilteredByMovie(id)
       return Response(res)


class GetSingleTheatre(APIView):
    def get(self,request,id):
        try:
            res=getSingleTheatrebyId(id)
        except:
            raise AuthenticationFailed("invalid id")
        return Response(res)


class DeductSeats(APIView):
    def put(self,request,id,date):
        res=getDataFilteredByHallandDate(id,date)
        serializer=DatewiseHallSerializer(instance=res,data=request.data)
        if(serializer.is_valid()):
            serializer.save()
        return Response(serializer.data)


class GetSingleHall(APIView):
    def get(self,request,id,date):
        try:
            res=hallFilteredByDate(id,date)
        except:
            raise AuthenticationFailed("invalid data")
        return Response(res)


class Cancel(APIView):
    def post(self,request,id,bid):
        cancelTickets(id,bid)
        return Response("ticket cancelled")


class cateogorisedMovies(APIView):
    def get(self,request,lang):
        try:
            movies=langmovies(lang)
            return Response(movies)
        except:
            raise AuthenticationFailed("some error occurred")


class GetOwners(APIView):
    def get(self,request):
        try:
            user=getowners()
            return Response(user)
        except:
            raise AuthenticationFailed("No owners found")


class OwnersTheatre(APIView):
    def get(self,request,owner):
        try:
            theatres=ownertheatre(owner)
            return Response(theatres)
        except:
            raise AuthenticationFailed("something went wrong")


class TheatreList(APIView):
    def get(self,request,id,date):
        try:
            info=theatrelist(id,date)
            return Response(info)
        except:
            raise AuthenticationFailed("something failed in theatrelist")


class HallList(APIView):
    def get(self,request,id):
        try:
            info=halllist(id)
            return Response(info)
        except:
            raise AuthenticationFailed("halllist failed")

class GetCancelledTickets(APIView):
    def get(self,request,user_id):
        try:
            info=getcancelledtickets(user_id)
            return Response(info)
        except:
            raise AuthenticationFailed("cancelled tickets failed")

class searchMovie(APIView):
    def get(self,request,a):
        res=searchmovies(a)
        return Response(res)

class searchTheatre(APIView):
    def get(self,request,movie_id,date,a):
        try:
            tlist=searchtheatre(movie_id=movie_id,date=date,a=a)
            return Response(tlist)
        except:
            raise AuthenticationFailed("search issue")


class getTop(APIView):
    def get(self,request):
        movies=Movie.objects.all()[0:6]
        serializer=MovieSerializer(movies,many=True)
        return Response(serializer.data)


class objectcreation(APIView):
    def post(self,request):
        for i in range(4):
            obj={
            "Date":'2022-10-28',
            "T_No_Of_Seats": 100,
            "startTime": "20:30:00",
            "endTime": "23:30:00",
            "Hall_id": i+14,
            "Movie_id": i+6
            }
            ser=DatewiseHallSerializer(data=obj)
            ser.is_valid()
            ser.save()
        return Response("sucess")

class getAllHistory(APIView):
    def get(self,request,user_id):
        info=[]
        info1=getcancelledtickets(user_id)
        info2=getTicket(user_id)
        info3=getBookingHistory(user_id,"previous")
        info4=getBookingHistory(user_id,"upcoming")
        info.append(info1)
        info.append(info2)
        info.append((info3))
        info.append(info4)
        return Response(info)


