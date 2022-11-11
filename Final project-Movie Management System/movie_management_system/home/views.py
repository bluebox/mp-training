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
        user=Users.objects.filter(User_email=email).first()
        if user is None:
            raise AuthenticationFailed("User not found")
        if user.password != password:
            raise exceptions.APIException("Incorrect password")
        payload={
            'id':user.User_id,
            "exp":datetime.datetime.utcnow()+datetime.timedelta(minutes=60),
            'iat':datetime.datetime.utcnow()
        }
        request.session['user_id'] = user.User_id
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
        del request.session['user_id']
        request.session.flush()
        response=Response()
        response.delete_cookie('jwt')
        response.data={
            'message':"sucess"
        }
        return response


class SelectedSeats(APIView):
    def get(self,id,request):
        # print(request.session.get('user_id', None))
        try:
            # uid = request.session.get('user_id', None)
            res=SeatsManager.get_selected_seats(id)
            return Response(res)
        except:
            raise exceptions.APIException("check get_selected seats")


class Ticket(APIView):
    def get(self,request):
        try:
            uid=request.session.get('user_id', None)
            return Response(TicketManager.get_ticket(uid))
        except:
            raise exceptions.APIException("get ticket error")

    def delete(self,request):
        uid = request.session.get('user_id', None)
        res = TicketManager.get_ticket(uid)
        print(res)
        response=Response()
        response.data={
            'message':'deleted'
        }
        return response


class BookingHistory(APIView):
    def get(self,request,data):
        try:
            uid = request.session.get('user_id', None)
            res=TicketManager.get_booking_history(uid,data)
            print("res",res)
            return Response(res)
        except:
            raise exceptions.APIException("get booking history error")


class GetFilteredData(APIView):
    def get(self,request,date):
        try:
            res = HallManager.get_halls_filtered_by_date(date)
            return Response(res)
        except:
            raise exceptions.APIException("date entry does not exist")


class GetFilteredHalls(APIView):
    def get(self,request,id):
        try:
            res=HallManager.get_halls_filtered_by_movie(id)
            return Response(res)
        except:
            raise exceptions.APIException("hall filtered by movie error")

class GetSingleTheatre(APIView):
    def get(self,request,id):
        try:
            res=TheatreManager.getSingleTheatrebyId(id)
        except:
            raise exceptions.APIException("invalid id")
        return Response(res)


class DeductSeats(APIView):
    def put(self,request,id,date):
        print(request.data)
        mid=request.data['Movie_id']
        res=HallManager.get_data_filtered_by_hallandDate(id,date,mid)
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
            return Response(res)
        except:
            raise exceptions.APIException("invalid data")



class Cancel(APIView):
    def post(self,request,bid):
        uid = request.session.get('user_id', None)
        TicketManager.cancel_tickets(uid,bid)
        return Response("ticket cancelled")


class CateogorisedMovies(APIView):
    def get(self,request,lang):
        try:
            movies=MovieManager.langmovies(lang)
            return Response(movies)
        except:
            raise exceptions.APIException("some error occurred")


class GetOwners(APIView):
    def get(self,request):
        try:
            user=UserManager.getowners()
            return Response(user)
        except:
            raise exceptions.APIException("No owners found")


class OwnersTheatre(APIView):
    def get(self,request,owner):
        try:
            theatres=TheatreManager.ownertheatre(owner)
            return Response(theatres)
        except:
            raise exceptions.APIException("something went wrong")


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
            raise exceptions.APIException("halllist failed")


class GetCancelledTickets(APIView):
    def get(self,request,user_id):
        # try:
        uid = request.session.get('user_id', None)
        info=TicketManager.get_cancelled_tickets(uid)
        return Response(info)
        # except:
        #     raise exceptions.APIException("cancelled tickets failed")


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
            raise exceptions.APIException("search issue")


class GetTop(APIView):
    def get(self,request):
        try:
            movies=Movie.objects.all()[0:6]
            serializer=MovieSerializer(movies,many=True)
            return Response(serializer.data)
        except:
            raise exceptions.APIException("get top movies error")


class ObjectCreation(APIView):
    'create objects'
    def post(self,request):
        for i in range(4):
            obj={
                "Date":'2022-11-8',
                "T_No_Of_Seats": 100,
                "startTime": "14:00:00",
                "endTime": "17:00:00",
                "Hall_id": i+14,
                "Movie_id": i+6
            }
            ser=DatewiseHallSerializer(data=obj)
            ser.is_valid()
            ser.save()
        return Response("sucess")


class Filter(APIView):
    'filter'
    def get(self,request,flist,search):
        try:
            l=flist.split(",")
            print(l)
            return Response(MovieManager.filtermovies(l,search))
        except:
            raise exceptions.APIException("filter error")


class Pagination(APIView):
    'Pagination'
    def get(self,request,pno):
        try:
            return Response(MovieManager.get_page(pno))
        except:
            raise exceptions.APIException("pagination error")


class test(APIView):
    def get(self,request,id):
        return Response(TicketManager.test(id))

class GetAllBookings(APIView):
    def get(self,request,id):
        uid = request.session.get('user_id', None)
        previous=TicketManager.get_booking_history(id,"previous")
        upcoming=TicketManager.get_booking_history(id,"upcoming")
        cancelled=TicketManager.get_booking_history(id,"cancelled")
        last=TicketManager.get_ticket(id)
        res=[previous,cancelled,last,upcoming]
        return Response(res)