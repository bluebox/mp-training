'Managers file'
import datetime
from rest_framework.exceptions import AuthenticationFailed
from django.db.models import Q
from home.serializer import TheatreSerializer,MovieSerializer,UserSerializer,\
    HallSerializer,BookingSerializer,DatewiseHallSerializer
from .models import Movie,Theatre,Users,Booking,Hall,DatewiseHall


class SeatsManager:
    'functions related to seats'
    @staticmethod
    def get_selected_seats(id_):
        'gets booking records to fetch the previously selected seats'
        hallid = id_
        if not hallid:
            raise AuthenticationFailed("hall id failed")
        booking = Booking.objects.filter(Hall_id=hallid,Booking_status='Booked')
        serializer = BookingSerializer(booking, many=True)
        return serializer.data



class TicketManager:
    'functions related to tickets'
    @staticmethod
    def get_ticket(id_):
        'returns tickets'
        user_id = id_
        print(id)
        if not user_id:
            raise AuthenticationFailed("failed")

        booking = Booking.objects.filter(User_id=user_id,Booking_status='Booked').last()
        information={
            'Booking_id':booking.Booking_id,
            'User_name':booking.User_id.User_name,
            'User_phone':booking.User_id.User_phone,
            'Theatre_name':booking.Theatre_id.Theatre_name,
            'Theatre_location':booking.Theatre_id.Theatre_location,
            'Hall_no':booking.Hall_id.Hall_no,
            'Movie_name':booking.Movie_id.Movie_name,
            'Movie_lang':booking.Movie_id.Movie_lang,
            'Movie_category':booking.Movie_id.Movie_category,
            'Date':booking.Date,
            'SelectedSeats':booking.Selected_seats,
            'Movie_poster':booking.Movie_id.Movie_poster,
            'Booking_status':booking.Booking_status
        }
        return information

    @staticmethod
    def get_booking_history(id_,data):
        'gets upcoming and previously booked tickets'
        user_id = id_
        info=[]
        if not user_id:
            raise AuthenticationFailed("failed")
        query=(Q(User_id=user_id))
        if data=='upcoming':
            query=query & (Q(Date__gte =datetime.date.today())) & Q(Booking_status='Booked')
        elif data=='previous':
            query = query & (Q(Date__lte=datetime.date.today())) & Q(Booking_status='Booked')
        else:
            query = query & (Q(Booking_status='Cancelled'))
        bookings = Booking.objects.filter(query)
        for booking in bookings:
            print("working")
            information = {
                'Booking_id': booking.Booking_id,
                'User_name': booking.User_id.User_name,
                'User_phone': booking.User_id.User_phone,
                'Theatre_name': booking.Theatre_id.Theatre_name,
                'Theatre_location': booking.Theatre_id.Theatre_location,
                'Hall_no': booking.Hall_id.Hall_no,
                'Movie_name': booking.Movie_id.Movie_name,
                'Movie_lang': booking.Movie_id.Movie_lang,
                'Movie_category': booking.Movie_id.Movie_name,
                'Date': booking.Date,
                'SelectedSeats': booking.Selected_seats,
                'Movie_poster': booking.Movie_id.Movie_poster,
                'Booking_status': booking.Booking_status

            }
            info.append(information)
        print(info)
        return info

    @staticmethod
    def cancel_tickets(id_, bid):
        'cancel tickets'
        ticket = Booking.objects.filter(User_id=id_, Booking_id=bid).last()
        t_date= datetime.datetime.today().strftime('%Y-%m-%d')
        if str(ticket.Date) >= t_date:
            hall1 = DatewiseHall.objects.filter(Hall_id=ticket.Hall_id, Date=ticket.Date).first()
            print(hall1)
            count = 1
            for i in range(len(ticket.Selected_seats)):
                if ticket.Selected_seats[i] == ' ':
                    count = count + 1
            hall1.T_No_Of_Seats = hall1.T_No_Of_Seats + count
            print(hall1.T_No_Of_Seats)
            hall1.save()
            print(ticket.Selected_seats)
            ticket.Booking_status = 'Cancelled'
            print(ticket.Booking_status)
            ticket.save()
        else:
            raise AuthenticationFailed("cannot cancel tickets from previous date")

    @staticmethod
    def get_cancelled_tickets(id_):
        'check functionality before removing'
        booking = Booking.objects.select_related('User_id').filter(User_id=id_, Booking_status='Cancelled')
        info = []
        for i in range(len(booking)):
            information = {
                'Booking_id': booking[i].Booking_id,
                'User_name': booking[i].User_id.User_name,
                'User_phone': booking[i].User_id.User_phone,
                'Theatre_name': booking[i].Theatre_id.Theatre_name,
                'Theatre_location': booking[i].Theatre_id.Theatre_location,
                'Hall_no': booking[i].Hall_id.Hall_no,
                'Movie_name': booking[i].Movie_id.Movie_name,
                'Movie_lang': booking[i].Movie_id.Movie_lang,
                'Movie_category': booking[i].Movie_id.Movie_name,
                'Date': booking[i].Date,
                'SelectedSeats': booking[i].Selected_seats,
                'Movie_poster': booking[i].Movie_id.Movie_poster,
                'Booking_status': booking[i].Booking_status
            }
            info.append(information)

        return "info"
    @staticmethod
    def test(id):
        booking = Users.objects.values('User_name','Role')
        serializer=UserSerializer(booking,many=True)
        return serializer.data


class HallManager:
    'functions related to halls'
    @staticmethod
    def get_halls_filtered_by_date(date):
        ' gets all the halls displaying movies on that day'
        halls = DatewiseHall.objects.filter(Date=date)
        serializer=DatewiseHallSerializer(halls,many=True)
        return serializer.data

    @staticmethod
    def get_halls_filtered_by_movie(id_):
        'gets halls filtered by movie id'
        halls = Hall.objects.filter(Movie_id=id_)
        if len(halls)>0:
            serializer = HallSerializer(halls, many=True)
            return serializer.data
        else:
            raise AuthenticationFailed("Invalid id or no halls available")

    @staticmethod
    def get_data_filtered_by_hallandDate(id, date):
        'gets records from datewise hall'
        hall = DatewiseHall.objects.filter(Hall_id=id, Date=date).first()
        return hall

    @staticmethod
    def hallFilteredByDate(id, date):
        hall = DatewiseHall.objects.filter(Hall_id=id, Date=date).first()
        serializer = DatewiseHallSerializer(hall)
        return serializer.data

    @staticmethod
    def halllist(id):
        user = Users.objects.filter(User_id=id).first()
        hall = []
        if user.Role == 'Admin':
            halls = Hall.objects.all()
            serializer = HallSerializer(halls, many=True)
            return serializer.data
        else:
            theatres = Theatre.objects.filter(Theatre_owner=user.User_name)
            for i in range(len(theatres)):
                h = (Hall.objects.filter(Theatre_id=theatres[i].Theatre_id))
                for j in range(len(h)):
                    info = {
                        "Hall_id": h[j].Hall_id,
                        "Hall_no": h[j].Hall_no,
                        "T_No_Of_Seats": h[j].T_No_Of_Seats,
                        "Date": h[j].Date,
                        "startTime": h[j].startTime,
                        "endTime": h[j].endTime,
                        "rows": h[j].rows,
                        "cols": h[j].cols,
                        "baseprice": h[j].baseprice,
                        "Theatre_id": h[j].Theatre_id.Theatre_id,
                        "Movie_id": h[j].Movie_id.Movie_id
                    }
                    hall.append(info)
            return hall

class TheatreManager:
    'functions related to theatres'
    @staticmethod
    def getSingleTheatrebyId(id):
        theatre = Theatre.objects.filter(Theatre_id=id)
        serializer = TheatreSerializer(theatre)
        return serializer.data

    @staticmethod
    def ownertheatre(role):
        if role == 'Admin':
            theatre = Theatre.objects.all()
        else:

            theatre = Theatre.objects.filter(Theatre_owner="owner")
        serializer = TheatreSerializer(theatre, many=True)
        return serializer.data

    @staticmethod
    def theatrelist(movie_id, date):
        hall = DatewiseHall.objects.filter(Movie_id=movie_id, Date=date)
        print(len(hall))
        info = []
        for i in range(len(hall)):
            print(hall[i].Hall_id.Theatre_id.Theatre_name)
            # screen = Hall.objects.filter(Hall_id=hall[i].Hall_id)
            # print((screen))
            information = {
                'Theatre_name': hall[i].Hall_id.Theatre_id.Theatre_name,
                'Theatre_location':  hall[i].Hall_id.Theatre_id.Theatre_location,
                'Hall_no':  hall[i].Hall_id.Hall_no,
                'startTime': hall[i].startTime,
                'seats': hall[i].T_No_Of_Seats,
                'Hall_id': hall[i].Hall_id.Hall_id
            }
            info.append(information)
            print(information)
        print(info)
        return info

    @staticmethod
    def searchtheatre(movie_id, date, a):
        hall = DatewiseHall.objects.filter(Movie_id=movie_id, Date=date, Hall_id__Theatre_id__Theatre_name__icontains=a)
        info = []
        print(hall)
        for i in range(len(hall)):
            print(hall[i].Hall_id.Theatre_id.Theatre_name)
            # screen = Hall.objects.filter(Hall_id=hall[i].Hall_id)
            # print((screen))
            if len(hall) != 0:
                # if a in hall[i].Hall_id.Theatre_id.Theatre_name:
                information = {
                    'Theatre_name': hall[i].Hall_id.Theatre_id.Theatre_name,
                    'Theatre_location': hall[i].Hall_id.Theatre_id.Theatre_location,
                    'Hall_no': hall[i].Hall_id.Hall_no,
                    'startTime': hall[i].startTime,
                    'seats': hall[i].T_No_Of_Seats,
                    'Hall_id': hall[i].Hall_id.Hall_id
                }
                info.append(information)
        print(info)
        return info

class MovieManager:
    'functions related to movies'
    @staticmethod
    def langmovies(lang):
        movies = Movie.objects.filter(Movie_lang=lang)
        serializer = MovieSerializer(movies, many=True)
        return serializer.data

    @staticmethod
    def searchmovies(a):
        movies = Movie.objects.filter(Q(Movie_name__in=a) | Q(Movie_lang__in=a))
        serializer = MovieSerializer(movies, many=True)
        return serializer.data

    @staticmethod
    def filtermovies(flist,search):
        if flist[0]=="Empty":
            query=Q(Movie_name__icontains=search)
        else:
            query=Q(Movie_name__icontains=search) & Q(Movie_lang__in=flist)
        if search=="empty":
            query=Q(Movie_lang__in=flist)

        movies=Movie.objects.filter(query)
        serializer=MovieSerializer(movies,many=True)
        return serializer.data

    @staticmethod
    def get_page(pno):
        max_length = Movie.objects.all().count()
        count=(max_length+(6-(max_length%6)))
        print(count)
        e = pno * 6
        if e <=count:
            if e > max_length:
                e = max_length
            movies = Movie.objects.all()[0:e]
            serializer = MovieSerializer(movies, many=True)
            return serializer.data
        else:
            raise AuthenticationFailed("done")


class UserManager:
    """functions related to users"""
    @staticmethod
    def getowners():
        users=Users.objects.filter(Role='TheatreOwner')
        serializer=UserSerializer(users,many=True)
        return serializer.data
