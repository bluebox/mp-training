"View page"
# from time import strptime

from rest_framework import exceptions

from django.core.exceptions import ValidationError
from django.utils.translation import gettext_lazy as _
from django.http import HttpResponse
from rest_framework.response import Response
import jwt
from rest_framework.exceptions import AuthenticationFailed
from rest_framework.views import APIView
from rest_framework import viewsets
from home.serializer import TheatreSerializer,MovieSerializer,UserSerializer,CartSerializer,\
    HallSerializer,SeatingSerializer,BookingSerializer,BillSerializer,PromocodeSerializer,\
    PaymentSerializer,DatewiseHallSerializer
from .models import Movie,Theatre,Users,Bill,Booking,Cart,PROMOCODE,Hall,Seating,Payment,\
    DatewiseHall
import datetime
from .managers import SeatsManager,MovieManager,TicketManager,TheatreManager,HallManager,UserManager


'FIRST'# Create your views here.
def index(request):
    return HttpResponse("Movie Management System")


def validate_Phone(value):
    if len(value) < 10:
        raise ValidationError(
            _('%(value)s is not a right phone number'),
            params={'value': value},
        )


class MovieViewset(viewsets.ModelViewSet):
    serializer_class = MovieSerializer
    queryset = Movie.objects.all()
    serializer = MovieSerializer(queryset, many=True)


class TheatreViewset(viewsets.ModelViewSet):
    serializer_class = TheatreSerializer
    queryset = Theatre.objects.all()
    serializer = TheatreSerializer(queryset, many=True)


class UserViewset(viewsets.ModelViewSet):
    serializer_class = UserSerializer
    queryset = Users.objects.all()
    serializer = UserSerializer(queryset, many=True)


class HallViewset(viewsets.ModelViewSet):
    serializer_class = HallSerializer
    queryset = Hall.objects.all()
    serializer = HallSerializer(queryset, many=True)


class SeatingViewset(viewsets.ModelViewSet):
    serializer_class = SeatingSerializer
    queryset = Seating.objects.all()
    serializer = SeatingSerializer(queryset, many=True)


class BookingViewset(viewsets.ModelViewSet):
    serializer_class = BookingSerializer
    queryset = Booking.objects.all()
    serializer = BookingSerializer(queryset, many=True)


class BillViewset(viewsets.ModelViewSet):
    serializer_class = BillSerializer
    queryset = Bill.objects.all()
    serializer = BillSerializer(queryset,many=True)


class PromomcodeViewset(viewsets.ModelViewSet):
    serializer_class = PromocodeSerializer
    queryset = PROMOCODE.objects.all()
    serializer = PromocodeSerializer(queryset,many=True)


class CartViewset(viewsets.ModelViewSet):
    serializer_class = CartSerializer
    queryset = Cart.objects.all()
    serializer = CartSerializer(queryset,many=True)


class PaymentViewset(viewsets.ModelViewSet):
    serializer_class = PaymentSerializer
    queryset = Payment.objects.all()
    serializer = PaymentSerializer(queryset,many=True)


class DateViewset(viewsets.ModelViewSet):
    serializer_class = DatewiseHallSerializer
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
        print(Users.objects.all())
        user=Users.objects.filter(User_email=email).first()
        if user is None:
            raise AuthenticationFailed("User not found")
        payload={
            'id':user.User_id,
            "exp":datetime.datetime.utcnow()+datetime.timedelta(minutes=60),
            'iat':datetime.datetime.utcnow()
        }
        token=jwt.encode(payload,'secret',algorithm='HS256').decode('utf-8')
        response=Response()
        if user.password != password:
            raise exceptions.APIException("Incorrect password")
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
            payload=jwt.decode(token,'secret',algorithm=['HS256'])
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
        res=SeatsManager.get_selected_seats(id)
        return Response(res)


class Ticket(APIView):
    def get(self,request,id):
        return Response(TicketManager.get_ticket(id))

    def delete(self,request,id):
        res = TicketManager.get_ticket(id)
        print(res)
        response=Response()
        response.data={
            'message':'deleted'
        }
        return response


class BookingHistory(APIView):
    def get(self,request,id,data):
        res=TicketManager.get_booking_history(id,data)
        print("res",res)
        return Response(res)


class GetFilteredData(APIView):
    def get(self,request,date):
        try:
            res = HallManager.get_halls_filtered_by_date(date)
            return Response(res)
        except:
            raise AuthenticationFailed("date entry does not exist")


class GetFilteredHalls(APIView):
    def get(self,request,id):
        res=HallManager.get_halls_filtered_by_movie(id)
        return Response(res)

class GetSingleTheatre(APIView):
    def get(self,request,id):
        try:
            res=TheatreManager.getSingleTheatrebyId(id)
        except:
            raise AuthenticationFailed("invalid id")
        return Response(res)


class DeductSeats(APIView):
    def put(self,request,id,date):
        res=HallManager.get_data_filtered_by_hallandDate(id,date)
        serializer=DatewiseHallSerializer(instance=res,data=request.data)
        if serializer.is_valid():
            serializer.save()
        return Response(serializer.data)


class GetSingleHall(APIView):
    def get(self,request,id,date,stime):
        try:
            time=datetime.datetime.strptime(stime,'%H:%M:%S')
            print(time)
            res=HallManager.hallFilteredByDate(id,date,time)
        except:
            raise AuthenticationFailed("invalid data")
        return Response(res)


class Cancel(APIView):
    def post(self,request,id,bid):
        TicketManager.cancel_tickets(id,bid)
        return Response("ticket cancelled")


class CateogorisedMovies(APIView):
    def get(self,request,lang):
        try:
            movies=MovieManager.langmovies(lang)
            return Response(movies)
        except:
            raise AuthenticationFailed("some error occurred")


class GetOwners(APIView):
    def get(self,request):
        try:
            user=UserManager.getowners()
            return Response(user)
        except:
            raise AuthenticationFailed("No owners found")


class OwnersTheatre(APIView):
    def get(self,request,owner):
        try:
            theatres=TheatreManager.ownertheatre(owner)
            return Response(theatres)
        except:
            raise AuthenticationFailed("something went wrong")


class TheatreList(APIView):
    def get(self,request,id,date):
        # try:
        #     info=TheatreManager.theatrelist(id,date)
        #     return Response(info)
        # except:
        #     raise AuthenticationFailed("something failed in theatrelist")
        info = TheatreManager.theatrelist(id, date)
        return Response(info)


class HallList(APIView):
    def get(self,request,id_):
        try:
            info=HallManager.halllist(id_)
            return Response(info)
        except:
            raise AuthenticationFailed("halllist failed")


class GetCancelledTickets(APIView):
    def get(self,request,user_id):
        # try:
        info=TicketManager.get_cancelled_tickets(user_id)
        return Response(info)
        # except:
        #     raise AuthenticationFailed("cancelled tickets failed")


class SearchMovie(APIView):
    def get(self,request,a):
        res=MovieManager.searchmovies(a)
        return Response(res)


class SearchTheatre(APIView):
    'Search theatre based on movie id,date and the input '
    def get(self,request,movie_id,date,a):
        try:
            tlist=TheatreManager.searchtheatre(movie_id=movie_id,date=date,a=a)
            return Response(tlist)
        except:
            raise AuthenticationFailed("search issue")


class GetTop(APIView):
    def get(self,request):
        movies=Movie.objects.all()[0:6]
        serializer=MovieSerializer(movies,many=True)
        return Response(serializer.data)


class ObjectCreation(APIView):
    'create objects'
    def post(self,request):
        for i in range(2):
            obj={
                "Date":'2022-11-4',
                "T_No_Of_Seats": 100,
                "startTime": "16:00:00",
                "endTime": "19:00:00",
                "Hall_id": i+14,
                "Movie_id": i+10
            }
            ser=DatewiseHallSerializer(data=obj)
            ser.is_valid()
            ser.save()
        return Response("sucess")


'filter'
class Filter(APIView):
    def get(self,request,flist,search):
        l=flist.split(",")
        print(l)
        return Response(MovieManager.filtermovies(l,search))


'Pagination'


class Pagination(APIView):
    def get(self,request,pno):

        return Response(MovieManager.get_page(pno))


class test(APIView):
    def get(self,request,id):
        return Response(TicketManager.test(id))