import datetime
from home.serializer import TheatreSerializer,MovieSerializer,UserSerializer,CartSerializer,HallSerializer,SeatingSerializer,BookingSerializer,BillSerializer,PromocodeSerializer,PaymentSerializer,DatewiseHallSerializer
from .models import Movie,Theatre,Users,Bill,Booking,Cart,PROMOCODE,Hall,Seating,Payment,DatewiseHall
from rest_framework.exceptions import AuthenticationFailed
from django.db.models import Q

#seats
def getSelectedSeats(id):
    hallid = id
    if not hallid:
        raise AuthenticationFailed("hall id failed")
    booking = Booking.objects.filter(Hall_id=hallid,Booking_status='Booked')
    serializer = BookingSerializer(booking, many=True)
    return(serializer.data)

#tickets
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
        'Booking_id':booking.Booking_id,
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
        'Movie_poster':movie[0].Movie_poster,
        'Booking_status':booking.Booking_status
    }
    return(information)

#ticket
def getBookingHistory(id,data):
    User_id = id
    info=[]
    user = Users.objects.filter(User_id=User_id).first()
    if not User_id:
        raise AuthenticationFailed("failed")
    if(data=='upcoming'):
        booking = Booking.objects.filter(User_id=User_id,Date__gte =datetime.date.today(),Booking_status='Booked')
        for i in range(len(booking)):
            print("working")
            information = {
                'Booking_id':booking[i].Booking_id,
                'User_name': user.User_name,
                'User_phone': user.User_phone,
                'Theatre_name': booking[i].Theatre_id.Theatre_name,
                'Theatre_location': booking[i].Theatre_id.Theatre_location,
                'Hall_no': booking[i].Hall_id.Hall_no,
                'Movie_name':  booking[i].Movie_id.Movie_name,
                'Movie_lang':  booking[i].Movie_id.Movie_lang,
                'Movie_category':  booking[i].Movie_id.Movie_name,
                'Date': booking[i].Date,
                'SelectedSeats': booking[i].Selected_seats,
                'Movie_poster':  booking[i].Movie_id.Movie_poster,
                'Booking_status': booking[i].Booking_status

            }
            info.append(information)
    else:
        print("working22")
        booking = Booking.objects.filter(User_id=User_id, Date__lte=datetime.date.today(),Booking_status='Booked')
        print(booking)
        for i in range(len(booking)):
            information = {
                'Booking_id': booking[i].Booking_id,
                'User_name': user.User_name,
                'User_phone': user.User_phone,
                'Theatre_name': booking[i].Theatre_id.Theatre_name,
                'Theatre_location': booking[i].Theatre_id.Theatre_location,
                'Hall_no': booking[i].Hall_id.Hall_no,
                'Movie_name': booking[i].Movie_id.Movie_name,
                'Movie_lang': booking[i].Movie_id.Movie_lang,
                'Movie_category': booking[i].Movie_id.Movie_name,
                'Date': booking[i].Date,
                'SelectedSeats': booking[i].Selected_seats,
                'Movie_poster': booking[i].Movie_id.Movie_poster,
                'Booking_status':booking[i].Booking_status
            }
            print(information)
            info.append(information)
    print(info)
    return (info)

#halls
def getHallsFilteredByDate(date):
    halls = DatewiseHall.objects.filter(Date=date)  # gets all the halls displaying movies on that day
    serializer=DatewiseHallSerializer(halls,many=True)
    return (serializer.data)

#halls
def getHallsFilteredByMovie(id):
    halls = Hall.objects.filter(Movie_id=id)

    if len(halls)>0:
        serializer = HallSerializer(halls, many=True)
        return serializer.data
    else:
        raise AuthenticationFailed("Invalid id or no halls available")

#theatre
def getSingleTheatrebyId(id):
    theatre = Theatre.objects.filter(Theatre_id=id)
    serializer = TheatreSerializer(theatre)
    return(serializer.data)


#hall
def getDataFilteredByHallandDate(id,date):
    hall = DatewiseHall.objects.filter(Hall_id=id, Date=date).first()
    return (hall)


#hall
def hallFilteredByDate(id,date):
    hall = DatewiseHall.objects.filter(Hall_id=id, Date=date).first()
    serializer = DatewiseHallSerializer(hall)
    return (serializer.data)

#tickets
def cancelTickets(id,bid):
    ticket = Booking.objects.filter(User_id=id,Booking_id=bid).last()
    print(ticket.Date,datetime.datetime.today().strftime('%Y-%m-%d'))

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
    ticket.Booking_status='Cancelled'
    print(ticket.Booking_status)
    ticket.save()


#users
def getowners():
    users=Users.objects.filter(Role='TheatreOwner')
    serializer=UserSerializer(users,many=True)
    return serializer.data

#movies
def langmovies(lang):
    movies=Movie.objects.filter(Movie_lang=lang)
    serializer = MovieSerializer(movies, many=True)
    return serializer.data

#theatre
def ownertheatre(role):
    if(role=='Admin'):
        theatre=Theatre.objects.all()
    else:

        theatre = Theatre.objects.filter(Theatre_owner="owner")
    serializer = TheatreSerializer(theatre, many=True)
    return (serializer.data)


def theatrelist(movie_id,date):
    hall = DatewiseHall.objects.filter(Movie_id=movie_id,Date=date)
    print(len(hall))
    info = []
    for i in range(len(hall)):
        print( hall[i].Hall_id.Theatre_id.Theatre_name)
        # screen = Hall.objects.filter(Hall_id=hall[i].Hall_id)
        # print((screen))
        if(len(hall)!=0):
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


def getcancelledtickets(id):
    user=Users.objects.filter(User_id=id).first()
    booking=Booking.objects.filter(User_id=id,Booking_status='Cancelled')
    info=[]
    for i in range(len(booking)):
        print("working")
        information = {
            'Booking_id': booking[i].Booking_id,
            'User_name': user.User_name,
            'User_phone': user.User_phone,
            'Theatre_name': booking[i].Theatre_id.Theatre_name,
            'Theatre_location': booking[i].Theatre_id.Theatre_location,
            'Hall_no': booking[i].Hall_id.Hall_no,
            'Movie_name': booking[i].Movie_id.Movie_name,
            'Movie_lang': booking[i].Movie_id.Movie_lang,
            'Movie_category': booking[i].Movie_id.Movie_name,
            'Date': booking[i].Date,
            'SelectedSeats': booking[i].Selected_seats,
            'Movie_poster': booking[i].Movie_id.Movie_poster,
            'Booking_status':booking[i].Booking_status
        }
        info.append(information)

    return info


def searchmovies(a):
    movies=Movie.objects.filter(Q(Movie_name__icontains=a) | Q(Movie_lang__icontains=a))
    serializer=MovieSerializer(movies,many=True)
    return(serializer.data)


def searchtheatre(movie_id,date,a):
    hall = DatewiseHall.objects.filter(Movie_id=movie_id, Date=date,Hall_id__Theatre_id__Theatre_name__icontains=a)
    info = []
    print(hall)
    for i in range(len(hall)):
        print(hall[i].Hall_id.Theatre_id.Theatre_name)
        # screen = Hall.objects.filter(Hall_id=hall[i].Hall_id)
        # print((screen))
        if (len(hall) != 0):
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







