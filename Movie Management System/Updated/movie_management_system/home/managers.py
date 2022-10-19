import datetime

from home.serializer import TheatreSerializer,SnacksSerializer,MovieSerializer,UserSerializer,CartSerializer,HallSerializer,SeatingSerializer,BookingSerializer,BillSerializer,PromocodeSerializer,PaymentSerializer,DatewiseHallSerializer
from .models import Movie,Theatre,Snacks,Users,Bill,Booking,Cart,PROMOCODE,Hall,Seating,Payment,DatewiseHall
from rest_framework.exceptions import AuthenticationFailed


def getSelectedSeats(id):
    hallid = id
    if not hallid:
        raise AuthenticationFailed("failed")
    booking = Booking.objects.filter(Hall_id=hallid)
    serializer = BookingSerializer(booking, many=True)
    return(serializer.data)


def getTicket(id):
    User_id = id
    print(id)
    if not User_id:
        raise AuthenticationFailed("failed")
    print("TICKET IS IS", User_id)
    user=Users.objects.filter(User_id=User_id)
    booking = Booking.objects.filter(User_id=User_id).last()
    theatre=Theatre.objects.filter(Theatre_id=booking.Theatre_id.Theatre_id)
    movie=Movie.objects.filter(Movie_id=booking.Movie_id.Movie_id)
    information={
        'User_name':user[0].User_name,
        'User_phone':user[0].User_phone,
        'Theatre_name':theatre[0].Theatre_name,
        'Theatre_location':theatre[0].Theatre_location,
        'Hall_no':booking.Hall_id.Hall_no,
        'Movie_name':movie[0].Movie_name,
        'Movie_lang':movie[0].Movie_lang,
        'Movie_category':movie[0].Movie_category,
        'Date':booking.Date,
        'SelectedSeats':booking.Selected_seats,
        'Movie_poster':movie[0].Movie_poster
    }
    return(information)


def getBookingHistory(id):
    User_id = id
    print(id)
    if not User_id:
        raise AuthenticationFailed("failed")
    print("TICKET IS IS", User_id)
    booking = Booking.objects.filter(User_id=User_id)
    serializer = BookingSerializer(booking, many=True)
    return (serializer.data)

def getHallsFilteredByDate(date):
    halls = DatewiseHall.objects.filter(Date=date)  # gets all the halls displaying movies on that day
    serializer=DatewiseHallSerializer(halls,many=True)
    return (serializer.data)

def getHallsFilteredByMovie(id):
    halls = Hall.objects.filter(Movie_id=id)

    if len(halls)>0:
        serializer = HallSerializer(halls, many=True)
        return serializer.data
    else:
        raise AuthenticationFailed("Invalid id or no halls available")


def getSingleTheatrebyId(id):
    theatre = Theatre.objects.filter(Theatre_id=id)
    serializer = TheatreSerializer(theatre)
    return(serializer.data)


def getDataFilteredByHallandDate(id,date):
    hall = DatewiseHall.objects.filter(Hall_id=id, Date=date).first()
    return (hall)


def hallFilteredByDate(id,date):
    hall = DatewiseHall.objects.filter(Hall_id=id, Date=date).first()
    serializer = DatewiseHallSerializer(hall)
    return (serializer.data)

def cancelTickets(id):
    ticket = Booking.objects.filter(User_id=id).last()
    if(ticket.Date is datetime.datetime.today):
        hall1 = DatewiseHall.objects.filter(Hall_id=ticket.Hall_id, Date=ticket.Date).first()
        count = 1
        for i in range(len(ticket.Selected_seats)):
            if ticket.Selected_seats[i] == ',':
                count = count + 1
        hall1.T_No_Of_Seats = hall1.T_No_Of_Seats + count
        print(hall1.T_No_Of_Seats)
        hall1.save()
        print(ticket.Selected_seats)
        ticket.delete()
    else:
        return("not happening")


def getowners():
    users=Users.objects.filter(Role='TheatreOwner')
    serializer=UserSerializer(users,many=True)
    return serializer.data


def langmovies(lang):
    movies=Movie.objects.filter(Movie_lang=lang)
    serializer = MovieSerializer(movies, many=True)
    return serializer.data


def ownertheatre(role):
    if(role=='Admin'):
        theatre=Theatre.objects.all()
    else:

        theatre = Theatre.objects.filter(Theatre_owner="owner")
    serializer = TheatreSerializer(theatre, many=True)
    return (serializer.data)


def theatrelist(movie_id,date):
    hall = Hall.objects.filter(Movie_id=movie_id)
    print(hall)
    info = []
    for i in range(len(hall)):
        dates = DatewiseHall.objects.filter(Hall_id=hall[i].Hall_id, Date=date)
        print((date))
        if(len(dates)!=0):
            information = {
                'Theatre_name': hall[i].Theatre_id.Theatre_name,
                'Theatre_location': hall[i].Theatre_id.Theatre_location,
                'Hall_no': hall[i].Hall_no,
                'startTime': hall[i].startTime,
                'seats': dates[0].T_No_Of_Seats,
                'Hall_id': hall[i].Hall_id
            }
            info.append(information)
    return info



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


